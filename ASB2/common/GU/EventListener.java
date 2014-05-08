package GU;

import java.util.List;

import net.minecraft.client.renderer.texture.TextureUtil;
import GU.info.Variables;
import GU.render.NoiseManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class EventListener {
    
    boolean down = true;
    int position = 0;
    
    @SubscribeEvent
    public void noiseUpdate(ClientTickEvent event) {
        
        List<int[]> data = NoiseManager.instance.imageDataArray;
        
        TextureUtil.uploadTexture(NoiseManager.instance.textureImage.getGlTextureId(), data.get(position), Variables.NOISE_TEXTURE_SIZE, Variables.NOISE_TEXTURE_SIZE);
        
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
    }
}
