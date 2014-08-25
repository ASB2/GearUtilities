package GU.items;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.render.noise.NoiseManager;

public class ItemRenderers {
    
    public static final class GarnetRenderer implements IItemRenderer {
        
        public static final GarnetRenderer instance = new GarnetRenderer();
        
        Color color = Color.YELLOW;
        
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
                    
                    renderItemSwitched(item, type, 0f, .35f, 0f, .3F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, 0f, 1f, 0f, .7F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, .5f, 0f, .5F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(item, type, -.5F, 1f, 0.5f, .5F);
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
            
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            
            NoiseManager.bindImage();
            Models.ModelCrystal1.renderAll();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}