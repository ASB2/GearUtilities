package GU;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.fluid.FluidBase;
import GU.info.Reference;
import GU.items.ItemStorageCrystal.ItemStorageCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidRegistry {

    public static List<ItemStack> StorageCrystals = new ArrayList<ItemStack>();
    
    public static IconRegister iconProvider;

    public static FluidBase LiquidWood;
    public static FluidBase LiquidIron;
    public static FluidBase LiquidGold;
    public static FluidBase LiquidDiamond;
    public static FluidBase LiquidEmerald;

    public static void initFluids() {

        LiquidWood = new FluidBase("Liquid Wood", 0x000000);
        LiquidIron = new FluidBase("Liquid Iron", 0x000000);
        LiquidGold = new FluidBase("Liquid Gold", 0x000000);
        LiquidDiamond = new FluidBase("Liquid Diamond", 0x000000);
        LiquidEmerald = new FluidBase("Liquid Emerald", 0x000000);

    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Pre event) {

        if (event.map.textureType == 0) {

            LiquidDiamond.setIcon(event.map.registerIcon(Reference.MODDID + ":fluids/FluidDiamond"));
        }
    }

    public static void registerFluidContainers() {

        for (Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {

            ItemStack filled = new ItemStack(ItemRegistry.ItemStorageCrystal, 1, fluid.getID());
            FluidContainerRegistry.registerFluidContainer(fluid, filled, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0));
            ((ItemStorageCrystal)filled.getItem()).setFluidStack(filled, new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)));
            StorageCrystals.add(filled);
        }
    }
}
