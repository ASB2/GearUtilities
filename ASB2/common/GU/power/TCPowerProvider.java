package GU.power;

import net.minecraft.tileentity.TileEntity;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;

public class TCPowerProvider extends PowerProvider {

    public TCPowerProvider(TileEntity tile, int powerMax, PowerClass pClass, State state) {
        super(tile, powerMax, pClass);
    
        this.currentState = state;
    }

    public State getCurrentState() {
        
        if(currentState == null) {
            
            currentState = State.SINK;
        }
        return currentState;        
    }
}
