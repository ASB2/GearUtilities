package GU.blocks.containers.BlockLamp;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.models.ModelLamp;
import GU.utils.UtilRender;

public class LampRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    private ModelLamp model;

    public LampRenderer() {

        model = new ModelLamp();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        if(tileentity instanceof TileLamp) {

            TileLamp tile = (TileLamp)tileentity;

            GL11.glPushMatrix();      

            UtilRender.renderTexture(Textures.LAMP);

            switch (tile.getOrientation()) {

                case UP: {

                    GL11.glTranslatef((float)x + 0.5F, (float)y + .5F - .38F, (float)z + 0.5F);
                    break;
                }
                case DOWN: {

                    GL11.glTranslatef((float)x + 0.5F, (float)y + .5F + .38F, (float)z + 0.5F);
                    GL11.glRotatef(180F, 1F, 0F, 0F);
                    break;
                }
                case NORTH: {

                    GL11.glTranslatef((float)x + 0.5F, (float)y + .5F, (float)z + .8F + .08F);
                    GL11.glRotatef(-90F, 1F, 0F, 0F);
                    break;
                }
                case SOUTH: {

                    GL11.glTranslatef((float)x + 0.5F, (float)y + .5F, (float)z + .2F - .08F);
                    GL11.glRotatef(90F, 1F, 0F, 0F);
                    break;
                }
                case WEST: {

                    GL11.glTranslatef((float)x + 0.7F + .18F - .001F, (float)y + .5F, (float)z + 0.5F);
                    GL11.glRotatef(90F, 0F, 0F, 1F);
                    break;
                }
                case EAST: {

                    GL11.glTranslatef((float)x + .12F, (float)y + .5F, (float)z + 0.5F);
                    GL11.glRotatef(-90F, 0F, 0F, 1F);
                    break;
                }
                default: {
                    break;
                }
            }

            GL11.glScalef(.5F, .5F, .5F);      
            model.render();

            GL11.glPopMatrix();
        }
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

                renderItemSwitched(type, 0f, 1f, 0f, 1F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F);
                return;
            }

            default:return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x,  y,  z);
        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(180F, 0F, 180F, 0F);

        UtilRender.renderTexture(Textures.LAMP);

        model.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

