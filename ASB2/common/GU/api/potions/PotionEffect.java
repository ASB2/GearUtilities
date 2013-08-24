package GU.api.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PotionEffect {

    IngredientType type = IngredientType.OTHER;
    int cookChange;
    int durationChange;
    int strengthChange;
    
    public PotionEffect(IngredientType type, int cookChange, int durationChange, int strengthChange) {
    
        this.type = type;
        this.cookChange = cookChange;
        this.durationChange = durationChange;
        this.strengthChange = strengthChange;
    }
    
    /*
     * How the ingredient affects the potion's cook time.
     * Negative number decrease duration positive increase it. 
     */
    public IngredientType getIngredientType(ItemStack stack) {

        return type;
    }

    /*
     * How the ingredient affects the potion's cook time.
     * Negative number decrease duration positive increase it. 
     */
    public int getCookChange(ItemStack stack) {

        return cookChange;
    }

    /*
     * How the ingredient affects the potion effect's duration time.
     * Negative number decrease duration positive increase it. 
     */
    public int getDurationChange(ItemStack stack) {

        return durationChange;
    }

    /*
     * How the ingredient affects the potion effect's strength.
     * Negative number decrease duration positive increase it. 
     */
    public int getStrengthChange(ItemStack stack) {

        return strengthChange;
    }

    public void onEntityDrinkPotion(World world, ItemStack potion, EntityLivingBase entity) {

    }

    public void onThrownPotionHitEntity(World world, ItemStack potion, EntityLivingBase entity) {

    }
    
    public void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z) {

    }
}
