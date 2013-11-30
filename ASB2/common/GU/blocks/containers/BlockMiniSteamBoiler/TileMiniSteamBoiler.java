package GU.blocks.containers.BlockMiniSteamBoiler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilFluid;
import GU.blocks.containers.Inventory;
import GU.blocks.containers.TileFluidBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileMiniSteamBoiler extends TileFluidBase implements IInventory {

    boolean[] importing = new boolean[ForgeDirection.values().length];

    int storedHeat = 0;

    public TileMiniSteamBoiler() {

        this.fluidTank = new FluidTank(1000);
        tileInventory = new Inventory(9, 64, "Mini Steam Boiler", true);
    }

    @Override
    public void updateEntity() {

        if(UtilBlock.isWaterInfine(worldObj, xCoord, yCoord - 1, zCoord)) {

            for(ItemStack stack : tileInventory.getItemArray()) {

                if(stack != null && GameRegistry.getFuelValue(stack) > 0) {

                    this.useFuel(GameRegistry.getFuelValue(stack));
                }
            }
        }

        if(storedHeat >= 100) {

            if(UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, new FluidStack(GU.FluidRegistry.Steam, 100), true)) {

                storedHeat -= 100;
            }
        }

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && tile instanceof IFluidHandler) {

                if(importing[direction.ordinal()]) {

                    UtilFluid.moveFluid(this, direction, (IFluidHandler) tile, direction.getOpposite(), 100, true);
                }
            }
        }
    }

    public void useFuel(int amount) {

        storedHeat += amount / 10;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(resource.getFluid() == FluidRegistry.getFluid("steam")) {

            return fluidTank.fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public int getSizeInventory() {

        return tileInventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        return tileInventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileInventory.setInventorySlotContents(i, itemStack);
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

        return tileInventory.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getInvName() {

        return tileInventory.getInvName();
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;
                updateClients();
                UtilEntity.sendClientChat("" + importing[side]);
                return;
            }
            else {

                importing[side] = true;
                updateClients();
                UtilEntity.sendClientChat("" + importing[side]);
                return;
            }
        }
        else {

            side = ForgeDirection.getOrientation(side).getOpposite().ordinal();

            if(importing[side]) {

                importing[side] = false;
                updateClients();
                return;
            }
            else {

                importing[side] = true;
                updateClients();
                return;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        storedHeat = tag.getInteger("storedHeat");

        for(int i = 0; i < importing.length; i++) {

            importing[i] = tag.getBoolean("importing " + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("storedHeat", storedHeat);

        for(int i = 0; i < importing.length; i++) {

            tag.setBoolean("importing " + i, importing[i]);
        }
    }
}
