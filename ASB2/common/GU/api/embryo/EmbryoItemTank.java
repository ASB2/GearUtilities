package GU.api.embryo;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import GU.api.*;

public class EmbryoItemTank extends ItemTank {

    public EmbryoItemTank(ItemStack stack, int max) {
        super(stack, max);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {

        if(resource != null) {

            if(this.getFluid() != null) {

                if(this.getFluid().isFluidEqual(resource)) {

                    if(this.getCapacity() - this.getFluidAmount() >= resource.amount + this.getFluidAmount()) {

                        FluidStack stack = this.getFluid().copy();
                        stack.amount += resource.amount;

                        if(doFill) {

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

        if(this.getFluid() != null) {

            if(this.getFluid().amount >= maxDrain) {

                FluidStack stack = this.getFluid().copy();
                stack.amount -= maxDrain;

                if(doDrain) {

                    this.setFluidStack(stack);
                }
                return new FluidStack(this.getFluid(), maxDrain);
            }
        }
        return null;
    }
}
