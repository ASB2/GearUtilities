package GU.blocks.containers.BlockConduitInterface;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.network.IPacketHandler;
import GU.api.network.Packet;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileConduitInterface extends TileBase implements IPacketHandler {

    ArrayList<Packet> packetList = new ArrayList<Packet>();
    boolean[] importing = new boolean[7];

    public TileConduitInterface() {

        this.waitTimer = new Wait(10, this, 0);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
        else {

            side = ForgeDirection.getOrientation(side).getOpposite().ordinal();

            if(importing[side]) {

                importing[side] = false;
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
    }

    @Override
    public void trigger(int id) {

    }

    @Override
    public boolean addPacket(Packet packet) {
        
        if(packet != null) {
            
            return this.packetList.add(packet);
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            importing[i] = tag.getBoolean("importing " + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            tag.setBoolean("importing " + i, importing[i]);
        }
    }
}
