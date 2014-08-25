package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.EnumSimulationType;

public class PowerNetAbstract {
    
    public static interface ITilePowerHandler {
        
        IPowerManager getPowerManager();
        
        IPowerAttribute getPowerAttribute();
    }
    
    public static interface IBlockPowerHandler {
        
        IPowerManager getPowerManager(World world, int x, int y, int z);
        
        IPowerAttribute getPowerAttribute(World world, int x, int y, int z);
    }
    
    public static interface IItemPowerHandler {
        
        IPowerManager getPowerManager(ItemStack item);
        
        IPowerAttribute getPowerAttribute(ItemStack item);
    }
    
    public static interface IPowerManager {
        
        int getStoredPower();
        
        int getMaxPower();
        
        boolean increasePower(int powerAmount, EnumSimulationType type);
        
        boolean decreasePower(int powerAmount, EnumSimulationType type);
    }
    
    public interface IPowerAttribute {
        
        EnumPowerStatus getPowerStatus();
    }
    
    public enum EnumPowerStatus {
        
        NONE, SINK, SOURCE, BOTH;
    }
}
