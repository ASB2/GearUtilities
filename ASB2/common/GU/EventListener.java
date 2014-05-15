package GU;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class EventListener {
    
    boolean down = true;
    int position = 0;
    
    @SubscribeEvent
    public void noiseUpdate(RenderTickEvent event) {
        
    }
    
    // @SubscribeEvent
    // public void textureHook(TextureStitchEvent.Pre event) {
    //
    // String still = ":fluids/FluidBlankStill";
    // String flowing = ":fluids/FluidBlankFlowing";
    //
    // if (event.map.textureType == 0) {
    //
    // Icon stillIcon = event.map.registerIcon(Reference.MODDID + still);
    // Icon flowingIcon = event.map.registerIcon(Reference.MODDID + flowing);
    //
    // for (FluidBase base : FluidRegistry.GU_FLUIDS) {
    //
    // base.setStillIcon(stillIcon);
    // base.setFlowingIcon(flowingIcon);
    // }
    //
    // EnumState.SIDES[0] = event.map.registerIcon(Reference.MODDID +
    // ":sides/BlockInput");
    // EnumState.SIDES[1] = event.map.registerIcon(Reference.MODDID +
    // ":sides/BlockOutput");
    // EnumState.SIDES[2] = event.map.registerIcon(Reference.MODDID +
    // ":sides/BlockBoth");
    // EnumState.SIDES[3] = event.map.registerIcon(Reference.MODDID +
    // ":sides/BlockNone");
    // }
    // event.map.setTextureEntry(NoiseManager.instance.iconTexture.getIconName(),
    // NoiseManager.instance.iconTexture);
    // Particles.register(event);
    // }
}
