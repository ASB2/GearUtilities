package GU.api.conduit.packet;

public interface IPacketReciever {
    
    void onPacketRecieved(int x, int y, int z, IConduitPacket packet);
}
