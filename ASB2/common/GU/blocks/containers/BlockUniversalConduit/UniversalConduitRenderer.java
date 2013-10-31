package GU.blocks.containers.BlockUniversalConduit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilDirection;
import ASB2.utils.UtilRender;
import GU.api.network.IConductor;
import GU.info.Models;
import GU.info.Textures;
import GU.info.Variables;

public class UniversalConduitRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + .5f, y + .5, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(Textures.UNIVERSAL_CONDUIT_CENTER);
        
        GL11.glPushMatrix();
        GL11.glScalef(.5f, .5f, .5f);

        Models.ModelRhombicuboctahedron.renderAll();
        GL11.glPopMatrix();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            GL11.glPushMatrix();

            TileEntity tile = UtilDirection.translateDirectionToTile(tileEntity, tileEntity.worldObj, direction);
 
            if(tile != null) {

                if(tile instanceof IConductor) {
                    
                    switch (direction) {

                        case UP: {

                            GL11.glTranslated(0, .9, 0);
                            break;
                        }
                        case DOWN: {

                            GL11.glTranslated(0, -.9, 0);
                            GL11.glRotatef(180F, 1F, 0F, 0F);
                            break;
                        }
                        case NORTH: {

                            GL11.glTranslated(0, 0, -.9);
                            GL11.glRotatef(-90F, 1F, 0F, 0F);
                            break;
                        }
                        case SOUTH: {

                            GL11.glTranslated(0, 0, .9);
                            GL11.glRotatef(90F, 1F, 0F, 0F);
                            break;
                        }
                        case WEST: {

                            GL11.glTranslated(-0.9, 0, 0);
                            GL11.glRotatef(90F, 0F, 0F, 1F);
                            break;
                        }
                        case EAST: {

                            GL11.glTranslated(0.9, 0, 0);
                            GL11.glRotatef(-90F, 0F, 0F, 1F);
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    GL11.glPushMatrix();

                    GL11.glScalef(.5f, .5f, .5f);
                    UtilRender.renderTexture(Textures.UNIVERSAL_CONDUIT);
                    Models.ModelOctogon.renderAll();

                    GL11.glPopMatrix();
                }
            }
            GL11.glPopMatrix();
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

                renderItemSwitched(item, type, 0f, 0f - .1f, 0f, .6F);
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
        UtilRender.renderTexture(Textures.UNIVERSAL_CONDUIT_CENTER);
        GL11.glScalef(.5f, .5f, .5f);
        GL11.glRotatef(-Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 1F, 1F, 1F);
        Models.ModelRhombicuboctahedron.renderAll();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}