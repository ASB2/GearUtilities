package GU.blocks.containers.BlockTestRender;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.ContainerBase;

public class BlockTestRender extends ContainerBase {

    public BlockTestRender(int id, Material material) {
        super(id, material);

        this.registerTile(TileTestRender.class);
        useStandardRendering = false;
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
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {

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
    public void registerIcons(IconRegister iconRegistry) {


    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) 
    {

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileTestRender();
    }
}
