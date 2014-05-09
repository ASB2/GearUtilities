package GU.blocks.containers.BlockElectisCrystal;

import java.awt.Color;

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
    Wait waitTimer;
    DefaultPowerManager powerManager;
    
    public TileElectisCrystal() {
        
        waitTimer = new Wait(new ElectisWait(), 20, 0);
        powerManager = new DefaultPowerManager().setPowerMax(20).setPowerStored(20);
    }
    
    @Override
    public void updateEntity() {
        waitTimer.update();
        powerManager.setPowerStored(0);
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
            
            if (tileManager.getStoredPower() < this.powerManager.getStoredPower()) {
                
                if (tileManager.increasePower(powerCarried, EnumSimulationType.SIMULATE)) {
                    
                    if (powerManager.decreasePower(powerCarried, EnumSimulationType.SIMULATE)) {
                        
                        powerManager.decreasePower(powerCarried, EnumSimulationType.LIGITIMATE);
                        castedTile.getPowerManager().increasePower(powerCarried, EnumSimulationType.LIGITIMATE);
                    }
                }
            }
        }
    }
    
    private class ElectisWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                
                if (powerManager.getStoredPower() >= 5) {
                    
                    final int modifier = 10;
                    
                    for (int x = -modifier; x < modifier; x++) {
                        
                        for (int y = -modifier; y < modifier; y++) {
                            
                            for (int z = -modifier; z < modifier; z++) {
                                
                                if (!(x == 0 && y == 0 && z == 0)) {
                                    
                                    TileEntity tile = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);
                                    
                                    if (tile != null && tile instanceof ICrystalPowerHandler) {
                                        
                                        worldObj.spawnEntityInWorld(new EntityPhoton(worldObj, new Vector3d(xCoord, yCoord, zCoord), new Vector3i(xCoord + x, yCoord + y, zCoord + z)).setPowerCarried(5).setMaxDistance(modifier));
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
}
