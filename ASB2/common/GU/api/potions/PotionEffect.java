package GU.api.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class PotionEffect {

    /*
     * How the ingredient affects the potion's cook time.
     * Negative number decrease duration positive increase it. 
     */
    public IngredientType getIngredientType(ItemStack stack) {

        return IngredientType.OTHER;
    }

    /*
     * How the ingredient affects the potion's cook time.
     * Negative number decrease duration positive increase it. 
     */
    public int getCookChange(ItemStack stack) {

        return 0;
    }

    /*
     * How the ingredient affects the potion effect's duration time.
     * Negative number decrease duration positive increase it. 
     */
    public int getDurationChange(ItemStack stack) {

        return 0;
    }

    /*
     * How the ingredient affects the potion effect's strength.
     * Negative number decrease duration positive increase it. 
     */
    public int getStrengthChange(ItemStack stack) {

        return 0;
    }

    public void onEntityDrinkPotion(World world, ItemStack potion, EntityLivingBase entity) {

    }

    public void onThrownPotionHitEntity(World world, ItemStack potion, EntityLivingBase entity) {

    }
    
    public void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z) {

    }
}
