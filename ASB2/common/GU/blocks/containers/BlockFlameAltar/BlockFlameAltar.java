package GU.blocks.containers.BlockFlameAltar;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockFlameAltar extends ContainerBase {

    public BlockFlameAltar(int id, Material material) {
        super(id, material);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        // TODO Auto-generated method stub
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return new TileFlameAltar();
    }
}
