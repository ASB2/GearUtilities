package GU.api.power;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PowerManager implements ITickHandler {

    public static PowerManager instance = new PowerManager();

    public NBTTagCompound stackTagCompound = new NBTTagCompound();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<PowerProvider> powerProviders = new ArrayList();

    public void addPowerProvider(PowerProvider provider) {

        powerProviders.add(provider);
    }

    public static PowerManager getInstance() {

        return instance;
    }

    public List<PowerProvider> getPowerProviderList() {

        return powerProviders;
    }

    public void updatePowerProviders() {

        for(int i = 0; i < this.powerProviders.size(); i++) {

            if(powerProviders.get(i) != null) {

                powerProviders.get(i).updateProvider();
            }
            else {

                powerProviders.remove(i);
            }
        }
    }

    public void readFromNBT(NBTTagCompound tag) {

        for(int i = 0; i < this.powerProviders.size(); i++) {

            if(powerProviders.get(i) != null) {

                powerProviders.get(i).readFromNBT(tag);
            }
            else {

                powerProviders.remove(i);
            }
        }
    }
    
    public void writeToNBT(NBTTagCompound tag) {
    
        for(int i = 0; i < this.powerProviders.size(); i++) {

            if(powerProviders.get(i) != null) {

                powerProviders.get(i).writeToNBT(tag);
            }
            else {

                powerProviders.remove(i);
            }
        }
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        this.updatePowerProviders();
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return "Power Ticker";
    }
}
