package GU.blocks.containers.BlockMultiDirectionalConduit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import UC.utils.UCUtil;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilEntity;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockConduit.EnumConduitType;

public class TileMultiDirectionalConduit extends TileBase {
    
    boolean[] searchSide;
    EnumConduitType[] sideFilter;
    
    public TileMultiDirectionalConduit() {
        
        searchSide = new boolean[ForgeDirection.values().length];
        sideFilter = new EnumConduitType[ForgeDirection.values().length];
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (!world.isRemote) {
            
            if (player.isSneaking()) {
                
                EnumConduitType type = sideFilter[axis.ordinal()];
                
                if (type != null) {
                    
                    switch (type) {
                    
                        case ITEM: {
                            
                            type = EnumConduitType.FLUID;
                            break;
                        }
                        case FLUID: {
                            
                            type = EnumConduitType.GU_POWER;
                            break;
                        }
                        case GU_POWER: {
                            
                            type = EnumConduitType.ITEM;
                            break;
                        }
                        default: {
                            
                            type = EnumConduitType.ITEM;
                        }
                    }
                    
                    sideFilter[axis.ordinal()] = type;
                } else {
                    
                    sideFilter[axis.ordinal()] = EnumConduitType.ITEM;
                }
            } else {
                
                searchSide[axis.ordinal()] = UCUtil.toggle(searchSide[axis.ordinal()]);
            }
            UtilEntity.sendChatToPlayer(player, "----");
            UtilEntity.sendChatToPlayer(player, sideFilter[axis.ordinal()]);
            UtilEntity.sendChatToPlayer(player, !searchSide[axis.ordinal()]);
            UtilEntity.sendChatToPlayer(player, "----");
            world.markBlockForUpdate(x, y, z);
        }
        return super.triggerBlock(world, player, x, y, z, axis);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            searchSide[direction.ordinal()] = tag.getBoolean("searchSide" + direction.ordinal());
            sideFilter[direction.ordinal()] = EnumConduitType.values()[tag.getInteger("sideFilter" + direction.ordinal())];
        }
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setBoolean("searchSide" + direction.ordinal(), searchSide[direction.ordinal()]);
            
            if (sideFilter[direction.ordinal()] != null)
                tag.setInteger("sideFilter" + direction.ordinal(), sideFilter[direction.ordinal()].ordinal());
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setBoolean("searchSide" + direction.ordinal(), searchSide[direction.ordinal()]);
            if (sideFilter[direction.ordinal()] != null)
                tag.setInteger("sideFilter" + direction.ordinal(), sideFilter[direction.ordinal()].ordinal());
        }
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            searchSide[direction.ordinal()] = tag.getBoolean("searchSide" + direction.ordinal());
            sideFilter[direction.ordinal()] = EnumConduitType.values()[tag.getInteger("sideFilter" + direction.ordinal())];
        }
    }
    
    public Map<TileEntity, ForgeDirection> getAvaliableTiles(EnumConduitType type) {
        
        Map<TileEntity, ForgeDirection> tileList = new HashMap<TileEntity, ForgeDirection>();
        List<TileMultiDirectionalConduit> conduits = new ArrayList<TileMultiDirectionalConduit>();
        
        conduits.add(this);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (searchSide[direction.ordinal()]) {
                
                continue;
            }
            
            if (sideFilter[direction.ordinal()] != null) {
                
                EnumConduitType fType = sideFilter[direction.ordinal()];
                
                if (fType != EnumConduitType.NONE) {
                    
                    if (fType != type) {
                        
                        continue;
                    }
                }
            }
            
            for (int distance = 1; distance <= 7; distance++) {
                
                int x = xCoord + (distance * direction.offsetX), y = yCoord + (distance * direction.offsetY), z = zCoord + (distance * direction.offsetZ);
                
                TileEntity tile = worldObj.getTileEntity(x, y, z);
                
                if (tile != null) {
                    
                    if (type.checkObject(tile)) {
                        
                        tileList.put(tile, direction);
                        break;
                    } else if (tile instanceof TileMultiDirectionalConduit) {
                        
                        ((TileMultiDirectionalConduit) tile).getAvaliableTilesIndirect(type, tileList, conduits);
                    }
                } else if (worldObj.getBlock(x, y, z) instanceof BlockAir) {
                    
                    continue;
                } else {
                    
                    break;
                }
            }
        }
        return tileList;
    }
    
    private Map<TileEntity, ForgeDirection> getAvaliableTilesIndirect(EnumConduitType type, Map<TileEntity, ForgeDirection> tileList, List<TileMultiDirectionalConduit> conduits) {
        
        conduits.add(this);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (searchSide[direction.ordinal()]) {
                
                continue;
            }
            
if (sideFilter[direction.ordinal()] != null) {
                
                EnumConduitType fType = sideFilter[direction.ordinal()];
                
                if (fType != EnumConduitType.NONE) {
                    
                    if (fType != type) {
                        
                        continue;
                    }
                }
            }
            
            for (int distance = 1; distance <= 17; distance++) {
                
                int x = xCoord + (distance * direction.offsetX), y = yCoord + (distance * direction.offsetY), z = zCoord + (distance * direction.offsetZ);
                
                TileEntity tile = worldObj.getTileEntity(x, y, z);
                
                if (tile != null) {
                    
                    if (type.checkObject(tile)) {
                        
                        tileList.put(tile, direction);
                        break;
                    } else if (tile instanceof TileMultiDirectionalConduit) {
                        
                        if (!conduits.contains(tile)) {
                            
                            ((TileMultiDirectionalConduit) tile).getAvaliableTilesIndirect(type, tileList, conduits);
                        } else {
                            
                            continue;
                        }
                    }
                } else if (worldObj.getBlock(x, y, z) instanceof BlockAir) {
                    
                    continue;
                } else {
                    
                    break;
                }
            }
        }
        return null;
    }
}
