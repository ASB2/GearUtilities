package GUOLD.items.potionIngredients;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import GUOLD.api.potion.IPotionIngredient;
import GUOLD.info.Reference;
import GUOLD.items.ItemBase;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPotionIngredients extends ItemBase implements IPotionIngredient {
    
    private IIcon[] INGREDIENT_ICONS = new IIcon[INGREDIENTS];
    private String[] INGREDIENT_NAMES_UNLOCALIZED = new String[] { "ItemDurationChanger", "ItemStrengthChanger", "ItemSpiderEyeEssence", "ItemGhastTearExtract", "ItemEnergizedEnderPearl", "ItemSlightlyChangedRedstone" };
    private String[] INGREDIENT_NAMES_IG = new String[] { "Duration Increaser", "Strength Increaser", "Spider Eye Essence", "Ghast Tear Extract", "Energized Enderpearl", "Slight Changed Redstone" };
    
    private static int INGREDIENTS = 6;
    
    public ItemPotionIngredients() {
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        
        for (int i = 0; i < INGREDIENTS; i++) {
            
            LanguageRegistry.addName(new ItemStack(this, 1, i), INGREDIENT_NAMES_IG[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        for (int i = 0; i < INGREDIENTS; i++) {
            
            INGREDIENT_ICONS[i] = iconRegister.registerIcon(Reference.MODDID + ":" + INGREDIENT_NAMES_UNLOCALIZED[i]);
        }
    }
    
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        
        return INGREDIENT_ICONS[stack.getItemDamage()];
    }
    
    @Override
    public int getMetadata(int damageValue) {
        
        return damageValue;
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < INGREDIENTS; i++) {
            
            list.add(new ItemStack(this, 1, i));
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        return Reference.UNIQUE_ID + INGREDIENT_NAMES_UNLOCALIZED[itemStack.getItemDamage()];
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        return Reference.UNIQUE_ID + INGREDIENT_NAMES_IG[itemStack.getItemDamage()];
    }
    
    @Override
    public int getPowerChange(ItemStack stack) {
        
        switch (stack.getItemDamage()) {
        
            default:
                return 0;
        }
    }
    
    @Override
    public int getDurationChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getStrengthChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z) {
        // TODO Auto-generated method stub
        
    }
}
