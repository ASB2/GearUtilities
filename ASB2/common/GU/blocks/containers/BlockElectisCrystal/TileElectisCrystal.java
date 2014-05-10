package GU.blocks.containers.BlockElectisCrystal;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.color.IColorableBlock;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import GU.blocks.containers.TileBase;
import UC.ImplementedEntry;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class TileElectisCrystal extends TileBase implements IColorableBlock, ICrystalPowerHandler {
    
    DefaultPowerManager powerManager;
    CrystalType crystalType;
    List<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>> powerHandlers = new ArrayList<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>>();
    Wait poolValidNodes, sendEnergyPackets;
    
    public TileElectisCrystal() {
        
        powerManager = new DefaultPowerManager().setPowerMax(20).setPowerStored(20);
        crystalType = CrystalType.LEVEL1;
        poolValidNodes = new Wait(new PoolValidNodeWait(), 100, 0);
        sendEnergyPackets = new Wait(new SendEnergyPacketWait(), 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        poolValidNodes.update();
    }
    
    @Override
    public Color getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return crystalType.getDefaultColor();
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color color, ForgeDirection direction) {
        
        return false;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return powerManager;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        tag.setTag("powerManager", powerManager.save(tag));
        // super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        powerManager.load(tag.getCompoundTag("powerManager"));
        // super.readFromNBT(tag);
    }
    
    public static enum CrystalType {
        
        LEVEL1(Color.WHITE), LEVEL2(Color.CYAN), LEVEL3(Color.MAGENTA);
        
        Color defaultColor;
        
        CrystalType(Color defaultColor) {
            
            this.defaultColor = defaultColor;
        }
        
        public Color getDefaultColor() {
            return defaultColor;
        }
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
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
                                
                                powerHandlers.add(new ImplementedEntry<Vector3i, WeakReference<ICrystalPowerHandler>>(UtilVector.createTileEntityVector(tile), new WeakReference<ICrystalPowerHandler>((ICrystalPowerHandler) tile)));
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
    
    private class SendEnergyPacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> entry : powerHandlers) {
                
                TileEntity tile = UtilVector.getTileAtPostion(worldObj, entry.getKey());
                
                if (tile != null && tile instanceof ICrystalPowerHandler) {
                    
                    IPowerManager manager = ((ICrystalPowerHandler) tile).getPowerManager();
                    
                    if (manager != null) {
                        
                        if (powerManager.getStoredPower() >= 5) {
                            
                            UtilPower.movePower(powerManager, manager, 5, EnumSimulationType.FORCED);
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
