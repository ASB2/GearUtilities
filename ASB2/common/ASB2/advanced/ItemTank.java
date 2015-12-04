package ASB2.advanced;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import ASB2.utils.UtilItemStack;

public class ItemTank implements IFluidTank {
    
    ItemStack itemStack;
    int maxFluid;
    
    public ItemTank(ItemStack stack, int max) {
        
        this.itemStack = stack;
        this.maxFluid = max;
    }
    
    @Override
    public FluidStack getFluid() {
        
        return FluidStack.loadFluidStackFromNBT((UtilItemStack.getTAGfromItemstack(itemStack).getCompoundTag("fluidTag")));
    }
    
    @Override
    public int getFluidAmount() {
        
        return getFluid().amount;
    }
    
    @Override
    public int getCapacity() {
        
        return maxFluid;
    }
    
    @Override
    public FluidTankInfo getInfo() {
        
        return new FluidTankInfo(this);
    }
    
    @Override
    public int fill(FluidStack resource, boolean doFill) {
        
        if (resource != null) {
            
            if (this.getFluid() != null) {
                
                if (this.getFluid().isFluidEqual(resource)) {
                    
                    if (this.getCapacity() - this.getFluidAmount() >= resource.amount + this.getFluidAmount()) {
                        
                        FluidStack stack = this.getFluid().copy();
                        stack.amount += resource.amount;
                        
                        if (doFill) {
                            
                            this.setFluidStack(stack);
                        }
                        return resource.amount;
                    }
                }
            }
        }
        return 0;
    }
    
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        
        if (this.getFluid() != null) {
            
            if (this.getFluid().amount >= maxDrain) {
                
                FluidStack stack = this.getFluid().copy();
                stack.amount -= maxDrain;
                
                if (doDrain) {
                    
                    this.setFluidStack(stack);
                }
                return new FluidStack(this.getFluid(), maxDrain);
            }
        }
        return null;
    }
    
    public void setFluidStack(FluidStack stack) {
        
        UtilItemStack.getTAGfromItemstack(itemStack).setTag("fluidTag", stack.writeToNBT(new NBTTagCompound()));
    }
}
