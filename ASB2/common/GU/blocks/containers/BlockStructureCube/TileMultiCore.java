package GU.blocks.containers.BlockStructureCube;

import net.minecraft.nbt.NBTTagCompound;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileMultiCore extends TileMultiBase {

    public TileMultiCore() {

        this.maxMultiBlocks = 1;
    }

    @Override
    public void validate() {
        // TODO Auto-generated method stub
        super.validate();
    }

    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        // TODO Auto-generated method stub
        return super.addMultiBlock(multiBlock);
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        // TODO Auto-generated method stub
        super.removeMultiBlock(multiBlock);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.writeToNBT(tag);
    }
}
