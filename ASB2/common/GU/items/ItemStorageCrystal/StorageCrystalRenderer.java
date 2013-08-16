package GU.items.ItemStorageCrystal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

public class StorageCrystalRenderer implements IItemRenderer {

    public StorageCrystalRenderer() {

    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        ItemStorageCrystal crystal = (ItemStorageCrystal)item.getItem();
        Tessellator tess = Tessellator.instance;

        switch(type) {

            case ENTITY: {

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);

                GL11.glTranslatef(0,  1,  0);
                GL11.glScalef(1, 1, 1);

                if(crystal.getFluidStack(item) != null) {

                    FluidStack fluid = crystal.getFluidStack(item);

                    if(fluid.getFluid().getIcon() != null) {
                        
                        TextureManager engine = Minecraft.getMinecraft().renderEngine;
                        
                        ItemRenderer.renderItemIn2D(tess, 1, 1, 1, 1, 1, 1, .5f);
                    }
                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
                return;
            }

            case EQUIPPED: {    

//                renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

//                renderItemSwitched(type, 0f, 0f, 0f, .6F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

//                renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F);
                return;
            }

            default:return;
        }
    }
}
