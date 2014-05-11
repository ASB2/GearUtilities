package GUOLD.items.ItemFlameFocus;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import GUOLD.api.flame.EnumFlameType;
import GUOLD.info.Models;
import GUOLD.info.Reference;
import GUOLD.render.NoiseManager;

public class FlameFocusRenderer implements IItemRenderer {

    public static final FlameFocusRenderer instance = new FlameFocusRenderer();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {

            case ENTITY: {

                renderItemSwitched(item, type, 0f, 0f, 0f, .5F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .5f, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {

        Color color = EnumFlameType.values()[UtilItemStack.getNBTTagInt(item, "Flame")].getFlameColor();

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        GL11.glPushMatrix();
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1, 1F);
        // UtilRender.renderTexture(Textures.FLAME_FOCUS_CUBE);

        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        NoiseManager.bindImage();
        Models.ModelFlameFocus.renderPart("Cube");
        GL11.glPopMatrix();

        color = color.darker().darker().darker();

        GL11.glColor3d(1, 1, 1);
        GL11.glPushMatrix();
        GL11.glRotatef(-Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1F, 1F);
        // UtilRender.renderTexture(Textures.FLAME_FOCUS_HEXAGON);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        NoiseManager.bindImage();
        Models.ModelFlameFocus.renderPart("Hexagon");
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}