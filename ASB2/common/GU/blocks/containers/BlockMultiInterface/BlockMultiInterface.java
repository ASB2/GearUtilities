package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMultiInterface extends BlockMultiMetadataContainerBase implements INoiseBlockRender {
    
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
                return Color4i.GREEN.clone().setAlpha(128);
            case 1:
                return Color4i.BLUE.brighter();
            case 2:
                return Color4i.YELLOW.brighter();
            case 3:
                return new Color4i(50, 90, 0).darker();
            case 4:
                return Color4i.RED.brighter();
            case 5:
                return new Color4i(190, 100, 0);
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        return tile != null && tile instanceof IColorableTile ? ((IColorableTile) tile).getColor(direction) : getColor(world.getBlockMetadata(x, y, z));
        // return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean canRender(int metadata) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean canRender(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        // TODO Auto-generated method stub
        return true;
    }
}
