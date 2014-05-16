package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import ASB2.utils.UtilVector;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import UC.color.Color4f;
import UC.math.vector.Vector3i;
import GU.api.power.PowerNetAbstract.*;

public class Type4CrystalLogic extends CrystalLogic implements ICrystalPowerHandler {
    
    Vector3i oppositeCrystalVector = Vector3i.ZERO.clone();
    WeakReference<Type1CrystalLogic> oppositeCrystal;
    DefaultPowerManager powerManager;
    
    public Type4CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        powerStatus = EnumPowerStatus.SINK;
        color = Color4f.WHITE;
        powerManager = EnumElectisCrystalType.TYPE4.getDefaultPowerManager();
    }
    
    @Override
    public void update(Object... objects) {
        
    }
    
    public Vector3i getPostionVector() {
        
        return UtilVector.createTileEntityVector(getOriginCrystal());
    }
    
    public Type4CrystalLogic getThis() {
        
        return this;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return powerManager;
    }
}
