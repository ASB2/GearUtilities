package GU.api.conduit;

import java.util.ArrayList;

import net.minecraft.world.World;

public class ConduitNetwork implements IConduitNetwork {

    ArrayList<IConduitConductor> conduitList = new ArrayList<IConduitConductor>();

    public ConduitNetwork() {

    }

    @Override
    public void updateNetwork(World worldObj, int x, int y, int z) {

        if(!conduitList.isEmpty()) {

            for(IConduitConductor conduit : this.getAvaliableConductors()) {

                if(worldObj.getBlockId(conduit.getCoords()[0], conduit.getCoords()[1], conduit.getCoords()[2]) != 0) {

                    if(worldObj.getBlockTileEntity(conduit.getCoords()[0], conduit.getCoords()[1], conduit.getCoords()[2]) == null) {

                        conduitList.remove(conduit);
                        break;
                    }
                }
                else {

                    conduitList.remove(conduit);
                    break;
                }
            }
        }
    }


    @Override
    public void mergeNetworks(World world, ArrayList<IConduitConductor> newNetwork) {

        if(this.getAvaliableConductors() != null) {

            for(IConduitConductor conductor : this.getAvaliableConductors()) {

                if(conductor != null) {

                    if(world.getBlockId(conductor.getCoords()[0], conductor.getCoords()[1], conductor.getCoords()[2]) != 0) {

                        if(world.getBlockTileEntity(conductor.getCoords()[0], conductor.getCoords()[1], conductor.getCoords()[2]) != null) {

                            if(!newNetwork.contains(conductor)) {

                                newNetwork.add(conductor);
                            }
                        }
                    }
                }
            }        
        }
    }

    @Override
    public boolean addConductor(World worldObj, IConduitConductor conduit) {

        if(conduit != null) {

            if(worldObj != null) {

                if(worldObj.getBlockTileEntity(conduit.getCoords()[0], conduit.getCoords()[1], conduit.getCoords()[2]) != null) {

                    return conduitList.add(conduit);
                }
            }
            else {

                return conduitList.add(conduit);
            }
        }
        return false;
    }

    @Override
    public ArrayList<IConduitConductor> getAvaliableConductors() {

        return conduitList;
    }
}
