package GU.blocks.containers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import GU.api.IDirectionSpecific;
import GU.api.color.EnumColor;
import GU.api.color.IColorable;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerProvider;
import GU.utils.UtilDirection;

public abstract class TileBase extends TileEntity implements IPowerMisc, IColorable, IDirectionSpecific {

    protected PowerProvider powerProvider;
    protected ForgeDirection orientation;    
    protected EnumColor color;
    
    public TileBase() {
        
        if(color == null)
            color = EnumColor.WHITE;

        if(orientation == null)
            orientation = ForgeDirection.DOWN;
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

    @Override
    public ForgeDirection getOrientation() {

        if(!(orientation == UtilDirection.translateNumberToDirection(getBlockMetadata()))) {

            this.orientation = UtilDirection.translateNumberToDirection(getBlockMetadata());
        }

        if(orientation == ForgeDirection.SOUTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.NORTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.UP) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.DOWN) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        return orientation;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Not Set";
    }

    
    @Override
    public EnumColor getColorEnum() {

        return color;
    }

    @Override
    public void setColor(EnumColor color) {
        
        this.color = color;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if(color == EnumColor.NONE || color == null)
            color = EnumColor.translateNumberToColor(tag.getInteger("color"));

        if(this.getPowerProvider() != null)
            this.getPowerProvider().setPower(tag.getInteger("powerStored"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag); 

        if(this.getColorEnum() != EnumColor.NONE)
            tag.setInteger("color", EnumColor.translateColorToNumber(this.getColorEnum()));

        if(this.getPowerProvider() != null)
            tag.setInteger("powerStored", this.getPowerProvider().getPowerStored());
    }
}
