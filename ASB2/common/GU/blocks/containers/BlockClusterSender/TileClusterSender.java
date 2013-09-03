package GU.blocks.containers.BlockClusterSender;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.cluster.IClusterTrigger;
import GU.api.cluster.IClustor;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.entity.EntityCluster.EntityInfoCluster;

public class TileClusterSender extends TileBase implements IClusterTrigger {

    public TileClusterSender() {

        waitTimer = new Wait(20 * 10, this, 0);
    }

    public void updateEntity() {

        waitTimer.update();
//        this.trigger(0);
    }

    @Override
    public void onClustorCollosion(ForgeDirection side, Vector3 position, IClustor clustor) {

        TileEntity sink = position.getTileEntity(worldObj);

        if(sink != null) {

            TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

            if(source != null) {

                if(source != sink) {

                    if(source instanceof IInventory) {

                        if(sink instanceof IInventory) {

                            UtilInventory.moveEntireInventory((IInventory)source, (IInventory)sink);
                        }
                    }

                    if(source instanceof IFluidHandler) {

                        if(sink instanceof IFluidHandler) {

                            UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, true);
                        }
                    }

                    if(source instanceof IPowerMisc) {

                        if(sink instanceof IPowerMisc) {

                            if(PowerHelper.moveEnergy((IPowerMisc)source, (IPowerMisc)sink, side, true)) {

                                System.out.println("Packet Recieved");
                            }
                        }
                    }
                }
            }                 
        }   
    }

    @Override
    public void trigger(int id) {

        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

        if(tile != null) {

            if(tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler) {

                if(!worldObj.isRemote) {

                    worldObj.spawnEntityInWorld(new EntityInfoCluster(worldObj, new Vector3(this), this.getOrientation(), this, 20));
                }
            }
        }
    }
}
