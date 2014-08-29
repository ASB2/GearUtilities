package GU.items;

import java.util.List;

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
import GU.api.color.AbstractColorable.IColorableBlock;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;
import UC.color.Color4i;

public class GearReaderWrapper extends ItemMetadataWrapper {
    
    public GearReaderWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(GarnetRenderer.instance);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        if (!world.isRemote) {
            
            UtilEntity.sendChatToPlayer(player, "---------");
            
            // ForgeDirection direction = ForgeDirection.getOrientation(side);
            // TileEntity tile = world.getTileEntity(x + direction.offsetX, y +
            // direction.offsetY, z + direction.offsetZ);
            // Block block = world.getBlock(x + direction.offsetX, y +
            // direction.offsetY, z + direction.offsetZ);
            
            TileEntity tile = world.getTileEntity(x, y, z);
            Block block = world.getBlock(x, y, z);
            
            if (tile != null) {
                
                if (tile instanceof ITilePowerHandler) {
                    
                    ITilePowerHandler mTile = (ITilePowerHandler) tile;
                    
                    IPowerManager manager = mTile.getPowerManager();
                    IPowerAttribute attribute = mTile.getPowerAttribute();
                    
                    UtilEntity.sendChatToPlayer(player, "---Tile Power Handler---");
                    
                    if (manager != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Stored: " + manager.getStoredPower());
                        UtilEntity.sendChatToPlayer(player, "Max Power: " + manager.getMaxPower());
                        UtilEntity.sendChatToPlayer(player, "Power Difference: " + (manager.getMaxPower() - manager.getStoredPower()));
                    }
                    
                    if (attribute != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Status: " + attribute.getPowerStatus());
                    }
                }
                
                if (tile instanceof ICrystalPowerHandler) {
                    
                    ICrystalPowerHandler mTile = (ICrystalPowerHandler) tile;
                    
                    IPowerManager manager = mTile.getPowerManager();
                    IPowerAttribute attribute = mTile.getPowerAttribute();
                    
                    UtilEntity.sendChatToPlayer(player, "---Crystal Power Handler---");
                    
                    if (manager != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Stored: " + manager.getStoredPower());
                        UtilEntity.sendChatToPlayer(player, "Max Power: " + manager.getMaxPower());
                        UtilEntity.sendChatToPlayer(player, "Power Difference: " + (manager.getMaxPower() - manager.getStoredPower()));
                    } else {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Manager: null");
                    }
                    
                    if (attribute != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Status: " + attribute.getPowerStatus());
                    } else {
                        
                        UtilEntity.sendChatToPlayer(player, "Power Attribute: null");
                    }
                }
                
                if (tile instanceof ISidedInventory) {
                    
                    ISidedInventory mTile = (ISidedInventory) tile;
                    
                    UtilEntity.sendChatToPlayer(player, "Inventory name is: " + mTile.getInventoryName());
                    UtilEntity.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                    UtilEntity.sendChatToPlayer(player, "Accessible Slots From Side: " + (mTile.getAccessibleSlotsFromSide(side) != null ? mTile.getAccessibleSlotsFromSide(side).length : 0));
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
                                    UtilEntity.sendChatToPlayer(player, "Fluid Conained: " + info.fluid.getFluid().getLocalizedName());
                                    UtilEntity.sendChatToPlayer(player, "Precent Filled: " + (((int) ((info.fluid.amount / (float) info.capacity) * 100000)) / 1000.0));
                                } else {
                                    
                                    UtilEntity.sendChatToPlayer(player, "Capasity: " + info.capacity);
                                }
                            }
                        }
                        
                        UtilEntity.sendChatToPlayer(player, "Tanks For Side: " + loop);
                    }
                }
                
                if (tile instanceof IColorableTile) {
                    
                    IColorableTile mTile = (IColorableTile) tile;
                    
                    Color4i color = mTile.getColor(ForgeDirection.getOrientation(side));
                    
                    if (color != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Red: " + color.getRed());
                        UtilEntity.sendChatToPlayer(player, "Green: " + color.getGreen());
                        UtilEntity.sendChatToPlayer(player, "Blue: " + color.getBlue());
                        UtilEntity.sendChatToPlayer(player, "Alpha: " + color.getAlpha());
                    }
                }
                
                if (tile instanceof ICrystalNetworkPart) {
                    
                    ICrystalNetworkPart mTile = (ICrystalNetworkPart) tile;
                    
                    CrystalNetwork network = mTile.getNetwork();
                    
                    if (network != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Network Size: " + network.getNetworkSize());
                        UtilEntity.sendChatToPlayer(player, "Network Core: " + network.getCorePosition().toString());
                    }
                }
                if (tile instanceof IMultiBlockPart) {
                    
                    IMultiBlockPart mTile = (IMultiBlockPart) tile;
                    
                    List<IMultiBlock> multiBlocks = mTile.getMultiBlocks();
                    
                    if (multiBlocks != null) {
                        
                        UtilEntity.sendChatToPlayer(player, "Part of : " + multiBlocks.size() + " Multiblocks");
                    }
                }
            }
            
            if (block instanceof IColorableBlock) {
                
                IColorableBlock mTile = (IColorableBlock) block;
                
                Color4i color = mTile.getColor(world, x, y, z, ForgeDirection.getOrientation(side));
                
                if (color != null) {
                    
                    UtilEntity.sendChatToPlayer(player, "Red: " + color.getRed());
                    UtilEntity.sendChatToPlayer(player, "Green: " + color.getGreen());
                    UtilEntity.sendChatToPlayer(player, "Blue: " + color.getBlue());
                    UtilEntity.sendChatToPlayer(player, "Alpha: " + color.getAlpha());
                }
            }
            
            if (block instanceof IBlockPowerHandler) {
                
                IBlockPowerHandler mTile = (IBlockPowerHandler) block;
                
                IPowerManager manager = mTile.getPowerManager(world, x, y, z);
                IPowerAttribute attribute = mTile.getPowerAttribute(world, x, y, z);
                
                if (manager != null) {
                    
                    UtilEntity.sendChatToPlayer(player, "Power Stored: " + manager.getStoredPower());
                    UtilEntity.sendChatToPlayer(player, "Max Power: " + manager.getMaxPower());
                    UtilEntity.sendChatToPlayer(player, "Power Difference: " + (manager.getMaxPower() - manager.getStoredPower()));
                }
                
                if (attribute != null) {
                    
                    UtilEntity.sendChatToPlayer(player, "Power Status: " + attribute.getPowerStatus());
                }
            }
            UtilEntity.sendChatToPlayer(player, "Block FixBlockName: " + block);
            UtilEntity.sendChatToPlayer(player, "Block Metadata: " + world.getBlockMetadata(x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block Direction: " + ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)));
            // UtilEntity.sendChatToPlayer(player, "Block Mixed Brightness: " +
            // block.getMixedBrightnessForBlock(world, x, y, z));
            // UtilEntity.sendChatToPlayer(player, "Block Ambient Acclusion: " +
            // block.getAmbientOcclusionLightValue());
            UtilEntity.sendChatToPlayer(player, "Tile Entity: " + tile);
            UtilEntity.sendChatToPlayer(player, "Block Opaque: " + block.isOpaqueCube());
            UtilEntity.sendChatToPlayer(player, "Normal Cube: " + block.isNormalCube(world, x, y, z));
            UtilEntity.sendChatToPlayer(player, "---------");
            return true;
        }
        return false;
    }
}
