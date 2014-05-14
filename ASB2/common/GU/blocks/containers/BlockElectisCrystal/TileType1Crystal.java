package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileType1Crystal extends TileBase {
    
    Vector3i oppositeCrystalVector = Vector3i.ZERO.clone();
    WeakReference<TileType1Crystal> oppositeCrystal;
    Wait poolValidNode;
    
    public TileType1Crystal() {
        
        poolValidNode = new Wait(new PoolValidNodeWait(), 5, 0);
    }
    
    @Override
    public void updateEntity() {
        
        poolValidNode.update();
    }
    
    public TileType1Crystal setOppositeCrystal(TileType1Crystal oppositeCrystal) {
        
        if (canConnect()) {
            
            oppositeCrystalVector = UtilVector.createTileEntityVector(oppositeCrystal);
            this.oppositeCrystal = new WeakReference<TileType1Crystal>(oppositeCrystal);
        }
        return this;
    }
    
    public TileType1Crystal disconnectCrystal() {
        
        oppositeCrystalVector.setXYZ(0);
        oppositeCrystal = null;
        return this;
    }
    
    public boolean canConnect() {
        
        return oppositeCrystal == null || oppositeCrystal.get() == null || UtilVector.getTileAtPostion(worldObj, oppositeCrystalVector) == null;
    }
    
    public TileType1Crystal getThis() {
        
        return this;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            ForgeDirection direction = getOrientation();
            
            if (oppositeCrystal != null && oppositeCrystal.get() != null && UtilVector.getTileAtPostion(worldObj, oppositeCrystalVector) == oppositeCrystal.get()) {
                
                return;
            }
            else {
                
                final int modifier = 10;
                
                for (int distance = 1; distance < modifier; distance++) {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + (distance * direction.offsetX), yCoord + (distance * direction.offsetY), zCoord + (distance * direction.offsetZ));
                    
                    if (tile != null && tile instanceof TileType1Crystal) {
                        
                        if (((TileType1Crystal) tile).getOrientation() == direction.getOpposite() && ((TileType1Crystal) tile).canConnect()) {
                            
                            ((TileType1Crystal) tile).setOppositeCrystal(getThis());
                            setOppositeCrystal(((TileType1Crystal) tile));
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
