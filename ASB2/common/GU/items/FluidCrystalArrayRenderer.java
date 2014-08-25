package GU.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.Fluid;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.EventListener;
import GU.info.Models;
import GU.info.Reference;
import GU.render.noise.NoiseManager;
import GU.utils.UtilGU;
import UC.VariableIterator;

public class FluidCrystalArrayRenderer implements IItemRenderer {
    
    public static final FluidCrystalArrayRenderer instance = new FluidCrystalArrayRenderer();
    VariableIterator var = new VariableIterator(.0003, 0, .25);
    
    public FluidCrystalArrayRenderer() {
        
        EventListener.instance.VARIABLES.add(var);
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
                
                renderItemSwitched(item, type, 0f, 0f, 0f, .9F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, .5f, 1f, .5f, 1F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0f, 0f, 1.2F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                
                renderItemSwitched(item, type, -.5F, 1.2f, 0.5f, .5F);
                return;
            }
            
            default:
                return;
        }
    }
    
    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslatef(x, y, z);
        
        GL11.glScaled(scale, scale, scale);
        
        NoiseManager.bindImage();
        
        {
            final float secondCrystalScale = .5f, distanceFromCenter = .4f;
            
            GL11.glPushMatrix();
            GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
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
        
        {
            Fluid renderFluid = UtilGU.getFluid(item);
            
            if (renderFluid != null) {
                
                IIcon topIcon = renderFluid.getStillIcon();
                
                if (topIcon != null) {
                    
                    double renderHeight = var.getCurrentAmount();
                    
                    GL11.glPushMatrix();
                    
                    GL11.glDisable(GL11.GL_LIGHTING);
                    
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    
                    GL11.glTranslated(0, -.5 + renderHeight, 0);
                    
                    Tessellator tess = Tessellator.instance;
                    
                    UtilRender.bindBlockTextures();
                    
                    // IIcon icon = NoiseManager.instance.blockNoiseIcon;
                    
                    int renderBrightness = Reference.BRIGHT_BLOCK;
                    
                    double scaledHeight = .7;
                    double xScaledPos = .125, xScaledNeg = -xScaledPos, zScaledPos = .125, zScaledNeg = -zScaledPos;
                    int argb = renderFluid.getColor();
                    
                    if (renderFluid.isGaseous()) {
                        
                        GL11.glColor4d(argb & 0xFF, (argb >> 8) & 0xFF, (argb >> 16) & 0xFF, .6);
                        GL11.glRotated(Minecraft.getSystemTime() / 5, 0, -1, 0);
                    }
                    else {
                        
                        GL11.glColor4d(argb & 0xFF, (argb >> 8) & 0xFF, (argb >> 16) & 0xFF, .9);
                        GL11.glRotated(Minecraft.getSystemTime() / 17, 0, -1, 0);
                    }
                    
                    // Up
                    tess.startDrawingQuads();
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, topIcon.getMaxU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, topIcon.getMinU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, topIcon.getMinU(), topIcon.getMinV());
                    
                    tess.draw();
                    
                    // Down
                    tess.startDrawingQuads();
                    
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, topIcon.getMaxU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledPos, 0, zScaledPos, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, topIcon.getMinU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, topIcon.getMinU(), topIcon.getMinV());
                    
                    tess.draw();
                    
                    // North
                    tess.startDrawingQuads();
                    
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledPos, 0, zScaledPos, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, topIcon.getMaxU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, topIcon.getMinU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, topIcon.getMinU(), topIcon.getMaxV());
                    
                    tess.draw();
                    
                    // South
                    tess.startDrawingQuads();
                    
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, topIcon.getMinU(), topIcon.getMinV()); // South
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, topIcon.getMaxU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, topIcon.getMinU(), topIcon.getMaxV());
                    
                    tess.draw();
                    
                    // West
                    tess.startDrawingQuads();
                    
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, topIcon.getMinU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledPos, 0, zScaledPos, topIcon.getMinU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, topIcon.getMaxU(), topIcon.getMinV());
                    
                    tess.draw();
                    
                    // East
                    tess.startDrawingQuads();
                    
                    tess.setBrightness(renderBrightness);
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, topIcon.getMaxU(), topIcon.getMinV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, topIcon.getMaxU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, topIcon.getMinU(), topIcon.getMaxV());
                    tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, topIcon.getMinU(), topIcon.getMinV());
                    
                    tess.draw();
                    
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }
        }
        GL11.glPopMatrix();
    }
}