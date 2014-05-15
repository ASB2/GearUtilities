package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;
import UC.*;

public class Type3CrystalLogic extends CrystalLogic implements ICrystalNetworkPart {
    
    /**
     * A list of network parts in the imediate area
     */
    List<CrystalWrapper> crystalHandlers = new ArrayList<CrystalWrapper>();
    CrystalNetwork network;
    Wait poolValidNodes;
    
    public Type3CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        network = new CrystalNetwork(originCrystal.getWorldObj(), UtilVector.createTileEntityVector(originCrystal));
        poolValidNodes = new Wait(new PoolValidNodeWait(), 20, 0);
    }
    
    public Type3CrystalLogic getThis() {
        
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
    public boolean setCrystalNetwork(CrystalNetwork newNetwork) {
        
        this.network = newNetwork;
        return true;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            crystalHandlers.clear();
            
            final int modifier = 5;
            
            for (int x = -modifier; x < modifier; x++) {
                
                for (int y = -modifier; y < modifier; y++) {
                    
                    for (int z = -modifier; z < modifier; z++) {
                        
                        if (!(x == 0 && y == 0 && z == 0)) {
                            
                            TileEntity tile = originCrystal.getWorldObj().getTileEntity(originCrystal.xCoord + x, originCrystal.yCoord + y, originCrystal.zCoord + z);
                            
                            if (tile != null && tile instanceof ICrystalNetworkPart) {
                                
                                ICrystalNetworkPart crystal = ((ICrystalNetworkPart) tile);
                                CrystalNetwork crystalNetwork = crystal.getNetwork();
                                
                                if (crystalNetwork == null) {
                                    
                                    network.addCrystal(crystal);
                                    crystalHandlers.add(new CrystalWrapper().setHandler(crystal).setPos(UtilVector.createTileEntityVector(tile)));
                                }
                            }
                        }
                    }
                }
            }
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
        
        public CrystalWrapper setHandler(ICrystalNetworkPart handler) {
            this.handler = new WeakReference<ICrystalNetworkPart>(handler);
            return this;
        }
        
        public CrystalWrapper setHandler(WeakReference<ICrystalNetworkPart> handler) {
            this.handler = handler;
            return this;
        }
        
        public CrystalWrapper setPos(Vector3i pos) {
            this.pos = pos;
            return this;
        }
    }
}
