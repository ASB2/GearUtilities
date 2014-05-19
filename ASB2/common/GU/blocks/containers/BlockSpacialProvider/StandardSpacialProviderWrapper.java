package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.BlockMetadata.MetadataWrapper;

public class StandardSpacialProviderWrapper extends MetadataWrapper {
    
    public StandardSpacialProviderWrapper() {
        
        this.setIconNames(new String[] { "BlockStandardSpacialProvider" });
        this.setDisplayName("Standard Spacial Provider");
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        return super.createNewTileEntity(var1, metadata);
    }
}
