package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.*;

public class PowerNetObject {
    
    public static class DefaultPowerManager implements IPowerManager {
        
        int powerStored = 0, powerMax = 0;
        
        int minInputPacketSize = 0, maxInputPacketSize = 100;
        
        int minOutputPacketSize = 0, maxOutputPacketSize = 100;
        
        public DefaultPowerManager() {
            this(0, 0);
        }
        
        public DefaultPowerManager(int powerStored, int powerMax) {
            
            this.powerStored = powerStored;
            this.powerMax = powerMax;
        }
        
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
        
        public DefaultPowerManager changeMaxPower(int powerChange) {
            powerMax += powerChange;
            return this;
        }
        
        public DefaultPowerManager changeStoredPower(int powerChange) {
            powerStored += powerChange;
            return this;
        }
        
        public DefaultPowerManager clone() {
            
            return new DefaultPowerManager().setMaxInputPacketSize(this.getMaxInputPacketSize()).setMinInputPacketSize(this.getMinInputPacketSize()).setMaxOutputPacketSize(this.getMaxOutputPacketSize()).setMinOutputPacketSize(this.getMinOutputPacketSize()).setPowerMax(this.getMaxPower()).setPowerStored(this.getStoredPower());
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
    
    public static class DefaultPowerAttribute implements IPowerAttribute {
        
        EnumPowerStatus powerStatus;
        
        public DefaultPowerAttribute() {
            this(EnumPowerStatus.NONE);
        }
        
        public DefaultPowerAttribute(EnumPowerStatus status) {
            
            powerStatus = status;
        }
        
        public DefaultPowerAttribute setPowerStatus(EnumPowerStatus powerStatus) {
            this.powerStatus = powerStatus;
            return this;
        }
        
        @Override
        public EnumPowerStatus getPowerStatus() {
            
            return powerStatus;
        }
        
        public NBTTagCompound save(NBTTagCompound tag) {
            
            tag.setInteger("powerStatus", powerStatus.ordinal());
            return tag;
        }
        
        public void load(NBTTagCompound tag) {
            
            powerStatus = EnumPowerStatus.values()[tag.getInteger("powerStatus")];
        }
    }
    
    public static final class UtilPower {
        
        private UtilPower() {
        }
        
        public static boolean movePower(IPowerManager source, IPowerManager sink, int powerAmount, EnumSimulationType type) {
            
            if (!(type == EnumSimulationType.FORCED || type == EnumSimulationType.FORCED_SIMULATE)) {
                
                if (!(source.getMinOutputPacketSize() <= powerAmount && source.getMaxOutputPacketSize() >= powerAmount)) {
                    
                    return false;
                }
                if (!(sink.getMinInputPacketSize() <= powerAmount && sink.getMaxInputPacketSize() >= powerAmount)) {
                    
                    return false;
                }
            }
            if (source.decreasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                if (sink.increasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                    
                    return source.decreasePower(powerAmount, type) && sink.increasePower(powerAmount, type);
                }
            }
            return false;
        }
        
        public static boolean addPower(IPowerManager sink, int powerAmount, EnumSimulationType type) {
            
            if (!(type == EnumSimulationType.FORCED || type == EnumSimulationType.FORCED_SIMULATE)) {
                
                if (!(sink.getMinInputPacketSize() <= powerAmount && sink.getMaxInputPacketSize() >= powerAmount)) {
                    
                    return false;
                }
            }
            
            if (sink.increasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                return sink.increasePower(powerAmount, type);
            }
            return false;
        }
        
        public static boolean removePower(IPowerManager source, int powerAmount, EnumSimulationType type) {
            
            if (!(type == EnumSimulationType.FORCED || type == EnumSimulationType.FORCED_SIMULATE)) {
                
                if (!(source.getMinOutputPacketSize() <= powerAmount && source.getMaxOutputPacketSize() >= powerAmount)) {
                    
                    return false;
                }
            }
            if (source.decreasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                return source.decreasePower(powerAmount, type);
            }
            return false;
        }
    }
}
