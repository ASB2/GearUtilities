package GU.blocks.containers.BlockGlassPipe;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilDirection;
import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class GlassPipeRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5, y + .5, z + .5);
        GL11.glScalef(.7f, .7f, .7f);

        UtilRender.renderTexture(Textures.GLASS_PIPE_CENTER);
        Models.ModelGlassPipe.renderPart("Center");

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(tileentity, tileentity.worldObj, direction);

            if(tile != null && tile instanceof IFluidHandler) {

                switch(direction) {

                    case UP: {

                        Models.ModelGlassPipe.renderPart("Cable");
                        break;
                    }
                    case DOWN: {

                        GL11.glPushMatrix();

                        GL11.glRotated(180, 0, 0, 0);
                        UtilRender.renderTexture(Textures.GLASS_PIPE_DOWN);
                        Models.ModelGlassPipe.renderPart("Cable");
                        
                        GL11.glPopMatrix();
                        break;
                    }
                    case NORTH: {

                        GL11.glPushMatrix();

                        GL11.glRotated(-90, 1, 0, 0);
                        UtilRender.renderTexture(Textures.GLASS_PIPE_DOWN);
                        Models.ModelGlassPipe.renderPart("Cable");
                        
                        GL11.glPopMatrix();
                        break;
                    }
                    case SOUTH: {

                        GL11.glPushMatrix();

                        GL11.glRotated(90, 1, 0, 0);
                        UtilRender.renderTexture(Textures.GLASS_PIPE_DOWN);
                        Models.ModelGlassPipe.renderPart("Cable");
                        
                        GL11.glPopMatrix();
                        break;
                    }
                    case WEST: {

                        GL11.glPushMatrix();

                        GL11.glRotated(90, 0, 0, 1);
                        UtilRender.renderTexture(Textures.GLASS_PIPE_DOWN);
                        Models.ModelGlassPipe.renderPart("Cable");
                        
                        GL11.glPopMatrix();
                        break;
                    }
                    case EAST: {

                        GL11.glPushMatrix();

                        GL11.glRotated(90, 0, 0, -1);
                        UtilRender.renderTexture(Textures.GLASS_PIPE_DOWN);
                        Models.ModelGlassPipe.renderPart("Cable");
                        
                        GL11.glPopMatrix();
                        break;
                    }
                    case UNKNOWN:
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

        switch(type) {

            case ENTITY: {

                renderItemSwitched(type, 0f, 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 1, .5f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, .5F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        UtilRender.renderTexture(Textures.BLACK);
        Models.ModelGlassPipe.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}