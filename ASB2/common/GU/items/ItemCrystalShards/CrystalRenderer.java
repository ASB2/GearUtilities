package GU.items.ItemCrystalShards;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.ItemRegistry;
import GU.info.Models;
import GU.info.Textures;

public class CrystalRenderer implements IItemRenderer {

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {

            case ENTITY: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .4F, item);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F, item);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, .5F, item);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f + 1f, 0f + .5F, .5F, item);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y,
            float z, float scale, ItemStack stack) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(45f, 0f, 10f, 0f);
        UtilRender.renderTexture(getTexture(stack));

        Models.ModelCrystalItem.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public ResourceLocation getTexture(ItemStack stack) {

        if (stack.itemID == ItemRegistry.ItemCrystal.ItemAirCrystalShard.itemID)
            return Textures.CRYSTAL_AIR;
        if (stack.itemID == ItemRegistry.ItemCrystal.ItemEarthCrystalShard.itemID)
            return Textures.CRYSTAL_EARTH;
        if (stack.itemID == ItemRegistry.ItemCrystal.ItemFireCrystalShard.itemID)
            return Textures.CRYSTAL_FIRE;
        if (stack.itemID == ItemRegistry.ItemCrystal.ItemWaterCrystalShard.itemID)
            return Textures.CRYSTAL_WATER;
        if (stack.itemID == ItemRegistry.ItemCrystal.ItemEnergyCrystalShard.itemID)
            return Textures.CRYSTAL_ENERGY;

        return Textures.CRYSTAL_BLANK;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        // TODO Auto-generated method stub
        return true;
    }
}
