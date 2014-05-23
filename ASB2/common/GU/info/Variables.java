package GU.info;

import net.minecraftforge.common.config.Configuration;

public class Variables {
    
    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;
    
    public static final int NOISE_TEXTURE_SIZE = 256;
    
    public static void updateVariables(Configuration config) {
        
        DO_RETROGEN = config.get("Misc", "Do Retrogen", false, "Set to true to enable retrogen").getBoolean(false);
        // NOISE_TEXTURE_SIZE = config.get("Misc", "Noise Texture Size",
        // NOISE_TEXTURE_SIZE,
        // "Change the value to change the pixle size").getInt(NOISE_TEXTURE_SIZE);
    }
}
