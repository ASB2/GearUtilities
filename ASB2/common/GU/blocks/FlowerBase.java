package GU.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.Mod.EventHandler;

public class FlowerBase extends BlockBase {

    public FlowerBase(int par1, Material par2Material) {
        super(par1, par2Material);

        setHardness(0f);
        setResistance(0F);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @EventHandler
    public void onUseBonemeal(BonemealEvent event) {

        this.managGrowth(event, event.world, event.X, event.Y, event.Z, new Random());
    }

    private void managGrowth(BonemealEvent event, World world, int x, int y, int z, Random random) {

    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        return null;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {

        return world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID;
    }

    public boolean isOpaqueCube() {

        return false;
    }

    public boolean renderAsNormalBlock() {

        return false;
    }

    public int getRenderType() {

        return 1;
    }
}
