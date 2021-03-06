package GU.blocks.containers.BlockInvertedPhotonicConverter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import GU.EventListener;
import GU.blocks.containers.BlockPhotonicConverter.FuelType;
import GU.info.Models;
import GU.render.noise.NoiseManager;
import UC.VariableIterator;

public class InvertedPhotonicConverterRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static InvertedPhotonicConverterRenderer instance = new InvertedPhotonicConverterRenderer();
    VariableIterator var = new VariableIterator(.0003, -.1, .1);
    
    public InvertedPhotonicConverterRenderer() {
        
        EventListener.instance.VARIABLES.add(var);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
//        GL11.glPushMatrix();
//        
//        final float translationAmount = .05f;
//        
//        switch (((TileBase) tileentity).getOrientation()) {
//        
//            case UP: {
//                
//                GL11.glTranslated(x + 0.5F, y + translationAmount, z + .5F);
//                break;
//            }
//            case DOWN: {
//                
//                GL11.glTranslated(x + 0.5F, y + (1 - translationAmount), z + .5F);
//                GL11.glRotatef(180F, 1F, 0F, 0F);
//                break;
//            }
//            
//            case SOUTH: {
//                
//                GL11.glTranslated(x + 0.5F, y + .5F, z + translationAmount);
//                GL11.glRotatef(90F, 1F, 0F, 0F);
//                break;
//            }
//            case NORTH: {
//                
//                GL11.glTranslated(x + 0.5F, y + .5F, z + (1 - translationAmount));
//                GL11.glRotatef(-90F, 1F, 0F, 0F);
//                break;
//            }
//            case WEST: {
//                
//                GL11.glTranslated(x + (1 - translationAmount), y + .5F, z + .5F);
//                GL11.glRotatef(90F, 0F, 0F, 1F);
//                break;
//            }
//            case EAST: {
//                
//                GL11.glTranslated(x + translationAmount, y + .5F, z + .5F);
//                GL11.glRotatef(-90F, 0F, 0F, 1F);
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        
//        GL11.glScaled(.4, .4, .4);
//        
//        // NoiseManager.bindImage();
//        
//        ((TileInvertedPhotonicConverter) tileentity).fuelType.bindTextureBase();
//        Color4i colorBase = ((TileInvertedPhotonicConverter) tileentity).fuelType.getBaseColor();
//        GL11.glColor4d(colorBase.getRed() / 255.0, colorBase.getGreen() / 255.0, colorBase.getBlue() / 255.0, colorBase.getAlpha() / 255.0);
//        Models.ModelGyro.renderPart("Base");
//        
//        ((TileInvertedPhotonicConverter) tileentity).fuelType.bindTextureCenter();
//        Color4i colorCenter = ((TileInvertedPhotonicConverter) tileentity).fuelType.getCenterColor();
//        GL11.glColor4d(colorCenter.getRed() / 255.0, colorCenter.getGreen() / 255.0, colorCenter.getBlue() / 255.0, colorCenter.getAlpha() / 255.0);
//        Models.ModelGyro.renderPart("Center");
//        
//        GL11.glPushMatrix();
//        NoiseManager.bindImage();
//        GL11.glScaled(.99, .99, .99);
//        Models.ModelGyro.renderPart("Center");
//        GL11.glPopMatrix();
//        
//        ((TileInvertedPhotonicConverter) tileentity).fuelType.bindTexturePanel();
//        GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
//        Color4i colorOuter = ((TileInvertedPhotonicConverter) tileentity).fuelType.getPanelColor();
//        GL11.glColor4d(colorOuter.getRed() / 255.0, colorOuter.getGreen() / 255.0, colorOuter.getBlue() / 255.0, colorOuter.getAlpha() / 255.0);
//        
//        for (int i = 0; i < 4; i++) {
//            GL11.glPushMatrix();
//            GL11.glTranslated(0, i % 2 == 0 ? var.getCurrentAmount() : -var.getCurrentAmount(), 0);
//            GL11.glRotated(90 * i, 0, 1, 0);
//            Models.ModelGyro.renderPart("Outer");
//            GL11.glPopMatrix();
//        }
//        
//        GL11.glPopMatrix();
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
        
        FuelType.NONE.bindTextureBase();
        Models.ModelGyro.renderPart("Base");
        
        FuelType.NONE.bindTextureCenter();
        Models.ModelGyro.renderPart("Center");
        
        GL11.glRotated(Minecraft.getSystemTime() / 17.0, 0, 1, 0);
        
        FuelType.NONE.bindTexturePanel();
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
