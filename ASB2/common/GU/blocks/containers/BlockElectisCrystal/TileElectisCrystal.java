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
import GU.GearUtilities;
import GU.api.EnumSimulationType;
import GU.api.color.IColorableBlock;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import GU.blocks.containers.TileBase;
import GU.entities.EntityPhoton;
import GU.packets.CrystalTypePacket;
import GU.packets.PowerPacket;
import UC.ImplementedEntry;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileElectisCrystal extends TileBase implements IColorableBlock, ICrystalPowerHandler, ICrystalNetworkPart {
    
    EnumElectisCrystalType crystalType;
    DefaultPowerManager powerManager;
    List<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>> powerHandlers = new ArrayList<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>>();
    Wait poolValidNodes, sendEnergyPackets, serverPacketWait;
    CrystalNetwork network;
    
    boolean hasUpdated = false;
    
    public TileElectisCrystal() {
        
        crystalType = EnumElectisCrystalType.BROKEN;
        powerManager = new DefaultPowerManager().setPowerMax(20);
        poolValidNodes = new Wait(new PoolValidNodeWait(), 50, 0);
        sendEnergyPackets = new Wait(new SendEnergyPacketWait(), 5, 0);
        serverPacketWait = new Wait(new ServerPacketWait(), 40, 0);
    }
    
    @Override
    public void updateEntity() {
        
        poolValidNodes.update();
        
        if (!worldObj.isRemote) {
            
            sendEnergyPackets.update();
            serverPacketWait.update();
            
            if (!hasUpdated) {
                
                GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord, yCoord, zCoord, this.crystalType.ordinal()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                hasUpdated = true;
            }
        }
        
        switch (crystalType) {
        
            case TYPE1: {
                
                // Should connect to other type ones and transfer energy
                break;
            }
            case TYPE2: {
                
                // Should search for nodes if it has a network, then move energy
                break;
            }
            case TYPE3: {
                
                // Should search for nearby nodes
                break;
            }
            case TYPE4: {
                break;
                
            }
            default:
                break;
        
        }
    }
    
    public EnumElectisCrystalType getCrystalType() {
        
        return crystalType;
    }
    
    public TileElectisCrystal setCrystalType(EnumElectisCrystalType crystalType) {
        this.crystalType = crystalType;
        hasUpdated = false;
        return this;
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
    public void setCrystalNetwork(CrystalNetwork newNetwork) {
        
        this.network = newNetwork;
    }
    
    @Override
    public CrystalNetwork getNetwork() {
        
        return network;
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
        
        if (network != null) {
            
            return network.getPowerManager();
        }
        return powerManager;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        crystalType.save(tag);
        tag.setTag("powerManager", powerManager.save(new NBTTagCompound()));
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        crystalType = EnumElectisCrystalType.load(tag);
        powerManager.load(tag.getCompoundTag("powerManager"));
        super.readFromNBT(tag);
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            powerHandlers.clear();
            
            final int modifier = 10;
            
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
            
            // if (!worldObj.isRemote) {
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> entry : powerHandlers) {
                    
                    TileEntity tile = UtilVector.getTileAtPostion(worldObj, entry.getKey());
                    
                    if (tile != null && tile instanceof ICrystalPowerHandler) {
                        
                        IPowerManager manager = ((ICrystalPowerHandler) tile).getPowerManager();
                        
                        if (manager != null) {
                            
                            final int powerToMove = 2;
                            
                            if (powerManager.getStoredPower() >= powerToMove) {
                                
                                if (UtilPower.movePower(powerManager, manager, powerToMove, EnumSimulationType.FORCED_SIMULATE)) {
                                    
                                    EntityPhoton entity = new EntityPhoton(worldObj, xCoord, yCoord, zCoord, entry.getKey()).setMaxDistance(10).setPowerCarried(5);
                                    worldObj.spawnEntityInWorld(entity);
                                }
                            }
                        }
                    }
                }
            }
            // }
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
    
    private class ServerPacketWait implements IWaitTrigger {
        
        IPowerManager lastManager;
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote) {
                
                GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord, yCoord, zCoord, crystalType.ordinal()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                
                if (lastManager != null) {
                    
                    if (!lastManager.equals(powerManager)) {
                        
                        lastManager = powerManager.clone();
                        GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, powerManager), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                        // GearUtilities.log("Packet Sent");
                    }
                    else {
                        
                        // GearUtilities.log("Power Packet Not Made: THINGY IS THE SAME!");
                    }
                }
                else {
                    
                    lastManager = powerManager.clone();
                    GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, powerManager), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
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