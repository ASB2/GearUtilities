package GUOLD.color;

import java.awt.Color;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.wait.Wait;
import GUOLD.api.color.IColorable;
import GUOLD.blocks.containers.TileBase;
import GUOLD.packets.ColorPacket;

public class TileColorable extends TileBase implements IColorable {
    
    protected Color[] coloredSides = new Color[7];
    
    public TileColorable() {
        
        waitTimer = new Wait(60, this, 0);
        
        for (int i = 0; i < coloredSides.length; i++) {
            
            coloredSides[i] = Color.WHITE;
        }
    }
    
    @Override
    public Color getColor(ForgeDirection direction) {
        
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return coloredSides[direction.ordinal()];
    }
    
    @Override
    public boolean setColor(Color color, ForgeDirection direction) {
        
        coloredSides[direction.ordinal()] = color;
        this.trigger(0);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            coloredSides[direction.ordinal()] = new Color(tag.getInteger("red" + direction.ordinal()), tag.getInteger("green" + direction.ordinal()), tag.getInteger("blue" + direction.ordinal()), tag.getInteger("alpha" + direction.ordinal()));
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setInteger("red" + direction.ordinal(), coloredSides[direction.ordinal()].getRed());
            tag.setInteger("green" + direction.ordinal(), coloredSides[direction.ordinal()].getGreen());
            tag.setInteger("blue" + direction.ordinal(), coloredSides[direction.ordinal()].getBlue());
            tag.setInteger("alpha" + direction.ordinal(), coloredSides[direction.ordinal()].getAlpha());
        }
    }
    
    @Override
    public void trigger(int id) {
        
        if (!worldObj.isRemote) {
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 20, worldObj.provider.dimensionId, new ColorPacket(xCoord, yCoord, zCoord, coloredSides[direction.ordinal()], direction.ordinal()).makePacket());
            }
        }
    }
    
    // @Override
    // public Object[] callMethod(IComputerAccess computer, ILuaContext context,
    // int method, Object[] arguments) throws Exception {
    //
    // if (method == 0) {
    //
    // if (arguments.length == 2) {
    //
    // if (arguments[0] instanceof Double) {
    //
    // ForgeDirection direction = ForgeDirection.getOrientation(((Double)
    // arguments[0]).intValue());
    //
    // switch (((Double) arguments[1]).intValue()) {
    //
    // case 0:
    // return new Object[] { this.getColor(direction).getRed() };
    // case 1:
    // return new Object[] { this.getColor(direction).getGreen() };
    // case 2:
    // return new Object[] { this.getColor(direction).getBlue() };
    //
    // }
    // }
    // }
    // return new String[] { "Incorrect input. Expected int side, int r/g/b" };
    // }
    //
    // if (method == 1) {
    //
    // if (arguments.length == 4) {
    //
    // if (arguments[0] instanceof Double && arguments[1] instanceof Double &&
    // arguments[2] instanceof Double && arguments[3] instanceof Double) {
    //
    // ForgeDirection direction = ForgeDirection.getOrientation(((Double)
    // arguments[0]).intValue());
    //
    // return new Boolean[] { this.setColor(new Color(((Double)
    // arguments[1]).intValue(), ((Double) arguments[2]).intValue(), ((Double)
    // arguments[3]).intValue()), direction) };
    // }
    // return new String[] {
    // "Incorrect input. Expected int side, int red value, int green value, int blue value"
    // };
    // }
    // }
    //
    // return new String[] { "Paramaters Requires 4 Integers" };
    // }
}