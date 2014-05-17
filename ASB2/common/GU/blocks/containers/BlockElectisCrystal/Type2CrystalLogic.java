package GU.blocks.containers.BlockElectisCrystal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.EnumPowerStatus;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.color.Color4f;
import UC.math.vector.Vector3i;
import UC.utils.*;

public class Type2CrystalLogic extends CrystalLogic {
    
    List<CrystalWrapper> powerHandlers = new ArrayList<CrystalWrapper>();
    Wait poolValidNode, transferPower;
    Color4f color;
    
    boolean directional = true;
    
    public Type2CrystalLogic(TileElectisCrystal tile) {
        super(tile);
        
        color = Color4f.WHITE;
        poolValidNode = new Wait(new PoolValidNodeWait(), 20, 0);
        transferPower = new Wait(new TransferPowerWait(), 5, 0);
        manager.setPowerMax(60);
        powerStatus = EnumPowerStatus.BOTH;
    }
    
    @Override
    public void update(Object... objects) {
        
        poolValidNode.update();
        transferPower.update();
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setBoolean("directional", directional);
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        directional = tag.getBoolean("directional");
        super.load(tag);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        if (player.getHeldItem() == null && player.isSneaking()) {
            
            directional = UCUtil.toggle(directional);
            poolValidNode.getTrigger().trigger(0);
            
            if (directional) {
                
                UtilEntity.sendChatToPlayer(player, "Now Checking in simple directions");
            }
            else {
                
                UtilEntity.sendChatToPlayer(player, "Now Checking in a square");
            }
        }
        return false;
    }
    
    private class PoolValidNodeWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (worldObj != null && position != null) {
                
                powerHandlers.clear();
                
                if (!directional) {
                    
                    final int modifier = 5;
                    
                    for (int x = -modifier; x <= modifier; x++) {
                        
                        for (int y = -modifier; y <= modifier; y++) {
                            
                            for (int z = -modifier; z <= modifier; z++) {
                                
                                if (!(x == 0 && y == 0 && z == 0)) {
                                    
                                    TileEntity tile = worldObj.getTileEntity(position.getX() + x, position.getY() + y, position.getZ() + z);
                                    
                                    if (tile != null) {
                                        
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
                else {
                    
                    final int modifier = 9;
                    
                    for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                        
                        for (int value = 0; value <= modifier; value++) {
                            
                            TileEntity tile = worldObj.getTileEntity(position.getX() + (direction.offsetX * value), position.getY() + (direction.offsetY * value), position.getZ() + (direction.offsetZ * value));
                            
                            if (tile != null) {
                                
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
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
    
    private class TransferPowerWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!powerHandlers.isEmpty()) {
                
                if (!worldObj.isBlockIndirectlyGettingPowered(position.getX(), position.getY(), position.getZ())) {
                    
                    final int powerAmount = 1;
                    
                    for (CrystalWrapper wrapper : powerHandlers) {
                        
                        ICrystalPowerHandler handler = wrapper.getHandler().get();
                        
                        if (handler != null) {
                            
                            IPowerManager otherManager = handler.getPowerManager();
                            
                            if (otherManager != null) {
                                
                                IPowerAttribute attrubute = handler.getPowerAttribute();
                                
                                if (attrubute != null) {
                                    
                                    EnumPowerStatus powerStatus = attrubute.getPowerStatus();
                                    
                                    switch (powerStatus) {
                                    
                                        case SINK: {
                                            
                                            if (otherManager.getStoredPower() < manager.getStoredPower()) {
                                                
                                                UtilPower.movePower(manager, otherManager, powerAmount, EnumSimulationType.LIGITIMATE);
                                            }
                                            break;
                                        }
                                        
                                        // case SOURCE: {
                                        //
                                        // if (otherManager.getStoredPower() >
                                        // manager.getStoredPower()) {
                                        //
                                        // UtilPower.movePower(otherManager,
                                        // getPowerManager(), powerAmount,
                                        // EnumSimulationType.LIGITIMATE);
                                        // }
                                        // break;
                                        // }
                                        
                                        case BOTH: {
                                            
//                                            if (otherManager.getStoredPower() > manager.getStoredPower()) {
//                                                
//                                                UtilPower.movePower(otherManager, getPowerManager(), powerAmount, EnumSimulationType.LIGITIMATE);
//                                            }
//                                            else 
                                                if (otherManager.getStoredPower() < manager.getStoredPower()) {
                                                
                                                UtilPower.movePower(manager, otherManager, powerAmount, EnumSimulationType.LIGITIMATE);
                                            }
                                            break;
                                        }
                                        
                                        default:
                                            break;
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
