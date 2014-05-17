package GU.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import ASB2.utils.UtilRender;
import GU.BlockRegistry;
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
            
            renderer.setRenderBounds(-.001, -.001, -.001, 1.001, 1.001, 1.001);
            UtilRender.renderStandardInvBlock(renderer, block, EnumInputIcon.NONE.getStateIcon());
        }
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        UtilRender.renderStandardInvBlock(renderer, block, metadata);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        
        if (block == BlockRegistry.SPACIAL_PROVIDER) {
            
            // TileEntity tile = world.getTileEntity(x, y, z);
            
            renderer.setRenderBounds(-.0015, -.0015, -.0015, 1.0015, 1.0015, 1.0015);
            
            // renderer.setRenderBounds(-.001, -.001, -.001, 1.001, 1.001,
            // 1.001);
            UtilRender.renderStandardInvBlock(renderer, block, EnumInputIcon.NONE.getStateIcon());
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
}
