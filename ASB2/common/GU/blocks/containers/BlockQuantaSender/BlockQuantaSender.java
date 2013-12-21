package GU.blocks.containers.BlockQuantaSender;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockQuantaSender extends ContainerBase {
    
    public BlockQuantaSender(int id, Material material) {
        super(id, material);
        useStandardRendering = false;
        this.registerTile(TileQuantaSender.class);
        this.setLightOpacity(0);
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        // TODO Auto-generated method stub
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileQuantaSender();
    }
}
