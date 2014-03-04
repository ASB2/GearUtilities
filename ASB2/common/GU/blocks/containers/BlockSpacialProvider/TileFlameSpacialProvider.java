package GU.blocks.containers.BlockSpacialProvider;

import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import GU.multiblock.MultBlockFlameSource;

public class TileFlameSpacialProvider extends TileSpacialProvider {

    public TileFlameSpacialProvider() {
        // TODO Auto-generated constructor stub
    }

    public void createLoadedStructure() {

        MultBlockFlameSource chest = new MultBlockFlameSource(worldObj);
        chest.load(bufferedMultiData);

        if (chest.isStructureValid()) {
            chest.create();
        }
    }

    public boolean createNewStructure(Cuboid size) {

        MultBlockFlameSource chest = new MultBlockFlameSource(worldObj, size);

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
