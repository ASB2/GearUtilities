package GU.blocks.containers.BlockElectisDrill;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.render.noise.NoiseManager;

public class DrillRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static DrillRenderer instance = new DrillRenderer();
    
    public DrillRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        final float translationAmount = 1f;
        
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
        
        GL11.glScaled(1.5, 1.5, 1.5);
        NoiseManager.bindImage();
        boolean dark = ((TileElectisDrill) tileentity).darkCystal;
        
        {
            final float secondCrystalScale = .25f, distanceFromCenter = .3f;
            
            GL11.glPushMatrix();
            
            if (dark)
                GL11.glColor3d(.2, .2, .2);
            
            GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
            GL11.glTranslated(0, -.3, 0);
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(distanceFromCenter, -0.1, 0);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-distanceFromCenter, -0.1, 0);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        
        GL11.glScaled(.25, .25, .25);
        
        GL11.glColor3d(1, 1, 1);
        
        Models.ModelCrystal1.renderAll();
        
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
                
                renderItemSwitched(item, type, 0f, 1f, 0f, 1F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, .5f, 0f, 1.8F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                
                renderItemSwitched(item, type, -.5F, 1.4f, 0.5f, 1F);
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
        
        {
            final float secondCrystalScale = .25f, distanceFromCenter = .3f;
            
            GL11.glPushMatrix();
            GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
            GL11.glTranslated(0, -.3, 0);
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(distanceFromCenter, -0.1, 0);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -distanceFromCenter);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-distanceFromCenter, -0.1, 0);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(40F, 1F, 0F, 0F);
                
                Models.ModelElectisShard.renderAll();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        
        GL11.glScaled(.25, .25, .25);
        
        Models.ModelCrystal1.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
