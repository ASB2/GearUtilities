package GU.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;

public class DestructorWrapper extends ItemMetadataWrapper {
    
    public DestructorWrapper(String ign) {
        super(ign);
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(GarnetRenderer.instance);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        if (!world.isRemote) {
            
            ForgeDirection direction = ForgeDirection.getOrientation(side).getOpposite();
            int distance = UtilItemStack.getNBTTagInt(itemStack, "distance");
            Block block = world.getBlock(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            
            if (block != null) {
                
                for (int x_ = -distance; x_ <= distance; x_++) {
                    
                    for (int y_ = -distance; y_ <= distance; y_++) {
                        
                        for (int z_ = -distance; z_ <= distance; z_++) {
                            
                            int changedX = x_;
                            int changedY = y_;
                            int changedZ = z_;
                            
                            if (!player.isSneaking()) {
                                
                                if (direction.offsetX != 0) {
                                    changedX *= 0;
                                }
                                if (direction.offsetY != 0) {
                                    changedY *= 0;
                                }
                                if (direction.offsetZ != 0) {
                                    changedZ *= 0;
                                }
                            } else {
                                
                                if (direction.offsetX != 0) {
                                    changedX += (direction.offsetX * distance);
                                }
                                if (direction.offsetY != 0) {
                                    changedY += (direction.offsetY * distance);
                                }
                                if (direction.offsetZ != 0) {
                                    changedZ += (direction.offsetZ * distance);
                                }
                            }
                            
                            if (world.getBlock(changedX + x, changedY + y, changedZ + z) == block && world.getBlockMetadata(changedX + x, changedY + y, changedZ + z) == meta) {
                                
                                // world.spawnEntityInWorld(new
                                // EntityBlockDestoryer(world, new
                                // Vector3i(changedX + x, changedY + y, changedZ
                                // + z), direction, distance, block, meta));
                                
                                UtilBlock.breakBlockNoDrop(world, changedX + x, changedY + y, changedZ + z);
                                
                                // UtilBlock.breakAndAddToInventorySpawnExcess(player.inventory,
                                // world, changedX + x, changedY + y, changedZ +
                                // z, 1, true);
                                
                                // UtilBlock.breakBlockNoDrop(world, changedX +
                                // x, changedY + y, changedZ + z);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        int distance = UtilItemStack.getNBTTagInt(itemStack, "distance");
        
        if (player.isSneaking()) {
            
            distance--;
        } else {
            
            distance++;
        }
        UtilItemStack.setNBTTagInt(itemStack, "distance", Math.max(distance, 0));
        if (!world.isRemote)
            UtilEntity.sendChatToPlayer(player, "Radius: " + Math.max(distance, 0));
        return super.onItemRightClick(itemStack, world, player);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        
        par3List.add("Distance: " + UtilItemStack.getNBTTagInt(par1ItemStack, "direction"));
    }
}
