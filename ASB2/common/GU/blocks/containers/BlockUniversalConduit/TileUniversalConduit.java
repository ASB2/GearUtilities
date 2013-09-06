package GU.blocks.containers.BlockUniversalConduit;

import java.util.Iterator;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.*;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.power.IPowerMisc;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.network.UniversalConduitNetwork;

public class TileUniversalConduit extends TileBase implements IConductor {

    UniversalConduitNetwork network;
    boolean importing = false;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new UniversalConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();

        if(this.getNetwork() != null) {

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, new Vector3(this));
            }

            if(importing) {

                if(UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite()) != null) {

                    if(!((UniversalConduitNetwork)this.getNetwork()).getAvaliableInventorys().isEmpty()) {

                        Iterator<Vector3> it = ((UniversalConduitNetwork)this.getNetwork()).getAvaliableInventorys().iterator();

                        while(it.hasNext()) {

                            Vector3 vector = it.next();

                            if(vector != null) {

                                if(vector.getTileEntity(worldObj) != null) {

                                    if(vector.getTileEntity(worldObj) instanceof IInventory && UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite()) instanceof IInventory) {

                                        UtilInventory.moveEntireInventory((IInventory)UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite()), (IInventory)vector.getTileEntity(worldObj));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(importing) {

            importing = false;
            return;
        }
        else {

            importing = true;
            return;
        }
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null ) {

                    if(tile instanceof IConductor) {

                        IConductor conduit = (IConductor)tile;

                        if(conduit.getNetwork() != null) {

                            if(conduit.getNetwork() != this.getNetwork()) {

                                conduit.getNetwork().mergeNetworks(worldObj, this.getNetwork().getAvaliableConductors());
                            }
                            else {

                                conduit.setNetwork(this.getNetwork());
                            }
                        }
                        else {

                            conduit.setNetwork(this.getNetwork());
                        }
                    }
                }

                if(tile instanceof IInventory) {

                    ((UniversalConduitNetwork)this.getNetwork()).addAvaliableInventory(new Vector3(tile));
                }

                if(tile instanceof IFluidHandler) {

                    ((UniversalConduitNetwork)this.getNetwork()).addAvaliableTank(new Vector3(tile));
                } 

                if(tile instanceof IPowerMisc) {

                    ((UniversalConduitNetwork)this.getNetwork()).postPowerRequest(new Vector3(tile));
                }
            }
        }
    }

    @Override
    public boolean setNetwork(INetwork network) {

        if(network instanceof UniversalConduitNetwork) {

            this.network = (UniversalConduitNetwork)network;
            return true;
        }
        return false;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public int[] getCoords() {

        return new int[]{xCoord, yCoord, zCoord};
    }
}
