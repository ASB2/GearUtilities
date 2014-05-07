package GU.blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockPhantomBlock extends BlockBase {
    
    public BlockPhantomBlock(Material material) {
        super(Material.vine);
        this.setHardness(0);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        // TODO Auto-generated method stub
        return null;
    }
}
