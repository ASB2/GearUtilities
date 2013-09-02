package GU.blocks.containers.BlockUniversalConduit;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.conduit.ConduitNetwork;
import GU.api.conduit.IConduitConductor;
import GU.api.conduit.IConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileUniversalConduit extends TileBase implements IConduitConductor {

    IConduitNetwork network;

    public TileUniversalConduit() {

        this.waitTimer = new Wait(20, this, 0);        
        network = new ConduitNetwork();
    }

    public void updateEntity() {

        waitTimer.update();

        if(this.getNetwork() != null) {

            this.getNetwork().updateNetwork(worldObj, xCoord, yCoord, zCoord);

            if(!this.getNetwork().getAvaliableConductors().contains(this)) {

                this.getNetwork().addConductor(worldObj, this);
            }

            if(this.hasValidTile(this.xCoord, this.yCoord, this.zCoord, 1)) {

                HashMap<Integer, IConduitConductor> closeTiles = getNearestValidConduit(worldObj, this, 0);

                if(!closeTiles.isEmpty()) {

                    for(IConduitConductor conduit : closeTiles.values()) {

                        if(conduit != null) {

                            this.moveStuff(this, conduit);
                        }
                    }                               
                }
            }
        }
    }

    public void moveStuff(IConduitConductor source, IConduitConductor destination) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tileDestination = UtilDirection.translateDirectionToTile(worldObj, direction, destination.getCoords()[0], destination.getCoords()[1], destination.getCoords()[2]);

            if(tileDestination != null) {

                if(tileDestination instanceof IInventory) {

                    for(ForgeDirection direction2 : ForgeDirection.VALID_DIRECTIONS) {

                        TileEntity tileSource = UtilDirection.translateDirectionToTile(this, worldObj, direction2);

                        if(tileSource != null) {

                            if(tileSource instanceof IInventory) {

                                UtilInventory.moveEntireInventory((IInventory)tileSource, (IInventory)tileDestination);
                            }
                        }
                    }
                }

                if(tileDestination instanceof IPowerMisc) {

                    for(ForgeDirection direction2 : ForgeDirection.VALID_DIRECTIONS) {

                        TileEntity tileSource = UtilDirection.translateDirectionToTile(this, worldObj, direction2);

                        if(tileSource != null) {

                            if(tileSource instanceof IPowerMisc) {

                                PowerHelper.moveEnergy((IPowerMisc)tileSource, (IPowerMisc)tileDestination, ForgeDirection.UNKNOWN, true);
                            }
                        }
                    }
                }

                if(tileDestination instanceof IFluidHandler) {

                    for(ForgeDirection direction2 : ForgeDirection.VALID_DIRECTIONS) {

                        TileEntity tileSource = UtilDirection.translateDirectionToTile(this, worldObj, direction2);

                        if(tileSource != null) {

                            if(tileSource instanceof IFluidHandler) {

                                UtilFluid.moveFluid((IFluidHandler)tileSource, ForgeDirection.UNKNOWN, (IFluidHandler)tileDestination, 1000, true);
                            }
                        }
                    }
                }
            }
        }
    }

    public HashMap<Integer, IConduitConductor> getNearestValidConduit(World world, IConduitConductor source, int id) {

        HashMap<Integer, IConduitConductor> conduitsToDistance = new HashMap<Integer, IConduitConductor>();

        int distance = 0;        
        IConduitConductor last = source;

        if(source != null) {

            conduitsToDistance.put(0, source);

            while(true) {

                ArrayList<IConduitConductor> conductors = getValidConductors(conduitsToDistance, world, last, id);

                if(!conductors.isEmpty() && !hasDuplicate(conduitsToDistance, conductors)) {

                    distance++;

                    for(IConduitConductor conduit : conductors) {

                        conduitsToDistance.put(distance, conduit);
                        last = conduit;

                        conductors = getValidConductors(conduitsToDistance, world, last, id);
                    }
                }
                else {

                    break;
                }
            }
        }
        return conduitsToDistance;
    }

    public ArrayList<IConduitConductor> getValidConductors(HashMap<Integer, IConduitConductor> conduitsToDistance, World world, IConduitConductor source, int id) {

        ArrayList<IConduitConductor> sourrounding = new ArrayList<IConduitConductor>();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(world, direction, source.getCoords()[1], source.getCoords()[2], source.getCoords()[0]);

            if(tile != null) {

                if(tile instanceof IConduitConductor && !conduitsToDistance.containsKey(tile)) {

                    if(source.isConditValid(world, (IConduitConductor)tile, id)) {

                        sourrounding.add((IConduitConductor)tile);
                    }
                }
            }
        }
        return sourrounding;
    }

    public boolean hasDuplicate(HashMap<Integer, IConduitConductor> conduitsToDistance, ArrayList<IConduitConductor> conductors) {


        for(IConduitConductor conduitConductor : conductors) {

            if(conduitsToDistance.containsKey(conduitConductor)) {

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isConditValid(World world, IConduitConductor conduit, int id) {

        return this.hasValidTile(conduit.getCoords()[0], conduit.getCoords()[1], conduit.getCoords()[2], id);
    }

    public boolean hasValidTile(int x, int y, int z, int id) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(worldObj, direction, x, y, z);

            if(tile != null) {

                if((tile instanceof IInventory || tile instanceof IPowerMisc || tile instanceof IFluidHandler)) {

                    return true;
                }
                else if(tile instanceof IConduitConductor && id != 1) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                if(tile != null && tile instanceof IConduitConductor) {

                    IConduitConductor conduit = (IConduitConductor)tile;

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
        }
    }

    @Override
    public boolean setNetwork(IConduitNetwork network) {

        this.network = network;
        return true;
    }

    @Override
    public IConduitNetwork getNetwork() {

        return network;
    }

    @Override
    public int[] getCoords() {

        return new int[]{xCoord, yCoord, zCoord};
    }
}
