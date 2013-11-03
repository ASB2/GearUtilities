package GU.api.network;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class UniversalConduitNetwork implements INetwork {

    ArrayList<Vector3> conduitList = new ArrayList<Vector3>();
    ArrayList<Vector3> fluidInterfaces = new ArrayList<Vector3>();
    ArrayList<Vector3> itemInterfaces = new ArrayList<Vector3>();
    ArrayList<Vector3> powerInterfaces = new ArrayList<Vector3>();

    public UniversalConduitNetwork() {

    }

    @Override
    public void mergeNetworks(World world, INetwork newNetwork) {

        if(this.getConductors() != null && newNetwork != null) {

            for(Vector3 vector : this.getConductors()) {

                if(vector != null && vector.getTileEntity(world) != null && vector.getTileEntity(world) instanceof IConductor) {

                    if(vector.getBlockID(world) != 0) {

                        if(!newNetwork.getConductors().contains(vector)) {

                            newNetwork.addConductor(vector);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean addConductor(Vector3 vector) {

        if(vector != null && !conduitList.contains(vector)) {

            return conduitList.add(vector);
        }
        return false;
    }

    @Override
    public boolean removeConductor(Vector3 conduit) {

        if(conduitList.contains(conduit)) {

            return conduitList.remove(conduit);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Vector3> getConductors() {

        return (ArrayList<Vector3>) conduitList.clone();
    }

    @Override
    public void onNetworkConductorBroken(World world, Vector3 tile) {

        Iterator<Vector3> stepByStep = conduitList.iterator();

        while (stepByStep.hasNext()) {

            Vector3 currentVector = (Vector3) stepByStep.next();

            if(currentVector.getTileEntity(world) != null) {

                if(currentVector.getTileEntity(world) instanceof IConductor) {

                    ((IConductor) currentVector.getTileEntity(world)).setNetwork(new UniversalConduitNetwork());
                }
            }
        }
        conduitList.clear();
    }

    @Override
    public boolean addFluidInterface(Vector3 vector) {

        if(vector != null && !fluidInterfaces.contains(vector)) {

            return fluidInterfaces.add(vector);
        }
        return false;
    }

    @Override
    public boolean removeFluidInterface(Vector3 interfaces) {

        return fluidInterfaces.remove(interfaces);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Vector3> getFluidInterfaces() {

        return (ArrayList<Vector3>) fluidInterfaces.clone();
    }

    @Override
    public boolean addItemInterface(Vector3 vector) {

        if(vector != null && !itemInterfaces.contains(vector)) {

            return itemInterfaces.add(vector);
        }
        return false;
    }

    @Override
    public boolean removeItemInterface(Vector3 interfaces) {

        return itemInterfaces.remove(interfaces);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Vector3> getItemInterfaces() {

        return (ArrayList<Vector3>) itemInterfaces.clone();
    }

    @Override
    public boolean addGUUPowerInterface(Vector3 vector) {

        if(vector != null && !powerInterfaces.contains(vector)) {

            return powerInterfaces.add(vector);
        }
        return false;
    }

    @Override
    public boolean removeGUUPowerInterface(Vector3 interfaces) {

        return powerInterfaces.remove(interfaces);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Vector3> getGUUPowerInterfaces() {

        return (ArrayList<Vector3>) powerInterfaces.clone();
    }
}
