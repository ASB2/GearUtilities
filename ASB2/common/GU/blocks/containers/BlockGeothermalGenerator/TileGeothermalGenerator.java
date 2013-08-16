package GU.blocks.containers.BlockGeothermalGenerator;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;
import GU.power.GUPowerProvider;
import GU.utils.*;
import GU.info.*;

public class TileGeothermalGenerator extends TileBase implements IFluidHandler, IInventory, IPowerMisc {

    public TileGeothermalGenerator() {
        
        powerProvider = new GUPowerProvider(1000, PowerClass.LOW, State.SOURCE);
        this.tileItemStacks = new ItemStack[2];
        this.fluidTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 10);
    }

    public void updateEntity() {
     
        if(this.getPowerProvider().canGainPower(Variables.GEOTHERMAL_GENERATOR_POWER_PER_BUCKET, ForgeDirection.UNKNOWN) && fluidTank.getFluid() != null && fluidTank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME) {
            
            if(UtilFluid.removeFluidFromTank(this, ForgeDirection.UNKNOWN, new FluidStack(fluidTank.getFluid().getFluid(), FluidContainerRegistry.BUCKET_VOLUME))) {
                
                this.getPowerProvider().gainPower(Variables.GEOTHERMAL_GENERATOR_POWER_PER_BUCKET, ForgeDirection.UNKNOWN);
            }
        }
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if(UtilDirection.translateDirectionToBlockId(worldObj, direction, this) == Block.lavaStill.blockID) {
                
                if(this.getPowerProvider().canGainPower(Variables.GEOTHERMAL_GENERATOR_POWER_PER_BUCKET, ForgeDirection.UNKNOWN)) {
                    
                    this.getPowerProvider().gainPower(.01f, direction.getOpposite());
                }
            }
        }
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if(resource.getFluid() == FluidRegistry.LAVA) {
            
            return fluidTank.fill(resource, doFill);
        }        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        
        return 0;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {

        if(fluidTank != null) {
            
            if(fluid != null && fluid == FluidRegistry.LAVA) {

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
        return false;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        
        return fluidTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        return fluidTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        return new FluidTankInfo[] {fluidTank.getInfo()};
    }
    
    @Override
    public String getName() {

        return "Geothermal Generator";
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
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
    public ItemStack decrStackSize(int i, int j) {

        if(UtilInventory.decreaseSlotContents(this, i, j)) {
            
            return this.getStackInSlot(i);
        }
        return null;
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
    public String getInvName() {
        // TODO Auto-generated method stub
        return getName();
    }

    @Override
    public boolean isInvNameLocalized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
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
}
