package GU.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import GU.ItemRegistry;

public class BlockFireCrystalOre extends BlockBase {

    public BlockFireCrystalOre(int par1, Material par3Material) {
        super(par1, par3Material);
    }


    public int idDropped(int par1, Random par2Random, int par3) {

        return ItemRegistry.ItemFireCrystalShard.itemID;
    }

    public int quantityDropped(Random par1Random) {
        return par1Random.nextInt(5) + 1;
    }
}
