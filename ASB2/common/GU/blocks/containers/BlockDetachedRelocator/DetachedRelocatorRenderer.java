package GU.blocks.containers.BlockDetachedRelocator;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class DetachedRelocatorRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .1f, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(Textures.SOLAR_FOCUS_TOP);
        Models.ModelSolarFocus.renderPart("Top");
        

        Models.ModelSolarFocus.renderPart("Bottom");

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

                renderItemSwitched(item, type, 0f, .5f, 0f, 1f);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f - .1f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .9f, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        UtilRender.renderTexture(Textures.SOLAR_FOCUS_TOP);
        Models.ModelSolarFocus.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}