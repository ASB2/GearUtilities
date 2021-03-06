package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerManager;

public class PowerNetObject {
    
    public static class DefaultPowerManager implements IPowerManager {
        
        int powerStored = 0, powerMax = 0;
        EnumPowerStatus powerStatus;
        
        public DefaultPowerManager() {
            this(0, 0);
        }
        
        public DefaultPowerManager(int powerMax) {
            this(0, powerMax);
        }
        
        public DefaultPowerManager(int powerStored, int powerMax) {
            
            this.powerStored = powerStored;
            this.powerMax = powerMax;
            powerStatus = EnumPowerStatus.BOTH;
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
        public boolean increasePower(int powerAmount, EnumSimulationType type) {
            
            if (powerStatus.canInput() || type.isForced()) {
                
                if (powerStored + powerAmount <= powerMax) {
                    
                    if (type.isLigitimate()) {
                        
                        powerStored += powerAmount;
                    }
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public boolean decreasePower(int powerAmount, EnumSimulationType type) {
            
            if (powerStatus.canOutput() || type.isForced()) {
                
                if (powerStored - powerAmount >= 0) {
                    
                    if (type.isLigitimate()) {
                        
                        powerStored -= powerAmount;
                    }
                    return true;
                }
            }
            return false;
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
        
        public DefaultPowerManager setPowerStatus(EnumPowerStatus powerStatus) {
            this.powerStatus = powerStatus;
            return this;
        }
        
        @Override
        public EnumPowerStatus getPowerStatus() {
            // TODO Auto-generated method stub
            return powerStatus;
        }
        
        public DefaultPowerManager clone() {
            
            return new DefaultPowerManager().setPowerMax(this.getMaxPower()).setPowerStored(this.getStoredPower());
        }
        
        public NBTTagCompound save(NBTTagCompound tag) {
            
            tag.setInteger("powerStored", powerStored);
            tag.setInteger("powerMax", powerMax);
            tag.setInteger("powerStatus", powerStatus.ordinal());
            return tag;
        }
        
        public void load(NBTTagCompound tag) {
            
            powerStored = tag.getInteger("powerStored");
            powerMax = tag.getInteger("powerMax");
            powerStatus = EnumPowerStatus.values()[tag.getInteger("powerStatus")];
        }
    }
    
    public static final class UtilPower {
        
        private UtilPower() {
        }
        
        public static boolean movePower(IPowerManager source, IPowerManager sink, int powerAmount, EnumSimulationType type) {
            
            if (source.decreasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                if (sink.increasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                    
                    return source.decreasePower(powerAmount, type) && sink.increasePower(powerAmount, type);
                }
            }
            return false;
        }
        
        public static boolean addPower(IPowerManager sink, int powerAmount, EnumSimulationType type) {
            
            if (sink.increasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                return sink.increasePower(powerAmount, type);
            }
            return false;
        }
        
        public static boolean removePower(IPowerManager source, int powerAmount, EnumSimulationType type) {
            
            if (source.decreasePower(powerAmount, EnumSimulationType.SIMULATE)) {
                
                return source.decreasePower(powerAmount, type);
            }
            return false;
        }
    }
}
