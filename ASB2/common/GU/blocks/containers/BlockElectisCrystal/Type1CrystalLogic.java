package GU.blocks.containers.BlockElectisCrystal;

import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class Type1CrystalLogic extends CrystalLogic {
    
    Wait poolValidNode;
    
    public Type1CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
        this.powerStatus = EnumPowerStatus.SINK;
        manager.setPowerMax(60);
    }
    
    @Override
    public void update(Object... objects) {
        
        poolValidNode.update();
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
