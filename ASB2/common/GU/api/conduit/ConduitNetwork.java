package GU.api.conduit;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.World;
import GU.api.conduit.packet.IConduitPacket;

public class ConduitNetwork implements IConduitNetwork {

    ArrayList<IConduitConductor> conduitList = new ArrayList<IConduitConductor>();
    ArrayList<IConduitPacket> conduitPackets = new ArrayList<IConduitPacket>();

    public ConduitNetwork() {

    }

    @Override
    public void updateNetwork(World worldObj) {

        if(!conduitPackets.isEmpty()) {

            Iterator<IConduitPacket> packetIt = conduitPackets.iterator();

            while(packetIt.hasNext()) {

                IConduitPacket packet = packetIt.next();

                if(packet != null) {

                    if(!packet.destory(worldObj)) {
                        
                        packet.updatePacket(worldObj);
                    }
                    else {
                        
                        packetIt.remove();
                    }
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
    public boolean addConduitPacketToQuene(IConduitPacket packet) {

        if(packet != null && !conduitPackets.contains(packet)) {

            return conduitPackets.add(packet);
        }
        return false;
    }


    @Override
    public boolean removeConduitPacketFromQuene(IConduitPacket packet) {

        if(conduitPackets.contains(packet)) {

            return conduitPackets.remove(packet);
        }
        return false;
    }

    @Override
    public ArrayList<IConduitPacket> getQuenedPackets() {

        return conduitPackets;
    }

    @Override
    public boolean addConductor(World worldObj, IConduitConductor conduit) {

        if(conduit != null && !conduitList.contains(conduit)) {

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
    public boolean removeConductor(World world, IConduitConductor conduit) {

        if(conduitList.contains(conduit)) {

            return conduitList.remove(conduit);
        }
        return false;
    }

    @Override
    public ArrayList<IConduitConductor> getAvaliableConductors() {

        return conduitList;
    }

    public void recalculateList(World worldObj) {

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
}
