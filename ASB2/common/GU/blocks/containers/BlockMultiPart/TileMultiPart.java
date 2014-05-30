package GU.blocks.containers.BlockMultiPart;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import UC.color.Color4i;
import ASB2.utils.UtilBlock;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.TileMultiBase;
import UC.Wait.*;
import UC.Wait;
import GU.*;
import GU.packets.*;

public class TileMultiPart extends TileMultiBase implements IColorableTile {
    
    // Color4i[] colors = new Color4i[ForgeDirection.values().length];
    Color4i color;
    Wait colorTimer = new Wait(new IWaitTrigger() {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote) GearUtilities.getPipeline().sendToAllAround(new ColorPacket(color, xCoord, yCoord, zCoord, ForgeDirection.UNKNOWN), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 200));
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }, 40, 0);
    
    public TileMultiPart() {
        
        // for (int index = 0; index < colors.length; index++) {
        //
        // colors[index] = Color4i.WHITE.clone();
        // }
        color = Color4i.WHITE.clone();
        this.setMaxMultiBlocks(4);
    }
    
    @Override
    public void updateEntity() {
        colorTimer.update();
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (multiBlocks.isEmpty()) {
            
            UtilBlock.breakBlockNoDrop(worldObj, xCoord, yCoord, zCoord);
        }
    }
    
    @Override
    public Color4i getColor(ForgeDirection direction) {
        
        // return colors[direction.ordinal()];
        return color;
    }
    
    @Override
    public boolean setColor(Color4i color, ForgeDirection direction) {
        
        // colors[direction.ordinal()].setAll(color);
        
        if (direction == ForgeDirection.UNKNOWN) {
            
            this.color.setAll(color);
        }
        else if (this.multiBlocks.size() > 1) {
            
            this.color.setRed((this.color.getRed() + color.getRed()) / 2);
            this.color.setGreen((this.color.getGreen() + color.getGreen()) / 2);
            this.color.setBlue((this.color.getBlue() + color.getBlue()) / 2);
        }
        else {
            
            this.color.setAll(color);
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return true;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("red", color.getRed());
        tag.setInteger("green", color.getGreen());
        tag.setInteger("blue", color.getBlue());
        tag.setInteger("alpha", color.getAlpha());
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        color = new Color4i(tag.getInteger("red"), tag.getInteger("green"), tag.getInteger("blue"), tag.getInteger("alpha"));
    }
}
