package GU;

import GU.packets.ColorPacket;
import GU.packets.CrystalNetPacket;
import GU.packets.CrystalTypePacket;
import GU.packets.PowerPacket;
import GU.packets.abstractPacket.MutliBlockTankPacket;

public final class PacketRegistry {
    
    private PacketRegistry() {
    }
    
    public static void init() {
        
        GearUtilities.getPipeline().registerPacket(PowerPacket.class);
        GearUtilities.getPipeline().registerPacket(CrystalTypePacket.class);
        GearUtilities.getPipeline().registerPacket(CrystalNetPacket.class);
        GearUtilities.getPipeline().registerPacket(ColorPacket.class);
        GearUtilities.getPipeline().registerPacket(MutliBlockTankPacket.class);
    }
}
