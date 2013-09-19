package GU.items.ItemHandheldTank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class HandheldTankRenderer implements IItemRenderer {

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

                renderItemSwitched(item, type, 0f, .5f, 0f, .5f, data);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F, data);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f - .1f, 0f, .6F, data);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .9f, .5F, data);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale, Object... data) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);
        
        UtilRender.renderTexture(Textures.HANDHELD_TANK_OUTSIDE);
        Models.ModelHandheldTank.renderPart("Points");
        
        UtilRender.renderTexture(Textures.HANDHELD_TANK_CENTER);
        Models.ModelHandheldTank.renderPart("Center");
        
        if(((ItemHandheldTank)item.getItem()).getFluidStack(item) != null && ((ItemHandheldTank)item.getItem()).getFluidStack(item).getFluid().getStillIcon() != null) {

            UtilRender.renderIcon(0, 0, ((ItemHandheldTank)item.getItem()).getFluidStack(item).getFluid().getStillIcon(), 16, 16);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
