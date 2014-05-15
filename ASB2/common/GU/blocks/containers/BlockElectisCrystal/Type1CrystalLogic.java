package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.color.IColorableBlock;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import UC.AbstractLogic;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;

public class Type1CrystalLogic extends CrystalLogic implements AbstractLogic, IColorableBlock, ICrystalPowerHandler {
    
    TileElectisCrystal originCrystal;
    
    Vector3i oppositeCrystalVector = Vector3i.ZERO.clone();
    WeakReference<Type1CrystalLogic> oppositeCrystal;
    Wait poolValidNode;
    Color4f color;
    DefaultPowerManager powerManager;
    
    public Type1CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        originCrystal = tile;
        
        color = Color4f.WHITE;
        powerManager = EnumElectisCrystalType.TYPE1.getDefaultPowerManager();
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
    }
    
    @Override
    public void update(Object... objects) {
        
        poolValidNode.update();
    }
    
    public Vector3i getPostionVector() {
        
        return UtilVector.createTileEntityVector(originCrystal);
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
        
        return oppositeCrystal == null || oppositeCrystal.get() == null || UtilVector.getTileAtPostion(originCrystal.getWorldObj(), oppositeCrystalVector) == null;
    }
    
    public Type1CrystalLogic getThis() {
        
        return this;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return powerManager;
    }
    
    @Override
    public Color4f getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return color;
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color4f color, ForgeDirection direction) {
        
        this.color = color;
        return true;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            // ForgeDirection direction = originCrystal.getOrientation();
            //
            // if (oppositeCrystal != null && oppositeCrystal.get() != null &&
            // UtilVector.getTileAtPostion(originCrystal.getWorldObj(),
            // oppositeCrystalVector) == oppositeCrystal.get()) {
            //
            // // Should Transfer Power Here
            // return;
            // }
            // else {
            //
            // final int modifier = 10;
            //
            // for (int distance = 1; distance < modifier; distance++) {
            //
            // TileEntity tile = worldObj.getTileEntity(xCoord + (distance *
            // direction.offsetX), yCoord + (distance * direction.offsetY),
            // zCoord + (distance * direction.offsetZ));
            //
            // if (tile != null && tile instanceof Type1CrystalLogic) {
            //
            // if (((Type1CrystalLogic) tile).getOrientation() ==
            // direction.getOpposite() && ((Type1CrystalLogic)
            // tile).canConnect()) {
            //
            // ((Type1CrystalLogic) tile).setOppositeCrystal(getThis());
            // setOppositeCrystal(((Type1CrystalLogic) tile));
            // }
            // }
            // }
            // }
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
