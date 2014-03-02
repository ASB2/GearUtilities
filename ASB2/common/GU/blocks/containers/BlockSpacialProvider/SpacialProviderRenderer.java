package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class SpacialProviderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public static SpacialProviderRenderer instance = new SpacialProviderRenderer();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

        if (tile instanceof TileFluidSpacialProvider) {

//            TileFluidSpacialProvider part = (TileFluidSpacialProvider) tile;

//            Set<MultiBlockTank> multiBlocks = part.fluidMultiBlock;

//            if (!multiBlocks.isEmpty()) {
//
//                GL11.glPushMatrix();
//
//                for (MultiBlockTank multi : multiBlocks) {
//
//                    Cuboid size = multi.getSize();
//
//                    if (size.getCore().intEquals(new Vector3(tile))) {
//
//                        FluidStack fluid = multi.fluidTank.getFluid();
//
//                        if (fluid != null) {
//
//                            GL11.glTranslated(x, y + 1, z);
//                            UtilRender.renderCube(0, 0, 0, 1, 1, 1, BlockRegistry.BlockSpacialProvider, fluid.getFluid().getFlowingIcon(), 0, Reference.BRIGHT_BLOCK);
//
//                        } else {
//
//                            GL11.glTranslated(x, y + 1, z);
//                            UtilRender.renderCube(0, 0, 0, 1, 1, 1, BlockRegistry.BlockSpacialProvider, FluidRegistry.WATER.getFlowingIcon(), 0, Reference.BRIGHT_BLOCK);
//
//                        }
//                    }
//                }
//                GL11.glPopMatrix();
//            }
        }
    }

    public static void setGLColorFromInt(int color) {
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {

            case ENTITY: {

                renderItemSwitched(type, item, 0f, .7f, 0f, .5F);
                return;
            }

            case EQUIPPED: {

                renderItemSwitched(type, item, 0f, 1, .5f, .7F);
                return;
            }

            case INVENTORY: {

                renderItemSwitched(type, item, 0f, .5f, 0f, .5F);
                return;
            }

            case EQUIPPED_FIRST_PERSON: {

                renderItemSwitched(type, item, -.5F, 1f, .5F, .5F);
                return;
            }

            default:
                return;
        }
    }

    private void renderItemSwitched(ItemRenderType type, ItemStack item, float x, float y, float z, float scale) {

    }
}