package GU.blocks.containers.BlockFlameConduit;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.api.flame.EnumFlameType;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;

public class FlameConduitRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public static FlameConduitRenderer instance = new FlameConduitRenderer();

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        Color color = EnumFlameType.STORM.getFlameColor();

        GL11.glPushMatrix();

        switch (((TileBase) tileentity).getOrientation()) {

            case UP: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .07f, (float) z + .5F);
                break;
            }
            case DOWN: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .94F, (float) z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }

            case SOUTH: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 0.07F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case NORTH: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + .93F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case WEST: {

                GL11.glTranslatef((float) x + .94F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case EAST: {

                GL11.glTranslatef((float) x + .07F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {
                break;
            }
        }

        GL11.glScalef(.5f, .5f, .5f);

        NoiseManager.bindImage();
        Models.ModelFlameConduit.renderPart("Cube");

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);

        NoiseManager.bindImage();
        Models.ModelFlameConduit.renderPart("Hexagon_Cylinder");

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

        NoiseManager.bindImage();
        Models.ModelFlameConduit.renderPart("Cube");

        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        GL11.glColor3f(EnumFlameType.CLEAR.getFlameColor().getRed() / 255.0f, EnumFlameType.CLEAR.getFlameColor().getGreen() / 255.0f, EnumFlameType.CLEAR.getFlameColor().getBlue() / 255.0f);

        NoiseManager.bindImage();
        Models.ModelFlameConduit.renderPart("Hexagon_Cylinder");

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}