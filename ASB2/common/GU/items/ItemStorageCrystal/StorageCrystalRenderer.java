package GU.items.ItemStorageCrystal;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

public class StorageCrystalRenderer implements IItemRenderer {

    private static RenderItem renderItem = new RenderItem();
    
    public StorageCrystalRenderer() {

    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        
        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
       
        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        ItemStorageCrystal crystal = (ItemStorageCrystal) item.getItem();
        Tessellator tess = Tessellator.instance;
        Icon icon = crystal.getIcon(item, 1);

        
        float scale = 2.2f;
        
        switch (type) {

            case ENTITY: {

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);

                GL11.glTranslatef(0, 1, 0);
                GL11.glScalef(1, 1, 1);

                if (crystal.getFluidStack(item) != null) {

                    FluidStack fluid = crystal.getFluidStack(item);

                    if (fluid.getFluid().getIcon() != null) {

                        // Icon texture = fluid.getFluid().getIcon();

                    }
                } else {

                    crystal.setFluidStack(item, new FluidStack(
                            FluidRegistry.WATER, 1000));
                }
                // TileEntityRenderer.instance.renderTileEntityAt(this.teTank,
                // 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
                return;
            }

            case EQUIPPED: {
                // renderItemSwitched(type, 0f, 0f + 1, 0f, .7F);
                return;
            }

            case INVENTORY: {

                if (crystal.getFluidStack(item) != null) {

                    FluidStack fluid = crystal.getFluidStack(item);

                    if (fluid.getFluid().getIcon() != null) {

                        Icon texture = fluid.getFluid().getIcon();
//                        GL11.glPushMatrix();
//                        GL11.glDisable(GL11.GL_LIGHTING);
//                        GL11.glScalef(scale, scale, scale);
//                        GL11.glTranslatef(0 - .5f, 0 - .5f, 0 - 0f);
//                        
//                        tess.startDrawingQuads();
//                        tess.addVertexWithUV(0, 0, 0, texture.getMaxU(), texture.getMaxV());
//                        tess.addVertexWithUV(1, 0, 0, texture.getMinU(), texture.getMaxV());
//                        tess.addVertexWithUV(1, 1, 0, texture.getMinU(), texture.getMinV());
//                        tess.addVertexWithUV(0, 1, 0, texture.getMaxU(), texture.getMinV());
//                        tess.draw();
//
//                        GL11.glEnable(GL11.GL_LIGHTING);
//                        GL11.glPopMatrix();
                    }
                }

                //Works but icon is rendering as if on a block face
//                GL11.glPushMatrix();
//                GL11.glDisable(GL11.GL_LIGHTING);
//
//                tess.startDrawingQuads();
//                tess.addVertexWithUV(0, 0, 0, icon.getMaxU(), icon.getMaxV());
//                tess.addVertexWithUV(1, 0, 0, icon.getMinU(), icon.getMaxV());
//                tess.addVertexWithUV(1, 1, 0, icon.getMinU(), icon.getMinV());
//                tess.addVertexWithUV(0, 1, 0, icon.getMaxU(), icon.getMinV());
//                tess.draw();
//
//                GL11.glEnable(GL11.GL_LIGHTING);
//                GL11.glPopMatrix();
                
                //Hello
//                GL11.glPushMatrix();
//                GL11.glDisable(GL11.GL_LIGHTING);
//                tess.startDrawingQuads();
//                
//                tess.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMaxV());
//                tess.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMaxV());
//                tess.addVertexWithUV(1, 1, 0, icon.getMaxU(), icon.getMinV());
//                tess.addVertexWithUV(0, 1, 0, icon.getMinU(), icon.getMinV());
//                tess.draw();
//
//                GL11.glEnable(GL11.GL_LIGHTING);
//                GL11.glPopMatrix();
                
                renderItem.renderIcon(0, 0, icon, 16, 16);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                // renderItemSwitched(type, 0f - .5F, 0f, 0f + .5F, .5F);
                return;
            }

            default:
                return;
        }
    }
}
