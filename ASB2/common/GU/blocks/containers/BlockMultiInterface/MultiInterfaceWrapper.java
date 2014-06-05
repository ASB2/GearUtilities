package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.blocks.BlockMetadata.MetadataWrapper;
import GU.utils.UtilGU;

public class MultiInterfaceWrapper extends MetadataWrapper {
    
    public MultiInterfaceWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float xHit, float yHit, float zHit) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case 0: {
                
                if (UtilGU.isWrench(entityplayer.getHeldItem())) {
                    
                    TileItemMultiInterface tile = (TileItemMultiInterface) world.getTileEntity(x, y, z);
                    
                    tile.sideState[side] = tile.sideState[side].increment();
                    world.markBlockForUpdate(x, y, z);
                    return true;
                }
                return false;
            }
            case 1: {
                
                ItemStack current = entityplayer.inventory.getCurrentItem();
                
                if (current != null) {
                    
                    FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
                    TileFluidMultiInterface tank = (TileFluidMultiInterface) world.getTileEntity(x, y, z);
                    
                    if (fluid != null) {
                        
                        if (!entityplayer.capabilities.isCreativeMode) {
                            
                            if (UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, false)) {
                                
                                if (UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, true)) {
                                    
                                    UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                                }
                            }
                        }
                        else {
                            
                            UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, true);
                        }
                        return true;
                    }
                    else {
                        //
                        // if (FluidContainerRegistry.isEmptyContainer(current))
                        // {
                        //
                        // if (tank.fluidTank.getFluid() != null) {
                        //
                        // ItemStack filled =
                        // FluidContainerRegistry.fillFluidContainer(tank.fluidTank.getFluid(),
                        // current);
                        //
                        // if (!entityplayer.capabilities.isCreativeMode) {
                        //
                        // if (UtilFluid.removeFluidFromTank(tank,
                        // ForgeDirection.getOrientation(side),
                        // FluidContainerRegistry.getFluidForFilledItem(filled),
                        // true)) {
                        //
                        // if
                        // (UtilInventory.addItemStackToInventoryAndSpawnExcess(world,
                        // entityplayer.inventory, filled, x, y, z)) {
                        //
                        // UtilInventory.consumeItemStack(entityplayer.inventory,
                        // current, 1);
                        // }
                        // }
                        // }
                        // else {
                        //
                        // UtilFluid.removeFluidFromTank(tank,
                        // ForgeDirection.getOrientation(side), fluid, true);
                        // }
                        // }
                        // return true;
                        // }
                    }
                }
                return false;
            }
        }
        return super.onBlockActivated(world, x, y, z, entityplayer, side, xHit, yHit, zHit);
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        switch (metadata) {
        
            case 0:
                return new TileItemMultiInterface();
            case 1:
                return new TileFluidMultiInterface();
            case 2:
                return new TilePowerMultiInterface();
            case 3:
                return new TileDataMultiInterface();
            case 4:
                return new TileRedstoneMultiInterface();
            case 5:
                return new TileGuiMultiInterface();
        }
        return null;
    }
}
