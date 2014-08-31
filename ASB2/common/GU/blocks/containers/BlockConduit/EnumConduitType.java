package GU.blocks.containers.BlockConduit;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.fluids.IFluidHandler;

public enum EnumConduitType {
    
    NONE, ITEM, FLUID, GU_POWER;
    
    public boolean checkObject(Object object) {
        
        switch (this) {
        
            case NONE: {
                return false;
            }
            case ITEM: {
                
                return object instanceof IInventory;
            }
            case FLUID: {
                
                return object instanceof IFluidHandler;
            }
            case GU_POWER: {
                
                return false;
            }
        }
        return false;
    }
}
