package GU.blocks.containers.BlockFlameAltar;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.api.flame.EnumFlameType;
import GU.blocks.containers.ContainerBase;

public class BlockFlameAltar extends ContainerBase {
    
    public BlockFlameAltar(Material material) {
        super(material);
        this.specialMetadata = true;
        this.dropMetadata = true;
        this.useStandardRendering = false;
        this.setLightOpacity(0);
        this.registerTile(TileFlameAltar.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        
        int flame = world.getBlockMetadata(x, y, z);
        
        if (flame < EnumFlameType.values().length - 1) {
            
            world.setBlockMetadataWithNotify(x, y, z, flame + 1, 3);
        }
        else {
            world.setBlockMetadataWithNotify(x, y, z, EnumFlameType.STORM.ordinal(), 3);
        }
        return true;
        // return super.onBlockActivated(world, x, y, z, par5EntityPlayer, par6,
        // par7, par8, par9);
    }
    
    @Override
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        // TODO Auto-generated method stub
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }
    
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        // TODO Auto-generated method stub
        return super.canBlockStay(par1World, par2, par3, par4);
    }
    
    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return super.canPlaceTorchOnTop(world, x, y, z);
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
        // TODO Auto-generated method stub
        return new TileFlameAltar();
    }
}
