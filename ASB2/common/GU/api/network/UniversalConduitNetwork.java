package GU.api.network;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class UniversalConduitNetwork implements INetwork {

    ArrayList<Vector3> conduitList = new ArrayList<Vector3>();
    ArrayList<Vector3> interfaces = new ArrayList<Vector3>();

    public UniversalConduitNetwork() {

    }


    @Override
    public void mergeNetworks(World world, ArrayList<Vector3> newNetwork) {

        if(this.getAvaliableConductors() != null && newNetwork != null) {

            for(Vector3 vector : this.getAvaliableConductors()) {

                if(vector != null && vector.getTileEntity(world) != null && vector.getTileEntity(world) instanceof IConductor) {

                    if(vector.getBlockID(world) != 0) {

                        if(!newNetwork.contains(vector)) {

                            newNetwork.add(vector);
                        }
                    }
                }
            }        
        }
    }

    @Override
    public boolean addConductor(World worldObj, Vector3 vector) {

        if(vector != null && !conduitList.contains(vector)) {

            if(worldObj != null) {

                if(vector.getTileEntity(worldObj) != null) {

                    return conduitList.add(vector);
                }
            }
            else {

                return conduitList.add(vector);
            }
        }
        return false;
    }

    @Override
    public boolean removeConductor(World world, Vector3 conduit) {

        if(conduitList.contains(conduit)) {

            return conduitList.remove(conduit);
        }
        return false;
    }

    @Override
    public ArrayList<Vector3> getAvaliableConductors() {

        conduitList.remove(null);
        return conduitList;
    }

    @Override
    public boolean addNetworkInterface(Vector3 vector) {

        if(vector != null && !interfaces.contains(vector)) {

            return interfaces.add(vector);
        } 
        return false;
    }

    @Override
    public boolean removeNetworkInterface(Vector3 vector) {

        if(vector != null && interfaces.contains(vector)) {

            return interfaces.remove(vector);
        }
        return false;
    }
    
 @Override
    public ArrayList<Vector3> getNetworkInterfaces() {

        return interfaces;
    }

    @Override
    public void onNetworkConductorBroken(World world, Vector3 tile) {
        
        Iterator<Vector3> stepByStep = conduitList.iterator();

        while(stepByStep.hasNext()) {

            Vector3 currentVector = (Vector3) stepByStep.next();

            if(currentVector.getTileEntity(world) != null) {

                if(currentVector.getTileEntity(world) instanceof IConductor) {

                    ((IConductor)currentVector.getTileEntity(world)).setNetwork(new UniversalConduitNetwork());
                }
            }
        }
        conduitList.clear();
        interfaces.clear();        
    }   
}
