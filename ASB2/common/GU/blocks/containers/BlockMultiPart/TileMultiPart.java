package GU.blocks.containers.BlockMultiPart;

import net.minecraft.nbt.NBTTagCompound;
import ASB2.utils.UtilBlock;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.blocks.containers.TileMultiBase;
import UC.color.Color4i;

public class TileMultiPart extends TileMultiBase implements IColorableTile {
    
    // Color4i[] colors = new Color4i[ForgeDirection.values().length];
    Color4i color;
    
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
        
        this.checkInvalidMultiBlocks();
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
        
        if (!worldObj.isRemote) {
            
            if ( !Color4i.WHITE.equals(this.color) && direction == ForgeDirection.UNKNOWN) {
                
                this.color.setRed((this.color.getRed() + color.getRed()) / 2);
                this.color.setGreen((this.color.getGreen() + color.getGreen()) / 2);
                this.color.setBlue((this.color.getBlue() + color.getBlue()) / 2);
            } else if (direction == ForgeDirection.UNKNOWN) {
                
                this.color.setAll(color);
            } else {
                
                this.color.setAll(color);
            }
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
        // colors[direction.ordinal()].setAll(color);
        
        // if (Color4i.WHITE.equals(this.color)) {
        //
        // this.color.setAll(color);
        // } else if (direction.ordinal() == 6) {
        //
        // this.color.setRed((this.color.getRed() + color.getRed()) / 2);
        // this.color.setGreen((this.color.getGreen() + color.getGreen()) / 2);
        // this.color.setBlue((this.color.getBlue() + color.getBlue()) / 2);
        // }
        return true;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        if (!worldObj.isRemote) {
            
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("red", color.getRed());
            tag.setInteger("green", color.getGreen());
            tag.setInteger("blue", color.getBlue());
            tag.setInteger("alpha", color.getAlpha());
            this.sendNBTPacket(tag, 0);
        }
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        color = new Color4i(tag.getInteger("red"), tag.getInteger("green"), tag.getInteger("blue"), tag.getInteger("alpha"));
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
