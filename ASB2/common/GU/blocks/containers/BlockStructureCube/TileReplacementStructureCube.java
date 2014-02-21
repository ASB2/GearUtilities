package GU.blocks.containers.BlockStructureCube;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import GU.api.multiblock.IMultiBlock;
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
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);

        if (this.multiBlocks.isEmpty()) {

            worldObj.setBlock(xCoord, yCoord, zCoord, this.getSavedID(), this.getSavedMetadata(), 3);
        }
    }

    public Block getFalseBlock() {

        Block block = Block.blocksList[this.getSavedID()];
        // UtilEntity.sendClientChat("Saved ID " + ((TileReplacementStructureCube) tile).getSavedID() + " " + "Saved Meatadata: " +
        // ((TileReplacementStructureCube) tile).getSavedMetadata());
        return block != null ? block : null;

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        savedId = tag.getInteger("savedId");
        savedMetadata = tag.getInteger("savedMetadata");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("savedId", savedId);
        tag.setInteger("savedMetadata", savedMetadata);
    }
}
