package GU.items;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.Fluid;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilRender;
import GU.EventListener;
import GU.info.Models;
import GU.info.Reference;
import GU.render.noise.NoiseManager;
import GU.utils.UtilGU;
import UC.VariableIterator;

public class ItemRenderers {
    
    public static class FluidCrystalArrayRenderer implements IItemRenderer {
        
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
                        } else {
                            
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
    
    public static class ColorModifierRenderer implements IItemRenderer {
        
        public static final ColorModifierRenderer instance = new ColorModifierRenderer();
        
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
                        double renderHeight = UtilItemStack.getNBTTagDouble(item, "renderHeight");
                        
                        {
                            boolean renderMovement = UtilItemStack.getNBTTagBoolean(item, "renderMovement");
                            
                            if (renderMovement) {
                                
                                renderHeight += .0003;
                                
                                if (renderHeight > .25) {
                                    
                                    UtilItemStack.setNBTTagBoolean(item, "renderMovement", false);
                                }
                            } else {
                                
                                renderHeight -= .0003;
                                
                                if (renderHeight < 0) {
                                    
                                    UtilItemStack.setNBTTagBoolean(item, "renderMovement", true);
                                }
                            }
                            UtilItemStack.setNBTTagDouble(item, "renderHeight", renderHeight);
                        }
                        
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
                        } else {
                            
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
    
    public static class ElectisCrystalShardRenderer implements IItemRenderer {
        
        public static final ElectisCrystalShardRenderer instance = new ElectisCrystalShardRenderer();
        
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
            
            color = Color.WHITE;
            
            NoiseManager.bindImage();
            
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
            
            Models.ModelElectisShard.renderPart("Center");
            GL11.glPopMatrix();
            
            color = color.darker().darker().darker();
            
            GL11.glPushMatrix();
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            Models.ModelElectisShard.renderPart("Outside");
            GL11.glPopMatrix();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
    
    public static class TeleporterRenderer implements IItemRenderer {
        
        public static final TeleporterRenderer instance = new TeleporterRenderer();
        
        public TeleporterRenderer() {
            
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
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .5F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .6F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .5f, .5F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
            
            Color color = Color.GREEN;
            
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glTranslatef(x, y, z);
            GL11.glScalef(scale, scale, scale);
            
            GL11.glPushMatrix();
            GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1, 1F);
            // UtilRender.renderTexture(Textures.FLAME_FOCUS_CUBE);
            
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            NoiseManager.bindImage();
            Models.ModelFlameFocus.renderPart("Cube");
            GL11.glPopMatrix();
            
            if (UtilItemStack.getNBTTagBoolean(item, "coordsSet")) {
                
                color = Color.DARK_GRAY.darker();
            } else {
                
                color = Color.WHITE.darker();
            }
            
            GL11.glColor3d(1, 1, 1);
            GL11.glPushMatrix();
            GL11.glRotatef(-Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1F, 1F);
            // UtilRender.renderTexture(Textures.FLAME_FOCUS_HEXAGON);
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            NoiseManager.bindImage();
            Models.ModelFlameFocus.renderPart("Hexagon");
            GL11.glPopMatrix();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
    
    public static class UtilityTabletRenderer implements IItemRenderer {
        
        public static final UtilityTabletRenderer instance = new UtilityTabletRenderer();
        
        public UtilityTabletRenderer() {
            
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
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            GL11.glTranslatef(x, y, z);
            
            GL11.glScaled(scale, scale, scale);
            
            NoiseManager.bindImage();
            
            Models.ModelUtilityTablet.renderAll();
            
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            
            GL11.glPopMatrix();
        }
    }
    
    public static class AdvancedStickRenderer implements IItemRenderer {
        
        public static final AdvancedStickRenderer instance = new AdvancedStickRenderer();
        VariableIterator var = new VariableIterator(.0003, 0, .25);
        
        public AdvancedStickRenderer() {
            
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
                    
                    renderItemSwitched(item, type, 0f, 0.034f, 0f, .39F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, .5f, 1f, .5f, 1F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .7F);
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
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            Models.ModelAdvancedStick.renderPart("Shaft");
            
            GL11.glColor4f(1, 1, 1, .8f);
            
            Models.ModelAdvancedStick.renderPart("Top_Cap");
            Models.ModelAdvancedStick.renderPart("Bottom_Cap");
            
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            
            GL11.glPopMatrix();
        }
    }
    
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