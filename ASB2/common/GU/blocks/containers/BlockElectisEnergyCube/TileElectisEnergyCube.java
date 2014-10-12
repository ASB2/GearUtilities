package GU.blocks.containers.BlockElectisEnergyCube;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilEntity;
import GU.api.EnumSideState;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import GU.blocks.containers.TileBase;

public class TileElectisEnergyCube extends TileBase implements ITilePowerHandler {
    
    EnumSideState[] sideState;
    DefaultPowerManager manager;
    
    public TileElectisEnergyCube() {
        
        sideState = new EnumSideState[] { EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE, EnumSideState.NONE };
        manager = new DefaultPowerManager(1000);
    }
    
    int wait = 0;
    
    @Override
    public void updateEntity() {
        
        if (wait >= 5) {
            
            wait = 0;
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                final int toMove = 5;
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);
                    
                    if (tile != null) {
                        
                        IPowerManager otherManager = null;
                        
                        if (tile instanceof ITilePowerHandler) {
                            
                            otherManager = ((ITilePowerHandler) tile).getPowerManager(direction.getOpposite());
                        } else if (tile instanceof IBlockPowerHandler) {
                            
                            otherManager = ((IBlockPowerHandler) tile).getPowerManager(worldObj, xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ, direction.getOpposite());
                        }
                        
                        if (otherManager != null) {
                            
                            if (sideState[direction.ordinal()] == EnumSideState.OUTPUT) {
                                
                                UtilPower.movePower(manager, otherManager, toMove, EnumSimulationType.LIGITIMATE);
                            } else if (sideState[direction.ordinal()] == EnumSideState.INPUT){
                                
                                UtilPower.movePower(otherManager, manager, toMove, EnumSimulationType.LIGITIMATE);
                            }
                        }
                    }
                }
            }
        } else {
            
            wait++;
        }
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (!player.isSneaking()) {
            
            sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
            
            if (sideState[axis.ordinal()] == EnumSideState.BOTH) {
                
                sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
            }
            if (!world.isRemote)
                UtilEntity.sendChatToPlayer(player, "Side State: " + sideState[axis.ordinal()]);
            
            world.markBlockForUpdate(x, y, z);
        } else {
            
            if (!world.isRemote)
                UtilEntity.sendChatToPlayer(player, "Current Power: " + this.manager.getStoredPower() + " / " + this.manager.getMaxPower());
        }
        return true;
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return manager;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        this.readFromNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumSideState.values()[tag.getInteger("sideState" + direction.ordinal())];
        }
        manager.load(tag);
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setInteger("sideState" + direction.ordinal(), sideState[direction.ordinal()].ordinal());
        }
        manager.save(tag);
        
        super.writeToNBT(tag);
    }
}
