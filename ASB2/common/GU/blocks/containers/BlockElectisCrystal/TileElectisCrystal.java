package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.GearUtilities;
import GU.api.color.IColorableBlock;
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

public class TileElectisCrystal extends TileBase implements IColorableBlock, ICrystalPowerHandler, ICrystalNetworkPart {
    
    EnumElectisCrystalType crystalType;
    List<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>> powerHandlers = new ArrayList<Entry<Vector3i, WeakReference<ICrystalPowerHandler>>>();
    CrystalLogic crystalLogic;
    Wait sendPacket;
    
    public TileElectisCrystal() {
        
        crystalType = EnumElectisCrystalType.BROKEN;
        sendPacket = new Wait(new SimplePacket(), 20, 0);
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
    
    public EnumElectisCrystalType getCrystalType() {
        
        return crystalType;
    }
    
    public TileElectisCrystal setCrystalType(EnumElectisCrystalType crystalType) {
        
        this.crystalType = crystalType;
        
        crystalLogic = crystalType.getNewCrystalLogicInstance(this);
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
    public Color4f getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        if (crystalLogic != null) {
            
            return crystalLogic.getColor(world, x, y, z, direction);
        }
        return null;
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color4f color, ForgeDirection direction) {
        
        if (crystalLogic != null) {
            
            return crystalLogic.setColor(world, x, y, z, color, direction);
        }
        return false;
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        if (crystalLogic != null) {
            
            crystalLogic.getPowerManager();
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
    
    private class SimplePacket implements IWaitTrigger {
        
        boolean hasFired = false;
        
        @Override
        public void trigger(int id) {
            
            if (!hasFired) {
                
                GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord, yCoord, zCoord, crystalType.ordinal()), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
                hasFired = true;
            }
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
        
    }
}