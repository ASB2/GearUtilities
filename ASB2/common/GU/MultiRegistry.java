package GU;

import GU.api.multiblock.MultiBlockRegistry;
import GU.info.Reference;
import GU.multiblock.*;

public class MultiRegistry {

    public static final String MultiBlockTankName = "MultiTank";
    public static final String MultiBlockChestName = "MultiChest";
    public static final String MultiBlockFurnaceName = "MultiFurnace";
    public static final String MultiBlockFlameSourceName = "MultiFlameSource";

    public static void init() {

        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockTankName, MultiBlockTank.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockChestName, MultiBlockChest.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockFurnaceName, MultiBlockFurnace.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockFlameSourceName, MultiBlockFlameSource.class);
    }
}
