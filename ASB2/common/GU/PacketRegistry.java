package GU;

import GU.packets.PowerPacket;

public final class PacketRegistry {
    
    private PacketRegistry() {
    }
    
    public static void init() {
        
        GearUtilities.packetPipeline.registerPacket(PowerPacket.class);
    }
}
