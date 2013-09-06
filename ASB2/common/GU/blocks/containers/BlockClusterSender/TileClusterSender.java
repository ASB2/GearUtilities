package GU.blocks.containers.BlockClusterSender;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

        waitTimer = new Wait(20, this, 0);
    }

    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            waitTimer.update();
        }
    }

    @Override
    public void onSentClustorCollosion(IClusterTrigger sender, ForgeDirection side, Vector3 position, IClustor clustor, int id) {

        System.out.println("Packet Hit");
        TileEntity sink = position.getTileEntity(worldObj);

        if(sink != null) {

            TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

            if(source != null) {

                if(source != sink) {

                    if(source instanceof IInventory) {

                        if(sink instanceof IInventory) {

                            UtilInventory.moveEntireInventory((IInventory)source, (IInventory)sink);
                            clustor.stopClustor();
                        }
                    }

                    if(source instanceof IFluidHandler) {

                        if(sink instanceof IFluidHandler) {

                            UtilFluid.moveFluid((IFluidHandler)source, side, (IFluidHandler)sink, true);
                            clustor.stopClustor();
                        }
                    }

                    if(source instanceof IPowerMisc) {

                        if(sink instanceof IPowerMisc) {

                            PowerHelper.moveEnergy((IPowerMisc)source, (IPowerMisc)sink, side, true, false);
                            clustor.stopClustor();
                        }
                    }
                }
            }                 
        }   
        else {

            if(position.getBlockID(worldObj) != 0) {
                
                if(Block.blocksList[position.getBlockID(worldObj)] != null) {

                    if(!(Block.blocksList[position.getBlockID(worldObj)].blockMaterial == Material.air)) {

                        clustor.stopClustor();
                    }
                }
            }
        }
    }

    @Override
    public void onClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor) {

        if(!worldObj.isRemote) {

            worldObj.spawnEntityInWorld(new EntityInfoCluster(worldObj, position, this.getOrientation(), source, 0));
        }
    }

    @Override
    public void trigger(int id) {

        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());

        if(tile != null) {

            if(tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler) {

                if(!worldObj.isRemote) {

                    worldObj.spawnEntityInWorld(new EntityInfoCluster(worldObj, new Vector3(this), this.getOrientation(), this, 0));
                }
            }
        }
    }
}
