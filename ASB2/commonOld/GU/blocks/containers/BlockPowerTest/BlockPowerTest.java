package GU.blocks.containers.BlockPowerTest;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockPowerTest extends ContainerBase {
    
    public BlockPowerTest(Material material) {
        super(material);
        this.registerTile(TilePowerTest.class);
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        // par5EntityPlayer.getEntityData()
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    
    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        // TODO Auto-generated method stub
        super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TilePowerTest();
    }
}
