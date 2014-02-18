package GU.info;

import net.minecraftforge.common.Configuration;

public class Variables {

    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;
    public static boolean COUNT_JUST_TANK_AIR_BLOCKS = false;

    public static void updateVariables(Configuration config) {

        DO_RETROGEN = config.get("Misc", "Do Retrogen", false, "Set to true to enable retrogen").getBoolean(false);
        COUNT_JUST_TANK_AIR_BLOCKS = config.get("Misc", "Count Air Blocks in Tank Size Calculation", false, "Set to true to count only air blocks").getBoolean(false);
    }
}
