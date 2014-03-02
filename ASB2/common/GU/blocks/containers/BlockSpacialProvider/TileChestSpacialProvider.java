package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.multiblock.MultiBlockChest;

public class TileChestSpacialProvider extends TileSpacialProvider {

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

                    MultiBlockChest chest = new MultiBlockChest(worldObj, new Cuboid(new Vector3(xCoord, yCoord, zCoord), getMultiBlockXChange(), getMultiBlockYChange(), getMultiBlockZChange()));

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

            MultiBlockChest chest = new MultiBlockChest(worldObj);
            chest.load(bufferedTankData);

            if (chest.isStructureValid()) {
                return chest.create();
            }
            return false;
        }
    }
}
