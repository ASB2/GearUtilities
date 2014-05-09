package GU.blocks.containers.BlockElectisCrystal;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.color.IColorableBlock;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.blocks.containers.TileBase;
import GU.entities.EntityPhoton;
import GU.entities.EntityPhoton.IPhotonImpact;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3d;
import UC.math.vector.Vector3i;

public class TileElectisCrystal extends TileBase implements IColorableBlock, ICrystalPowerHandler, IPhotonImpact {
    
    public static Color[] COLORS = new Color[] { Color.WHITE, Color.BLUE, Color.BLACK };
    
    int crystalLevel = 0;
    Wait packetSending, powerIncrease;
    DefaultPowerManager powerManager;
    List<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>> powerHandlers = new ArrayList<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>>();
    
    public TileElectisCrystal() {
        
        packetSending = new Wait(new PacketWait(), 20, 0);
        powerIncrease = new Wait(new NetworkWait(), 100, 0);
        powerManager = new DefaultPowerManager().setPowerMax(20).setPowerStored(20);
    }
    
    @Override
    public void updateEntity() {
        packetSending.update();
        powerIncrease.update();
        super.updateEntity();
    }
    
    @Override
    public Color getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return COLORS[crystalLevel];
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color color, ForgeDirection direction) {
        
        COLORS[crystalLevel] = color;
        return true;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return powerManager;
    }
    
    @Override
    public void onImpact(int powerCarried, Vector3i impactPosition) {
        
        TileEntity tile = UtilVector.getTileAtPostion(worldObj, impactPosition);
        
        if (tile != null && tile instanceof ICrystalPowerHandler) {
            
            ICrystalPowerHandler castedTile = (ICrystalPowerHandler) tile;
            
            IPowerManager tileManager = castedTile.getPowerManager();
            
            if (tileManager.increasePower(powerCarried, EnumSimulationType.SIMULATE)) {
                
                if (powerManager.decreasePower(powerCarried, EnumSimulationType.SIMULATE)) {
                    
                    powerManager.decreasePower(powerCarried, EnumSimulationType.LIGITIMATE);
                    tileManager.increasePower(powerCarried, EnumSimulationType.LIGITIMATE);
                }
            }
        }
    }
    
    private class PacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                if (powerManager.getStoredPower() >= 5) {
                    
                    for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> crystal : powerHandlers) {
                        
                        ICrystalPowerHandler crystal_ = crystal.getValue().get();
                        
                        if (crystal_ != null) {
                            
                            IPowerManager manager = crystal_.getPowerManager();
                            
                            if (manager != null && manager.getStoredPower() < powerManager.getStoredPower() && manager.increasePower(5, EnumSimulationType.SIMULATE)) {
                                
                                worldObj.spawnEntityInWorld(new EntityPhoton(worldObj, new Vector3d(xCoord, yCoord, zCoord), crystal.getKey()).setPowerCarried(5).setMaxDistance(5));
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
    
    private class NetworkWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            powerHandlers.clear();
            
            final int modifier = 5;
            
            for (int x = -modifier; x < modifier; x++) {
                
                for (int y = -modifier; y < modifier; y++) {
                    
                    for (int z = -modifier; z < modifier; z++) {
                        
                        if (!(x == 0 && y == 0 && z == 0)) {
                            
                            TileEntity tile = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);
                            
                            if (tile != null && tile instanceof ICrystalPowerHandler) {
                                
                                powerHandlers.add(new CustomEntry<Vector3i, WeakReference<ICrystalPowerHandler>>(UtilVector.createTileEntityVector(tile), new WeakReference<ICrystalPowerHandler>((ICrystalPowerHandler) tile)));
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
    
    public static class CustomEntry<K, V> implements Entry<K, V> {
        
        K key;
        V value;
        
        public CustomEntry(K key, V value) {
            
            this.key = key;
            this.value = value;
        }
        
        public CustomEntry<K, V> setKey(K key) {
            this.key = key;
            return this;
        }
        
        @Override
        public K getKey() {
            
            return key;
        }
        
        @Override
        public V getValue() {
            
            return value;
        }
        
        public CustomEntry<K, V> setValueCuston(V value) {
            this.value = value;
            return this;
        }
        
        @Override
        public V setValue(V arg0) {
            
            V old = value;
            value = arg0;
            return old;
        }
    }
}
