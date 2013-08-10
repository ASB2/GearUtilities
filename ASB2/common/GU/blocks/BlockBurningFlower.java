package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import GU.utils.UtilPlayers;

public class BlockBurningFlower extends FlowerBase {

    public BlockBurningFlower(int par1, Material par3Material) {
        super(par1, par3Material);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        UtilPlayers.damagePlayer(world, x, y, z, entity, DamageSource.onFire, 1);
    }
}