package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import GU.blocks.BlockMetadata.MetadataWrapper;
import UC.math.vector.Vector3i;
import GU.api.multiblock.MultiBlockAbstract.*;

public class SpacialProviderWrapper extends MetadataWrapper {
    
    public SpacialProviderWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
//        if (tile != null && tile instanceof IMultiBlockPart && ((IMultiBlockPart) tile).getMultiBlocks().isEmpty()) {
            
            if (player.getHeldItem() == null) {
                
                switch (world.getBlockMetadata(x, y, z)) {
                
                    case 0: {
                        
                        UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.STANDARD.createMultiBlock(world, new Vector3i(x, y, z)));
                        break;
                    }
                    
                    case 1: {
                        
                        UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.CHEST.createMultiBlock(world, new Vector3i(x, y, z)));
                        break;
                    }
                    case 2: {
                        
                        UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.FURNACE.createMultiBlock(world, new Vector3i(x, y, z)));
                        break;
                    }
                    case 3: {
                        
                        UtilEntity.sendChatToPlayer(player, EnumMultiBlockType.TANK.createMultiBlock(world, new Vector3i(x, y, z)));
                        break;
                    }
                }
                return true;
//            }
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return new TileSpacialProvider();
    }
}
