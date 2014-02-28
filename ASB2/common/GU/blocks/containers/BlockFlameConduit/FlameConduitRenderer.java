package GU.blocks.containers.BlockFlameConduit;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.api.flame.EnumFlameType;
import GU.info.Models;
import GU.info.Reference;
import GU.info.Textures;
import GU.render.BufferedImageTest;

public class FlameConduitRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public static FlameConduitRenderer instance = new FlameConduitRenderer();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        Color color = EnumFlameType.STORM.getFlameColor();

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5F, y + .25f, z + .5F);

        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(Textures.FLAME_ALTAR_CUBE);
        Models.ModelFlameAltar.renderPart("Cube");

        GL11.glTranslated(0, 1, 0);

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        BufferedImageTest.bindImage();
        Models.ModelFlameFocus.renderPart("Hexagon");

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

                renderItemSwitched(type, item, 0f, 0f, 0f, .5F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, item, -.5F, 1f, .5F, .5F);
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

        UtilRender.renderTexture(Textures.FLAME_ALTAR_CUBE);
        Models.ModelFlameAltar.renderPart("Cube");

        GL11.glScaled(1.1, 1.1, 1.1);

        GL11.glTranslated(0, .7, 0);

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);

        // UtilRender.renderTexture(Textures.FLAME_ALTAR_HEXAGON);
        BufferedImageTest.bindImage();
        Models.ModelFlameFocus.renderPart("Hexagon");

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}