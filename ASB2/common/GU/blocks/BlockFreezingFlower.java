package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockFreezingFlower extends FlowerBase {

    public BlockFreezingFlower(int par1, Material par3Material) {
        super(par1, par3Material);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity entity) {

        entity.setInWeb();
    }
}