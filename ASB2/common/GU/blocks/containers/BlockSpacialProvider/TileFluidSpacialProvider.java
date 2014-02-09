package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.IMultiBlock;
import GU.multiblock.MultiBlockTank;

public class TileFluidSpacialProvider extends TileSpacialProvider {

    NBTTagCompound bufferedTankData;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (hasBufferedCreateMultiBlock) {

            this.createMultiBlock(true);
            hasBufferedCreateMultiBlock = false;
            bufferedTankData = null;
        }
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
                    return false;
                } else {
                    return false;
                }
            } else {

                return false;
            }
        } else {

            MultiBlockTank tank = new MultiBlockTank(worldObj);
            tank.load(bufferedTankData);
            boolean spaceValid = tank.isStructureValid();
            UtilEntity.sendClientChat("Area Valid: " + spaceValid);

            if (spaceValid) {

                boolean valid = tank.create();
                UtilEntity.sendClientChat("Structure Created:  " + valid);
                return valid;
            }
            UtilEntity.sendClientChat("Structure Created: false");
            return false;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (!this.getComprisedMultiBlocks().isEmpty()) {

            for (IMultiBlock multi : this.getComprisedMultiBlocks()) {

                if (new Vector3(this).intEquals(multi.getSize().getCore())) {

                    tag.setCompoundTag("multiBlockSave", multi.save(new NBTTagCompound()));
                }
            }
            tag.setBoolean("isInMultiBlock", isInMultiBlock);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        hasBufferedCreateMultiBlock = tag.getBoolean("isInMultiBlock");

        if (hasBufferedCreateMultiBlock) {

            bufferedTankData = tag.getCompoundTag("multiBlockSave");
        }
    }
}
