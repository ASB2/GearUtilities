package GU.items.ItemBloodStone;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class BloodStoneRenderer implements IItemRenderer {

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

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(item, type, 0f, 0f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(item, type, 0f - .5F, 0f, 0f + .5F, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {

        int rotate = UtilItemStack.getNBTTagInt(item, "rotate");
        rotate++;
        UtilItemStack.setNBTTagInt(item, "rotate", rotate);
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);

GL11.glRotatef(-rotate, 1F, 1,01F);

        UtilRender.renderTexture(Textures.BLOOD_STONE_HEXAGON);
        Models.ModelBloodStone.renderPart("Hexagon");
        
        GL11.glRotatef(rotate, 1F, 1,01F);

        UtilRender.renderTexture(Textures.BLOOD_STONE_CUBE);
        Models.ModelBloodStone.renderPart("Cube");

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}
