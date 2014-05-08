package GU.blocks.containers.BlockStructureCube;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockStructureCube extends BlockMetadataContainerBase {
    
    public BlockStructureCube(Material material) {
        super(material);
        
        this.registerTile(TileStructureCube.class);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        // TODO Auto-generated method stub
        return null;
    }
}
