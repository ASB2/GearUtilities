package GUOLD.blocks.containers.BlockEssenceDiffuser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GUOLD.blocks.containers.TileBase;
import GUOLD.info.Models;
import GUOLD.info.Reference;
import GUOLD.info.Textures;
import GUOLD.render.NoiseManager;

public class EssenceDiffuserRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static EssenceDiffuserRenderer instance = new EssenceDiffuserRenderer();
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        switch (((TileBase) tileentity).getOrientation()) {
        
            case UP: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .07f, (float) z + .5F);
                break;
            }
            case DOWN: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .93F, (float) z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            
            case SOUTH: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 0.07F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case NORTH: {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + .93F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case WEST: {
                
                GL11.glTranslatef((float) x + .94F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case EAST: {
                
                GL11.glTranslatef((float) x + .07F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {
                break;
            }
        }
        
        GL11.glScalef(.5f, .5f, .5f);
        
        UtilRender.renderTexture(Textures.ESSENCE_DIFFUSER_STAND);
        Models.ModelEssenceDiffuser.renderPart("Stand");
        
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0f, tileentity.yCoord % 2 == 0 ? -1F : 1f, 0F);
        
        GL11.glColor3d(((TileBase) tileentity).renderDoubles[0], ((TileBase) tileentity).renderDoubles[1], ((TileBase) tileentity).renderDoubles[2]);
        // UtilRender.renderTexture(Textures.ESSENCE_DIFFUSER_ROTATING);
        NoiseManager.bindImage();
        
        for (int i = 0; i < 4; i++) {
            
            switch (i) {
            
                case 0: {
                    
                    GL11.glRotatef(0F, 0F, 1F, 0F);
                    break;
                }
                
                case 1: {
                    
                    GL11.glRotatef(90F, 0F, 1F, 0F);
                    break;
                }
                
                case 2: {
                    
                    GL11.glRotatef(180F, 0F, 1F, 0F);
                    break;
                }
                
                case 3: {
                    
                    GL11.glRotatef(90F, 0F, -1F, 0F);
                    break;
                }
            }
            Models.ModelEssenceDiffuser.renderPart("Rotating");
        }
        
        GL11.glTranslated(0, -.5f, 0);
        
        for (int i = 0; i < 4; i++) {
            
            switch (i) {
            
                case 0: {
                    
                    GL11.glRotatef(0F, 0F, 1F, 0F);
                    break;
                }
                
                case 1: {
                    
                    GL11.glRotatef(90F, 0F, 1F, 0F);
                    break;
                }
                
                case 2: {
                    
                    GL11.glRotatef(180F, 0F, 1F, 0F);
                    break;
                }
                
                case 3: {
                    
                    GL11.glRotatef(90F, 0F, -1F, 0F);
                    break;
                }
            }
            Models.ModelEssenceDiffuser.renderPart("Rotating");
        }
        GL11.glEnable(GL11.GL_LIGHTING);
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
                
                renderItemSwitched(type, item, 0f, 0 - .3f, 0f, .9F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(type, item, 0f, 1, .5f, .7F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(type, item, 0f, -.4f, 0f, .7F);
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
        
        UtilRender.renderTexture(Textures.ESSENCE_DIFFUSER_STAND);
        Models.ModelEssenceDiffuser.renderPart("Stand");
        
        GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
        
        NoiseManager.bindImage();
        // UtilRender.renderTexture(Textures.ESSENCE_DIFFUSER_ROTATING);
        
        for (int i = 0; i < 4; i++) {
            
            switch (i) {
            
                case 0: {
                    
                    GL11.glRotatef(0F, 0F, 1F, 0F);
                    break;
                }
                
                case 1: {
                    
                    GL11.glRotatef(90F, 0F, 1F, 0F);
                    break;
                }
                
                case 2: {
                    
                    GL11.glRotatef(180F, 0F, 1F, 0F);
                    break;
                }
                
                case 3: {
                    
                    GL11.glRotatef(90F, 0F, -1F, 0F);
                    break;
                }
            }
            Models.ModelEssenceDiffuser.renderPart("Rotating");
        }
        
        GL11.glTranslatef(0, -.5f, 0);
        
        for (int i = 0; i < 4; i++) {
            
            switch (i) {
            
                case 0: {
                    
                    GL11.glRotatef(0F, 0F, 1F, 0F);
                    break;
                }
                
                case 1: {
                    
                    GL11.glRotatef(90F, 0F, 1F, 0F);
                    break;
                }
                
                case 2: {
                    
                    GL11.glRotatef(180F, 0F, 1F, 0F);
                    break;
                }
                
                case 3: {
                    
                    GL11.glRotatef(90F, 0F, -1F, 0F);
                    break;
                }
            }
            Models.ModelEssenceDiffuser.renderPart("Rotating");
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}