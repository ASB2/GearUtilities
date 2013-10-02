package GU.blocks;

import java.util.ArrayList;
import java.util.Random;

import ASB2.utils.UtilBlock;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BlockBloodPlant extends FlowerBase {

    public BlockBloodPlant(int par1, Material material) {
        super(par1, material);

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {

        if(world.rand.nextInt(100) == 1) {

            switch(world.getBlockMetadata(x, y, z)) {

                case 0: {

                    UtilBlock.placeBlockInAir(world, x, y, z, blockID, 1);
                    break;
                }
                case 1: {

                    UtilBlock.placeBlockInAir(world, x, y, z, blockID, 2);
                    break;
                }
            }
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        
        int meta = world.getBlockMetadata(x, y, z);

        if(meta == 0 && world.getBlockMetadata(x, y, z) == 1) {

            
        }
        return itemStacks;
    }

    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event) {

    }
}
