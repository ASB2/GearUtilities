package GU.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import GU.info.Reference;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class GUPacketBase {

    private static final BiMap<Integer, Class<? extends GUPacketBase>> idMap;

    static {
        ImmutableBiMap.Builder<Integer, Class<? extends GUPacketBase>> builder = ImmutableBiMap.builder();

        builder.put(Integer.valueOf(0), ConnectableTankPacket.class);
        builder.put(Integer.valueOf(1), ColorPacket.class);
        
        idMap = builder.build();
    }

    public static GUPacketBase constructPacket(int packetId)
            throws ProtocolException, ReflectiveOperationException {

        Class<? extends GUPacketBase> clazz = idMap.get(Integer
                .valueOf(packetId));

        if (clazz == null) {

            throw new ProtocolException("Unknown Packet Id!");
        } else {

            return clazz.newInstance();
        }
    }

    public final int getPacketId() {

        if (idMap.inverse().containsKey(getClass())) {

            return idMap.inverse().get(getClass()).intValue();
        } else {

            throw new RuntimeException("Packet " + getClass().getSimpleName() + " is missing a mapping!");
        }
    }

    public final Packet makePacket() {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(getPacketId());
        write(out);
        return PacketDispatcher.getPacket(Reference.MOD_CHANNEL,
                out.toByteArray());
    }

    @SuppressWarnings("serial")
    public static class ProtocolException extends Exception {

        public ProtocolException() {
        }

        public ProtocolException(String message, Throwable cause) {
            super(message, cause);
        }

        public ProtocolException(String message) {
            super(message);
        }

        public ProtocolException(Throwable cause) {
            super(cause);
        }
    }

    protected abstract void write(ByteArrayDataOutput out);

    protected abstract void read(ByteArrayDataInput in)
            throws ProtocolException;

    protected abstract void execute(EntityPlayer player, Side side)
            throws ProtocolException;
}
