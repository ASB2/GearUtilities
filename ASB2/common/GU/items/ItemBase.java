package GU.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;

import org.lwjgl.input.Keyboard;

import ASB2.utils.UtilMisc;
import GU.GearUtilities;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {

    public boolean useDefaultTexture = false;
    Icon texture;
    String itemName = "";

    public ItemBase(int id) {
        super(id);

        this.setCreativeTab(GearUtilities.tabGUItems);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        
        return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        info.add("From: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + Reference.NAME);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            
            this.addInformationSneaking(itemStack, player, info, var1);
        }
        else {

            info.add("Press " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Shift "+ UtilMisc.getColorCode(EnumChatFormatting.GRAY) + "to show more info");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Made just for you " + player.username);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MODDID + ":ItemTestItem");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + itemName);
    }
    
    public void setItemName(String texture) {

        this.itemName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + itemName);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {

        if (useDefaultTexture || texture == null)
            return this.itemIcon;

        return texture;
    }
}