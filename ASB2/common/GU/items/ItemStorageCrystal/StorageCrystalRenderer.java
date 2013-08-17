package GU.items.ItemStorageCrystal;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidRegistry;
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
        Tessellator t = Tessellator.instance;

        switch(type) {

            case ENTITY: {

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);

                GL11.glTranslatef(0,  1,  0);
                GL11.glScalef(1, 1, 1);

                if(crystal.getFluidStack(item) != null) {

                    FluidStack fluid = crystal.getFluidStack(item);

                    if(fluid.getFluid().getIcon() != null) {

                        Icon texture = fluid.getFluid().getIcon();

                        double uMin = texture.getInterpolatedU(0.0D);
                        double uMax = texture.getInterpolatedU(16.0D);
                        double vMin = texture.getInterpolatedV(0.0D);
                        double vMax = texture.getInterpolatedV(16.0D);
                        
                        double vHeight = vMax - vMin;
                        
                        t.startDrawingQuads();
                        t.addVertexWithUV(0, 0, 0, uMax, vMin);
                        t.addVertexWithUV(1, 0, 0, uMin, vMin);
                        t.addVertexWithUV(0, 1, 0, uMin, vMin + vHeight * 1);
                        t.addVertexWithUV(1, 1, 1, uMax, vMin + vHeight * 1);
                        t.draw();
                    }
                }
                else {
                    
                    crystal.setFluidStack(item, new FluidStack(FluidRegistry.WATER, 1000));
                }
                //                TileEntityRenderer.instance.renderTileEntityAt(this.teTank, 0.0D, 0.0D, 0.0D, 0.0F);
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
