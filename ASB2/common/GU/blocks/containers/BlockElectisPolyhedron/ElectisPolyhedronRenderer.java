package GU.blocks.containers.BlockElectisPolyhedron;

import java.awt.Color;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.render.noise.NoiseManager;

public class ElectisPolyhedronRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static ElectisPolyhedronRenderer instance = new ElectisPolyhedronRenderer();
    
    public ElectisPolyhedronRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x + 0.5F, y + .5f, z + .5F);
        
        GL11.glScaled(.25, .25, .25);
        
        NoiseManager.bindImage();
        
        Models.ModelRhombicuboctahedron.renderAll();
        
        GL11.glPopMatrix();
    }
    
    Color color = Color.WHITE;
    
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
        
        NoiseManager.bindImage();
        
        Models.ModelRhombicuboctahedron.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
