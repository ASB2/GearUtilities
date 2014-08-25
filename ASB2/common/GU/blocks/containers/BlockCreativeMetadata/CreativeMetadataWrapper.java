package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import GU.blocks.BlockMetadata.BlockMetadataWrapper;
import GU.blocks.containers.TileBase;

public class CreativeMetadataWrapper extends BlockMetadataWrapper {
    
    public CreativeMetadataWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float xHit, float yHit, float zHit) {
        
        switch (this.getMetadata()) {
        
            case 1: {
                
                ItemStack current = entityplayer.inventory.getCurrentItem();
                
                if (current != null) {
                    
                    FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
                    TileBase tank = (TileBase) world.getTileEntity(x, y, z);
                    
                    if (fluid != null) {
                        
                        tank.setTank(new FluidTank(fluid, fluid.amount), 0);
                    }
                    world.markBlockForUpdate(x, y, z);
                }
                return true;
            }
            case 2: {
                
                TileCreativeItem tile = (TileCreativeItem) world.getTileEntity(x, y, z);
                tile.toDuplicate = entityplayer.inventory.getCurrentItem();
                return true;
            }
        }
        return super.onBlockActivated(world, x, y, z, entityplayer, side, xHit, yHit, zHit);
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
