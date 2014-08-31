package GU.blocks.containers.BlockTeleportAlter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import GU.blocks.containers.TileBase;

public class TileTeleportAlter extends TileBase {
    
    double x, y, z;
    int dimension;
    boolean coordsSet;
    
    public TileTeleportAlter() {
        // TODO Auto-generated constructor stub
    }
    
    public void setCoords(double x, double y, double z, int dimension) {
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
        coordsSet = true;
    }
    
    public void clearCoords() {
        
        coordsSet = false;
    }
    
    public void teleport(EntityPlayer entity) {
        
        if (!worldObj.isRemote) {
            
            if (!coordsSet) {
                
                return;
            }
            if (dimension != entity.dimension) {
                
                entity.travelToDimension(dimension);
            }
            entity.setPositionAndUpdate(x, y, z);
        }
    }
    
    public boolean isCoordsSet() {
        
        return coordsSet;
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        x = tag.getDouble("xPos");
        y = tag.getDouble("yPos");
        z = tag.getDouble("zPos");
        dimension = tag.getInteger("dimension");
        coordsSet = tag.getBoolean("coordsSet");
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("xPos", x);
        tag.setDouble("yPos", y);
        tag.setDouble("zPos", z);
        tag.setInteger("dimension", dimension);
        tag.setBoolean("coordsSet", coordsSet);
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        x = tag.getDouble("xPos");
        y = tag.getDouble("yPos");
        z = tag.getDouble("zPos");
        dimension = tag.getInteger("dimension");
        coordsSet = tag.getBoolean("coordsSet");
        
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        tag.setDouble("xPos", x);
        tag.setDouble("yPos", y);
        tag.setDouble("zPos", z);
        tag.setInteger("dimension", dimension);
        tag.setBoolean("coordsSet", coordsSet);
        super.writeToNBT(tag);
    }
}
