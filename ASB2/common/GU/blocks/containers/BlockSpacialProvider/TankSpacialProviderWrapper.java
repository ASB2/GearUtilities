package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.BlockMetadata.MetadataWrapper;

public class TankSpacialProviderWrapper extends MetadataWrapper {
    
    public TankSpacialProviderWrapper() {
        
        this.setIconNames(new String[] { "BlockTankSpacialProvider" });
        this.setDisplayName("Tank Spacial Provider");
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return super.createNewTileEntity(var1, metadata);
    }
}
