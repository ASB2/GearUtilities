package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMultiInterface extends BlockMetadataContainerBase implements INoiseBlockRender {
    
    public BlockMultiInterface(Material material) {
        super(material);
        this.registerTile(TileItemMultiInterface.class);
        this.registerTile(TileFluidMultiInterface.class);
        this.registerTile(TilePowerMultiInterface.class);
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        switch (metadata) {
        
            case 0:
                return Color4i.GREEN;
            case 1:
                return Color4i.BLUE.brighter();
            case 2:
                return Color4i.YELLOW.brighter();
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        // TODO Auto-generated method stub
        return super.createNewTileEntity(var1, var2);
    }
}
