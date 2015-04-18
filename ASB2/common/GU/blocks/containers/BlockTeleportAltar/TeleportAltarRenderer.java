package GU.blocks.containers.BlockTeleportAltar;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.render.noise.NoiseManager;

public class TeleportAltarRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static TeleportAltarRenderer instance = new TeleportAltarRenderer();
    
    public TeleportAltarRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x + 0.5F, y + .5, z + .5F);
        
        NoiseManager.bindImage();
        
        GL11.glScaled(1.5, 1.5, 1.5);
        
        {
            final float secondCrystalScale = .25f, distanceFromCenter = .3f;
            
            GL11.glPushMatrix();
            
            if (((TileTeleportAltar) tileentity).coordsSet)
                GL11.glColor3d(.2, .2, .2);
            
            GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
            GL11.glTranslated(0, .1, 0);
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(distanceFromCenter, -0.1, 0);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -distanceFromCenter);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-distanceFromCenter, -0.1, 0);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        
        GL11.glColor3d(1, 1, 1);
        GL11.glScaled(.125, .125, .125);
        Models.ModelCrystal2.renderAll();
        
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
                
                renderItemSwitched(item, type, 0f, 0, 0f, 1F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 1f, .5f, 1f, 1F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0, 0f, 1F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                
                renderItemSwitched(item, type, -.5F, 1f, 0.5f, .7F);
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
        
        //
        
        NoiseManager.bindImage();
        
        GL11.glScaled(1.5, 1.5, 1.5);
        
        {
            final float secondCrystalScale = .25f, distanceFromCenter = .4f;
            
            GL11.glPushMatrix();
            GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
            GL11.glTranslated(0, .1, 0);
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(distanceFromCenter, -0.1, 0);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -distanceFromCenter);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-distanceFromCenter, -0.1, 0);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        
        GL11.glScaled(.125, .125, .125);
        Models.ModelCrystal2.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
