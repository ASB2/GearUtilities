package GU.info;

import net.minecraftforge.common.Configuration;

public class Variables {

    public static boolean TESTING_MODE = true;
    public static boolean DO_RETROGEN = false;

    public static boolean COUNT_JUST_MULTI_TANK_AIR_BLOCKS = true;
    public static boolean CAN_USE_NON_STRUCURE_MULTI_BLOCKS = true;

    public static void updateVariables(Configuration config) {

        DO_RETROGEN = config.get("Misc", "Do Retrogen", false, "Set to true to enable retrogen").getBoolean(false);

        COUNT_JUST_MULTI_TANK_AIR_BLOCKS = config.get("MultiBlock", "Count Only Air Blocks in Multi Tank Size Calculation", COUNT_JUST_MULTI_TANK_AIR_BLOCKS, "Set to true to count only air blocks").getBoolean(COUNT_JUST_MULTI_TANK_AIR_BLOCKS);
        CAN_USE_NON_STRUCURE_MULTI_BLOCKS = config.get("MultiBlock", "Allow Non Strucure Cube Blocks In Multi Blocks", CAN_USE_NON_STRUCURE_MULTI_BLOCKS, "Set to false to allow only strucure blocks in tanks").getBoolean(CAN_USE_NON_STRUCURE_MULTI_BLOCKS);
    }
}
