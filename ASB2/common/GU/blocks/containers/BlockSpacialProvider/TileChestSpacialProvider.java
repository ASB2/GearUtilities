package GU.blocks.containers.BlockSpacialProvider;

import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import GU.multiblock.MultiBlockChest;

public class TileChestSpacialProvider extends TileSpacialProvider {

    public void createLoadedStructure() {

        MultiBlockChest chest = new MultiBlockChest(worldObj);
        chest.load(bufferedTankData);

        if (chest.isStructureValid()) {
            chest.create();
        }
    }

    public boolean createNewStructure(Cuboid size) {

        MultiBlockChest chest = new MultiBlockChest(worldObj, size);

        boolean spaceValid = chest.isStructureValid();
        UtilEntity.sendClientChat("Area Valid: " + spaceValid);

        if (spaceValid) {

            boolean valid = chest.create();
            UtilEntity.sendClientChat("Structure Created:  " + valid);
            return valid;
        }
        return false;
    }
}
