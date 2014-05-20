package GU.api.multiblock;

import net.minecraft.block.Block;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockRow;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockStructure;

public class MultiBlockObject {
    
    public static class MultiBlockStructure implements IMultiBlockStructure {
        
        MultiBlockRow[] multiBlockRows;
        
        public MultiBlockStructure() {
            
            multiBlockRows = new MultiBlockRow[3];
        }
        
        @Override
        public IMultiBlockRow[] getStructure() {
            
            return multiBlockRows;
        }
    }
    
    public static class MultiBlockRow implements IMultiBlockRow {
        
        Block[][] blocks;
        int xSize, zSize;
        
        public MultiBlockRow(int xSize, int zSize) {
            
            blocks = new Block[xSize][zSize];
            this.xSize = xSize;
            this.zSize = zSize;
        }
        
        public MultiBlockRow setBlock(Block block, int x, int z) {
            
            blocks[x][z] = block;
            return this;
        }
        
        @Override
        public int getXSize() {
            
            return xSize;
        }
        
        @Override
        public int getZSize() {
            
            return zSize;
        }
        
        @Override
        public Block getBlockAtPosition(int x, int z) {
            
            return blocks[x][z];
        }
    }
}
