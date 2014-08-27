package GU;

import GU.packets.ColorPacket;
import GU.packets.ConduitTypePacket;
import GU.packets.CrystalTypePacket;
import GU.packets.EnumInputIconPacket;
import GU.packets.MultiBlockFlamePacket;
import GU.packets.MutliBlockTankPacket;
import GU.packets.NBTPacket;
import GU.packets.PowerPacket;
import GU.packets.TankUpdatePacket;
import cpw.mods.fml.relauncher.Side;

public final class PacketRegistry {
    
    private PacketRegistry() {
    }
    
    public static void init() {
        
        GearUtilities.getPipeline().registerMessage(PowerPacket.class, PowerPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(CrystalTypePacket.class, CrystalTypePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(ColorPacket.class, ColorPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(MutliBlockTankPacket.class, MutliBlockTankPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(EnumInputIconPacket.class, EnumInputIconPacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(TankUpdatePacket.class, TankUpdatePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(MultiBlockFlamePacket.class, MultiBlockFlamePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(ConduitTypePacket.class, ConduitTypePacket.class, getNextID(), Side.CLIENT);
        GearUtilities.getPipeline().registerMessage(NBTPacket.class, NBTPacket.class, getNextID(), Side.CLIENT);
    }
    
    static int ID = -1;
    
    public static int getNextID() {
        
        return ID++;
    }
}
