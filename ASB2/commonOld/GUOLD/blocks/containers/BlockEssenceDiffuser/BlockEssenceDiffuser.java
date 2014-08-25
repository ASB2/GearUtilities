package GUOLD.blocks.containers.BlockEssenceDiffuser;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GUOLD.blocks.containers.ContainerBase;

public class BlockEssenceDiffuser extends ContainerBase {
    
    public BlockEssenceDiffuser(Material material) {
        super(material);
        
        this.useStandardRendering = false;
        this.registerTile(TileEssenceDiffuser.class);
        this.setLightOpacity(0);
        
        this.maxHeight = .6f;
        this.maxWidth = .75f;
        this.minWidth = .25f;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        return false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileEssenceDiffuser();
    }
}
