package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.nbt.NBTTagCompound;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockCore;
import GU.blocks.containers.TileMultiBase;
import GU.multiblock.MultiBlockBase;
import GU.multiblock.MultiBlockChest;
import GU.multiblock.MultiBlockFurnace;
import GU.multiblock.MultiBlockTank;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileSpacialProvider extends TileMultiBase implements IMultiBlockCore {
    
    MultiBlockBase updating;
    boolean shouldConstructMultiBlock = false;
    NBTTagCompound multiBlockTag;
    int waitTimer = 0;
    Wait multiBlockPacket;
    
    public TileSpacialProvider() {
        
        multiBlockPacket = new Wait(new PacketWait(), 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        if (multiBlockTag != null) {
            
            if (waitTimer > 20) {
                
                switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
                
                    case 1:
                        updating = new MultiBlockChest(worldObj);
                        break;
                    case 2:
                        updating = new MultiBlockFurnace(worldObj);
                        break;
                    case 3:
                        updating = new MultiBlockTank(worldObj);
                        break;
                // case 2:
                // updating = new ChestMultiBlock(worldObj);
                // break;
                // case 3:
                // updating = new ChestMultiBlock(worldObj);
                // break;
                }
                if (updating != null) {
                    
                    updating.load(multiBlockTag);
                    multiBlockTag = null;
                }
            }
            else
                waitTimer++;
            
        }
        else {
            
            if (updating != null) {
                
                updating.update((Object) null);
                multiBlockPacket.update();
            }
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        boolean worked = super.addMultiBlock(multiBlock);
        
        if (worked) {
            
            if (updating == null) {
                
                if (multiBlock instanceof MultiBlockBase) {
                    
                    Vector3i core = ((MultiBlockBase) multiBlock).getSpacialProvider();
                    
                    if (core.getX() == xCoord && core.getY() == yCoord && core.getZ() == zCoord) {
                        
                        updating = (MultiBlockBase) multiBlock;
                    }
                }
            }
        }
        return worked;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (updating == multiBlock) {
            
            updating = null;
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        if (updating != null) {
            
            tag.setTag("MultiBlockData", updating.save(new NBTTagCompound()));
            tag.setBoolean("shouldConstructMultiBlock", true);
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        shouldConstructMultiBlock = tag.getBoolean("shouldConstructMultiBlock");
        
        if (shouldConstructMultiBlock) {
            
            multiBlockTag = tag.getCompoundTag("MultiBlockData");
        }
    }
    
    private class PacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            updating.sendPacket();
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}