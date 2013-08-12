package GU.blocks.containers.BlockTestLaser;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.models.ModelLaser;
import GU.utils.UtilRender;

public class TestLaserRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    private ModelLaser model;

    public TestLaserRenderer() {

        model = new ModelLaser();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        if(tileentity instanceof TileTestLaser) {

                        TileTestLaser tile = (TileTestLaser)tileentity;

            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
            GL11.glScalef(1.0F, -1F, -1F);            

            UtilRender.renderTexture(Textures.TEST_LASER);
            
            switch(tile.getOrientation()) {
                
                case SOUTH: GL11.glRotatef(0F, 0F, 0F, 0F); break;
                case WEST : GL11.glRotatef(450F, 0F, 90F, 0F); break;
                case NORTH: GL11.glRotatef(180F, 0F, 180F, 0F); break;
                case EAST : GL11.glRotatef(450F, 0F, -90F, 0F); break;
                default : GL11.glRotatef(0F, 0F, 0F, 0F); break;
            }

            model.renderBase();
            model.renderHead();

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

                renderItemSwitched(type, 0f, 0f - 1, 0f, 1);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f - .9F, 0f, 1);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f - 1, 0f, 1);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, 1);
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

        UtilRender.renderTexture(Textures.TEST_LASER);

        model.renderHead();
        model.renderBase();


        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

