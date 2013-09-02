package GU.api.conduit;

import java.util.ArrayList;

import net.minecraftforge.common.ForgeDirection;

public interface IConduitConductor {

    boolean addPacketToQuene(ConduitPacket packet, ForgeDirection direction, boolean doMove);

    ArrayList<ConduitPacket> getQuenedPackets();
    
    IConduitNetwork getNetwork();
}
