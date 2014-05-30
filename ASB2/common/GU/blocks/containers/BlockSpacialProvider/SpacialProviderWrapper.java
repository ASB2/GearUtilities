package GU.blocks.containers.BlockSpacialProvider;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.BlockMetadata.MetadataWrapper;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.MultiBlockBase;
import UC.math.vector.Vector3i;

public class SpacialProviderWrapper extends MetadataWrapper {
    
    public SpacialProviderWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        if (!world.isRemote) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof TileMultiBase) {
                
                List<IMultiBlock> multiList = ((TileMultiBase) tile).getMultiBlocks();
                
                if (multiList.isEmpty()) {
                    
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
                    }
                }
                else {
                    
                    for (IMultiBlock multi : multiList) {
                        
                        if (multi instanceof MultiBlockBase) {
                            
                            ((MultiBlockBase) multi).onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        // TODO Auto-generated method stub
        return new TileSpacialProvider();
    }
}
