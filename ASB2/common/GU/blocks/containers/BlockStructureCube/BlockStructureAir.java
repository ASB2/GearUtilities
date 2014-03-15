package GU.blocks.containers.BlockStructureCube;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlockPart;
import GU.blocks.containers.BlockMultiBase;

import com.google.common.collect.Lists;

public class BlockStructureAir extends BlockMultiBase {

    public BlockStructureAir(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;

        if (this.getClass() ==  BlockStructureAir.class)
            this.registerTile(TileStuctureAir.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {

    }

    @Override
    public int getLightOpacity(World world, int x, int y, int z) {

        return 0;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        IMultiBlockPart tile = (IMultiBlockPart) world.getBlockTileEntity(x, y, z);

        if (tile != null && tile.getComprisedMultiBlocks().size() > 0) {

            return 16;
        }
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileStuctureAir();
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {

        return super.getCollisionBoundingBoxFromPool(par1World, x, y, z);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        return Lists.newArrayList();
    }
}
