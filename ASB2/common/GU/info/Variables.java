package GU.info;

import net.minecraftforge.common.config.Configuration;

public class Variables {
    
    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;
    
    public static int NOISE_TEXTURE_SIZE = 64;
    
    public static int BUCKETS_PER_TANK_BLOCK = 16;
    
    public static boolean ANIMATE_NOISE_TEXTURE = true;
    
    public static boolean TELEPORTER_RESET_BREAK_BLOCK = false;
    
    // public static boolean CANT_BREAK_WOOD_WITH_HAND = false;
    
    public static void updateVariables(Configuration config) {
        
        DO_RETROGEN = config.get("Misc", "Do Retrogen", DO_RETROGEN, "Set to true to enable retrogen").getBoolean(DO_RETROGEN);
        NOISE_TEXTURE_SIZE = config.get("Misc", "Noise Texture Size", NOISE_TEXTURE_SIZE, "Change the value to change the pixle size").getInt(NOISE_TEXTURE_SIZE);
        BUCKETS_PER_TANK_BLOCK = config.get("Misc", "Buckets per tank block", BUCKETS_PER_TANK_BLOCK, "Change the value to change amount of buckets each tank block is worth").getInt(BUCKETS_PER_TANK_BLOCK);
        ANIMATE_NOISE_TEXTURE = config.get("Misc", "Animate Noise Texture", ANIMATE_NOISE_TEXTURE, "Set to false to make noise only have one frame").getBoolean(ANIMATE_NOISE_TEXTURE);
        TELEPORTER_RESET_BREAK_BLOCK = config.get("Misc", "Teleporter must be broken to reset", TELEPORTER_RESET_BREAK_BLOCK, "Set to true to ").getBoolean(TELEPORTER_RESET_BREAK_BLOCK);
    }
}
