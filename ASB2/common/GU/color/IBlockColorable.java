package GU.color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockColorable {

    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer);
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer);
}
