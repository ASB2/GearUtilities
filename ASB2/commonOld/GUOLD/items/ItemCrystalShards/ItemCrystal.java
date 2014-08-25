package GUOLD.items.ItemCrystalShards;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import GUOLD.info.Reference;
import GUOLD.items.ItemBase;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrystal extends ItemBase {
    
    public final ItemStack ItemAirCrystalShard = new ItemStack(this, 1, 0);
    public final ItemStack ItemEarthCrystalShard = new ItemStack(this, 1, 1);
    public final ItemStack ItemFireCrystalShard = new ItemStack(this, 1, 2);
    public final ItemStack ItemWaterCrystalShard = new ItemStack(this, 1, 3);
    public final ItemStack ItemEnergyCrystalShard = new ItemStack(this, 1, 4);
    public final ItemStack ItemVoidCrystalShard = new ItemStack(this, 1, 5);
    public final ItemStack ItemMetallicCrystalShard = new ItemStack(this, 1, 6);
    public final ItemStack ItemBloodCrystalShard = new ItemStack(this, 1, 7);
    public final ItemStack ItemCrystalCasing = new ItemStack(this, 1, 8);
    public final ItemStack ItemGarnet = new ItemStack(this, 1, 9);
    public final ItemStack ItemPlantBloodCrystalShard = new ItemStack(this, 1, 10);
    
    IIcon[] CRYSTAL_ICONS = new IIcon[CRYSTAL_SHARDS];
    String[] CRYSTAL_NAMES_UNLOCALIZED = new String[] { "ItemAirCrystalShard", "ItemEarthCrystalShard", "ItemFireCrystalShard", "ItemWaterCrystalShard", "ItemEnergyCrystalShard", "ItemVoidCrystalShard", "ItemMetallicCrystalShard", "ItemBloodCrystalShard", "ItemCrystalCasing", "ItemGarnet", "ItemPlantBloodCrystalShard" };
    String[] CRYSTAL_NAMES_IG = new String[] { "Air Crystal Shard", "Earth Crystal Shard", "Fire Crystal Shard", "Water Crystal Shard", "Energy Crystal Shard", "Void Crystal Shard", "Metallic Crystal Shard", "Blood Crystal Shard", "Crystal Casing", "Garnet", "Plant Blood Crystal Shard" };
    
    static int CRYSTAL_SHARDS = 11;
    
    public ItemCrystal() {
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        
        for (int i = 0; i < CRYSTAL_SHARDS; i++) {
            
            LanguageRegistry.addName(new ItemStack(this, 1, i), CRYSTAL_NAMES_IG[i]);
            
            if (CRYSTAL_SHARDS != 9) OreDictionary.registerOre(Reference.CRYSTALS_ALL, new ItemStack(this, 1, i));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        for (int i = 0; i < CRYSTAL_SHARDS; i++) {
            
            CRYSTAL_ICONS[i] = iconRegister.registerIcon(Reference.MODDID + ":" + CRYSTAL_NAMES_UNLOCALIZED[i]);
        }
    }
    
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        
        return CRYSTAL_ICONS[stack.getItemDamage()];
    }
    
    @Override
    public int getMetadata(int damageValue) {
        
        return damageValue;
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list) {
        
        for (int i = 0; i < CRYSTAL_SHARDS; i++) {
            
            list.add(new ItemStack(this, 1, i));
        }
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        return Reference.UNIQUE_ID + CRYSTAL_NAMES_UNLOCALIZED[itemStack.getItemDamage()];
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        return Reference.UNIQUE_ID + CRYSTAL_NAMES_IG[itemStack.getItemDamage()];
    }
}
