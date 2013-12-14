package GU.render;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilRender;
import GU.BlockRegistry;
import GU.api.color.IColorable;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockEnhancedBricks.BlockEnhancedBricks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockSimpleRenderer implements ISimpleBlockRenderingHandler {
    
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();
    
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        
        if (block.blockID == BlockRegistry.BlockEnhancedBricks.blockID) {
            
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
            UtilRender.renderStandardInvBlock(renderer, block, block.getIcon(0, 0), 255, 255, 255, 255);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderStandardInvBlock(renderer, block, ((BlockEnhancedBricks) block).overlay, 255, 255, 255, 255);
            return;
        }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, meta);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        if (block.blockID == BlockRegistry.BlockEnhancedBricks.blockID) {
            
            TileEntity tile = world.getBlockTileEntity(x, y, z);
            
            if (tile != null && tile instanceof IColorable) {
                
                renderer.setRenderBounds(0.0001, 0.0001, 0.0001, .9999, .9999, .9999);
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (block.shouldSideBeRendered(world, x, y, z, direction.ordinal())) {
                        
                        Color color = ((IColorable) tile).getColor(direction);
                        
                        UtilRender.renderFakeSide(renderer, block, direction, x, y, z, block.getIcon(0, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), 15728864);
                    }
                }
            } else {
                
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
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileBase) {
            
            renderer.setRenderBounds(-.001, -.001, -.001, 1.001, 1.001, 1.001);
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (((TileBase) tile).getSideStateArray(direction.ordinal()) != null) {
                    
                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, ((TileBase) tile).getSideStateArray(direction.ordinal()).getStateIcon(), 255, 255, 255, 255, 15728864);
                }
            }
        }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderMetadataBlock(block, world.getBlockMetadata(x, y, z), x, y, z, renderer, world);
        return true;
    }
    
    @Override
    public boolean shouldRender3DInInventory() {
        
        return true;
    }
    
    @Override
    public int getRenderId() {
        
        return renderID;
    }
}