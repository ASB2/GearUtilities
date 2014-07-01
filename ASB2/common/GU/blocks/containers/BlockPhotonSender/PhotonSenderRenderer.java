package GU.blocks.containers.BlockPhotonSender;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.EventListener;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.render.noise.NoiseManager;
import UC.VariableIterator;

public class PhotonSenderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static PhotonSenderRenderer instance = new PhotonSenderRenderer();
    VariableIterator var = new VariableIterator(.0003, -.1, .1);
    
    public PhotonSenderRenderer() {
        
        EventListener.instance.VARIABLES.add(var);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        final float translationAmount = .05f;
        
        switch (((TileBase) tileentity).getOrientation()) {
        
            case UP: {
                
                GL11.glTranslated(x + 0.5F, y + translationAmount, z + .5F);
                break;
            }
            case DOWN: {
                
                GL11.glTranslated(x + 0.5F, y + (1 - translationAmount), z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            
            case SOUTH: {
                
                GL11.glTranslated(x + 0.5F, y + .5F, z + translationAmount);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case NORTH: {
                
                GL11.glTranslated(x + 0.5F, y + .5F, z + (1 - translationAmount));
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case WEST: {
                
                GL11.glTranslated(x + (1 - translationAmount), y + .5F, z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case EAST: {
                
                GL11.glTranslated(x + translationAmount, y + .5F, z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {
                break;
            }
        }
        
        GL11.glScaled(.4, .4, .4);
        NoiseManager.bindImage();
        
        Models.ModelGyro.renderPart("Base");
        Models.ModelGyro.renderPart("Center");
        
        GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
        
        for (int i = 0; i < 4; i++) {
            GL11.glPushMatrix();
            GL11.glTranslated(0, i % 2 == 0 ? var.getCurrentAmount() : -var.getCurrentAmount(), 0);
            GL11.glRotated(90 * i, 0, 1, 0);
            Models.ModelGyro.renderPart("Outer");
            GL11.glPopMatrix();
        }
        
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
                
                renderItemSwitched(item, type, 0f, -.5f, 0f, .8F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 1f, .5f, 1f, .5F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, -.5f, 0f, .6F);
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
        
        Models.ModelGyro.renderPart("Base");
        Models.ModelGyro.renderPart("Center");
        
        GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
        
        for (int i = 0; i < 4; i++) {
            
            GL11.glPushMatrix();
            GL11.glTranslated(0, i % 2 == 0 ? var.getCurrentAmount() : -var.getCurrentAmount(), 0);
            GL11.glRotated(90 * i, 0, 1, 0);
            Models.ModelGyro.renderPart("Outer");
            GL11.glPopMatrix();
        }
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
