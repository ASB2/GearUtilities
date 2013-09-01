package GU.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IPotionIngredient {
    
    /*
     * How the ingredient affects the potion's cook time.
     * Negative number decrease duration positive increase it. 
     */
    IngredientType getIngredientType(ItemStack stack);

    /*
     * How the ingredient affects the potion's power requirement.
     * Negative number decreases energy required positive increases it. 
     */
    int getPowerChange(ItemStack stack);

    /*
     * How the ingredient affects the potion effect's duration time.
     * Negative number decreases potion duration positive increases it. 
     */
    int getDurationChange(ItemStack stack);
    
    /*
     * How the ingredient affects the potion effect's strength.
     * Negative number decreases potion duration positive increases it. 
     */
    int getStrengthChange(ItemStack stack);
    
    void onEntityDrinkPotion(World world, ItemStack potion, EntityLivingBase entity);

    void onPotionThrown(World world, ItemStack potion, EntityLivingBase entity);
    
    void onThrownPotionHitEntity(World world, ItemStack potion, EntityLivingBase entity);
    
    void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z);
}
