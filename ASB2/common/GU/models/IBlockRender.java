package GU.models;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockRender {

    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer);
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer);
}
