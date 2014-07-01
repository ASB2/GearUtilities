package GU.blocks.containers.BlockMultiInterface;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.BlockMetadata.MetadataWrapper;
import GU.utils.UtilGU;
import UC.math.vector.Vector3i;

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
                
                TileFluidMultiInterface tile = (TileFluidMultiInterface) world.getTileEntity(x, y, z);
                
                if (tile != null && current != null) {
                    
                    FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
                    
                    if (fluid != null) {
                        
                        if (!entityplayer.capabilities.isCreativeMode) {
                            
                            if (UtilFluid.addFluidToTank(tile, ForgeDirection.getOrientation(side), fluid, false)) {
                                
                                if (UtilFluid.addFluidToTank(tile, ForgeDirection.getOrientation(side), fluid, true)) {
                                    
                                    UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                                }
                            }
                        }
                        else {
                            
                            UtilFluid.addFluidToTank(tile, ForgeDirection.getOrientation(side), fluid, true);
                        }
                        return true;
                    }
                    else if (FluidContainerRegistry.isEmptyContainer(current)) {
                        
                        FluidTankInfo[] infoArray = tile.getTankInfo(ForgeDirection.getOrientation(side));
                        
                        if (infoArray != null) {
                            
                            for (FluidTankInfo info : infoArray) {
                                
                                if (info != null && info.fluid != null) {
                                    
                                    ItemStack filled = FluidContainerRegistry.fillFluidContainer(info.fluid, current);
                                    
                                    if (filled != null) {
                                        
                                        if (!entityplayer.capabilities.isCreativeMode) {
                                            
                                            if (UtilFluid.removeFluidFromHandler(tile, ForgeDirection.getOrientation(side), FluidContainerRegistry.getFluidForFilledItem(filled), true)) {
                                                
                                                UtilInventory.addItemStackToInventoryAndSpawnExcess(world, entityplayer.inventory, filled, entityplayer.posX, entityplayer.posY, entityplayer.posZ);
                                                
                                                UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                                                return true;
                                            }
                                        }
                                        else {
                                            
                                            UtilFluid.removeFluidFromHandler(tile, ForgeDirection.getOrientation(side), FluidContainerRegistry.getFluidForFilledItem(filled), true);
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            // case 4: {
            // break;
            // }
            case 5: {
                
                if (!world.isRemote) {
                    
                    TileGuiMultiInterface tile = (TileGuiMultiInterface) world.getTileEntity(x, y, z);
                    
                    List<IMultiBlock> list = tile.getMultiBlocks();
                    
                    if (tile != null && list != null && list.size() >= 1) {
                        
                        IMultiBlock multi = list.get(0);
                        
                        if (multi instanceof IGuiMultiBlock) {
                            
                            return ((IGuiMultiBlock) multi).openGui(new Vector3i(x, y, z), entityplayer, side);
                        }
                    }
                }
                return true;
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
                // case 4:
                // return new TileRedstoneMultiInterface();
            case 5:
                return new TileGuiMultiInterface();
        }
        return null;
    }
}
