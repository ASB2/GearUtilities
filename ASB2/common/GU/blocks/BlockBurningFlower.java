package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockBurningFlower extends FlowerBase {

    public BlockBurningFlower(int id, Material material) {
        super(id, material);
        this.setBlockBounds(.3f, 0f, .3f, .7f, .8f, .7f);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        entity.setFire(5);
        entity.attackEntityFrom(DamageSource.onFire, 1);
    }
}