package GU.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import GU.ItemRegistry;

public class BlockGarnetOre extends BlockBase {

    public BlockGarnetOre(int par1, Material par3Material) {
        super(par1, par3Material);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {

        return ItemRegistry.ItemGarnet.itemID;
    }
}
