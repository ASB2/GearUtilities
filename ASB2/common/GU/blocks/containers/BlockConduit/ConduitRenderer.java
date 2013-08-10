package GU.blocks.containers.BlockConduit;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.models.ModelConduit;
import GU.utils.UtilRender;

public class ConduitRenderer  extends TileEntitySpecialRenderer implements IItemRenderer {

    private ModelConduit model;

    public ConduitRenderer() {

        model = new ModelConduit();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        if(tileentity instanceof TileConduit) {

            TileConduit tile = (TileConduit)tileentity;

            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
            GL11.glScalef(1.0F, -1F, -1F);
            GL11.glRotatef(0F, 0F, 0F, 0F);

            UtilRender.renderTexture(Textures.CONDUIT);

            model.renderCenter();

            if(tile.renderDirection(ForgeDirection.DOWN)) {
                model.renderWireDOWN();
            }

            if(tile.renderDirection(ForgeDirection.UP)) {
                model.renderWireUP();
            }

            if(tile.renderDirection(ForgeDirection.WEST)) {
                model.renderWireWEST();
            }

            if(tile.renderDirection(ForgeDirection.EAST)) {
                model.renderWireEAST();
            }

            if(tile.renderDirection(ForgeDirection.SOUTH)) {
                model.renderWireSOUTH();
            }

            if(tile.renderDirection(ForgeDirection.NORTH)) {
                model.renderWireNORTH();
            }
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

        UtilRender.renderTexture(Textures.CONDUIT);

        model.renderCenter();

        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            model.renderWireNORTH();
            model.renderWireSOUTH();
        }
        else {
            
            model.renderWireEAST();
            model.renderWireWEST();
        }
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

