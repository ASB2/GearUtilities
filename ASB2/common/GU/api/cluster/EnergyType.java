package GU.api.cluster;

public enum EnergyType {

    GU,
    IC2,
    UE,
    BC;
    
    int energyClass;
    
    public int getEnergyClass() {
        
        return energyClass;
    }
    
    public void setEnergyClass(int energyClass){
        
        this.energyClass = energyClass;
    }
}
