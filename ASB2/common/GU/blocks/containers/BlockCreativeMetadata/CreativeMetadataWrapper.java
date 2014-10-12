package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import ASB2.utils.UtilEntity;
import GU.blocks.BlockMetadata.BlockMetadataWrapper;

public class CreativeMetadataWrapper extends BlockMetadataWrapper {
    
    public CreativeMetadataWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        switch (this.getMetadata()) {
        
            case 1: {
                
                ItemStack current = player.inventory.getCurrentItem();
                
                if (current != null) {
                    
                    FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
                    TileCreativeFluid tank = (TileCreativeFluid) world.getTileEntity(x, y, z);
                    
                    if (fluid != null) {
                        
                        tank.fluidTank = new FluidTank(fluid, fluid.amount);
                        world.markBlockForUpdate(x, y, z);
                        return true;
                    }
                }
                return false;
            }
            case 2: {
                
                TileCreativeItem tile = (TileCreativeItem) world.getTileEntity(x, y, z);
                
                if (tile != null) {
                    
                    if (player.isSneaking()) {
                        
                        if (tile.toDuplicate != null) {
                            
                            tile.toDuplicate = null;
                            
                            if (!world.isRemote)
                                UtilEntity.sendChatToPlayer(player, "Item Cleared");
                        }
                    } else {
                        
                        if (tile.toDuplicate == null && player.inventory.getCurrentItem() != null) {
                            
                            tile.toDuplicate = player.inventory.getCurrentItem();
                            
                            if (!world.isRemote)
                                UtilEntity.sendChatToPlayer(player, "Item Set");
                        } else {
                            
                            if (!world.isRemote)
                                UtilEntity.sendChatToPlayer(player, "Current Item: " + (tile.toDuplicate != null ? tile.toDuplicate.getDisplayName() : "Empty"));
                            
                        }
                    }
                }
                return true;
            }
        }
        return super.onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        switch (metadata) {
        
            case 0:
                return new TileCreativePower();
            case 1:
                return new TileCreativeFluid();
            case 2:
                return new TileCreativeItem();
        }
        return null;
    }
}
