package GU.info;

import net.minecraftforge.common.Configuration;

public class Variables {

    public final static String CRYSTALS_ALL = "CRYSTALS_ALL";
    
    public static int POTION_BASE_COST = 100;    
    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;
    
    public static int SENDER_ITEM_COST = 100;
    public static int SENDER_SMELT_COST = 200 + SENDER_ITEM_COST;
    public static int SENDER_POWERSEND_COST = 500 + SENDER_ITEM_COST;
    public static int SENDER_POWERSEND_AMOUNT = 500 + SENDER_ITEM_COST;
    public static int SENDER_BREAKBLOCK_AMOUNT = 50;
    
    public static void updateVariables(Configuration config) {
        
        DO_RETROGEN = config.get("Misc", "Do Retrogen", false, "Set to true to enable retrogen").getBoolean(true);
    }
}
