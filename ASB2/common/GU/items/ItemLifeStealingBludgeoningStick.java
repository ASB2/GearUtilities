package GU.items;

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

    public boolean hitEntity(ItemStack itemStack,
            EntityLiving entityGettingHit, EntityLiving entityWhoHit) {

        entityWhoHit.heal(1);
        itemStack.damageItem(1, entityWhoHit);
        return true;
    }

    public int getDamageVsEntity(Entity par1Entity) {
        return 1;
    }
}
