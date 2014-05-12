package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.EnumSimulationType;

public class PowerNetAbstract {
    
    public static interface ITilePowerHandler {
        
        IPowerManager getPowerManager();
    }
    
    public static interface IBlockPowerHandler {
        
        IPowerManager getPowerManager(World world, int x, int y, int z);
    }
    
    public static interface IItemPowerHandler {
        
        IPowerManager getPowerManager(ItemStack item);
    }
    
    public static interface IPowerManager {
        
        int getStoredPower();
        
        int getMaxPower();
        
        int getMinInputPacketSize();
        
        int getMaxInputPacketSize();
        
        int getMinOutputPacketSize();
        
        int getMaxOutputPacketSize();
        
        boolean increasePower(int powerAmount, EnumSimulationType type);
        
        boolean decreasePower(int powerAmount, EnumSimulationType type);
    }
}
