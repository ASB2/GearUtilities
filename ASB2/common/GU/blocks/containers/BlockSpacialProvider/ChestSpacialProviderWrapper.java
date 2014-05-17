package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.BlockMetadata.MetadataWrapper;

public class ChestSpacialProviderWrapper extends MetadataWrapper {
    
    public ChestSpacialProviderWrapper() {
        
        this.setIconNames(new String[] { "BlockChestSpacialProvider" });
        this.setDisplayName("Chest Spacial Provider");
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return super.createNewTileEntity(var1, metadata);
    }
}
