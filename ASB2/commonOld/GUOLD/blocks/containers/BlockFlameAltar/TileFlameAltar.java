package GUOLD.blocks.containers.BlockFlameAltar;

import GUOLD.api.flame.EnumFlameType;
import GUOLD.blocks.containers.TileBase;

public class TileFlameAltar extends TileBase {

    public TileFlameAltar() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void updateEntity() {
        // TODO Auto-generated method stub
        super.updateEntity();
    }

    @Override
    public void trigger(int id) {
        // TODO Auto-generated method stub
        super.trigger(id);
    }

    public EnumFlameType getCurrentFlame() {

        return EnumFlameType.values()[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)];
    }
}
