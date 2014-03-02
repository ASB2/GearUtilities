package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.multiblock.MultBlockFlameSource;

public class TileFlameSpacialProvider extends TileSpacialProvider {

    public TileFlameSpacialProvider() {
        // TODO Auto-generated constructor stub
    }

    public boolean createMultiBlock() {

        return createMultiBlock(false);
    }

    public boolean createMultiBlock(boolean hasStructure) {

        if (!hasStructure) {

            if (getComprisedMultiBlocks().isEmpty()) {

                int found = 0;

                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                    if (getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {

                        TileEntity foundTile = getNearesthestProvider(direction);

                        if (foundTile != null) {

                            found++;
                        }
                    }
                }

                if (found > 0) {

                    MultBlockFlameSource chest = new MultBlockFlameSource(worldObj, new Cuboid(new Vector3(xCoord, yCoord, zCoord), getMultiBlockXChange(), getMultiBlockYChange(), getMultiBlockZChange()));

                    boolean spaceValid = chest.isStructureValid();
                    UtilEntity.sendClientChat("Area Valid: " + spaceValid);

                    if (spaceValid) {

                        boolean valid = chest.create();
                        UtilEntity.sendClientChat("Structure Created:  " + valid);
                        return valid;
                    }
                }
            }
            return false;

        } else {

            MultBlockFlameSource chest = new MultBlockFlameSource(worldObj);
            chest.load(bufferedTankData);

            if (chest.isStructureValid()) {
                return chest.create();
            }
            return false;
        }
    }
}
