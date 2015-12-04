package GU.blocks.containers.BlockMultiDirectionalConduit;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;
import GU.render.noise.NoiseManager;

public class MultiDirectionalConduitRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static MultiDirectionalConduitRenderer instance = new MultiDirectionalConduitRenderer();
    
    public MultiDirectionalConduitRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x + .5, y + .5, z + .5);
        
        GL11.glScaled(.25, .25, .25);
        
        UtilRender.renderTexture(Textures.MULTI_DIRECTIONAL_CONDUIT);
        
        Models.ModelMultiDirectionalConduit.renderAll();
        
        GL11.glScaled(.9, .9, .9);
        NoiseManager.bindImage();
        Models.ModelMultiDirectionalConduit.renderAll();
        
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
                
                renderItemSwitched(item, type, 0f, 0, 0f, .8F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 1f, .5f, 1f, .5F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0, 0f, .5F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                
                renderItemSwitched(item, type, -.5F, 1f, 0.5f, .2F);
                return;
            }
            
            default:
                return;
        }
    }
    
    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslated(x, y, z);
        GL11.glScaled(scale, scale, scale);
        
        UtilRender.renderTexture(Textures.MULTI_DIRECTIONAL_CONDUIT);
        
        Models.ModelMultiDirectionalConduit.renderAll();
        
        GL11.glScaled(.9, .9, .9);
        NoiseManager.bindImage();
        Models.ModelMultiDirectionalConduit.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
