package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.api.ISolarFocus;
import GU.info.Models;
import GU.info.Textures;

public class SolarFocusRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .1f, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(Textures.SOLAR_FOCUS_TOP);
        Models.ModelSolarFocus.renderAll();

        GL11.glPopMatrix();

        if(tile.worldObj.isDaytime()) {
            
        if(((IInventory)tile).getStackInSlot(0) != null && ((IInventory)tile).getStackInSlot(0).getItem() instanceof ISolarFocus) {
            
            GL11.glPushMatrix();

            RenderItem renderitem = (RenderItem)RenderManager.instance.getEntityClassRenderObject(EntityItem.class); 
            EntityItem entityitem = new EntityItem(tile.worldObj, x, y, z, ((IInventory)tile).getStackInSlot(0)); 
            entityitem.age = 0;
                
            entityitem.hoverStart = Minecraft.getSystemTime();
            
            GL11.glTranslatef((float)x + .5F, (float)y + .5F, (float)z + 0.5F);

            renderitem.doRenderItem(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            GL11.glPopMatrix();
        }
        }
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
