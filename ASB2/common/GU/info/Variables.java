package GU.info;

import net.minecraftforge.common.config.Configuration;

public class Variables {
    
    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;
    
    public static int NOISE_TEXTURE_SIZE = 64;
    
    public static int BUCKETS_PER_TANK_BLOCK = 16;
    
    public static boolean ANIMATE_NOISE_TEXTURE = true;
    
    public static boolean TELEPORTER_RESET_BREAK_BLOCK = false;
    
    public static int HANDHELD_TELEPORTER_USES = 0;
    
    public static boolean CAN_HANDHELD_TELEPORTER_GO_BETWEEN_DIMENTIONS = true;
    
    public static boolean CAN_HANDHELD_TELEPORTER_GO_IN_SAME_DIMENTIONS = true;
    
    // public static boolean CANT_BREAK_WOOD_WITH_HAND = false;
    
    public static void updateVariables(Configuration config) {
        
        // DO_RETROGEN = config.get("Misc", "Do Retrogen", DO_RETROGEN,
        // "Set to true to enable retrogen").getBoolean(DO_RETROGEN);
        NOISE_TEXTURE_SIZE = config.get("Misc", "Noise Texture Size", NOISE_TEXTURE_SIZE, "Change the value to change the pixle size").getInt(NOISE_TEXTURE_SIZE);
        BUCKETS_PER_TANK_BLOCK = config.get("Misc", "Buckets per tank block", BUCKETS_PER_TANK_BLOCK, "Change the value to change amount of buckets each tank block is worth").getInt(BUCKETS_PER_TANK_BLOCK);
        ANIMATE_NOISE_TEXTURE = config.get("Misc", "Animate Noise Texture", ANIMATE_NOISE_TEXTURE, "Set to false to make noise only have one frame").getBoolean(ANIMATE_NOISE_TEXTURE);
        TELEPORTER_RESET_BREAK_BLOCK = config.get("Misc", "Teleporter must be broken to reset", TELEPORTER_RESET_BREAK_BLOCK, "Set to true to ").getBoolean(TELEPORTER_RESET_BREAK_BLOCK);
        HANDHELD_TELEPORTER_USES = config.get("Items", "Number of uses for a teleporter before it gets used up", HANDHELD_TELEPORTER_USES, "Change the value to change number of uses for a teleporter. Less than one means unlimited uses").getInt(HANDHELD_TELEPORTER_USES);
        CAN_HANDHELD_TELEPORTER_GO_BETWEEN_DIMENTIONS = config.get("Items", "Can Handheld Teleporter teleport between dimetions", CAN_HANDHELD_TELEPORTER_GO_BETWEEN_DIMENTIONS, "Set to false to not allow teleporting between dimentions with teleporter").getBoolean(CAN_HANDHELD_TELEPORTER_GO_BETWEEN_DIMENTIONS);
        CAN_HANDHELD_TELEPORTER_GO_IN_SAME_DIMENTIONS = config.get("Items", "Can Handheld Teleporter teleport in same dimetions", CAN_HANDHELD_TELEPORTER_GO_IN_SAME_DIMENTIONS, "Set to false to not allow teleporting in the dimention with teleporter").getBoolean(CAN_HANDHELD_TELEPORTER_GO_IN_SAME_DIMENTIONS);
    }
}
