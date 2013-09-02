package GU.blocks.containers.BlockTestTile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilRender;
import GU.api.power.IPowerMisc;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.fx.TestEffect;
import GU.vector.Vector3;

public class TileTestTile extends TileBase implements IBlockCycle {

    public TileTestTile() {

        waitTimer = new Wait(1, this, 0);
    }

    @Override
    public void updateEntity() {

        // UtilBlock.cycle2DBlock(null, worldObj, xCoord, yCoord, zCoord,
        // ForgeDirection.DOWN, 10, this, 0);

        waitTimer.update();

        for (ForgeDirection direction : ForgeDirection.values()) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this,
                    worldObj, direction);

            if (tile != null) {

                if (tile instanceof IPowerMisc) {

                    IPowerMisc pTile = (IPowerMisc) tile;

                    if (pTile.getPowerProvider() != null) {

                        if (pTile.getPowerProvider().gainPower(pTile.getPowerProvider().getPowerClass().getPowerValue(), direction.getOpposite(), false)) {

                            pTile.getPowerProvider().gainPower(pTile.getPowerProvider().getPowerClass().getPowerValue(), direction.getOpposite(), true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void trigger(int id) {
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y,
            int z, ForgeDirection side, int id) {

        Vector3 vector = new Vector3(x + .5, y + .5, z + .5);

        if (world.isRemote) {
            TestEffect test = new TestEffect(worldObj, vector, vector);

            UtilRender.renderFX(test);
        }
        return true;
    }
}
