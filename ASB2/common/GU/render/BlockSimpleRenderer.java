package GU.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilRender;
import GU.BlockRegistry;
import GU.blocks.containers.BlockCreativeMetadata.TileCreativeFluid;
import GU.info.Reference;
import GU.render.noise.NoiseManager;
import UC.color.Color4i;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockSimpleRenderer implements ISimpleBlockRenderingHandler {
    
    public static BlockSimpleRenderer instance = new BlockSimpleRenderer();
    public static final int renderID;
    
    static {
        
        renderID = RenderingRegistry.getNextAvailableRenderId();
    }
    
    private BlockSimpleRenderer() {
        
    }
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        
        if (block == BlockRegistry.MULTI_BLOCK_PART_AIR) {
            return;
        }
        
        if (block instanceof INoiseBlockRender) {
            
            if (((INoiseBlockRender) block).canRender(metadata)) {
                
                renderer.setRenderBounds(.01, .01, .01, 1 - .01, 1 - .01, 1 - .01);
                
                Color4i color = ((INoiseBlockRender) block).getColor(metadata);
                
                if (color != null) {
                    
                    UtilRender.renderStandardInvBlock(renderer, block, NoiseManager.instance.blockNoiseIcon, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                } else
                    UtilRender.renderStandardInvBlock(renderer, block, NoiseManager.instance.blockNoiseIcon, 255, 255, 255, 255);
            }
        }
        
        if (block == BlockRegistry.SPATIAL_PROVIDER) {
            
            renderer.setRenderBounds(.0001, .0001, .0001, 1 - .0001, 1 - .0001, 1 - .0001);
            UtilRender.renderStandardInvBlock(renderer, block, metadata);
            
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderStandardInvBlock(renderer, block, EnumInputIcon.NONE.getStateIcon());
            return;
        }
        
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, metadata);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        // int metadata = world.getBlockMetadata(x, y, z);
        
        if (block instanceof INoiseBlockRender) {
            
            renderer.setRenderBounds(.005, .005, .005, 1 - .005, 1 - .005, 1 - .005);
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (((INoiseBlockRender) block).canRender(world, x, y, z, direction)) {
                    
                    Color4i color = ((INoiseBlockRender) block).getColor(world, x, y, z, direction);
                    
                    if (color == null) {
                        
                        color = Color4i.WHITE;
                    }
                    UtilRender.renderFakeSide(renderer, block, direction, x, y, z, NoiseManager.instance.blockNoiseIcon, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), Reference.BRIGHT_BLOCK);
                }
            }
            // return true;
        }
        
        if (block == BlockRegistry.SPATIAL_PROVIDER) {
            
            renderer.setRenderBounds(-.0015, -.0015, -.0015, 1.0015, 1.0015, 1.0015);
            UtilRender.renderFakeBlock(renderer, block, x, y, z, EnumInputIcon.NONE.getStateIcon(), 255, 255, 255, 255, Reference.BRIGHT_BLOCK);
        }
        
        if (block == BlockRegistry.CREATIVE_METADATA && world.getBlockMetadata(x, y, z) == 1) {
            
            TileCreativeFluid tile = (TileCreativeFluid) world.getTileEntity(x, y, z);
            
            if (tile != null) {
                
                renderer.setRenderBounds(.005, .005, .005, 1 - .005, 1 - .005, 1 - .005);
                
                if (tile.fluidToSave.getFluid() != null && tile.fluidToSave.getFluid().getFluid().getStillIcon() != null) {
                    
                    UtilRender.renderFakeBlock(renderer, block, x, y, z, tile.fluidToSave.getFluid().getFluid().getStillIcon(), 255, 255, 255, 255, Reference.BRIGHT_BLOCK);
                }
            }
        }
        // if (block == BlockRegistry.MULTI_INTERFACE && metadata == 0) {
        //
        // renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        //
        // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
        //
        // Color4i color = ((INoiseBlockRender) block).getColor(world, x, y, z,
        // direction);
        //
        // if (color == null) {
        //
        // color = Color4i.WHITE;
        // }
        // UtilRender.renderFakeSide(renderer, block, direction, x, y, z,
        // block.getIcon(direction.ordinal(), metadata), color.getRed(),
        // color.getGreen(), color.getBlue(), color.getAlpha(),
        // Reference.BRIGHT_BLOCK);
        // }
        // // return true;
        // }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderStandardBlock(block, x, y, z);
        
        return true;
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        
        return true;
    }
    
    @Override
    public int getRenderId() {
        
        return renderID;
    }
    
    public static interface INoiseBlockRender {
        
        Color4i getColor(int metadata);
        
        Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction);
        
        boolean canRender(int metadata);
        
        boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction);
    }
}
