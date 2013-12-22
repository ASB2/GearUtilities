package GU.blocks.containers.BlockQuantaSender;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.power.IPowerHandler;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.blocks.containers.TileBase;

public class TileQuantaSender extends TileBase implements IPowerHandler {
    
    public TileQuantaSender() {
        
        powerProvider = new PowerProvider(PowerClass.LOW, State.SOURCE);
    }
    
    @Override
    public void updateEntity() {
        
        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            ForgeDirection direction = this.getOrientation().getOpposite();
            
            TileEntity managing = UtilDirection.translateDirectionToTile(this, worldObj, direction.getOpposite());
            
            if (managing != null) {
                
                Vector3 position = new Vector3(this);
                
                for (int i = 1; i <= 16; i++) {
                    
                    position.add(direction, i);
                    TileEntity tile = position.getTileEntity(worldObj);
                    
                    if (tile != null) {
                        
                        if (tile instanceof IInventory && managing instanceof IInventory) {
                            
                            UtilInventory.moveEntireInventory((IInventory) managing, (IInventory) tile);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public IPowerProvider getPowerProvider() {
        
        return powerProvider;
    }
}
