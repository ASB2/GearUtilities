package GU.blocks.containers.BlockElectisCrystal;

import java.awt.Color;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.color.IColorableBlock;
import GU.blocks.containers.TileBase;
import GU.entities.EntityPhoton;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileElectisCrystal extends TileBase implements IColorableBlock {
    
    public static Color[] COLORS = new Color[] { Color.WHITE, Color.BLUE, Color.BLACK };
    
    int crystalLevel = 0;
    Wait waitTimer;
    
    public TileElectisCrystal() {
        
        waitTimer = new Wait(new ElectisWait(this), 20, 0);
    }
    
    @Override
    public void updateEntity() {
        waitTimer.update();
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
    
    private class ElectisWait implements IWaitTrigger {
        
        TileEntity tile;
        
        public ElectisWait(TileEntity tile) {
            this.tile = tile;
        }
        
        @Override
        public void trigger(int id) {
            
            worldObj.spawnEntityInWorld(new EntityPhoton(worldObj, UtilVector.createTileEntityVector(tile).toVector3d(), UtilVector.createTileEntityVector(tile).add(0, 1000, 0)));
        }
        
        @Override
        public boolean shouldTick(int id) {
            
            return true;
        }
    }
}
