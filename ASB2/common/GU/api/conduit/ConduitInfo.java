package GU.api.conduit;

import net.minecraft.tileentity.TileEntity;
import GU.api.color.EnumColor;

public class ConduitInfo {
    
    TileEntity tile;
    EnumConduitType couduitType;
    EnumColor conduitColor;
    
    public ConduitInfo(TileEntity tile, EnumConduitType cT, EnumColor color) {
        
        this.tile = tile;
        couduitType = cT;
        conduitColor = color;
    }
    
    public EnumConduitType getConductorType() {
        
        return couduitType;
    }
    
    EnumColor getColorEnum() {
     
        return conduitColor;
    }
}
