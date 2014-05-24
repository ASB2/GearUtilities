package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.Colorable.IColorableBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockMarker;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockSpacialProvider extends BlockMultiMetadataContainerBase implements INoiseBlockRender, IMultiBlockMarker, IColorableBlock {
    
    public BlockSpacialProvider(Material material) {
        super(material);
        
        this.registerTile(TileSpacialProvider.class);
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        switch (metadata) {
        
            case 0:
                return Color4i.WHITE;
            case 1:
                return Color4i.GREEN;
            case 2:
                return Color4i.RED.brighter();
            case 3:
                return Color4i.BLUE.brighter();
        }
        return null;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean isValid(World world, int x, int y, int z) {
        
        return true;
    }
    
    @Override
    public Color4i getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return this.getColor(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color4i color, ForgeDirection direction) {
        
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileSpacialProvider();
    }
}
