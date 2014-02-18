package GU.blocks.containers.BlockEnhancedBricks;

import java.awt.Color;

import net.minecraftforge.common.ForgeDirection;
import ASB2.wait.Wait;
import GU.color.TileColorable;

public class TileEnhancedBricks extends TileColorable {

    public TileEnhancedBricks() {

        waitTimer = new Wait(this, 20, 0);
    }

    @Override
    public void updateEntity() {
//        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

//        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
//
//            Color color = this.getColor(direction);
//
//            if (!(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)) {
//
//                return;
//            }
//        }
//        worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
    }
}
