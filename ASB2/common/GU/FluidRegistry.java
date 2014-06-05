package GU;

import GU.render.noise.NoiseManager;
import net.minecraftforge.fluids.Fluid;

public class FluidRegistry {
    
    public static Fluid STEAM = new Fluid("Steam") {
        
        public net.minecraft.util.IIcon getStillIcon() {
            return NoiseManager.instance.blockNoiseIcon;
        }
        
        public net.minecraft.util.IIcon getFlowingIcon() {
            return NoiseManager.instance.blockNoiseIcon;
        }
    };
    
    public static void initFluids() {
        
        STEAM.setGaseous(true);
        net.minecraftforge.fluids.FluidRegistry.registerFluid(STEAM);
    }
    
    public static void registerFluidContainers() {
        // TODO Auto-generated method stub
        
    }
    
}
