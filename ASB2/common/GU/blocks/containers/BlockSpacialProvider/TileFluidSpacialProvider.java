package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.IMultiBlock;
import GU.multiblock.MultiBlockTank;

public class TileFluidSpacialProvider extends TileSpacialProvider {

    public Set<MultiBlockTank> fluidMultiBlock = new HashSet<MultiBlockTank>();

    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {

        if (multiBlock.getClass() == MultiBlockTank.class) {

            fluidMultiBlock.add((MultiBlockTank) multiBlock);
        }
        return super.addMultiBlock(multiBlock);
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

                        TileEntity foundTile = getFarthestProvider(direction);

                        if (foundTile != null) {

                            found++;
                        }
                    }
                }

                if (found > 0) {

                    MultiBlockTank tank = new MultiBlockTank(worldObj, new Cuboid(new Vector3(xCoord, yCoord, zCoord), getMultiBlockXChange(), getMultiBlockYChange(), getMultiBlockZChange()));

                    boolean spaceValid = tank.isStructureValid();
                    UtilEntity.sendClientChat("Area Valid: " + spaceValid);

                    if (spaceValid) {

                        boolean valid = tank.create();
                        UtilEntity.sendClientChat("Structure Created:  " + valid);
                        return valid;
                    }
                }
            }
            return false;

        } else {

            MultiBlockTank tank = new MultiBlockTank(worldObj);
            tank.load(bufferedTankData);

            if (tank.isStructureValid()) {

                return tank.create();
            }
            return false;
        }
    }
}
