package GUOLD.render;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilRender;
import GUOLD.BlockRegistry;
import GUOLD.EnumState;
import GUOLD.api.color.IColorable;
import GUOLD.blocks.containers.TileBase;
import GUOLD.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockSimpleRenderer implements ISimpleBlockRenderingHandler {
    
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();
    
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        
        if (block == BlockRegistry.BlockEnhancedBricks) {
            
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
            UtilRender.renderStandardInvBlock(renderer, block, block.getIcon(0, 0), 255, 255, 255, 255);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderStandardInvBlock(renderer, block, ((BlockEnhancedBricks) block).overlay, 255, 255, 255, 255);
            return;
        }
        if (block == BlockRegistry.BlockSpacialProvider) {
            
            renderer.setRenderBounds(-.001, -.001, -.001, 1.001, 1.001, 1.001);
            UtilRender.renderStandardInvBlock(renderer, block, EnumState.NONE.getStateIcon());
        }
        if (block == BlockRegistry.BlockEtherealStone) {
            UtilRender.renderStandardInvBlock(renderer, block, NoiseManager.instance.iconTexture);
            return;
        }
        
        // if (block.blockID == BlockRegistry.BlockItemStand.blockID) {
        //
        // renderer.setRenderBounds(0, .85, 0, 1, 1, 1);
        // UtilRender.renderStandardInvBlock(renderer, block, meta);
        //
        // renderer.setRenderBounds(.25, .2, .25, .75, .85, .75);
        // UtilRender.renderStandardInvBlock(renderer, block, meta);
        //
        // renderer.setRenderBounds(0, 0, 0, 1, .2, 1);
        // UtilRender.renderStandardInvBlock(renderer, block, meta);
        // return;
        // }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        if (block == BlockRegistry.BlockEnhancedBricks) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof IColorable) {
                
                renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {
                        
                        Color color = ((IColorable) tile).getColor(direction);
                        
                        UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), 15728864);
                    }
                }
            }
            else {
                
                renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {
                        
                        Color color = Color.white;
                        
                        UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), 15728864);
                    }
                }
            }
            renderer.setRenderBounds(.0001, .0001, .0001, .9999, .9999, .9999);
            UtilRender.renderFakeBlock(renderer, block, x, y, z, ((BlockEnhancedBricks) block).overlay, 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
            
            return true;
        }
        
        if (block == BlockRegistry.BlockEtherealStone) {
            
            UtilRender.renderFakeBlock(renderer, block, x, y, z, NoiseManager.instance.iconTexture, 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
            
            return true;
        }
        
        // if (block.blockID == BlockRegistry.BlockItemStand.blockID) {
        //
        // renderer.setRenderBounds(0, .85, 0, 1, 1, 1);
        // UtilRender.renderMetadataBlock(block, world.getBlockMetadata(x, y,
        // z), x, y, z, renderer, world);
        //
        // renderer.setRenderBounds(.25, .2, .25, .75, .85, .75);
        // UtilRender.renderMetadataBlock(block, world.getBlockMetadata(x, y,
        // z), x, y, z, renderer, world);
        //
        // renderer.setRenderBounds(0, 0, 0, 1, .2, 1);
        // UtilRender.renderMetadataBlock(block, world.getBlockMetadata(x, y,
        // z), x, y, z, renderer, world);
        // return true;
        // }
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileBase) {
            
            if (((TileBase) tile).useSidesRendering) {
                
                renderer.setRenderBounds(-.0015, -.0015, -.0015, 1.0015, 1.0015, 1.0015);
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (((TileBase) tile).getSideStateArray(direction.ordinal()) != null) {
                        
                        UtilRender.renderFakeSide(renderer, block, direction, x, y, z, ((TileBase) tile).getSideStateArray(direction.ordinal()).getStateIcon(), 255, 255, 255, 255, 15728864);
                    }
                }
            }
        }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderStandardBlock(block, x, y, z);
        return true;
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public int getRenderId() {
        
        return renderID;
    }
}