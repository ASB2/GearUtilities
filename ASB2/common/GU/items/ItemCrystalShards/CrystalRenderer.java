package GU.items.ItemCrystalShards;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.models.ModelCrystal;
import GU.utils.UtilRender;
import GU.*;

public class CrystalRenderer implements IItemRenderer {

    private ModelCrystal model = new ModelCrystal();

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch(type) {

            case ENTITY: {

                renderItemSwitched(type, 0f, 0f - 1, 0f, .7F, item);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F, item);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, 0f + .4F, 0f, 0f, .7F, item);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F, item);
                return;
            }

            default:return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, float x, float y, float z, float scale, ItemStack stack) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x,  y,  z);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(45, 0F, 0F, 0F);
        
        UtilRender.renderTexture(getTexture(stack));
        model.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public ResourceLocation getTexture(ItemStack stack) {

        if(stack.itemID == ItemRegistry.ItemAirCrystalShard.itemID) return Textures.CRYSTAL_AIR;
        if(stack.itemID == ItemRegistry.ItemEarthCrystalShard.itemID) return Textures.CRYSTAL_EARTH;
        if(stack.itemID == ItemRegistry.ItemFireCrystalShard.itemID) return Textures.CRYSTAL_FIRE;
        if(stack.itemID == ItemRegistry.ItemWaterCrystalShard.itemID) return Textures.CRYSTAL_WATER;
        if(stack.itemID == ItemRegistry.ItemEnergyCrystalShard.itemID) return Textures.CRYSTAL_ENERGY;

        return Textures.CRYSTAL_ITEM;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        // TODO Auto-generated method stub
        return true;
    }
}
