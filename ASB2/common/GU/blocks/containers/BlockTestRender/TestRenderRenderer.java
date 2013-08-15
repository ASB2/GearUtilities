package GU.blocks.containers.BlockTestRender;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import GU.models.ModelTest;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class TestRenderRenderer implements ISimpleBlockRenderingHandler  {

    public static int testRenderID = RenderingRegistry.getNextAvailableRenderId();

    ModelTest testModel = new ModelTest();
    
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderBlocks) {
                        
//        UtilRender.renderTexture(Textures.BLANK);
        
//        testModel.render();
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {

        return true;
    }

    @Override
    public int getRenderId() {

        return testRenderID;
    }


}
