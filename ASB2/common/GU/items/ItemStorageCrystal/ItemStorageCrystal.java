package GU.items.ItemStorageCrystal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.items.ItemBase;
import GU.utils.UtilItemStack;
import GU.utils.UtilMisc;

public class ItemStorageCrystal extends ItemBase {

    public ItemStorageCrystal(int id) {
        super(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformation(itemStack, player, info, var1);


        if(this.getFluidStack(itemStack) != null) {

            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Stored: " + this.getFluidStack(itemStack).getFluid().getName());
            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Amount: " + this.getFluidStack(itemStack).amount);
        }
        else {

            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Stored: None");
            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Amount: 0");
        }
    }

    public FluidStack getFluidStack(ItemStack itemStack) {

        if(FluidRegistry.getFluid(UtilItemStack.getTAGfromItemstack(itemStack).getString("fluidName")) != null && UtilItemStack.getTAGfromItemstack(itemStack).getInteger("fluidAmount") != 0) {

            return new FluidStack(FluidRegistry.getFluid(UtilItemStack.getTAGfromItemstack(itemStack).getString("fluidName")), UtilItemStack.getTAGfromItemstack(itemStack).getInteger("fluidAmount"));
        }
        return null;
    }

    public boolean setFluidStack(ItemStack itemStack, FluidStack fluid) {

        if(fluid != null) {
            if(this.getFluidStack(itemStack) != null) {

                if(this.getFluidStack(itemStack).isFluidEqual(fluid)) {

                    if(this.getFluidStack(itemStack).amount + fluid.amount <= this.getCapasity(itemStack)) {

                        UtilItemStack.getTAGfromItemstack(itemStack).setInteger("fluidAmount", fluid.amount);
                        UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", fluid.getFluid().getName());
                        return true;
                    }
                }
            }
            else {

                UtilItemStack.getTAGfromItemstack(itemStack).setInteger("fluidAmount", fluid.amount);
                UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", fluid.getFluid().getName());
                return true;
            }
        }
        else {
            
            UtilItemStack.getTAGfromItemstack(itemStack).setInteger("fluidAmount", 0);
            UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", "");
        }
        return false;
    }

    public int getCapasity(ItemStack itemStack) {

        return 1600;
    }
}
