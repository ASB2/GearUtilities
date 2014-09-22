package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.EnumSimulationType;

public class PowerNetAbstract {
    
    public static interface ITilePowerHandler {
        
        IPowerManager getPowerManager(ForgeDirection direction);
        
        EnumPowerStatus getPowerStatus(ForgeDirection direction);
    }
    
    public static interface IBlockPowerHandler {
        
        IPowerManager getPowerManager(World world, int x, int y, int z, ForgeDirection direction);
        
        EnumPowerStatus getPowerStatus(World world, int x, int y, int z, ForgeDirection direction);
    }
    
    public static interface IItemPowerHandler {
        
        IPowerManager getPowerManager(ItemStack item, ForgeDirection direction);
        
        EnumPowerStatus getPowerAttribute(ItemStack item, ForgeDirection direction);
    }
    
    public static interface IPowerManager {
        
        int getStoredPower();
        
        int getMaxPower();
        
        boolean increasePower(int powerAmount, EnumSimulationType type);
        
        boolean decreasePower(int powerAmount, EnumSimulationType type);
    }
    
    public enum EnumPowerStatus {
        
        NONE, SINK, SOURCE, BOTH;
    }
}
