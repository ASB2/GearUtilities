package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.EnumSimulationType;

public class PowerNetAbstract {
    
    public static interface ITilePowerHandler {
        
        IPowerManager getPowerManager(ForgeDirection direction);
    }
    
    public static interface IBlockPowerHandler {
        
        IPowerManager getPowerManager(World world, int x, int y, int z, ForgeDirection direction);
    }
    
    public static interface IItemPowerHandler {
        
        IPowerManager getPowerManager(ItemStack item);
    }
    
    public static interface IPowerManager {
        
        int getStoredPower();
        
        int getMaxPower();
        
        boolean increasePower(int powerAmount, EnumSimulationType type);
        
        boolean decreasePower(int powerAmount, EnumSimulationType type);
        
        EnumPowerStatus getPowerStatus();
    }
    
    public enum EnumPowerStatus {
        
        NONE, SINK, SOURCE, BOTH;
        
        public boolean canOutput() {
            
            return this == SOURCE || this == BOTH;
        }
        
        public boolean canInput() {
            
            return this == SINK || this == BOTH;
        }
    }
}
