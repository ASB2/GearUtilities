package GU.blocks.containers.BlockTestTile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import GU.MiscRegistry;
import GU.api.power.IPowerMisc;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.fx.TestEffect;
import GU.utils.IBlockCycle;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;
import GU.utils.UtilRender;
import GU.vector.*;

public class TileTestTile extends TileBase implements IBlockCycle {

    public TileTestTile() {

        waitTimer = new Wait(1, this, 0);
    }

    @Override
    public void updateEntity() {



        UtilBlock.cycle2DBlock(null, worldObj, xCoord, yCoord, zCoord, ForgeDirection.DOWN, 10, this, 0);
        waitTimer.update();

        for(ForgeDirection direction: ForgeDirection.values()) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null) {

                if(tile instanceof IFluidHandler) {

                    IFluidHandler fTile = (IFluidHandler)tile;

                    if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                        UtilFluid.addFluidToTank(fTile, direction, new FluidStack(MiscRegistry.FluidGUPower, 1000));
                    }
                    else {

                        UtilFluid.removeFluidFromTank(fTile, direction, new FluidStack(MiscRegistry.FluidGUPower, 1000));
                    }
                }

                if(tile instanceof IPowerMisc) {

                    IPowerMisc pTile = (IPowerMisc)tile;

                    if(pTile.getPowerProvider() != null) {

                        if(pTile.getPowerProvider().canGainPower(pTile.getPowerProvider().getPowerClass().getPowerValue())){

                            pTile.getPowerProvider().gainPower(pTile.getPowerProvider().getPowerClass().getPowerValue());
                        }
                    }                            
                }
            }
        }
    }

    public void trigger(int id) {
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int id) {

        Vector3 vector = new Vector3(x + .5, y + .5, z + .5);
        
        if(world.isRemote) {
            TestEffect test = new TestEffect(worldObj, vector, vector);

            UtilRender.renderFX(test);
        }
        return true;
    }
}
