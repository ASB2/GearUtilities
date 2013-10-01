package GU.blocks;

import java.util.ArrayList;
import java.util.Random;

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

      
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {


        return super.getBlockDropped(world, x, y, z, metadata, fortune);
    }
    
    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event) {

    }
}
