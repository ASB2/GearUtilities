package GU;

import GU.api.multiblock.MultiBlockRegistry;
import GU.info.Reference;
import GU.multiblock.*;

public class MultiRegistry {

    public static void init() {

        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, "MultiTank", MultiBlockTank.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, "MultiChest", MultiBlockChest.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, "MultiFurnace", MultiBlockFurnace.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, "MultiFlameSource", MultiBlockFlameSource.class);
    }
}
