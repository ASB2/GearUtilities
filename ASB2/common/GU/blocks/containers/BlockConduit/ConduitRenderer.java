package GU.blocks.containers.BlockConduit;

import java.awt.Color;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.info.Textures;
import GU.render.noise.NoiseManager;

public class ConduitRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static ConduitRenderer instance = new ConduitRenderer();
    
    public ConduitRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        final float translationAmount = .04f;
        
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
        
        GL11.glScaled(.25, .25, .25);
        
        switch (((TileConduit) tileentity).getConduitType()) {
        
            case ITEM: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_ITEM);
                break;
            }
            case FLUID: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_FLUID);
                break;
            }
            case GU_POWER: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_GUPOWER);
                break;
            }
            default: {
                
                NoiseManager.bindImage();
                break;
            }
        }
        
        Models.ModelConduit.renderPart("Top");
        
        UtilRender.renderTexture(Textures.CONDUIT_BASE);
        
        Models.ModelConduit.renderPart("Base");
        
        GL11.glPushMatrix();
        NoiseManager.bindImage();
        GL11.glScaled(.8, .8, .8);
        Models.ModelConduit.renderPart("Base");
        GL11.glPopMatrix();
        
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
                
                renderItemSwitched(item, type, 0f, 0, 0f, .8F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 1f, .5f, 1f, .5F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0, 0f, .5F);
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
        
        switch (EnumConduitType.values()[item.getItemDamage()]) {
        
            case ITEM: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_ITEM);
                break;
            }
            case FLUID: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_FLUID);
                break;
            }
            case GU_POWER: {
                
                UtilRender.renderTexture(Textures.CONDUIT_TOP_GUPOWER);
                break;
            }
            default: {
                
                NoiseManager.bindImage();
                break;
            }
        }
        
        Models.ModelConduit.renderPart("Top");
        
        UtilRender.renderTexture(Textures.CONDUIT_BASE);
        
        Models.ModelConduit.renderPart("Base");
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
