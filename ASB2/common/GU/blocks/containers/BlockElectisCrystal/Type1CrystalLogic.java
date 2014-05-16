package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;

public class Type1CrystalLogic extends CrystalLogic implements ICrystalPowerHandler {
    
    Vector3i oppositeCrystalVector = Vector3i.ZERO.clone();
    WeakReference<Type1CrystalLogic> oppositeCrystal;
    Wait poolValidNode;
    DefaultPowerManager powerManager;
    
    public Type1CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        color = Color4f.WHITE;
        powerManager = EnumElectisCrystalType.TYPE1.getDefaultPowerManager();
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
    }
    
    @Override
    public void update(Object... objects) {
        
        poolValidNode.update();
    }
    
    @Override
    public IPowerAttribute getPowerAttribute() {
        // TODO Auto-generated method stub
        return super.getPowerAttribute();
    }
    
    public Vector3i getPostionVector() {
        
        return UtilVector.createTileEntityVector(this.getOriginCrystal());
    }
    
    public Type1CrystalLogic setOppositeCrystal(Type1CrystalLogic oppositeCrystal) {
        
        if (canConnect()) {
            
            oppositeCrystalVector = oppositeCrystal.getPostionVector();
            this.oppositeCrystal = new WeakReference<Type1CrystalLogic>(oppositeCrystal);
        }
        return this;
    }
    
    public Type1CrystalLogic disconnectCrystal() {
        
        oppositeCrystalVector.setXYZ(0);
        oppositeCrystal = null;
        return this;
    }
    
    public boolean canConnect() {
        
        return oppositeCrystal == null || oppositeCrystal.get() == null || UtilVector.getTileAtPostion(getOriginCrystal().getWorldObj(), oppositeCrystalVector) == null;
    }
    
    public Type1CrystalLogic getThis() {
        
        return this;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return powerManager;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            ForgeDirection direction = getOriginCrystal().getOrientation();
            
            if (oppositeCrystal != null && oppositeCrystal.get() != null) {
                
                // Should Transfer Power Here
                return;
            }
            else {
                
                final int modifier = 10;
                
                for (int distance = 1; distance < modifier; distance++) {
                    
                    TileEntity tile = getOriginCrystal().getWorldObj().getTileEntity(getOriginCrystal().xCoord + (distance * direction.offsetX), getOriginCrystal().yCoord + (distance * direction.offsetY), getOriginCrystal().zCoord + (distance * direction.offsetZ));
                    
                    if (tile != null && tile instanceof TileElectisCrystal) {
                        
                        TileElectisCrystal crystal = ((TileElectisCrystal) tile);
                        
                        if (crystal.getCrystalLogic() instanceof Type1CrystalLogic) {
                            
                            Type1CrystalLogic logic = (Type1CrystalLogic) crystal.getCrystalLogic();
                            
                            if (crystal.getOrientation() == direction.getOpposite() && logic.canConnect()) {
                                
                                logic.setOppositeCrystal(getThis());
                                setOppositeCrystal(logic);
                            }
                        }
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
