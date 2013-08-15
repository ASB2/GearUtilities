package GU.blocks.containers.BlockCreationTable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.utils.UtilRender;

public class CreationTableRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        if(!(tileentity instanceof TileCreationTable)) {

            GL11.glPushMatrix();      
            GL11.glDisable(GL11.GL_LIGHTING);
            UtilRender.renderTexture(Textures.CREATION_TABLE);

            GL11.glTranslated(x + .5f,  y + .37f,  z + .5f);

//            Models.ModelCreationTable.render();
            
            GL11.glDisable(GL11.GL_LIGHTING);
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

                renderItemSwitched(type, 0f, 0f - 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f, 0f, 0f, 1F);
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

        UtilRender.renderTexture(Textures.CREATION_TABLE);

//        Models.ModelCreationTable.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

