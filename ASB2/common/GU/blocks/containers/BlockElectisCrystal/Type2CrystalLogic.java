package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;

public class Type2CrystalLogic extends CrystalLogic implements ICrystalNetworkPart {
    
    List<CrystalWrapper> powerHandlers = new ArrayList<CrystalWrapper>();
    Wait poolValidNode;
    Color4f color;
    WeakReference<CrystalNetwork> network;
    
    boolean directional;
    
    public Type2CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        color = Color4f.WHITE;
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
    }
    
    @Override
    public void update(Object... objects) {
        
        poolValidNode.update();
    }
    
    public Type2CrystalLogic getThis() {
        
        return this;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        if (network != null && network.get() != null) {
            
            return network.get().getPowerManager();
        }
        return null;
    }
    
    @Override
    public IPowerAttribute getAttributes() {
        
        return new IPowerAttribute() {
            
            @Override
            public EnumPowerStatus getPowerStatus() {
                
                return EnumPowerStatus.BOTH;
            }
        };
    }
    
    @Override
    public CrystalNetwork getNetwork() {
        
        return network != null ? network.get() : null;
    }
    
    @Override
    public boolean setCrystalNetwork(CrystalNetwork newNetwork) {
        
        this.network = new WeakReference<CrystalNetwork>(newNetwork);
        return true;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (network != null) {
                
                final int modifier = 5;
                
                for (int x = -modifier; x <= modifier; x++) {
                    
                    for (int y = -modifier; y <= modifier; y++) {
                        
                        for (int z = -modifier; z <= modifier; z++) {
                            
                            if (!(x == 0 && y == 0 && z == 0)) {
                                
                                TileEntity tile = getOriginCrystal().getWorldObj().getTileEntity(getOriginCrystal().xCoord + x, getOriginCrystal().yCoord + y, getOriginCrystal().zCoord + z);
                                
                                if (tile != null) {
                                    
                                    if (tile instanceof ICrystalNetworkPart) {
                                        
                                        ICrystalNetworkPart crystal = ((ICrystalNetworkPart) tile);
                                        CrystalNetwork crystalNetwork = crystal.getNetwork();
                                        
                                        if (crystalNetwork == null && network != null && network.get() != null) {
                                            
                                            network.get().addCrystal(crystal);
                                        }
                                    }
                                    
                                    if (tile instanceof ICrystalPowerHandler) {
                                        
                                        ICrystalPowerHandler crystal = ((ICrystalPowerHandler) tile);
                                        
                                        powerHandlers.add(new CrystalWrapper().setHandler(crystal).setPos(UtilVector.createTileEntityVector(tile)));
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (getPowerManager() != null) {
                    
                    for (CrystalWrapper wrapper : powerHandlers) {
                        
                        ICrystalPowerHandler handler = wrapper.getHandler().get();
                        
                        if (handler != null) {
                            
                            IPowerManager manager = handler.getPowerManager();
                            
                            if (manager != null) {
                                
                                IPowerAttribute attrubute = handler.getPowerAttribute();
                                
                                if (attrubute != null) {
                                    
                                    EnumPowerStatus powerStatus = attrubute.getPowerStatus();
                                    
                                    if (powerStatus == EnumPowerStatus.SINK) {
                                        
                                        UtilPower.movePower(getPowerManager(), manager, 5, EnumSimulationType.LIGITIMATE);
                                    }
                                    if (powerStatus == EnumPowerStatus.SOURCE) {
                                        
                                        UtilPower.movePower(manager, getPowerManager(), 5, EnumSimulationType.LIGITIMATE);
                                    }
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
    
    @SuppressWarnings("unused")
    private class CrystalWrapper {
        
        Vector3i pos;
        WeakReference<ICrystalPowerHandler> handler;
        
        public WeakReference<ICrystalPowerHandler> getHandler() {
            return handler;
        }
        
        public Vector3i getPos() {
            return pos;
        }
        
        public CrystalWrapper setHandler(ICrystalPowerHandler handler) {
            this.handler = new WeakReference<ICrystalPowerHandler>(handler);
            return this;
        }
        
        public CrystalWrapper setHandler(WeakReference<ICrystalPowerHandler> handler) {
            this.handler = handler;
            return this;
        }
        
        public CrystalWrapper setPos(Vector3i pos) {
            this.pos = pos;
            return this;
        }
    }
}
