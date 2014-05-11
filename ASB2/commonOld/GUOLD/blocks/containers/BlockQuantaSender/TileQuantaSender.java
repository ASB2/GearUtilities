package GUOLD.blocks.containers.BlockQuantaSender;

import ASB2.vector.Vector3;
import ASB2.wait.Wait;
import GUOLD.ResourcePacket;
import GUOLD.api.EnumResourceType;
import GUOLD.api.power.IPowerHandler;
import GUOLD.api.power.IPowerProvider;
import GUOLD.api.power.PowerClass;
import GUOLD.api.power.PowerProvider;
import GUOLD.api.power.State;
import GUOLD.blocks.containers.TileBase;
import GUOLD.entity.EntityQuanta.EntityQuanta;

public class TileQuantaSender extends TileBase implements IPowerHandler {
    
    public TileQuantaSender() {
        
        powerProvider = new PowerProvider(PowerClass.LOW, State.SOURCE);
        waitTimer = new Wait(this, 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        // if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord,
        // zCoord)) {
        //
        // ForgeDirection direction = this.getOrientation().getOpposite();
        //
        // TileEntity managing = UtilDirection.translateDirectionToTile(this,
        // worldObj, direction.getOpposite());
        //
        // if (managing != null) {
        //
        // Vector3 position = new Vector3(this);
        //
        // for (int i = 1; i <= 16; i++) {
        //
        // position.add(direction, i);
        // TileEntity tile = position.getTileEntity(worldObj);
        //
        // if (tile != null) {
        //
        // if (tile instanceof IInventory && managing instanceof IInventory) {
        //
        // UtilInventory.moveEntireInventory((IInventory) managing, (IInventory)
        // tile);
        // } else {
        // return;
        // }
        // }
        // }
        // }
        // }
        waitTimer.update();
    }
    
    @Override
    public void trigger(int id) {
        
        if (!worldObj.isRemote) {
            
            ResourcePacket packet = new ResourcePacket();
            packet.addResource(EnumResourceType.EnumPowerType.GEAR_UTILITIES, 10);
            EntityQuanta quanta = new EntityQuanta(worldObj, new Vector3(this).add(this.getOrientation().getOpposite(), 2), this.getOrientation().getOpposite(), 64, packet);
            worldObj.spawnEntityInWorld(quanta);
        }
    }
    
    @Override
    public IPowerProvider getPowerProvider() {
        
        return powerProvider;
    }
}
