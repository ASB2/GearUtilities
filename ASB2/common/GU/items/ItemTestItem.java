package GU.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilBlock;
import GU.api.potion.IPotionIngredient;
import GU.entity.EntityTest.EntityTestEntity;

public class ItemTestItem extends ItemBase implements IBlockCycle, IPotionIngredient {

    public ItemTestItem(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        if(player.isSneaking()) {

            if(!world.isRemote) {

                EntityZombie entity = new EntityZombie(world);
                entity.setPosition(x + .5, y + 1.5, z + .5);
                world.spawnEntityInWorld(entity);
            }
        }
        else {

            if(!world.isRemote) {

                EntityTestEntity entity = new EntityTestEntity(world, x, y + 1, z,  x + 10, y + 50 , z);
                world.spawnEntityInWorld(entity);
            }
        }
        return true;
    }

    @Override
    public boolean execute(EntityLivingBase player, World world, int x, int y, int z, ForgeDirection side, int id) {

        UtilBlock.breakBlock(world, x, y, z);
        return false;
    }

    @Override
    public int getPowerChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public int getDurationChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public int getStrengthChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public void onEntityDrinkPotion(World world, ItemStack potion, EntityLivingBase entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPotionThrown(World world, ItemStack potion, EntityLivingBase entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onThrownPotionHitEntity(World world, ItemStack potion, EntityLivingBase entity) {

        entity.addPotionEffect(new PotionEffect(1, 1000, 3));
    }

    @Override
    public void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z) {

        world.setBlockToAir(x, y - 0, z);
    }

}
