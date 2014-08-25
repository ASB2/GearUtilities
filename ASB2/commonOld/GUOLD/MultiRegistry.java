package GUOLD;

import GUOLD.api.multiblock.IModMultiBlockHandler;
import GUOLD.api.multiblock.IMultiBlock;
import GUOLD.api.multiblock.MultiBlockRegistry;
import GUOLD.info.Reference;
import GUOLD.multiblock.MultiBlockChest;
import GUOLD.multiblock.MultiBlockFlameSource;
import GUOLD.multiblock.MultiBlockFurnace;
import GUOLD.multiblock.MultiBlockTank;

public class MultiRegistry {

    private static MultiHandler instance = new MultiHandler();

    public static final String MultiBlockTankName = "MultiTank";
    public static final String MultiBlockChestName = "MultiChest";
    public static final String MultiBlockFurnaceName = "MultiFurnace";
    public static final String MultiBlockFlameSourceName = "MultiFlameSource";

    public static void init() {

        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockTankName, MultiRegistry.instance, MultiBlockTank.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockChestName, MultiRegistry.instance, MultiBlockChest.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockFurnaceName, MultiRegistry.instance, MultiBlockFurnace.class);
        MultiBlockRegistry.getInstance().registerMultiBlock(Reference.MODDID, MultiBlockFlameSourceName, MultiRegistry.instance, MultiBlockFlameSource.class);
    }

    private static class MultiHandler implements IModMultiBlockHandler {

        @Override
        public IMultiBlock getMultiBlockInstance(String multiBlockClass) {

            switch (multiBlockClass) {

                case MultiBlockTankName: {
                    return new MultiBlockTank();
                }

                case MultiBlockChestName: {
                    return new MultiBlockChest();
                }

                case MultiBlockFurnaceName: {
                    return new MultiBlockFurnace();
                }
                case MultiBlockFlameSourceName: {
                    return new MultiBlockFlameSource();
                }
            }
            return null;
        }
    }
}
