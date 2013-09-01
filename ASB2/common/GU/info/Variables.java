package GU.info;

import net.minecraftforge.common.Configuration;

public class Variables {

    public final static String CRYSTALS_ALL = "CRYSTALS_ALL";
    
    public static int POTION_BASE_COST = 100;

    public static boolean MODDER_APPRECATION = true;
    public static boolean TESTING_MODE = true;

    public static void updateVariables(Configuration config) {

        MODDER_APPRECATION = config.get("Misc", "MODDER_APPRECATION", true, "Show your aprecation").getBoolean(true);
    }
}
