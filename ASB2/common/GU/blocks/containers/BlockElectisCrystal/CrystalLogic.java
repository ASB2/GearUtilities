package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import UC.IAbstractUpdateable;
import UC.color.Color4i;
import GU.api.power.PowerNetObject.*;
import UC.math.vector.*;

public abstract class CrystalLogic implements IAbstractUpdateable, IColorableTile, ICrystalPowerHandler {
    
    WeakReference<TileElectisCrystal> originCrystal;
    World worldObj;
    Vector3i position;
    Color4i color;
    EnumPowerStatus powerStatus;
    IPowerAttribute attribute;
    DefaultPowerManager manager;
    
    public CrystalLogic(TileElectisCrystal tile) {
        
        originCrystal = new WeakReference<TileElectisCrystal>(tile);
        
        color = Color4i.WHITE;
        powerStatus = EnumPowerStatus.NONE;
        manager = new DefaultPowerManager();
        attribute = new IPowerAttribute() {
            
            @Override
            public EnumPowerStatus getPowerStatus() {
                
                return powerStatus;
            }
        };
    }
    
    @Override
    public void update(Object... objects) {
        
    }
    
    public void validate() {
        
        TileElectisCrystal crystal = originCrystal.get();
        
        if (crystal != null) {
            
            worldObj = crystal.getWorldObj();
            position = UtilVector.createTileEntityVector(crystal);
        }
    }
    
    public TileElectisCrystal getOriginCrystal() {
        
        return originCrystal.get();
    }
    
    @Override
    public IPowerManager getPowerManager() {
        
        return manager;
    }
    
    @Override
    public Color4i getColor(ForgeDirection direction) {
        
        return color;
    }
    
    @Override
    public boolean setColor(Color4i color, ForgeDirection direction) {
        
        this.color = color;
        return true;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("manager", manager.save(new NBTTagCompound()));
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        manager.load(tag.getCompoundTag("manager"));
    }
    
    @Override
    public IPowerAttribute getPowerAttribute() {
        
        return attribute;
    }
    
    public void handlePowerPacket(IPowerManager handler) {
        
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        return false;
    }
    
    // protected static class SendEnergyPacketWait implements IWaitTrigger {
    //
    // public SendEnergyPacketWait() {
    //
    // }
    //
    // @Override
    // public void trigger(int id) {
    //
    // if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
    //
    // for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> entry :
    // powerHandlers) {
    //
    // TileEntity tile = UtilVector.getTileAtPostion(worldObj, entry.getKey());
    //
    // if (tile != null && tile instanceof ICrystalPowerHandler) {
    //
    // IPowerManager manager = ((ICrystalPowerHandler) tile).getPowerManager();
    //
    // if (manager != null) {
    //
    // final int powerToMove = 2;
    //
    // if (powerManager.getStoredPower() >= powerToMove) {
    //
    // if (UtilPower.movePower(powerManager, manager, powerToMove,
    // EnumSimulationType.FORCED_SIMULATE)) {
    //
    // EntityPhoton entity = new EntityPhoton(worldObj, xCoord, yCoord, zCoord,
    // entry.getKey()).setMaxDistance(10).setPowerCarried(5);
    // worldObj.spawnEntityInWorld(entity);
    // }
    // }
    // }
    // }
    // }
    // }
    // }
    //
    // @Override
    // public boolean shouldTick(int id) {
    // // TODO Auto-generated method stub
    // return true;
    // }
    // }
    //
    // protected static class ServerPacketWait implements IWaitTrigger {
    //
    // IPowerManager lastManager;
    //
    // public ServerPacketWait() {
    // // TODO Auto-generated constructor stub
    // }
    //
    // @Override
    // public void trigger(int id) {
    //
    // if (!worldObj.isRemote) {
    //
    // GearUtilities.getPipeline().sendToAllAround(new CrystalTypePacket(xCoord,
    // yCoord, zCoord, crystalType.ordinal()), new
    // TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
    //
    // if (lastManager != null) {
    //
    // if (!lastManager.equals(powerManager)) {
    //
    // lastManager = powerManager.clone();
    // GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord,
    // yCoord, zCoord, powerManager), new
    // TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
    // // GearUtilities.log("Packet Sent");
    // }
    // else {
    //
    // // GearUtilities.log("Power Packet Not Made: THINGY IS THE SAME!");
    // }
    // }
    // else {
    //
    // lastManager = powerManager.clone();
    // GearUtilities.getPipeline().sendToAllAround(new PowerPacket(xCoord,
    // yCoord, zCoord, powerManager), new
    // TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
    // }
    // }
    // }
    //
    // @Override
    // public boolean shouldTick(int id) {
    // // TODO Auto-generated method stub
    // return true;
    // }
    // }
}
