package GU.blocks.containers.BlockClusterSender;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.BlockRegistry;
import GU.info.Models;
import GU.info.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ClusterSenderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {

            case ENTITY: {

                renderItemSwitched(type, 0f, 0f - 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F);
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

        UtilRender.renderTexture(Textures.CLUSTER_SENDER);
        Models.ModelCulsterSender.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}