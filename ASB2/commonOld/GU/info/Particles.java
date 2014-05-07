package GU.info;

import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

public final class Particles {
    
    public static IIcon TEST_PARTICLE;
    
    public static void register(TextureStitchEvent.Pre event) {
        
        if (event.map.getTextureType() == 0) {
            
            TEST_PARTICLE = event.map.registerIcon(Reference.MODDID + ":TestParticle");
        }
    }
}
