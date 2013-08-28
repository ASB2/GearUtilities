package GU.blocks.containers.BlockLamp;

import java.awt.Color;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraftforge.common.ForgeDirection;
import GU.color.TileColorable;
import GU.packets.ColorPacket;

public class TileLamp extends TileColorable {

    @Override
    public Color getColor(ForgeDirection direction) {

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return coloredSides[ForgeDirection.DOWN.ordinal()];
    }

    @Override
    public boolean setColor(Color color, ForgeDirection direction) {

        coloredSides[ForgeDirection.DOWN.ordinal()] = color;
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return true;
    }

    public int getLightValue() {
        
       
        return 15;
    }
    
    public void trigger(int id) {

        PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new ColorPacket(xCoord, yCoord, zCoord, coloredSides[ForgeDirection.DOWN.ordinal()], ForgeDirection.DOWN.ordinal()).makePacket());
    }
}
