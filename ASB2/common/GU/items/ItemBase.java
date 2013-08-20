package GU.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.info.Reference;
import GU.utils.*;

public class ItemBase extends Item {

    public boolean useDefaultTexture = false;
    Icon texture;
    String itemName = "";

    public ItemBase(int id) {
        super(id);

        this.setCreativeTab(GearUtilities.tabGUItems);
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            java.util.List info, boolean var1) {
        info.add(UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "From: "
                + Reference.NAME);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister
                .registerIcon(Reference.MODDID + ":ItemTestItem");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + itemName);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        return false;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {

        return super.getUnlocalizedName(itemstack);
        // return "Unknown Metadata Notify ASB2";
    }

    public void setItemName(String texture) {

        this.itemName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + itemName);
    }

    public Icon getIcon(ItemStack stack, int pass) {

        if (useDefaultTexture || texture == null)
            return this.itemIcon;

        return texture;
    }
}