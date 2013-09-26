package GU.blocks.containers.BlockMasher;

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
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.recipe.MasherRecipe;
import GU.api.recipe.MasherRecipe.MasherRecipeHolder;
import GU.blocks.containers.TileBase;

public class TileMasher extends TileBase implements IPowerMisc, IInventory, IFluidHandler {

    boolean shouldCraft;

    public TileMasher() {
        
        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        tileItemStacks = new ItemStack[10];
        fluidTank = new FluidTank(10000);
    }

    public void updateEntity() {
    
        if(shouldCraft || !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            MasherRecipeHolder currentRecipe = MasherRecipe.findRecipe(new ItemStack[]{
                    tileItemStacks[0], tileItemStacks[1], tileItemStacks[2], 
                    tileItemStacks[3], tileItemStacks[4], tileItemStacks[5], 
                    tileItemStacks[6], tileItemStacks[7], tileItemStacks[8]});
            
            if(currentRecipe != null) {
                
                if(this.getPowerProvider().usePower(currentRecipe.getEnergyRequired(), ForgeDirection.UNKNOWN, false)) {
                    
                    if(UtilFluid.addFluidToTank(this, ForgeDirection.UNKNOWN, currentRecipe.getFluidOutput(), false)) {
                        
                        if(UtilInventory.addItemStackToSlot(this, currentRecipe.getItemOutput(), 10, false)) {
                 
                            if(UtilInventory.decreaseSlotContentsBoolean(this, 0, 1)) {
                                
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
        
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return fluidTank.fill(resource, doFill);   
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
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if(resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {

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

        return "Masher";
    }
}
