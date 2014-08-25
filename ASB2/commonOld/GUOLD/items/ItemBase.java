package GUOLD.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

import org.lwjgl.input.Keyboard;

import ASB2.utils.UtilMisc;
import GUOLD.GearUtilities;
import GUOLD.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {
    
    public boolean useDefaultTexture = false;
    IIcon texture;
    String itemName = "";
    
    public ItemBase() {
        
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
        
        if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) {
            
            this.addInformationSneaking(itemStack, player, info, var1);
        }
        else {
            
            info.add("Press " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Shift " + UtilMisc.getColorCode(EnumChatFormatting.GRAY) + "to show more info");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        info.add("Made just for you " + player.getDisplayName());
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        
        itemIcon = iconRegister.registerIcon(Reference.MODDID + ":ItemTestItem");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + itemName);
    }
    
    public void setItemName(String texture) {
        
        this.itemName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + itemName);
    }
    
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        
        if (useDefaultTexture || texture == null) return this.itemIcon;
        
        return texture;
    }
}