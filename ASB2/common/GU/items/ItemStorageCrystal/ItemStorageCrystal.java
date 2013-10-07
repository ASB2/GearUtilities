package GU.items.ItemStorageCrystal;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilMisc;
import ASB2.utils.UtilRender;
import GU.GearUtilities;
import GU.ItemRegistry;
import GU.items.ItemBase;

public class ItemStorageCrystal extends ItemBase {

    public ItemStorageCrystal(int id) {
        super(id);
        this.setCreativeTab(GearUtilities.tabGUFluids);
        this.setHasSubtypes(true);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(int id, CreativeTabs tab, List list) {

        list.add(new ItemStack(id, 1, 0));

        for(ItemStack stack : GU.FluidRegistry.STORAGE_CRYSTAL_LIST) {

            list.add(stack);
        }
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        FluidStack fluid = this.getFluidStack(stack);
        if (fluid != null) {

            if (fluid.getFluid().getStillIcon() != null) {

                if(pass == 0 && fluid.getFluid().getStillIcon() != null) {

                    UtilRender.bindBlockTextures();
                    return fluid.getFluid().getStillIcon();
                }
            }
        }
        UtilRender.bindItemTextures();
        return super.getIcon(stack, pass);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        if (this.getFluidStack(itemStack) != null) {

            info.add("Fluid Stored: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + UtilMisc.capitilizeFirst(this.getFluidStack(itemStack).getFluid().getName()));
            info.add("Fluid Amount: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + UtilMisc.capitilizeFirst(Integer.toString(this .getFluidStack(itemStack).amount)));
        } else {

            info.add("Fluid Stored: None");
            info.add("Fluid Amount: 0");
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        FluidStack stack = this.getFluidStack(itemStack);
        int[] coords = UtilDirection.translateDirectionToCoords( ForgeDirection.getOrientation(side), x, y, z);

        if(stack != null) {

            if(stack.getFluid().getBlockID() != -1 && stack.getFluid().getBlockID() != 0) {

                if(!player.capabilities.isCreativeMode) {

                    if(UtilInventory.consumeItemStack(player.inventory, itemStack, 1) && UtilInventory.addItemStackToInventory(player.inventory, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0), true)) {

                        return UtilBlock.placeBlockInAir(world, coords[0], coords[1], coords[2], stack.getFluid().getBlockID(), 0);
                    }
                }
                else {

                    return UtilBlock.placeBlockInAir(world, coords[0], coords[1], coords[2], stack.getFluid().getBlockID(), 0);
                }
            }
        }
//        else {
//
//            Fluid fluid = FluidRegistry.getFluid(world.getBlockId(coords[0], coords[1], coords[2]));
//
//            if(fluid != null) {
//
//                ItemStack filled = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid, 1000), new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 1));
//
//                if(filled != null) {
//
//                    if(!player.capabilities.isCreativeMode) {
//
//                        if(UtilInventory.addItemStackToInventory(player.inventory, filled, true) && UtilInventory.consumeItemStack(player.inventory, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0), 1)) {
//
//                            return world.setBlockToAir(coords[0], coords[1], coords[2]);
//                        }
//                    }
//                    else {
//
//                        return world.setBlockToAir(coords[0], coords[1], coords[2]);
//                    }
//                }
//            }
//        }
        return false;
    }

    public FluidStack getFluidStack(ItemStack itemStack) {

        String name = UtilItemStack.getTAGfromItemstack(itemStack).getString("fluidName");
        Fluid fluid = FluidRegistry.getFluid(name);
        int fluidAmount = UtilItemStack.getTAGfromItemstack(itemStack).getInteger("fluidAmount");

        if (fluid != null && fluidAmount != 0) {

            return new FluidStack(fluid, fluidAmount);
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

        return 1000;
    }
}
