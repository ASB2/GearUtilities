package GU.blocks.containers.BlockElectisCrystal;

import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;

public class Type4CrystalLogic extends CrystalLogic implements ICrystalPowerHandler {
    
    public Type4CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        powerStatus = EnumPowerStatus.BOTH;
        manager.setPowerMax(60);
    }
    
    @Override
    public void update(Object... objects) {
        
    }
}
