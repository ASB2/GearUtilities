package GU.api.crystals;

import java.util.LinkedList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilVector;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerAttribute;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import UC.math.vector.Vector3i;

public final class CrystalNetwork {
    
    World worldObj;
    Vector3i corePosition;
    DefaultPowerManager storedPower;
    DefaultPowerAttribute attribute;
    LinkedList<ICrystalNetworkPart> crystals = new LinkedList<ICrystalNetworkPart>();
    int crystalNumber;
    
    public CrystalNetwork(World world, Vector3i corePosition) {
        
        this.worldObj = world;
        this.corePosition = corePosition;
        storedPower = new DefaultPowerManager().setPowerMax(20);
        attribute = new DefaultPowerAttribute().setPowerStatus(EnumPowerStatus.BOTH);
    }
    
    public World getWorldObj() {
        
        return worldObj;
    }
    
    public void addCrystal(ICrystalNetworkPart crystal) {
        
        if (crystal.setCrystalNetwork(this)) {
            
            crystals.add(0, crystal);
            crystalNumber++;
            if (crystal instanceof ICrystalPowerHandler) {
                
                IPowerManager manager = ((ICrystalPowerHandler) crystal).getPowerManager();
                
                if (manager != null) {
                    
                    if (manager.getMaxPower() > 0) {
                        
                        if (manager.getStoredPower() > 0) {
                            
                            storedPower.changeStoredPower(manager.getStoredPower());
                        }
                        storedPower.changeMaxPower(manager.getMaxPower());
                    }
                }
            }
        }
    }
    
    public void removeCrystal(ICrystalNetworkPart crystal) {
        
        crystalNumber--;
        crystals.remove(crystal);
        crystal.setCrystalNetwork(null);
    }
    
    public DefaultPowerManager getPowerManager() {
        
        return storedPower;
    }
    
    public DefaultPowerAttribute getAttribute() {
        
        return attribute;
    }
    
    public int getNetworkSize() {
        
        return crystalNumber;
    }
    
    public Vector3i getCorePosition() {
        
        return corePosition.clone();
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("corePosition", UtilVector.saveVector(new NBTTagCompound(), corePosition));
        tag.setTag("storedPower", storedPower.save(new NBTTagCompound()));
        tag.setTag("attribute", attribute.save(new NBTTagCompound()));
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        corePosition = UtilVector.loadVector3i(tag.getCompoundTag("corePosition"));
        storedPower.load(tag.getCompoundTag("corePosition"));
        attribute.load(tag.getCompoundTag("attribute"));
    }
}
