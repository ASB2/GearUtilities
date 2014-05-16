package GU.api.crystals;

import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;

public interface ICrystalPowerHandler {
    
    IPowerManager getPowerManager();
    
    IPowerAttribute getPowerAttribute();
}