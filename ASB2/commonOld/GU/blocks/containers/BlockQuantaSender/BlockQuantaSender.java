package GU.blocks.containers.BlockQuantaSender;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockQuantaSender extends ContainerBase {
    
    public BlockQuantaSender(Material material) {
        super(material);
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
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return super.isNormalCube(world, x, y, z);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileQuantaSender();
    }
    
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        
        // UtilEntity.spawnFX(new FXBase(world, x, y, z, rand.nextInt(),
        // rand.nextInt(), rand.nextInt(), 10, Particles.TEST_PARTICLE,
        // Color.WHITE));
    }
}
