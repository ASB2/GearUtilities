package GU;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.fluid.FluidBase;
import GU.info.Reference;
import GU.items.ItemHandheldTank.ItemHandheldTank;
import GU.items.ItemStorageCrystal.ItemStorageCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidRegistry {

    public static List<ItemStack> STORAGE_CRYSTAL_LIST = new ArrayList<ItemStack>();
    public static List<ItemStack> HANDHELDTANK = new ArrayList<ItemStack>();
    public static List<FluidBase> GU_FLUIDS = new ArrayList<FluidBase>();

    public static IconRegister iconProvider;

    public static FluidBase Blood;
    public static FluidBase CapturedSoul;

    public static FluidBase Nutrients;
    public static FluidBase VegetableMash;
    public static FluidBase MeatMash;

    public static FluidBase Vitam;
    public static FluidBase AerVitam;
    public static FluidBase TerraeVitam;
    public static FluidBase IgnisVitam;
    public static FluidBase AquaVitam;

    public static FluidBase Steam;

    public static void initFluids() {

        MinecraftForge.EVENT_BUS.register(new FluidRegistry());

        Blood = new FluidBase("Blood", Color.RED.hashCode());
        CapturedSoul = new FluidBase("Captured Soul", Color.LIGHT_GRAY.hashCode());
        Nutrients = new FluidBase("Nutrients", Color.GREEN.hashCode());

        VegetableMash = new FluidBase("Vegetable Mash", Color.GREEN.hashCode());
        MeatMash = new FluidBase("Meat Mash", Color.GREEN.hashCode());

        Vitam = new FluidBase("Vitam", Color.BLUE.hashCode());
        AerVitam = new FluidBase("Aer Vitam", Color.YELLOW.hashCode());
        TerraeVitam = new FluidBase("Terrae Vitam", Color.GREEN.hashCode());
        IgnisVitam = new FluidBase("Ignis Vitam", Color.RED.hashCode());
        AquaVitam = new FluidBase("Aqua Vitam", Color.BLUE.hashCode());

        Steam = new FluidBase("Steam", Color.WHITE.hashCode());
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Pre event) {

        String still = ":fluids/FluidBlankStill";
        String flowing = ":fluids/FluidBlankFlowing";

        if(event.map.textureType == 0) {

            Icon stillIcon = event.map.registerIcon(Reference.MODDID + still);
            Icon flowingIcon = event.map.registerIcon(Reference.MODDID + flowing);

            for(FluidBase base : GU_FLUIDS) {

                base.setStillIcon(stillIcon);
                base.setFlowingIcon(flowingIcon);
            }

            CapturedSoul.setStillIcon(Block.slowSand.getIcon(0, 0));
            CapturedSoul.setFlowingIcon(Block.slowSand.getIcon(0, 0));
        }
    }

    public static void registerFluidContainers() {

        for(Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {

            ItemStack filled = new ItemStack(ItemRegistry.ItemStorageCrystal, 1, fluid.getID());
            ((ItemStorageCrystal) filled.getItem()).setFluidStack(filled, new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)));
            FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)), filled, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0));
            STORAGE_CRYSTAL_LIST.add(filled);

            filled = new ItemStack(ItemRegistry.ItemHandheldTank, 1, fluid.getID());
            ((ItemHandheldTank) filled.getItem()).setFluidStack(filled, new FluidStack(fluid, ((ItemHandheldTank) filled.getItem()).getCapasity(filled)));
            FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, ((ItemHandheldTank) filled.getItem()).getCapasity(filled)), filled, new ItemStack(ItemRegistry.ItemHandheldTank, 1, 0));
            HANDHELDTANK.add(filled);
        }
    }
}
