package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import GU.GearUtilities;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.blocks.containers.TileBase;
import GU.packets.CrystalTypePacket;
import GU.packets.PowerPacket;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4i;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileElectisCrystal extends TileBase implements IColorableTile, ICrystalPowerHandler, ICrystalNetworkPart {
    
    EnumElectisCrystalType crystalType;
    CrystalLogic crystalLogic;
    Wait sendPacket;
    boolean called;
    
    public TileElectisCrystal() {
        
        crystalType = EnumElectisCrystalType.BROKEN;
        sendPacket = new Wait(new SendPacket(), 40, 0);
    }
    
    @Override
    public void updateEntity() {
        
        if (!called && sendPacket.getRemainingTime() == 5 && crystalType != EnumElectisCrystalType.BROKEN) {
            
            if (!worldObj.isRemote) {
                
                GearUtilities.getPipeline().sendToDimension(new CrystalTypePacket(xCoord, yCoord, zCoord, crystalType.ordinal()), worldObj.provider.dimensionId);
            }
            if (crystalLogic != null) {
                
                crystalLogic.validate();
            }
            called = true;
        }
        
        if (!worldObj.isRemote) {
            
            sendPacket.update();
        }
        
        if (crystalLogic != null) {
            
            crystalLogic.update((Object[]) null);
        }
    }
    
    @Override
    public void validate() {
        super.validate();
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
    public Color4i getColor(ForgeDirection direction) {
        
        if (crystalLogic != null) {
            
            return crystalLogic.getColor(direction);
        }
        return null;
    }
    
    @Override
    public boolean setColor(Color4i color, ForgeDirection direction) {
        
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
    
    @Override
    public void sendUpdatePacket() {
        
        GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord, yCoord, zCoord, crystalType.ordinal()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
    }
    
    private class SendPacket implements IWaitTrigger {
        
        IPowerManager lastManager;
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote) {
                
                IPowerManager manager = getPowerManager();
                
                if (manager != null && manager instanceof DefaultPowerManager) {
                    
                    if (lastManager != null) {
                        
                        if (!(lastManager.getStoredPower() == manager.getStoredPower() && lastManager.getMaxPower() == manager.getMaxPower())) {
                            
                            lastManager = ((DefaultPowerManager) manager).clone();
                            GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, ((DefaultPowerManager) manager).clone()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                        }
                    }
                    else {
                        
                        lastManager = ((DefaultPowerManager) manager).clone();
                        GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord, yCoord, zCoord, ((DefaultPowerManager) manager).clone()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                    }
                }
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}