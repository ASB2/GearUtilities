package GU;

import java.util.List;

import net.minecraft.client.renderer.texture.TextureUtil;
import GU.info.Variables;
import GU.render.NoiseManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class EventListener {
    
    boolean down = true;
    int position = 0;
    
    @SubscribeEvent
    public void noiseUpdate(RenderTickEvent event) {
        
        List<int[]> data = NoiseManager.instance.imageDataArray;
        
        TextureUtil.uploadTexture(NoiseManager.instance.GL_TEXTURE_ID, data.get(position), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        
        if (down) {
            
            if (position >= data.size() - 1) {
                down = false;
                position = data.size() - 1;
            }
            else {
                // position = Math.round((position + (1 * (float)
                // tickData[0])));
                position++;
            }
            
        }
        else {
            
            if (position <= 0) {
                down = true;
                position = 0;
            }
            else {
                // position = Math.round((position - (1 * (float)
                // tickData[0])));
                position--;
            }
        }
        NoiseManager.instance.CURRENT_POSITION = position;
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
