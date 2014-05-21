package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.BlockMetadata.MetadataWrapper;

public class MultiInterfaceWrapper extends MetadataWrapper {
    
    public MultiInterfaceWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        switch (metadata) {
        
            case 0:
                return new TileItemMultiInterface();
            case 1:
                return new TileFluidMultiInterface();
            case 2:
                return new TilePowerMultiInterface();
        }
        return null;
    }
}
