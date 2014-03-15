package GU.blocks.containers.BlockStructureCube;

import net.minecraft.nbt.NBTTagCompound;
import ASB2.utils.UtilEntity;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.MultiBlockRegistry;

public class TileMultiCore extends TileStuctureAir {

    String multiBlockName = "";
    NBTTagCompound multiBlockSave = null;

    public TileMultiCore() {

        this.maxMultiBlocks = 1;
    }

    @Override
    public void invalidate() {

        if (super.multiBlocks.size() == 1) {

            IMultiBlock multi = this.multiBlocks.get(0);

            if (multi.isValid()) {

                multiBlockName = MultiBlockRegistry.getInstance().getMultiBlockNameFromMultiBlockClass(multi.getClass());
                multiBlockSave = multi.save(new NBTTagCompound());
            }
        }
        super.invalidate();
    }

    @Override
    public void validate() {
        super.validate();

        if (!multiBlockName.equalsIgnoreCase("")) {

            if (multiBlockSave != null) {

                IMultiBlock multiInstance = MultiBlockRegistry.getInstance().getMultiBlockInstanceFromMutliBlockName(multiBlockName);

                if (multiInstance != null) {

                    multiInstance.setWorld(worldObj);
                    multiInstance.load(multiBlockSave);
                    UtilEntity.sendClientChat("Structure Created:  " + multiInstance.create());
                }
                multiBlockName = "";
                multiBlockSave = null;
            }
        }
    }

    @Override
    public void updateEntity() {

        super.updateEntity();
    }

    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        // TODO Auto-generated method stub
        return super.addMultiBlock(multiBlock);
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        worldObj.setBlockToAir(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        multiBlockName = tag.getString("multiBlockName");
        multiBlockSave = tag.getCompoundTag("multiBlockSave");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setString("multiBlockName", multiBlockName);
        if (multiBlockSave != null)
            tag.setCompoundTag("multiBlockSave", multiBlockSave);
    }
}
