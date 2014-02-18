package GU.blocks.containers.BlockStructureCube;

import net.minecraft.nbt.NBTTagCompound;
import GU.blocks.containers.TileMultiBase;

public class TileReplacementStructureCube extends TileMultiBase {

    private int savedId, savedMetadata;

    public TileReplacementStructureCube() {
        // TODO Auto-generated constructor stub
    }

    public void setSavedID(int newSavedId) {

        savedId = newSavedId;
    }

    public int getSavedID() {

        return savedId;
    }

    public int getSavedMetadata() {

        return savedMetadata;
    }

    public void setSavedMetadata(int newSavedMetadata) {

        savedMetadata = newSavedMetadata;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {

        savedId = tag.getInteger("savedId");
        savedMetadata = tag.getInteger("savedMetadata");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

        tag.setInteger("savedId", savedId);
        tag.setInteger("savedMetadata", savedMetadata);
    }
}
