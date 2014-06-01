package GU;

import GU.packets.ColorPacket;
import GU.packets.CrystalTypePacket;
import GU.packets.MutliBlockTankPacket;
import GU.packets.PowerPacket;
import cpw.mods.fml.relauncher.Side;

public final class PacketRegistry {
    
    private PacketRegistry() {
    }
    
    public static void init() {
        
        GearUtilities.getPipeline().registerMessage(PowerPacket.class, PowerPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(CrystalTypePacket.class, CrystalTypePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(ColorPacket.class, ColorPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(MutliBlockTankPacket.class, MutliBlockTankPacket.class, getNextID(), Side.CLIENT);
    }
    
    static int ID = -1;
    
    public static int getNextID() {
        
        return ID++;
    }
}
