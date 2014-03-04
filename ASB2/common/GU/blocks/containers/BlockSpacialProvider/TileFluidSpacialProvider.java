package GU.blocks.containers.BlockSpacialProvider;

import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import GU.multiblock.MultiBlockTank;

public class TileFluidSpacialProvider extends TileSpacialProvider {

    public void createLoadedStructure() {

        MultiBlockTank chest = new MultiBlockTank(worldObj);
        chest.load(bufferedMultiData);

        if (chest.isStructureValid()) {
            chest.create();
        }
    }

    public boolean createNewStructure(Cuboid size) {

        MultiBlockTank chest = new MultiBlockTank(worldObj, size);

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
