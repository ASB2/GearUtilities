package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilVector;
import GU.api.EnumSideState;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.api.multiblock.MultiBlockAbstract.IPowerMultiBlock;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.blocks.containers.TileMultiBase;
import UC.math.vector.Vector3i;

public class TilePowerMultiInterface extends TileMultiBase implements IMultiBlockPart, ITilePowerHandler {
    
    IPowerMultiBlock handler1 = null, handler2 = null;
    Vector3i position;
    EnumSideState[] sideState;
    
    public TilePowerMultiInterface() {
        
        sideState = new EnumSideState[] { EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE };
        this.setMaxMultiBlocks(2);
    }
    
    @Override
    public void updateEntity() {
        
        if (position == null) {
            
            position = UtilVector.createTileEntityVector(this);
        }
        super.updateEntity();
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return handler1 != null ? handler1.getPowerManager(position) : null;
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            if (multiBlock instanceof IPowerMultiBlock) {
                
                if (handler1 == null) {
                    
                    handler1 = (IPowerMultiBlock) multiBlock;
                } else if (handler2 == null) {
                    
                    handler2 = (IPowerMultiBlock) multiBlock;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (handler1 == multiBlock) {
            
            handler1 = null;
            
            if (handler2 != null) {
                
                handler1 = handler2;
                handler2 = null;
            }
        }
        if (handler2 == multiBlock) {
            
            handler2 = null;
        }
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        
        if (sideState[axis.ordinal()] == EnumSideState.BOTH) {
            sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        }
        
        world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        this.writeToNBT(tag);
        this.sendNBTPacket(tag, 0);
        super.sendUpdatePacket();
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        this.readFromNBT(tag);
        super.readNBTPacket(tag, id);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setInteger("sideState" + direction.ordinal(), sideState[direction.ordinal()].ordinal());
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumSideState.values()[tag.getInteger("sideState" + direction.ordinal())];
        }
        super.readFromNBT(tag);
    }
}
