package GU.items;

import java.util.List;

import GU.info.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemCrystal extends ItemBase {

    Icon ItemAirCrystalShard;
    Icon ItemEarthCrystalShard;
    Icon ItemFireCrystalShard;
    Icon ItemWaterCrystalShard;
    Icon ItemEnergyCrystalShard;
    Icon ItemGarnet;
    
    public ItemCrystal(int id) {
        super(id);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata (int damageValue) {

        return damageValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(int id, CreativeTabs creativeTab, @SuppressWarnings("rawtypes") List subItems) {

        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
        subItems.add(new ItemStack(this, 1, 2));
        subItems.add(new ItemStack(this, 1, 3));
        subItems.add(new ItemStack(this, 1, 4));
        subItems.add(new ItemStack(this, 1, 5));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {

        switch(itemstack.getItemDamage()) {

            case 0: return Reference.UNIQUE_ID + "ItemAirCrystalShard";
            case 1: return Reference.UNIQUE_ID + "ItemEarthCrystalShard";
            case 2: return Reference.UNIQUE_ID + "ItemFireCrystalShard";
            case 3: return Reference.UNIQUE_ID + "ItemWaterCrystalShard";
            case 4: return Reference.UNIQUE_ID + "ItemEnergyCrystalShard";
            case 5: return Reference.UNIQUE_ID + "ItemGarnet";

            default: return "Unknown Metadata Notify ASB2";
        }
    }

    @Override
    public String getLocalizedName(ItemStack itemstack) {

        switch(itemstack.getItemDamage()) {

            case 0: return "Air Crystal Shard";
            case 1: return "Earth Crystal Shard";
            case 2: return "Fire Crystal Shard";
            case 3: return "Water Crystal Shard";
            case 4: return "Energy Crystal Shard";
            case 5: return "Garnet";

            default: return "Unknown Metadata Notify ASB2";
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        
        ItemAirCrystalShard = iconRegister.registerIcon(Reference.MODDID + ":ItemAirCrystalShard");
        ItemEarthCrystalShard = iconRegister.registerIcon(Reference.MODDID + ":ItemEarthCrystalShard");
        ItemFireCrystalShard = iconRegister.registerIcon(Reference.MODDID + ":ItemFireCrystalShard");
        ItemWaterCrystalShard = iconRegister.registerIcon(Reference.MODDID + ":ItemWaterCrystalShard");
        ItemEnergyCrystalShard = iconRegister.registerIcon(Reference.MODDID + ":ItemEnergyCrystalShard");
        ItemGarnet = iconRegister.registerIcon(Reference.MODDID + ":ItemGarnet");
    }
    
    @Override
    public Icon getIconFromDamage(int damage) {

        switch(damage) {
            
            case 0: return ItemAirCrystalShard;
            case 1: return ItemEarthCrystalShard;
            case 2: return ItemFireCrystalShard;
            case 3: return ItemWaterCrystalShard;
            case 4: return ItemEnergyCrystalShard;
            case 5: return ItemGarnet;
        }
        return this.itemIcon;
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformation(itemStack, player, info, var1);
    }
}
