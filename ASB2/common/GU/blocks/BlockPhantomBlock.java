package GU.blocks;

import java.util.Random;

import GU.ItemRegistry;
import net.minecraft.block.material.Material;

public class BlockPhantomBlock extends BlockBase {

    public BlockPhantomBlock(int id, Material material) {
        super(id, Material.vine);
    }
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        
        return -1;
    }
}
