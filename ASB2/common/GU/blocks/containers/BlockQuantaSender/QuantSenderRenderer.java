package GU.blocks.containers.BlockQuantaSender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Reference;
import GU.info.Textures;

public class QuantSenderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static QuantSenderRenderer instance = new QuantSenderRenderer();
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        float translationAmount = .8f;
        float scale = .3f;
        
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glColor4f(1, 1, 1, 1.5f);
        
        switch (((TileQuantaSender) tileentity).getOrientation()) {
        
            case UP: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .07f + translationAmount, (float) z + .5F);
                break;
            }
            case DOWN: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .94F - translationAmount, (float) z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            
            case SOUTH: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 0.07F + translationAmount);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case NORTH: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + .93F - translationAmount);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case WEST: {
                
                GL11.glTranslatef((float) x + .94F - translationAmount, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case EAST: {
                
                GL11.glTranslatef((float) x + .07F + translationAmount, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {
                break;
            }
        }
        
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        
        UtilRender.renderTexture(Textures.CRYSTAL_1);
        Models.ModelCrystal1.renderAll();
        
        GL11.glDisable(GL11.GL_BLEND);
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
        UtilRender.renderTexture(Textures.CRYSTAL_1);
        Models.ModelCrystal1.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}