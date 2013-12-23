package GU.blocks.containers.BlockEssenceDiffuser;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockEssenceDiffuser extends ContainerBase {
    
    public BlockEssenceDiffuser(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TileEssenceDiffuser.class);
        this.setLightOpacity(0);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileEssenceDiffuser();
    }
}
