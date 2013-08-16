package GU.info;

import net.minecraftforge.common.Configuration;

public class Variables {

    public static int GEOTHERMAL_GENERATOR_POWER_PER_BUCKET = 100;
    
    public static boolean MODDER_APPRECATION = true;
    public static boolean TESTING_MODE = true;

    public static void updateVariables(Configuration config) {

        MODDER_APPRECATION = config.get("Misc", "MODDER_APPRECATION", true, "Show your aprecation").getBoolean(true);
    }
}
