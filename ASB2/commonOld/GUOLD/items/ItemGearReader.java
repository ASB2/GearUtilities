package GUOLD.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilMisc;
import GUOLD.api.color.IColorable;
import GUOLD.api.color.IVanillaColorable;
import GUOLD.api.multiblock.IMultiBlockPart;
import GUOLD.api.power.IPowerHandler;
import GUOLD.info.Reference;

public class ItemGearReader extends ItemBase {
    
    public ItemGearReader() {
        setMaxStackSize(1);
        this.setFull3D();
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        
        if (!world.isRemote) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            Block block = world.getBlock(x, y, z);
            
            if (tile != null) {
                
                if (tile instanceof IVanillaColorable) {
                    
                    IVanillaColorable mTile = (IVanillaColorable) tile;
                    
                    UtilEntity.sendChatToPlayer(player, "Block has color: " + mTile.getColorEnum().toString());
                }
                
                if (tile instanceof IPowerHandler) {
                    
                    IPowerHandler mTile = (IPowerHandler) tile;
                    
                    if (mTile.getPowerProvider() != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Tile has " + mTile.getPowerProvider().getPowerStored() + " out of " + mTile.getPowerProvider().getPowerMax() + " " + Reference.POWER_NAME + " Stored");
                    }
                }
                
                if (tile instanceof ISidedInventory) {
                    
                    ISidedInventory mTile = (ISidedInventory) tile;
                    
                    UtilEntity.sendChatToPlayer(player, "Inventory name is: " + mTile.getInventoryName());
                    UtilEntity.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                    UtilEntity.sendChatToPlayer(player, "Accessible Slots From Side: " + mTile.getAccessibleSlotsFromSide(side).length);
                    UtilEntity.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
                }
                
                else if (tile instanceof IInventory) {
                    
                    IInventory mTile = (IInventory) tile;
                    
                    UtilEntity.sendChatToPlayer(player, "Inventory name is: " + mTile.getInventoryName());
                    UtilEntity.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                    UtilEntity.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
                }
                
                if (tile instanceof IFluidHandler) {
                    
                    IFluidHandler mTile = (IFluidHandler) tile;
                    
                    int loop = 0;
                    
                    if (mTile.getTankInfo(ForgeDirection.getOrientation(side).getOpposite()) != null) {
                        
                        for (FluidTankInfo info : mTile.getTankInfo(ForgeDirection.getOrientation(side).getOpposite())) {
                            
                            loop++;
                            
                            if (info != null) {
                                
                                if (info.fluid != null && info.fluid.getFluid() != null) {
                                    
                                    UtilEntity.sendChatToPlayer(player, "Fluid Stored: " + info.fluid.amount + "/" + info.capacity);
                                    UtilEntity.sendChatToPlayer(player, "Fluid Conained: " + UtilMisc.capitilizeFirst(info.fluid.getFluid().getName()));
                                }
                                else {
                                    
                                    UtilEntity.sendChatToPlayer(player, "Capasity: " + info.capacity);
                                }
                            }
                        }
                        
                        UtilEntity.sendChatToPlayer(player, "Tanks For Side: " + loop);
                    }
                }
                
                if (tile instanceof IColorable) {
                    
                    IColorable mTile = (IColorable) tile;
                    
                    if (mTile.getColor(ForgeDirection.getOrientation(side)) != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Red: " + mTile.getColor(ForgeDirection.getOrientation(side)).getRed());
                        UtilEntity.sendChatToPlayer(player, "Green: " + mTile.getColor(ForgeDirection.getOrientation(side)).getGreen());
                        UtilEntity.sendChatToPlayer(player, "Blue: " + mTile.getColor(ForgeDirection.getOrientation(side)).getBlue());
                        UtilEntity.sendChatToPlayer(player, "Alpha: " + mTile.getColor(ForgeDirection.getOrientation(side)).getAlpha());
                    }
                }
                
                if (tile instanceof IMultiBlockPart) {
                    
                    IMultiBlockPart mTile = (IMultiBlockPart) tile;
                    
                    UtilEntity.sendChatToPlayer(player, "Part of " + mTile.getComprisedMultiBlocks().size() + " multiblocks");
                }
            }
            UtilEntity.sendChatToPlayer(player, "Block FixBlockName: " + world.getBlock(x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block metadata: " + world.getBlockMetadata(x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block direction: " + ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)));
            UtilEntity.sendChatToPlayer(player, "Block mixed brightness: " + block.getMixedBrightnessForBlock(world, x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block ambient acclusion: " + block.getAmbientOcclusionLightValue());
            UtilEntity.sendChatToPlayer(player, "Tile Entity: " + world.getTileEntity(x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block Opaque: " + block.isOpaqueCube());
            UtilEntity.sendChatToPlayer(player, "Normal Cube: " + block.isNormalCube(world, x, y, z));
            UtilEntity.sendChatToPlayer(player, "--------");
        }
        return true;
    }
}
