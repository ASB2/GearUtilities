package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class FlowerBase extends BlockBase {

    public FlowerBase(int par1, Material material) {
        super(par1, material);

        setHardness(0f);
        setResistance(0F);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event) {
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        return null;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {

        return world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID;
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public int getRenderType() {

        return 1;
    }
}
