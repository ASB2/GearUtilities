package GU.blocks.containers.BlockSender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilRender;
import ASB2.vector.Vector3;
import GU.info.Models;
import GU.info.Textures;
import GU.info.Variables;

public class SenderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        TileSender tile = (TileSender) tileentity;

        GL11.glPushMatrix();

        GL11.glColor3d(1, 1, 1);

        switch(ForgeDirection.getOrientation(new Vector3(tileentity).getBlockMetadata(tileentity.worldObj))) {

            case UP: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .07f, (float) z + .5F);
                break;
            }
            case DOWN: {

                GL11.glTranslatef((float) x + 0.5F, (float) y + .94F, (float) z + .5F);
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

        UtilRender.renderTexture(Textures.ITEM_SENDER);
        Models.ModelSender.renderPart("Panel");

        GL11.glRotatef(Minecraft.getSystemTime() / (Variables.ANIMATION_SPEED + 10), 0F, 1F, 0F);

        for(int i = 0; i < 4; i++) {

            switch(i) {

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

            switch(tile.getMode()) {

                case 1: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_1);
                    Models.ModelSender.renderPart("Render_Addition_1");
                    break;
                }

                case 2: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_2);
                    Models.ModelSender.renderPart("Render_Addition_2");
                    break;
                }

                case 3: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_3);
                    Models.ModelSender.renderPart("Render_Addition_3");
                    break;
                }

                case 4: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_4);
                    Models.ModelSender.renderPart("Render_Addition_4");
                    break;
                }

                case 5: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_5);
                    Models.ModelSender.renderPart("Render_Addition_5");
                    break;
                }

                case 6: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_6);
                    Models.ModelSender.renderPart("Render_Addition_6");
                    break;
                }

                case 7: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_7);
                    Models.ModelSender.renderPart("Render_Addition_7");
                    break;
                }
            }
        }
        
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        GL11.glColor3d(1, 1, 1);
        
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        
        switch(tile.getOrientation()) {

            case DOWN: {

                UtilRender.renderBox(x, y + 1, z, 1, -TileSender.MAX_DISTANCE, 1);
                break;
            }
            
            case UP: {

                UtilRender.renderBox(x, y, z, 1, TileSender.MAX_DISTANCE, 1);
                break;
            }
            
            case NORTH: {

                UtilRender.renderBox(x, y, z + 1, 1, 1, -TileSender.MAX_DISTANCE);
                break;
            }
            
            case SOUTH: {

                UtilRender.renderBox(x, y, z, 1, 1, TileSender.MAX_DISTANCE);
                break;
            }
            
            case WEST: {

                UtilRender.renderBox(x + 1, y, z, -TileSender.MAX_DISTANCE, 1, 1);
                break;
            }
            
            case EAST: {

                UtilRender.renderBox(x, y, z, TileSender.MAX_DISTANCE, 1, 1);
                break;
            }
            
            case UNKNOWN: break;
        }
        
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        
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

                renderItemSwitched(type, item, 0f, 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, item, 0f, 1, .5f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, item, 0f, 0f, 0f, .5F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, item, 0f - .5F, 0f, 0f + .5F, .5F);
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

        UtilRender.renderTexture(Textures.ITEM_SENDER);
        Models.ModelSender.renderPart("Panel");

        GL11.glRotatef(Minecraft.getSystemTime() / (Variables.ANIMATION_SPEED + 10), 0F, 1F, 0F);

        for(int i = 0; i < 4; i++) {

            switch(i) {

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

            switch(UtilItemStack.getNBTTagInt(item, "mode")) {

                case 1: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_1);
                    Models.ModelSender.renderPart("Render_Addition_1");
                    break;
                }

                case 2: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_2);
                    Models.ModelSender.renderPart("Render_Addition_2");
                    break;
                }

                case 3: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_3);
                    Models.ModelSender.renderPart("Render_Addition_3");
                    break;
                }

                case 4: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_4);
                    Models.ModelSender.renderPart("Render_Addition_4");
                    break;
                }

                case 5: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_5);
                    Models.ModelSender.renderPart("Render_Addition_5");
                    break;
                }

                case 6: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_6);
                    Models.ModelSender.renderPart("Render_Addition_6");
                    break;
                }

                case 7: {

                    UtilRender.renderTexture(Textures.SENDER_ADDITION_7);
                    Models.ModelSender.renderPart("Render_Addition_7");
                    break;
                }
            }
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}