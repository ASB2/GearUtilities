package GU.blocks.containers.BlockStructureCube;

import net.minecraft.nbt.NBTTagCompound;
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

        if (isInMultiBlock && multiBlockSave == null) {

            IMultiBlock multi = this.multiBlocks.get(0);
            multiBlockName = MultiBlockRegistry.getInstance().getNameFromMultiBlock(multi.getClass());
            multiBlockSave = multi.save(new NBTTagCompound());
        }
        super.invalidate();
    }

    @Override
    public void validate() {

        if (multiBlockSave != null && !multiBlockName.equalsIgnoreCase("")) {

            Class<? extends IMultiBlock> multi = MultiBlockRegistry.getInstance().getMultiBlockFromName(multiBlockName);

            if (multi != null) {

                IMultiBlock multiInstance = null;

                try {

                    multiInstance = multi.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {

                    e.printStackTrace();
                }

                if (multiInstance != null) {

                    multiInstance.setWorld(worldObj);
                    multiInstance.load(multiBlockSave);
                    multiInstance.create();
                }
            }
        }

        multiBlockName = "";
        multiBlockSave = null;
        super.validate();
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
