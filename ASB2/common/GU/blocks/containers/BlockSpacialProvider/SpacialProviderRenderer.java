package GU.blocks.containers.BlockSpacialProvider;

import java.util.Set;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import GU.multiblock.MultiBlockTank;

public class SpacialProviderRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

    public static SpacialProviderRenderer instance = new SpacialProviderRenderer();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

        if (tile instanceof TileFluidSpacialProvider) {

            TileFluidSpacialProvider part = (TileFluidSpacialProvider) tile;

            Set<MultiBlockTank> multiBlocks = part.fluidMultiBlock;

            if (!multiBlocks.isEmpty()) {

                // for (MultiBlockTank multiBlock : multiBlocks) {

//                GL11.glPushMatrix();
//
//                GL11.glTranslated(x, y, z);
//                UtilRender.renderBlockInstance.renderFaceXNeg(Block.grass, x, y+1, z, EnumState.BOTH.getStateIcon());
//                UtilRender.renderBlockInstance.renderFaceXPos(Block.stone, x, y+1, z, EnumState.BOTH.getStateIcon());
//
//                UtilRender.renderBlockInstance.renderFaceYNeg(Block.stone, x, y+1, z, EnumState.BOTH.getStateIcon());
//                UtilRender.renderBlockInstance.renderFaceYPos(Block.stone, x, y+1, z, EnumState.BOTH.getStateIcon());
//
//                UtilRender.renderBlockInstance.renderFaceZNeg(Block.stone, x, y+1, z, EnumState.BOTH.getStateIcon());
//                UtilRender.renderBlockInstance.renderFaceZPos(Block.stone, x, y+1, z, EnumState.BOTH.getStateIcon());
//
//                GL11.glPopMatrix();
                // }
            }
        }

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