package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;

public class BlockBurningFlower extends FlowerBase {

    public BlockBurningFlower(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        UtilEntity.damageEntity(world, entity, DamageSource.onFire, 1);
    }
}