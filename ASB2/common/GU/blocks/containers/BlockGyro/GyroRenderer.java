package GU.blocks.containers.BlockGyro;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Variables;

public class GyroRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .09f, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(this.getBaseTexture());
        Models.ModelGyro.renderPart("Base");

        UtilRender.renderTexture(this.getCenterTexture());
        Models.ModelGyro.renderPart("Center");

        GL11.glRotatef(Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0F, 1F, 0F);
        GL11.glPushMatrix();
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-180f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");
        GL11.glPopMatrix();

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

        switch(type) {

            case ENTITY: {

                renderItemSwitched(type, 0f, 0f - 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, .55F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 1f, 0 + .9f, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        UtilRender.renderTexture(this.getBaseTexture());
        Models.ModelGyro.renderPart("Base");

        UtilRender.renderTexture(this.getCenterTexture());
        Models.ModelGyro.renderPart("Center");

        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-180f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public ResourceLocation getBaseTexture() {

        return null;
    }

    public ResourceLocation getCenterTexture() {

        return null;
    }

    public ResourceLocation getOuterTexture() {

        return null;
    }
}
