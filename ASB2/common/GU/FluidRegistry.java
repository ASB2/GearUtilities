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
import GU.items.ItemStorageCrystal.ItemStorageCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluidRegistry {

    public static List<ItemStack> STORAGE_CRYSTAL_LIST = new ArrayList<ItemStack>();
    public static List<FluidBase> GU_FLUIDS = new ArrayList<FluidBase>();
    
    public static IconRegister iconProvider;

    public static FluidBase LiquidStone;
    
    public static FluidBase LiquidIron;
    public static FluidBase LiquidGold;
    public static FluidBase LiquidDiamond;
    public static FluidBase LiquidEmerald;

    public static FluidBase AirEssence;
    public static FluidBase EarthEssence;
    public static FluidBase FireEssence;
    public static FluidBase WaterEssence;
    
    public static void initFluids() {

        MinecraftForge.EVENT_BUS.register(new FluidRegistry());
        
        LiquidStone = new FluidBase("Liquid Stone", Color.LIGHT_GRAY.hashCode());
        
        LiquidIron = new FluidBase("Liquid Iron", Color.LIGHT_GRAY.hashCode());
        LiquidGold = new FluidBase("Liquid Gold", new Color(255, 220, 0).hashCode());
        LiquidDiamond = new FluidBase("Liquid Diamond", new Color(0, 225, 255).hashCode());
        LiquidEmerald = new FluidBase("Liquid Emerald", Color.GREEN.hashCode());
        
        AirEssence = new FluidBase("Air Essence", Color.YELLOW.hashCode());
        EarthEssence = new FluidBase("Earth Essence", Color.GREEN.hashCode());
        FireEssence = new FluidBase("Fire Essence", Color.RED.hashCode());
        WaterEssence = new FluidBase("Water Essence", Color.BLUE.hashCode());
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Pre event) {
        
        String still = ":fluids/FluidBlankStill";
        String flowing = ":fluids/FluidBlankFlowing";
        
        if (event.map.textureType == 0) {
            
            Icon stillIcon = event.map.registerIcon(Reference.MODDID + still);
            Icon flowingIcon = event.map.registerIcon(Reference.MODDID + flowing);
            
            for(FluidBase base: GU_FLUIDS) {
                
                base.setStillIcon(stillIcon);
                base.setFlowingIcon(flowingIcon);
            }
            LiquidStone.setStillIcon(Block.stone.getIcon(0, 0));
            LiquidStone.setFlowingIcon(Block.stone.getIcon(0, 0));
        }
    }

    public static void registerFluidContainers() {

        for (Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {

            ItemStack filled = new ItemStack(ItemRegistry.ItemStorageCrystal, 1, fluid.getID());
            FluidContainerRegistry.registerFluidContainer(fluid, filled, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0));
            ((ItemStorageCrystal)filled.getItem()).setFluidStack(filled, new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)));
            STORAGE_CRYSTAL_LIST.add(filled);
        }
    }
}
