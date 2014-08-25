package GU.api.crystals;

import GU.api.power.PowerNetAbstract.IPowerAttribute;

public interface ICrystalNetworkPart {
    
    IPowerAttribute getAttributes();
    
    CrystalNetwork getNetwork();
    
    boolean setCrystalNetwork(CrystalNetwork newNetwork);
}
