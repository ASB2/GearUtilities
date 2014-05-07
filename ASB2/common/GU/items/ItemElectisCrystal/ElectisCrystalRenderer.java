package GU.items.ItemElectisCrystal;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;

public class ElectisCrystalRenderer implements IItemRenderer {
    
    public static final ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
    
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
                
                renderItemSwitched(item, type, 0f, 0f, 0f, .45F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 0f, 1f, 0f, .7F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0f, 0f, .9F);
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
        color = Color.BLUE;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotated(90, 0, 0, 1);
        GL11.glRotated(90, 0, 1, 0);
        
        GL11.glPushMatrix();
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 0F, 1F);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        
        GL11.glScalef(.97f, .97f, .97f);
        NoiseManager.bindImage();
        Models.ModelFlameShard.renderPart("Center");
        GL11.glPopMatrix();
        
//        color = color.darker().darker().darker();
        
        GL11.glPushMatrix();
        // GL11.glRotatef(-Minecraft.getSystemTime() /
        // Reference.ANIMATION_SPEED, 0F, 0, 1F);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        NoiseManager.bindImage();
        Models.ModelFlameShard.renderPart("Outside");
        GL11.glPopMatrix();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}