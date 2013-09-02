package GU.blocks.containers.BlockBlockBreaker;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class BlockBreakerRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y,  double z, float f) {

        TileBlockBreaker tile = (TileBlockBreaker)tileentity;
        
        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .5f, z + .5f);        
        GL11.glScalef(.5F, .5F, .5F);
        
        switch (tile.getOrientation()) {

            case UP: {
                
                break;
            }
            case DOWN: {
                
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            case NORTH: {

                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case SOUTH: {

                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case WEST: {

                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case EAST: {

                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {
                break;
            }
        }
        
        UtilRender.renderTexture(Textures.BLOCK_BREAKER);
        Models.ModelBlockBreaker.renderAll();

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

                renderItemSwitched(type, 0f, 0f - 1, 0f, .7F);
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

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

        UtilRender.renderTexture(Textures.BLOCK_BREAKER);
        Models.ModelBlockBreaker.renderAll();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
