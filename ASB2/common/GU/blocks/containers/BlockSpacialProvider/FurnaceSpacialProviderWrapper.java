package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.BlockMetadata.MetadataWrapper;

public class FurnaceSpacialProviderWrapper extends MetadataWrapper {
    
    public FurnaceSpacialProviderWrapper() {
        
        this.setIconNames(new String[] { "BlockFurnaceSpacialProvider" });
        this.setDisplayName("Furnace Spacial Provider");
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return super.createNewTileEntity(var1, metadata);
    }
}
