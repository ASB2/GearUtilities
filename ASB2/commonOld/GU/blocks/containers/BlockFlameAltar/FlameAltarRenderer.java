package GU.blocks.containers.BlockFlameAltar;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;

public class FlameAltarRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public static FlameAltarRenderer instance = new FlameAltarRenderer();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        Color color = ((TileFlameAltar) tileentity).getCurrentFlame().getFlameColor();

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5F, y + .5f, z + .5F);

        GL11.glScalef(.2f, .2f, .2f);

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);

        NoiseManager.bindImage();
        Models.ModelFlameAltar.renderAll();

        GL11.glPopMatrix();

    }

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

                renderItemSwitched(type, item, 0f, .7f, 0f, .5F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, item, 0f, 1, .5f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, item, 0f, 0f, 0f, .2F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, item, -.5F, 1f, .5F, .2F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, ItemStack item, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);

        // UtilRender.renderTexture(Textures.FLAME_ALTAR_HEXAGON);
        NoiseManager.bindImage();
        Models.ModelFlameAltar.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}