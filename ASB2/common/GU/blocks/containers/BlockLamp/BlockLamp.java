package GU.blocks.containers.BlockLamp;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilRender;
import GU.api.color.IColorable;
import GU.color.BlockColorable;
import GU.info.Reference;
import GU.models.BlockSimpleRenderer;
import GU.models.IBlockRender;

public class BlockLamp extends BlockColorable implements IBlockRender {

    Icon sides;
    Icon overlay;

    public BlockLamp(int id, Material material) {
        super(id, material);
        
        this.setLightOpacity(0);
        this.registerTile(TileLamp.class);
        useStandardRendering = false;
    }

    @Override
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {

        return ForgeDirection.VALID_DIRECTIONS;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        sides = iconRegister.registerIcon(Reference.MODDID + ":BlockLampSide");
        overlay = iconRegister.registerIcon(Reference.MODDID + ":BlockLampOverlay");
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        return ((TileLamp)world.getBlockTileEntity(x, y, z)).getLightValue();
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        return super.getIcon(side, metadata);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        float minWidth = 0, minHeight = 0;

        float maxWidth = 1, maxHeight = .25F;

        switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

            case DOWN: {

                this.setBlockBounds(minWidth, 1 - maxHeight, minWidth, maxWidth, 1, maxWidth);
                return;
            }

            case UP: {

                this.setBlockBounds(minWidth, minHeight, minWidth, maxWidth, maxHeight, maxWidth);
                break;
            }

            case NORTH: {

                this.setBlockBounds(minWidth, minWidth, 1 - maxHeight, maxWidth, maxWidth, maxWidth);
                break;
            }

            case SOUTH: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxWidth, maxWidth, maxHeight);
                break;
            }

            case WEST: {

                this.setBlockBounds(1 - maxHeight, minWidth, minWidth, maxWidth, maxWidth, maxWidth);
                break;
            }

            case EAST: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxHeight, maxWidth, maxWidth);
                break;
            }

            default: {

                this.setBlockBounds(0, 0, 0, 1, 1, 1);
                break;
            }
        }
    }

    @Override
    public int getRenderType() {

        return BlockSimpleRenderer.renderID;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileLamp();
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        
        renderer.setRenderBounds(0, 0, 0, 1, .24, 1);
        UtilRender.renderStandardInvBlock(renderer, block, overlay); 
        
        renderer.setRenderBounds(.001, 0, .01, .99, .22, .99);
        UtilRender.renderStandardInvBlock(renderer, block, meta); 
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof IColorable) {

            float minWidth = 0.001f, minHeight = 0.001f;
            float maxWidth = .999f, maxHeight = .22F;

            switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

                case DOWN: {

                    renderer.setRenderBounds(minWidth, maxWidth - maxHeight, minWidth, maxWidth, maxWidth, maxWidth);
                    break;
                }

                case UP: {

                    renderer.setRenderBounds(minWidth, minHeight, minWidth, maxWidth, maxHeight, maxWidth);
                    break;
                }

                case NORTH: {

                    renderer.setRenderBounds(minWidth, minWidth, maxWidth - maxHeight, maxWidth, maxWidth, maxWidth);
                    break;
                }

                case SOUTH: {

                    renderer.setRenderBounds(minWidth, minWidth, minWidth, maxWidth, maxWidth, maxHeight);
                    break;
                }

                case WEST: {

                    renderer.setRenderBounds(maxWidth - maxHeight, minWidth, minWidth, maxWidth, maxWidth, maxWidth);
                    break;
                }

                case EAST: {

                    renderer.setRenderBounds(minWidth, minWidth, minWidth, maxHeight, maxWidth, maxWidth);
                    break;
                }

                default: {

                    renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
                    break;
                }
            }

            for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

                if(block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {

                    Color color = ((IColorable) tile).getColor(direction);

                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(direction.ordinal(), 0), color.getRed(), color.getGreen(), color.getBlue(), 255, 15728864);
                }
            }

            float minWidthOut = 0f, minHeightOut = 0f;
            float maxWidthOut = 1f, maxHeightOut = .24F;

            switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

                case DOWN: {

                    renderer.setRenderBounds(minWidthOut, maxWidthOut - maxHeightOut, minWidthOut, maxWidthOut, maxWidthOut, maxWidthOut);
                    break;
                }

                case UP: {

                    renderer.setRenderBounds(minWidthOut, minHeightOut, minWidthOut, maxWidthOut, maxHeightOut, maxWidthOut);
                    break;
                }

                case NORTH: {

                    renderer.setRenderBounds(minWidthOut, minWidthOut, maxWidthOut - maxHeightOut, maxWidthOut, maxWidthOut, maxWidthOut);
                    break;
                }

                case SOUTH: {

                    renderer.setRenderBounds(minWidthOut, minWidthOut, minWidthOut, maxWidthOut, maxWidthOut, maxHeightOut);
                    break;
                }

                case WEST: {

                    renderer.setRenderBounds(maxWidthOut - maxHeightOut, minWidthOut, minWidthOut, maxWidthOut, maxWidthOut, maxWidthOut);
                    break;
                }

                case EAST: {

                    renderer.setRenderBounds(minWidthOut, minWidthOut, minWidthOut, maxHeightOut, maxWidthOut, maxWidthOut);
                    break;
                }

                default: {

                    renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
                    break;
                }
            }

            if (tile != null && tile instanceof IColorable) {

                for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

                    if(block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {

                        Color color = Color.WHITE;

                        UtilRender.renderFakeSide(renderer, block, direction, x, y, z, this.overlay, color.getRed(), color.getGreen(), color.getBlue(), 255, block.getLightValue(world, x, y, z));

                    }
                }
            }
        }
        return true;
    }
}
