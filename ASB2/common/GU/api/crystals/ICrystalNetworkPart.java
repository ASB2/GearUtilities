package GU.api.crystals;

import GU.api.power.PowerNetAbstract.IPowerAttribute;

public interface ICrystalNetworkPart {
    
    IPowerAttribute getAttributes();
    
    CrystalNetwork getNetwork();
    
    void setCrystalNetwork(CrystalNetwork newNetwork);
}
