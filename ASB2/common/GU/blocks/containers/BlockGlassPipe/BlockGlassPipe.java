package GU.blocks.containers.BlockGlassPipe;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilRender;
import GU.blocks.containers.ContainerBase;
import GU.models.BlockSimpleRenderer;
import GU.models.IBlockRender;

public class BlockGlassPipe extends ContainerBase implements IBlockRender {

    public BlockGlassPipe(int id, Material material) {
        super(id, material);

        this.registerTile(TileGlassPipe.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileGlassPipe();
    }

    @Override
    public int getRenderType() {

        return BlockSimpleRenderer.renderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {

        UtilRender.renderStandardInvBlock(renderer, block, meta);

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileGlassPipe tile = (TileGlassPipe)world.getBlockTileEntity(x, y, z);

            if(!tile.importing[direction.ordinal()]) {

                UtilRender.renderFakeSide(renderer, block, direction, x, y, z, Block.waterStill.getIcon(0, 0), 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
            }
            else {
                
                UtilRender.renderFakeSide(renderer, block, direction, x, y, z, Block.lavaStill.getIcon(0, 0), 255, 255, 255, 255, block.getMixedBrightnessForBlock(world, x, y, z));
            }
        }
        return false;
    }
}
