package GU.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import GU.ItemRegistry;

public class BlockEnergyCrystalOre extends BlockBase {

    public BlockEnergyCrystalOre(int par1, Material par3Material) {
        super(par1, par3Material);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {

        return ItemRegistry.ItemEnergyCrystalShard.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {

        return par1Random.nextInt(5) + 1;
    }

}
