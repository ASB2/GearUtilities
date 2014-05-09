package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.IPowerManager;

public class PowerNetObject {
    
    public static class DefaultPowerManager implements IPowerManager {
        
        int powerStored = 0, powerMax = 0;
        
        int minInputPacketSize = 0, maxInputPacketSize = 100;
        
        int minOutputPacketSize = 0, maxOutputPacketSize = 100;
        
        @Override
        public int getStoredPower() {
            
            return powerStored;
        }
        
        @Override
        public int getMaxPower() {
            
            return powerMax;
        }
        
        @Override
        public int getMinInputPacketSize() {
            
            return minInputPacketSize;
        }
        
        @Override
        public int getMaxInputPacketSize() {
            
            return maxInputPacketSize;
        }
        
        @Override
        public int getMinOutputPacketSize() {
            
            return minOutputPacketSize;
        }
        
        @Override
        public int getMaxOutputPacketSize() {
            
            return maxOutputPacketSize;
        }
        
        @Override
        public boolean increasePower(int powerAmount, EnumSimulationType type) {
            
            if (powerStored + powerAmount <= powerMax) {
                
                if (type.getBooleanValue()) {
                    
                    powerStored += powerAmount;
                }
                return true;
            }
            return false;
        }
        
        @Override
        public boolean decreasePower(int powerAmount, EnumSimulationType type) {
            
            if (powerStored - powerAmount >= 0) {
                
                if (type.getBooleanValue()) {
                    
                    powerStored -= powerAmount;
                }
                return true;
            }
            return false;
        }
        
        public DefaultPowerManager setMaxInputPacketSize(int maxInputPacketSize) {
            this.maxInputPacketSize = maxInputPacketSize;
            return this;
        }
        
        public DefaultPowerManager setMaxOutputPacketSize(int maxOutputPacketSize) {
            this.maxOutputPacketSize = maxOutputPacketSize;
            return this;
        }
        
        public DefaultPowerManager setMinInputPacketSize(int minInputPacketSize) {
            this.minInputPacketSize = minInputPacketSize;
            return this;
        }
        
        public DefaultPowerManager setMinOutputPacketSize(int minOutputPacketSize) {
            this.minOutputPacketSize = minOutputPacketSize;
            return this;
        }
        
        public DefaultPowerManager setPowerMax(int powerMax) {
            this.powerMax = powerMax;
            return this;
        }
        
        public DefaultPowerManager setPowerStored(int powerStored) {
            this.powerStored = powerStored;
            return this;
        }
        
        public NBTTagCompound save(NBTTagCompound tag) {
            
            tag.setInteger("powerStored", powerStored);
            tag.setInteger("powerMax", powerMax);
            tag.setInteger("minInputPacketSize", minInputPacketSize);
            tag.setInteger("maxInputPacketSize", maxInputPacketSize);
            tag.setInteger("minOutputPacketSize", minOutputPacketSize);
            tag.setInteger("maxOutputPacketSize", maxOutputPacketSize);
            return tag;
        }
        
        public void load(NBTTagCompound tag) {
            
            powerStored = tag.getInteger("powerStored");
            powerMax = tag.getInteger("powerMax");
            minInputPacketSize = tag.getInteger("minInputPacketSize");
            maxInputPacketSize = tag.getInteger("maxInputPacketSize");
            minOutputPacketSize = tag.getInteger("minOutputPacketSize");
            maxOutputPacketSize = tag.getInteger("maxOutputPacketSize");
            powerStored = tag.getInteger("powerStored");
        }
    }
}
