package GU.items.ItemPurificationHelmet;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPurificationHelmet extends ItemArmor {

    public ItemPurificationHelmet(int id, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(id, par2EnumArmorMaterial, par3, par4);

        this.setUnlocalizedName(Reference.MODDID + ":ItemPurificationHelmet");
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        
        return Color.WHITE.hashCode();
    }
    
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {

        if(entity != null && entity instanceof EntityLivingBase) {

            ((EntityLivingBase)entity).curePotionEffects(stack);
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layerType) {
        
        return "";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemRegister) {
        
        this.itemIcon = itemRegister.registerIcon(Reference.MODDID + ":ItemPurificationHelmet");
    } 
}
