package GU;

import net.minecraftforge.fml.relauncher.Side;
import GU.packets.ColorPacket;
import GU.packets.MultiBlockFlamePacket;
import GU.packets.MutliBlockTankPacket;
import GU.packets.NBTPacket;

public final class PacketRegistry {
    
    private PacketRegistry() {
    }
    
    public static void init() {
        
        GearUtilities.getPipeline().registerMessage(ColorPacket.class, ColorPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(MutliBlockTankPacket.class, MutliBlockTankPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(MultiBlockFlamePacket.class, MultiBlockFlamePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(NBTPacket.class, NBTPacket.class, getNextID(), Side.CLIENT);
    }
    
    static int ID = -1;
    
    public static int getNextID() {
        
        return ID++;
    }
}
