package GU.api.conduit.packet;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.power.IPowerMisc;
import GU.api.conduit.*;

public class ConduitPacket implements IConduitPacket {

    IConduitNetwork network;
    Vector3 position;
    IPacketReciever packetReciever;
    ForgeDirection direction;
    PacketType type;

    boolean shouldDie;

    public ConduitPacket(IPacketReciever packetReciever, ForgeDirection direction, PacketType type, int x, int y, int z, IConduitNetwork network) {
        this(packetReciever, direction, type, new Vector3(x, y, z), network);
    }

    public ConduitPacket(IPacketReciever packetReciever, ForgeDirection direction, PacketType type, Vector3 position, IConduitNetwork network) {

        this.network = network;
        this.position = position;
        this.packetReciever = packetReciever;
        this.direction = direction;
        this.type = type;
    }

    public void updatePacket(World world) {

        if(!shouldDie) {

            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                TileEntity tile = UtilDirection.translateDirectionToTile(world, direction, position.intX(), position.intY(), position.intZ());

                if(tile != null) {

                    if(tile instanceof IInventory || tile instanceof IFluidHandler || tile instanceof IPowerMisc) {

                        packetReciever.onPacketRecieved(tile.xCoord, tile.yCoord, tile.zCoord, this);
                        shouldDie = true;
                    }
                }
            }

            TileEntity tile = position.clone().add(new Vector3(direction)).getTileEntity(world);

            if(tile != null && tile instanceof IConduitConductor) {

                if(((IConduitConductor)tile).getNetwork() != null) {

                    if(((IConduitConductor)tile).getNetwork() != this.network) {
                        
                        ((IConduitConductor)tile).getNetwork().addConduitPacketToQuene(this);
                        this.network.removeConduitPacketFromQuene(this);
                    }             
                }
            }
            else {

                ForgeDirection temp = UtilDirection.translateDirectionToRandomRightAngle(direction);
                
                for(ForgeDirection tempD : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile1 = position.clone().add(new Vector3(tempD)).getTileEntity(world);
                    
                    if(tile1 != null && tile instanceof IConduitConductor) {
                        
                        direction = temp;
                        return;
                    }
                    else {
                        
                        temp = UtilDirection.translateDirectionToRandomRightAngle(direction);
                    }
                }
            }
        }
    }

    @Override
    public boolean destory(World world) {

        return shouldDie;
    }
    
    @Override
    public Vector3 getPosition() {

        return position;
    }

    public void savePacket(NBTTagCompound tag) {

    }

    public void loadPacket(NBTTagCompound tag) {

    }

    public PacketType getPacketType() {

        return type;
    }

    public ForgeDirection getDirection() {

        return direction;
    }
}
