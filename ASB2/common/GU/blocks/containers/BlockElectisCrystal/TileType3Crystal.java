package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.color.IColorableBlock;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;

public class TileType3Crystal extends TileBase implements IColorableBlock, ICrystalPowerHandler, ICrystalNetworkPart {
    
    List<CrystalWrapper> powerHandlers = new ArrayList<CrystalWrapper>();
    Wait poolValidNode;
    Color4f color;
    CrystalNetwork network;
    
    public TileType3Crystal() {
        
        color = Color4f.WHITE;
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
        network = new CrystalNetwork(worldObj, UtilVector.createTileEntityVector(this));
    }
    
    @Override
    public void updateEntity() {
        
        poolValidNode.update();
    }
    
    public TileType3Crystal getThis() {
        
        return this;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        if (network != null) {
            
            return network.getPowerManager();
        }
        
        return null;
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
    
    @Override
    public IPowerAttribute getAttributes() {
        
        return new IPowerAttribute() {
            
            @Override
            public EnumPowerStatus getPowerStatus() {
                
                return EnumPowerStatus.NONE;
            }
        };
    }
    
    @Override
    public CrystalNetwork getNetwork() {
        
        return network;
    }
    
    @Override
    public void setCrystalNetwork(CrystalNetwork newNetwork) {
        
        this.network = newNetwork;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
    
    private class CrystalWrapper {
        
        Vector3i pos;
        WeakReference<ICrystalNetworkPart> handler;
        
        public WeakReference<ICrystalNetworkPart> getHandler() {
            return handler;
        }
        
        public Vector3i getPos() {
            return pos;
        }
        
        public void setHandler(ICrystalNetworkPart handler) {
            this.handler = new WeakReference<ICrystalNetworkPart>(handler);
        }
        
        public void setHandler(WeakReference<ICrystalNetworkPart> handler) {
            this.handler = handler;
        }
        
        public void setPos(Vector3i pos) {
            this.pos = pos;
        }
    }
}
