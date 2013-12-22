package GU.blocks.containers.BlockQuantaSender;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import GU.blocks.containers.ContainerBase;
import GU.entity.FXBase;
import GU.info.Particles;

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
    
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        
//        UtilEntity.spawnFX(new FXBase(world, x, y, z, rand.nextInt(), rand.nextInt(), rand.nextInt(), 10, Particles.TEST_PARTICLE, Color.WHITE));
    }
}
