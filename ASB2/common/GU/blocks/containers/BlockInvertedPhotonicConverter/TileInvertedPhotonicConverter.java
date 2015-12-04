package GU.blocks.containers.BlockInvertedPhotonicConverter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilDirection;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetVariables;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileInvertedPhotonicConverter extends TileBase implements ITilePowerHandler {
    
    DefaultPowerManager manager;
    Wait wait;
    
    public static final double EFFICENCY = .7;
    
    public TileInvertedPhotonicConverter() {
        
        manager = new DefaultPowerManager((int) (PowerNetVariables.ONE_GARNET_ENERGY_VALUE));
        manager.setPowerStatus(EnumPowerStatus.SOURCE);
        wait = new Wait(new WaitTrigger(), 20);
        
    }
    
    @Override
    public void updateEntity() {
        
        TileEntity tile = UtilDirection.translateDirectionToTile(getThis(), worldObj, getOrientation());
        
        if (tile != null) {
            
            if (tile instanceof ITilePowerHandler) {
                
                IPowerManager manager = ((ITilePowerHandler) tile).getPowerManager(getOrientation().getOpposite());
                
                if (manager != null)
                    for (int index = 0; index < PowerNetVariables.ONE_GARNET_ENERGY_VALUE; index++)
                        PowerNetObject.UtilPower.movePower(getPowerManager(ForgeDirection.UNKNOWN), manager, 1, EnumSimulationType.LIGITIMATE);
            }
        }
        wait.update();
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        if (player.isSneaking()) {
            
            this.rotateBlock(world, x, y, z, axis);
            world.markBlockForUpdate(x, y, z);
        }
        return true;
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        
        ForgeDirection direction = this.getOrientation();
        
        if (direction.ordinal() + 1 < ForgeDirection.VALID_DIRECTIONS.length) {
            
            world.setBlockMetadataWithNotify(x, y, z, direction.ordinal() + 1, 3);
        } else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }
        return true;
    }
    
    @Override
    public IPowerManager getPowerManager(ForgeDirection direction) {
        
        return manager;
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        manager.load(tag);
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        manager.save(tag);
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        manager.load(tag);
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        manager.save(tag);
        super.writeToNBT(tag);
    }
    
    private TileInvertedPhotonicConverter getThis() {
        
        return this;
    }
    
    private class WaitTrigger implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            // ForgeDirection direction = getOrientation().getOpposite();
            
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
