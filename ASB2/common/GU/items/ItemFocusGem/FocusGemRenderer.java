package GU.items.ItemFocusGem;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Reference;
import GU.info.Textures;

public class FocusGemRenderer implements IItemRenderer {
    
    public FocusGemRenderer() {
        // TODO Auto-generated constructor stub
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
                
                renderItemSwitched(type, item, 0f, .5f, 0f, .5F);
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
        
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        UtilRender.renderTexture(Textures.FOCUS_GEM);
        Models.ModelCrystal1.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}