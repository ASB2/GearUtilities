package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import GU.GearUtilities;
import GU.api.color.Colorable.IColorableTile;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.blocks.containers.TileBase;
import GU.packets.CrystalTypePacket;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import GU.api.power.PowerNetObject.*;
import GU.packets.*;

public class TileElectisCrystal extends TileBase implements IColorableTile, ICrystalPowerHandler, ICrystalNetworkPart {
    
    EnumElectisCrystalType crystalType;
    List<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>> powerHandlers = new ArrayList<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>>();
    CrystalLogic crystalLogic;
    Wait sendPacket;
    
    public TileElectisCrystal() {
        
        crystalType = EnumElectisCrystalType.BROKEN;
        sendPacket = new Wait(new SendPacket(), 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        if (!worldObj.isRemote) {
            
            sendPacket.update();
        }
        
        if (crystalLogic != null) {
            
            crystalLogic.update((Object[]) null);
        }
    }
    
    @Override
    public void invalidate() {
        
        if (crystalLogic != null && crystalLogic instanceof ICrystalNetworkPart) {
            
            CrystalNetwork network = ((ICrystalNetworkPart) crystalLogic).getNetwork();
            
            if (network != null) {
                
                network.removeCrystal(((ICrystalNetworkPart) crystalLogic));
            }
        }
        super.invalidate();
    }
    
    public EnumElectisCrystalType getCrystalType() {
        
        return crystalType;
    }
    
    public CrystalLogic getCrystalLogic() {
        
        return crystalLogic;
    }
    
    public TileElectisCrystal setCrystalType(EnumElectisCrystalType crystalType) {
        
        if (this.crystalType != crystalType) {
            
            this.crystalType = crystalType;
            
            crystalLogic = crystalType.getNewCrystalLogicInstance(this);
        }
        return this;
    }
    
    @Override
    public IPowerAttribute getAttributes() {
        
        if (crystalLogic != null && crystalLogic instanceof ICrystalPowerHandler) {
            
            return ((ICrystalPowerHandler) crystalLogic).getPowerAttribute();
        }
        return new IPowerAttribute() {
            
            @Override
            public EnumPowerStatus getPowerStatus() {
                
                return EnumPowerStatus.NONE;
            }
        };
    }
    
    @Override
    public boolean setCrystalNetwork(CrystalNetwork newNetwork) {
        
        if (crystalLogic != null && crystalLogic instanceof ICrystalNetworkPart) {
            
            return ((ICrystalNetworkPart) crystalLogic).setCrystalNetwork(newNetwork);
        }
        return false;
    }
    
    @Override
    public CrystalNetwork getNetwork() {
        
        if (crystalLogic != null && crystalLogic instanceof ICrystalNetworkPart) {
            
            return ((ICrystalNetworkPart) crystalLogic).getNetwork();
        }
        return null;
    }
    
    @Override
    public Color4f getColor(ForgeDirection direction) {
        
        if (crystalLogic != null) {
            
            return crystalLogic.getColor(direction);
        }
        return null;
    }
    
    @Override
    public boolean setColor(Color4f color, ForgeDirection direction) {
        
        if (crystalLogic != null) {
            
            return crystalLogic.setColor(color, direction);
        }
        return false;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        if (crystalLogic != null) {
            
            return crystalLogic.getPowerManager();
        }
        return null;
    }
    
    @Override
    public IPowerAttribute getPowerAttribute() {
        
        if (crystalLogic != null) {
            
            return crystalLogic.getPowerAttribute();
        }
        return null;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        crystalType.save(tag);
        
        if (crystalLogic != null) {
            
            tag.setTag("crystalLogic", crystalLogic.save(new NBTTagCompound()));
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        this.setCrystalType(EnumElectisCrystalType.load(tag));
        
        if (crystalLogic != null) {
            
            crystalLogic.load(tag.getCompoundTag("crystalLogic"));
        }
        super.readFromNBT(tag);
    }
    
    private class SendPacket implements IWaitTrigger {
        
        boolean hasFired = false;
        IPowerManager lastManager;
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote) {
                
                GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord, yCoord, zCoord, crystalType.ordinal()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                hasFired = true;
                
                IPowerManager manager = getPowerManager();
                
                if (manager != null && manager instanceof DefaultPowerManager) {
                    
                    if (lastManager != null) {
                        
                        if (!lastManager.equals(getPowerManager())) {
                            
                            lastManager = ((DefaultPowerManager) manager).clone();
                            GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, ((DefaultPowerManager) manager).clone()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                            // GearUtilities.log("Packet Sent");
                        }
                        else {
                            
                            // GearUtilities.log("Power Packet Not Made: THINGY IS THE SAME!");
                        }
                    }
                    else {
                        
                        lastManager = ((DefaultPowerManager) manager).clone();
                        GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, ((DefaultPowerManager) manager).clone()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                    }
                }
                
                if (crystalLogic instanceof Type3CrystalLogic) {
                    
                    GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, ((DefaultPowerManager) manager).clone()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                    
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}