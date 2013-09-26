package GU.blocks.containers.BlockEnergyCube;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;
import GU.info.Variables;

public class EnergyCubeRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {

        double amount = .0;
        
        ForgeDirection direction = ForgeDirection.getOrientation(tileEntity.worldObj.getBlockMetadata((int)x, (int)y, (int)z));

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .5, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        GL11.glPushMatrix();        
        UtilRender.renderTexture(Textures.ENERGY_CUBE_CENTER);

        GL11.glRotatef(Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 1F, 0F, 1F);
        Models.ModelEnergyCube.renderPart("Center");
        GL11.glPopMatrix();

        for(ForgeDirection facing : ForgeDirection.VALID_DIRECTIONS) {

            switch(facing) {

                case NORTH: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_NORTH_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_NORTH);
                    }

                    GL11.glPushMatrix();   

                    GL11.glTranslated(0, 0, amount);


                    Models.ModelEnergyCube.renderPart("Front");  
                    GL11.glPopMatrix();
                    break;
                }

                case SOUTH: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_SOUTH_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_SOUTH);
                    }
                    
                    GL11.glPushMatrix();   

                    GL11.glTranslated(0, 0, -amount);
                    Models.ModelEnergyCube.renderPart("Back");  
                    GL11.glPopMatrix();
                    break;
                }

                case EAST: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_EAST_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_EAST);
                    }
                    
                    GL11.glPushMatrix();   

                    GL11.glTranslated(-amount, 0, 0);
                    
                    Models.ModelEnergyCube.renderPart("Right"); 
                    GL11.glPopMatrix();
                    break;
                }

                case WEST: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_WEST_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_WEST);
                    }
                    
                    GL11.glPushMatrix();   

                    GL11.glTranslated(amount, 0, 0);
                    
                    Models.ModelEnergyCube.renderPart("Left");
                    GL11.glPopMatrix();
                    break;
                }

                case UP: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_UP_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_UP);
                    }
                    
                    GL11.glPushMatrix();   

                    GL11.glTranslated(0, -amount, 0);
                    
                    Models.ModelEnergyCube.renderPart("Top");
                    GL11.glPopMatrix();
                    break;
                }

                case DOWN: {

                    if(facing == direction) {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_DOWN_EXPORTING);
                    }
                    else {

                        UtilRender.renderTexture(Textures.ENERGY_CUBE_DOWN);
                    }
                    
                    GL11.glPushMatrix();   

                    GL11.glTranslated(0, amount, 0);
                    Models.ModelEnergyCube.renderPart("Bottom");  
                    GL11.glPopMatrix();
                    break;
                }

                default: {
                    break;
                }
            }
        }        
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

                renderItemSwitched(item, type, 0f, .5f, 0f, 1f);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f - .1f, 0f, .5F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .9f, .5F);
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

        GL11.glPushMatrix();        
        UtilRender.renderTexture(Textures.ENERGY_CUBE_CENTER);

        GL11.glRotatef(Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 1F, 0F, 1F);
        Models.ModelEnergyCube.renderPart("Center");
        GL11.glPopMatrix();

        UtilRender.renderTexture(Textures.ENERGY_CUBE_NORTH);
        Models.ModelEnergyCube.renderPart("Front");     

        UtilRender.renderTexture(Textures.ENERGY_CUBE_SOUTH);
        Models.ModelEnergyCube.renderPart("Back");  

        UtilRender.renderTexture(Textures.ENERGY_CUBE_EAST);
        Models.ModelEnergyCube.renderPart("Right"); 

        UtilRender.renderTexture(Textures.ENERGY_CUBE_WEST);
        Models.ModelEnergyCube.renderPart("Left");

        UtilRender.renderTexture(Textures.ENERGY_CUBE_UP);
        Models.ModelEnergyCube.renderPart("Top");

        UtilRender.renderTexture(Textures.ENERGY_CUBE_DOWN);
        Models.ModelEnergyCube.renderPart("Bottom");     

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}