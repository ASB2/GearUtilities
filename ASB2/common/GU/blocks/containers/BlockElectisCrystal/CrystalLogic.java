package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.Colorable.IColorableTile;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import UC.AbstractLogic;
import UC.color.Color4f;

public class CrystalLogic implements AbstractLogic, IColorableTile, ICrystalPowerHandler {
    
    WeakReference<TileElectisCrystal> originCrystal;
    Color4f color;
    EnumPowerStatus powerStatus;
    IPowerAttribute attribute;
    
    public CrystalLogic(TileElectisCrystal tile) {
        
        originCrystal = new WeakReference<TileElectisCrystal>(tile);
        color = Color4f.WHITE;
        powerStatus = EnumPowerStatus.NONE;
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
    
    public TileElectisCrystal getOriginCrystal() {
        
        return originCrystal.get();
    }
    
    @Override
    public IPowerManager getPowerManager() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Color4f getColor(ForgeDirection direction) {
        
        return color;
    }
    
    @Override
    public boolean setColor(Color4f color, ForgeDirection direction) {
        
        this.color = color;
        return true;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
    }
    
    @Override
    public IPowerAttribute getPowerAttribute() {
        
        return attribute;
    }
    
    public void handlePowerPacket(IPowerManager handler) {
        
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
