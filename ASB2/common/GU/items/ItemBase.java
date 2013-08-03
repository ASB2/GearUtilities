package GU.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import GU.GearUtilities;
import GU.info.Reference;

public class ItemBase extends Item {

    public boolean useDefaultTexture = false;    
    Icon texture;
    
    public ItemBase(int id) {
        super(id);

        this.setCreativeTab(GearUtilities.tabGUItems);        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) 
    {
        info.add("From: " + Reference.NAME);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MODDID + ":ItemTestItem");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + this.getUnlocalizedName());
    }

    @Override
    public Icon getIconFromDamage(int damage) {

        if(texture != null) {
            
            return texture;
        }
        return this.itemIcon;
    }
}