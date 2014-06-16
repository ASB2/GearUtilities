package GU.multiblock.construction;

import net.minecraft.world.World;
import GU.BlockRegistry;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.multiblock.MultiBlockBase;
import UC.math.vector.Vector3i;

public abstract class CubeConstructionManager extends ConstructionManager {
    
    protected Vector3i positiveMostPoint;
    protected Vector3i size;
    
    public CubeConstructionManager(World world, MultiBlockBase multiBlock, Vector3i positiveMostPoint, Vector3i size) {
        super(world, multiBlock);
        this.positiveMostPoint = positiveMostPoint;
        this.size = size;
    }
    
    @Override
    public boolean checkStructure() {
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    if (!checkBlock(positiveMostPoint.subtract(x, y, z))) {
                        
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public void updateDuringDestruction() {
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    deconstructBlock(positiveMostPoint.subtract(x, y, z));
                }
            }
        }
        this.finishDeconstruction();
    }
    
    public boolean placeRenderBlock(Vector3i position) {
        
        return this.placeBlock(position, BlockRegistry.MULTI_BLOCK_PART_RENDER) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.INNER);
    }
    
    public boolean placeGlassBlock(Vector3i position) {
        
        return this.placeBlock(position, BlockRegistry.MULTI_BLOCK_PART_GLASS) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.FACE);
    }
    
    public boolean placeAirBlock(Vector3i position) {
        
        return this.placeBlock(position, BlockRegistry.MULTI_BLOCK_PART_AIR) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.INNER);
    }
    
    public boolean placeInnerBlock(Vector3i position) {
        
        return this.placeBlockAndColor(position, BlockRegistry.MULTI_BLOCK_PART, 0, multiBlockStructure.getDefaultBlockColor()) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.INNER);
    }
    
    public boolean placeEdgeBlock(Vector3i position) {
        
        return this.placeBlockAndColor(position, BlockRegistry.MULTI_BLOCK_PART, 1, multiBlockStructure.getDefaultBlockColor()) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.EDGE);
    }
    
    public boolean placeCornerBlock(Vector3i position) {
        
        return this.placeBlockAndColor(position, BlockRegistry.MULTI_BLOCK_PART, 2, multiBlockStructure.getDefaultBlockColor()) && this.addMultiBlockToBlock(position, EnumMultiBlockPartPosition.CORNER);
    }
}
