package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockFreezingFlower extends FlowerBase {

    public BlockFreezingFlower(int par1, Material par3Material) {
        super(par1, par3Material);

        this.setBlockBounds(.3f, 0f, .3f, .7f, .8f, .7f);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity entity) {

        entity.setInWeb();
        
        if(entity instanceof EntityLivingBase) {
            
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 20 * 10, 2));
        }
    }
}