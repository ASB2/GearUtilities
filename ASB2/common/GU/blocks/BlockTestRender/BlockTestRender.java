package GU.blocks.BlockTestRender;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.info.Reference;
import GU.blocks.*;

public class BlockTestRender extends BlockBase {

    public Icon inner;

    public BlockTestRender(int id, Material material) {
        super(id, material);
        
        useStandardRendering = false;
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
    
    public boolean isBlockNormalCube(World world, int x, int y, int z) {

        return true;
    }

    public int getLightOpacity(World world, int x, int y, int z)
    {
        return 255;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        return super.getBlockDropped(world, x, y, z, metadata, fortune);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        return super.getLightValue(world, x, y, z);
    }

    @Override
    public int getRenderType() {

        return TestRenderRenderer.testRenderID;
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        return super.getBlockTexture(world, x, y, z, side);
    }


    @Override
    public Icon getIcon(int side, int metadata) {

        return super.getIcon(side, metadata);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        inner = iconRegister.registerIcon(Reference.MODDID + ":BlockTestRender2");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) 
    {
        world.markBlockForRenderUpdate(x, y, z);
        return false;
    }
}
