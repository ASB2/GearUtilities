package GU.info;

import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;

public final class Particles {
    
    public static Icon TEST_PARTICLE;
    
    public static void register(TextureStitchEvent.Pre event) {
        
        if (event.map.textureType == 0) {
            
            TEST_PARTICLE = event.map.registerIcon(Reference.MODDID + ":TestParticle");
        }
    }
}
