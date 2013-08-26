package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.blocks.containers.TileBase;
import GU.utils.UtilInventory;

public class TileAdvancedPotionBrewery extends TileBase implements IInventory, IFluidHandler {

    public TileAdvancedPotionBrewery() {

        tileItemStacks = new ItemStack[4];
        fluidTank = new FluidTank(4000);
    }

    @Override
    public void updateEntity() {

    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(resource.getFluid() == FluidRegistry.WATER) {

            return fluidTank.fill(resource, doFill);
        }
        return 0;   
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluid == FluidRegistry.WATER) {
            
            if(fluidTank != null) {

                if(fluid != null) {

                    if(fluidTank.getFluid() != null) {

                        if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {

                            return true;
                        }
                    } 
                    else {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource,
            boolean doDrain) {

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

            return null;
        }

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {

        if(this.fluidTank.getFluid() != null) {

            if(fluidTank.getFluidAmount() > 0) {

                if(this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {

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
    public int getSizeInventory() {

        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileItemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {


        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileItemStacks[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileItemStacks[i] = itemStack;
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
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

        return "Camo Block";
    }
}
