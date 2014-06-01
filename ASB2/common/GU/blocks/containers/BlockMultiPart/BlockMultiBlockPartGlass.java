package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.BlockMultiMetadataContainerBase;

public class BlockMultiBlockPartGlass extends BlockMultiMetadataContainerBase {
    
    public BlockMultiBlockPartGlass(Material material) {
        super(material);
        this.setLightOpacity(0);
    }
    
    @Override
    public boolean isOpaqueCube() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPart();
    }
}
