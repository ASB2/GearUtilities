package GU.blocks.containers.BlockSpacialProvider;

import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import GU.multiblock.MultiBlockFurnace;

public class TileFurnaceSpacialProvider extends TileSpacialProvider {

    public void createLoadedStructure() {

        MultiBlockFurnace chest = new MultiBlockFurnace(worldObj);
        chest.load(bufferedTankData);

        if (chest.isStructureValid()) {
            chest.create();
        }
    }

    public boolean createNewStructure(Cuboid size) {

        MultiBlockFurnace chest = new MultiBlockFurnace(worldObj, size);

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