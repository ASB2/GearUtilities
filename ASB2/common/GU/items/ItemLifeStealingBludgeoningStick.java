package GU.items;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class ItemLifeStealingBludgeoningStick extends ItemBase {

    public ItemLifeStealingBludgeoningStick(int par1) {
        super(par1);
        this.setMaxDamage(50);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    public boolean hitEntity(ItemStack itemStack, EntityLiving entityGettingHit, EntityLiving entityWhoHit) {

        Random rand = new Random();
        int damage = rand.nextInt(10);
        itemStack.damageItem(damage, entityWhoHit);
        
        if(rand.nextInt(5) == rand.nextInt(5)) {

            entityWhoHit.heal(damage);
        }
        return true;
    }

    public int getDamageVsEntity(Entity par1Entity) {
        
        return 5;
    }
}
