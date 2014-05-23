package GU.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import ASB2.utils.UtilRender;
import GU.BlockRegistry;
import GU.info.Reference;
import GU.render.noise.NoiseManager;
import UC.color.Color4f;
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
        
        if (block == BlockRegistry.SPACIAL_PROVIDER) {
            
            renderer.setRenderBounds(.0001, .0001, .0001, 1 - .0001, 1 - .0001, 1 - .0001);
            UtilRender.renderStandardInvBlock(renderer, block, metadata);
            
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            UtilRender.renderStandardInvBlock(renderer, block, EnumInputIcon.NONE.getStateIcon());
            return;
        }
        
        if (block == BlockRegistry.MULTI_INTERFACE) {
            
            renderer.setRenderBounds(.0001, .0001, .0001, 1 - .0001, 1 - .0001, 1 - .0001);
            
            switch (metadata) {
            
            }
            UtilRender.renderStandardInvBlock(renderer, block, NoiseManager.instance.blockNoiseIcon, 0, 255, 0, 255);
            // return;
        }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, metadata);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        if (block == BlockRegistry.SPACIAL_PROVIDER) {
            
            // TileEntity tile = world.getTileEntity(x, y, z);w
            
            renderer.setRenderBounds(-.0015, -.0015, -.0015, 1.0015, 1.0015, 1.0015);
            
            // renderer.setRenderBounds(-.001, -.001, -.001, 1.001, 1.001,
            // 1.001);
            UtilRender.renderFakeBlock(renderer, block, x, y, z, EnumInputIcon.NONE.getStateIcon(), 255, 255, 255, 255, Reference.BRIGHT_BLOCK);
        }
        
        if (block == BlockRegistry.MULTI_INTERFACE) {
            
            renderer.setRenderBounds(.01, .01, .01, 1 - .01, 1 - .01, 1 - .01);
            
            if (block instanceof INoiseBlockRender) {
                
                Color4f color = ((INoiseBlockRender) block).getColor(world, x, y, z);
                
                UtilRender.renderFakeBlock(renderer, block, x, y, z, NoiseManager.instance.blockNoiseIcon, (int) color.getRed(), (int) color.getGreen(), (int) color.getBlue(), (int) color.getAlpha(), Reference.BRIGHT_BLOCK);
            }
            else
                UtilRender.renderFakeBlock(renderer, block, x, y, z, NoiseManager.instance.blockNoiseIcon, 0, 255, 0, 255, Reference.BRIGHT_BLOCK);
            
             return true;
        }
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
        
        Color4f getColor(int metadata);
        
        Color4f getColor(IBlockAccess world, int x, int y, int z);
    }
}
