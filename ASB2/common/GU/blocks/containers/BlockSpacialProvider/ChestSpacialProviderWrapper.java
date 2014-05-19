package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import GU.blocks.BlockMetadata.MetadataWrapper;
import UC.math.vector.Vector3i;

public class ChestSpacialProviderWrapper extends MetadataWrapper {
    
    public ChestSpacialProviderWrapper() {
        
        this.setIconNames(new String[] { "BlockChestSpacialProvider" });
        this.setDisplayName("Chest Spacial Provider");
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        if (player.getHeldItem() == null) {
            
            UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.CHEST.createMultiBlock(world, new Vector3i(x, y, z)));
            return true;
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return super.createNewTileEntity(var1, metadata);
    }
}
