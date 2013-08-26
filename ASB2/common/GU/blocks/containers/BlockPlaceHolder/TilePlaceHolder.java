package GU.blocks.containers.BlockPlaceHolder;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import GU.blocks.containers.TileBase;

public class TilePlaceHolder extends TileBase {

    int heldBlockID;
    
    public TilePlaceHolder() {

    }

    public void setID(int newId) {
    
        heldBlockID = newId;
    }
    
    public Block getBlock() {
    
        return Block.blocksList[heldBlockID];
    }
    
    @Override
    public void updateEntity() {

    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        heldBlockID = tag.getInteger("heldBlock");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("heldBlock", heldBlockID);
    }
}
