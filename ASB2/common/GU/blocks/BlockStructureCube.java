package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;

public class BlockStructureCube extends BlockMetadata implements IMultiBlockMarker {
    
    public BlockStructureCube(Material material) {
        super(material);
    }
    
    @Override
    public boolean isValid(World world, int x, int y, int z) {
        
        return true;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
}
