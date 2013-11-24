package GU.items.ItemCrystalShards;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import GU.info.Reference;
import GU.info.Variables;
import GU.items.ItemBase;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import GU.api.*;
import GU.api.power.IPowerProvider;

public class ItemCrystal extends ItemBase implements ISolarFocus {

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
    
    Icon[] CRYSTAL_ICONS = new Icon[CRYSTAL_SHARDS];
    String[] CRYSTAL_NAMES_UNLOCALIZED = new String[] {"ItemAirCrystalShard", "ItemEarthCrystalShard", "ItemFireCrystalShard", "ItemWaterCrystalShard", "ItemEnergyCrystalShard", "ItemVoidCrystalShard", "ItemMetallicCrystalShard", "ItemBloodCrystalShard", "ItemCrystalCasing", "ItemGarnet", "ItemPlantBloodCrystalShard"};
    String[] CRYSTAL_NAMES_IG = new String[] {"Air Crystal Shard", "Earth Crystal Shard", "Fire Crystal Shard", "Water Crystal Shard", "Energy Crystal Shard", "Void Crystal Shard", "Metallic Crystal Shard", "Blood Crystal Shard", "Crystal Casing", "Garnet", "Plant Blood Crystal Shard"};

    static int CRYSTAL_SHARDS = 11;

    public ItemCrystal(int id) {
        super(id);
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);

        for(int i = 0; i < CRYSTAL_SHARDS; i++) {

            LanguageRegistry.addName(new ItemStack(this, 1, i), CRYSTAL_NAMES_IG[i]);
            
            if(CRYSTAL_SHARDS != 9)
            OreDictionary.registerOre(Variables.CRYSTALS_ALL, new ItemStack(this, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        for(int i = 0; i < CRYSTAL_SHARDS; i++) {

            CRYSTAL_ICONS[i] = iconRegister.registerIcon(Reference.MODDID + ":" + CRYSTAL_NAMES_UNLOCALIZED[i]);
        }
    }
    
    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        return CRYSTAL_ICONS[stack.getItemDamage()];
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)    
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list) {

        for(int i = 0; i < CRYSTAL_SHARDS; i++) {

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

    @Override
    public void damageFocus(ItemStack stack, World world, int x, int y, int z, IPowerProvider solar) {
        
    }

    @Override
    public int getPowerForTick(ItemStack stack, World world, int x, int y, int z, IPowerProvider solar) {

        return 1;
    }

    @Override
    public boolean canFocus(ItemStack stack, World world, int x, int y, int z, IPowerProvider solar) {

        return true;
    }
}
