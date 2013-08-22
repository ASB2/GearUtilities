package GU.blocks.containers.BlockCanvas;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.color.Color;
import GU.color.IColorable;
import GU.packets.ColorPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileCanvas extends TileBase implements IColorable {

    Color[] coloredSides = new Color[7];

    public TileCanvas() {
    
        waitTimer = new Wait(60, this, 0);
        
        for(int i = 0; i < coloredSides.length; i++) {
            
            coloredSides[i] = new Color();
        }
    }
    
    public void updateEntity() {    
    
        waitTimer.update();
    }
    
    @Override
    public Color getColor(ForgeDirection direction) {

        return coloredSides[direction.ordinal()];
    }

    @Override
    public boolean setColor(Color color, ForgeDirection direction) {

        coloredSides[direction.ordinal()] = color;
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {
            
            coloredSides[direction.ordinal()] = new Color( tag.getInteger("red" + direction.ordinal()), tag.getInteger("green" + direction.ordinal()), tag.getInteger("blue" + direction.ordinal()), tag.getInteger("alpha" + direction.ordinal()));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {
            

            tag.setInteger("red" + direction.ordinal(), coloredSides[direction.ordinal()].getRed());
            tag.setInteger("green" + direction.ordinal(), coloredSides[direction.ordinal()].getGreen());
            tag.setInteger("blue" + direction.ordinal(), coloredSides[direction.ordinal()].getBlue());
            tag.setInteger("alpha" + direction.ordinal(), coloredSides[direction.ordinal()].getAlpha());
        }
    }
    
    public void trigger(int id) {
        
        for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS){
            
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new ColorPacket(xCoord, yCoord, zCoord, coloredSides[direction.ordinal()], direction.ordinal()).makePacket());
        }
    }
}
