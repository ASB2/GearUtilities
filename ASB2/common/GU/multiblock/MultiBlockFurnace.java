package GU.multiblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.BlockSpacialProvider.TileFurnaceSpacialProvider;
import GU.inventory.Inventory;

public class MultiBlockFurnace extends MultiBlockBase implements ISidedInventory, IFluidHandler {

    int maxHeat, currentHeat;

    boolean solidFuled = false;
    Inventory multiInventory = new Inventory(6, "Multi Furnace", true);
    FluidTank fluidTank = new FluidTank(0);

    public MultiBlockFurnace(World world, Cuboid size) {
        super(world, size);
        fluidTank.setCapacity((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1) * 1000);
    }

    public MultiBlockFurnace(World world) {
        super(world);
    }

    public boolean isStructureValid() {

        // if ((this.size.getXSize() + 1) % 2 == 0 || (this.size.getYSize() + 1) % 2 == 0 || (this.size.getZSize() + 1) % 2 == 0) {
        //
        // return false;
        // }

        if (size.getXSize() + 1 < 3 || size.getYSize() + 1 < 5 || size.getZSize() + 1 < 3) {

            return false;
        }

        for (Vector3 vector : size.getCornerBlocks()) {

            TileEntity tile = vector.getTileEntity(worldObj);

            if (tile == null || !(tile instanceof TileFurnaceSpacialProvider)) {

                return false;
            }
        }

        for (Vector3 vector : size.getEdges()) {

            Block block = vector.getBlock(getWorldObj());

            if (block == null || !block.isBlockNormalCube(getWorldObj(), vector.intX(), vector.intY(), vector.intZ())) {

                return false;
            }
        }
        return size.iterate(this, 0);
    }

    public boolean isValidCore(Vector3 vector, TileEntity tile) {

        return tile.getClass() == TileFurnaceSpacialProvider.class;
    }

    @Override
    public void createWorked() {

        super.createWorked();
    }

    @Override
    public NBTTagCompound save(NBTTagCompound tag) {

        multiInventory.save(tag);
        return super.save(tag);
    }

    @Override
    public void load(NBTTagCompound tag) {

        multiInventory.load(tag);
        super.load(tag);
    }

    @Override
    public void init() {

        fluidTank.setCapacity((size.getXSize() + 1) * (size.getYSize() + 1) * (size.getZSize() + 1) * 1000);
    }

    @Override
    public int getSizeInventory() {

        return multiInventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return multiInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        return multiInventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return multiInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        multiInventory.setInventorySlotContents(i, itemStack);
    }

    @Override
    public boolean isInvNameLocalized() {

        return multiInventory.isInvNameLocalized();
    }

    @Override
    public int getInventoryStackLimit() {

        return multiInventory.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return multiInventory.isUseableByPlayer(entityplayer);
    }

    @Override
    public void openChest() {

        multiInventory.openChest();
    }

    @Override
    public void closeChest() {

        multiInventory.closeChest();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return multiInventory.isItemValidForSlot(i, itemstack);
    }

    @Override
    public String getInvName() {

        return multiInventory.getInvName();
    }

    @Override
    public void onInventoryChanged() {

        multiInventory.onInventoryChanged();
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        return fluidTank.fill(resource, doFill);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if (fluidTank != null) {

            if (fluid != null) {

                if (fluidTank.getFluid() != null) {

                    if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

                        return true;
                    }
                } else {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        if (this.fluidTank.getFluid() != null) {

            if (fluidTank.getFluidAmount() > 0) {

                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] { fluidTank.getInfo() };
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }
}
