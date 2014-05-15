package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
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
import UC.AbstractLogic;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;

public class Type2CrystalLogic extends CrystalLogic implements AbstractLogic, IColorableBlock, ICrystalPowerHandler, ICrystalNetworkPart {
    
    TileElectisCrystal originCrystal;
    
    List<CrystalWrapper> powerHandlers = new ArrayList<CrystalWrapper>();
    Wait poolValidNode;
    Color4f color;
    CrystalNetwork network;
    
    boolean directional;
    
    public Type2CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        originCrystal = tile;
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
            
            if (network != null) {
                
                final int modifier = 5;
                
                for (int x = -modifier; x < modifier; x++) {
                    
                    for (int y = -modifier; y < modifier; y++) {
                        
                        for (int z = -modifier; z < modifier; z++) {
                            
                            if (!(x == 0 && y == 0 && z == 0)) {
                                
                                TileEntity tile = originCrystal.getWorldObj().getTileEntity(originCrystal.xCoord + x, originCrystal.yCoord + y, originCrystal.zCoord + z);
                                
                                if (tile != null) {
                                    
                                    if (tile instanceof ICrystalNetworkPart) {
                                        
                                        ICrystalNetworkPart crystal = ((ICrystalNetworkPart) tile);
                                        CrystalNetwork crystalNetwork = crystal.getNetwork();
                                        
                                        if (crystalNetwork == null) {
                                            
                                            network.addCrystal(crystal);
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
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
    
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
