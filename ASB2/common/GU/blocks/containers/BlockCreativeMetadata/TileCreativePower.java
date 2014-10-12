package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileCreativePower extends TileBase implements ITilePowerHandler {
    
    Wait sendEnergyValidNodes;
    DefaultPowerManager manager;
    
    public TileCreativePower() {
        
        sendEnergyValidNodes = new Wait(new SendEnergyPacketWait(), 10, 0);
        manager = new DefaultPowerManager().setPowerMax(1000000);
    }
    
    @Override
    public void updateEntity() {
        
        sendEnergyValidNodes.update();
        
        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            manager.setPowerStored(manager.getMaxPower());
            manager.setPowerStatus(EnumPowerStatus.SOURCE);
        } else {
            
            manager.setPowerStored(0);
            manager.setPowerStatus(EnumPowerStatus.SINK);
        }
    }
    
    private class SendEnergyPacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                
                if (tile != null) {
                    
                    IPowerManager manager = null;
                    
                    if (tile instanceof ITilePowerHandler) {
                        
                        manager = ((ITilePowerHandler) tile).getPowerManager(direction);
                    } else if (tile instanceof IBlockPowerHandler) {
                        
                        manager = ((IBlockPowerHandler) tile).getPowerManager(worldObj, xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ, direction);
                    }
                    
                    if (manager != null) {
                        
                        final int powerToMove = 5;
                        
                        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                            
                            UtilPower.addPower(manager, powerToMove, EnumSimulationType.LIGITIMATE);
                        } else {
                            
                            UtilPower.removePower(manager, powerToMove, EnumSimulationType.LIGITIMATE);
                        }
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return manager;
    }
}