package GU.api.crystals;

import java.util.LinkedList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilVector;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import UC.math.vector.Vector3i;

public final class CrystalNetwork {
    
    World worldObj;
    Vector3i corePosition;
    DefaultPowerManager storedPower;
    LinkedList<ICrystalNetworkPart> crystals = new LinkedList<ICrystalNetworkPart>();
    
    public CrystalNetwork(World world, Vector3i corePosition) {
        
        this.worldObj = world;
        this.corePosition = corePosition;
        storedPower = new DefaultPowerManager();
    }
    
    public World getWorldObj() {
        
        return worldObj;
    }
    
    public void addCrystal(ICrystalNetworkPart crystal) {
        
        crystals.add(crystal);
        crystal.setCrystalNetwork(this);
    }
    
    public void removeCrystal(ICrystalNetworkPart crystal) {
        
        crystals.remove(crystal);
        crystal.setCrystalNetwork(null);
    }
    
    public DefaultPowerManager getPowerManager() {
        
        return storedPower;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("corePosition", UtilVector.saveVector(tag, corePosition));
        tag.setTag("storedPower", storedPower.save(tag));
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        corePosition = UtilVector.loadVector3i(tag.getCompoundTag("corePosition"));
        storedPower.load(tag.getCompoundTag("corePosition"));
    }
}
