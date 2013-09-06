package GU.blocks.containers.BlockConnectableTank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilMisc;
import GU.GUItemBlock;

public class ItemBlockConnectableTank extends GUItemBlock {

    public ItemBlockConnectableTank(int id) {
        super(id);
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        boolean itWorked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof TileConnectableTank) {

            TileConnectableTank tank = (TileConnectableTank)tile;

            tank.fluidTank.setFluid(this.getFluidStack(stack));
        }

        return itWorked;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
     
        if (this.getFluidStack(itemStack) != null) {

            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Stored: " + UtilMisc.capitilizeFirst(this.getFluidStack(itemStack).getFluid().getName()));
            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Amount: " + UtilMisc.capitilizeFirst(Integer.toString(this .getFluidStack(itemStack).amount)));
        } else {

            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Stored: None");
            info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Fluid Amount: 0");
        }
    }

    public FluidStack getFluidStack(ItemStack itemStack) {

        if (FluidRegistry.getFluid(UtilItemStack.getTAGfromItemstack(itemStack).getString("fluidName")) != null && UtilItemStack.getTAGfromItemstack(itemStack).getInteger("fluidAmount") != 0) {

            return new FluidStack(FluidRegistry.getFluid(UtilItemStack.getTAGfromItemstack(itemStack).getString("fluidName")), UtilItemStack.getTAGfromItemstack(itemStack).getInteger("fluidAmount"));
        }
        return null;
    }

    public boolean setFluidStack(ItemStack itemStack, FluidStack fluid) {

        if (fluid != null) {

            if (this.getFluidStack(itemStack) != null) {

                if (this.getFluidStack(itemStack).isFluidEqual(fluid)) {

                    if (this.getFluidStack(itemStack).amount + fluid.amount <= this.getCapasity(itemStack)) {

                        UtilItemStack.getTAGfromItemstack(itemStack).setInteger("fluidAmount", fluid.amount);
                        UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", fluid.getFluid().getName());
                        return true;
                    }
                }
            } else {

                UtilItemStack.getTAGfromItemstack(itemStack).setInteger("fluidAmount", fluid.amount);
                UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", fluid.getFluid().getName());
                return true;
            }
        } else {

            UtilItemStack.getTAGfromItemstack(itemStack).setInteger( "fluidAmount", 0);
            UtilItemStack.getTAGfromItemstack(itemStack).setString("fluidName", "");
        }
        return false;
    }

    public int getCapasity(ItemStack itemStack) {

        return TileConnectableTank.maxLiquid;
    }
}
