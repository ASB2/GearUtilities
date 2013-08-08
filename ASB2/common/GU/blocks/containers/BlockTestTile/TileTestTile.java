package GU.blocks.containers.BlockTestTile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import GU.blocks.containers.TileBase;
import GU.fx.FXBeam;
import GU.utils.IBlockCycle;
import GU.utils.UtilDirection;
import GU.utils.UtilFluid;
import GU.utils.UtilInventory;
import GU.utils.UtilRender;
import GU.vector.Vector3;

public class TileTestTile extends TileBase implements IBlockCycle {

    @Override
    public void updateEntity() {

        for(ForgeDirection direction: ForgeDirection.values()) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null) {

                if(tile instanceof IFluidHandler) {

                    IFluidHandler fTile = (IFluidHandler)tile;

                    if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                        UtilFluid.addFluidToTank(fTile, direction, new FluidStack(FluidRegistry.LAVA, 1000));
                    }
                    else {

                        UtilFluid.removeFluidToTank(fTile, direction, new FluidStack(FluidRegistry.LAVA, 1000));
                    }
                }
            }
        }
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int id) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

            UtilRender.renderFX(new FXBeam(worldObj, new Vector3(xCoord +.5, yCoord +.5, zCoord+.5), new Vector3(tile.xCoord +.5, tile.yCoord +.5, tile.zCoord+.5), 1, 1, 1, 20));

            if(tile instanceof IInventory) {

                UtilInventory.addItemStackToInventory((IInventory)tile, new ItemStack(Item.diamond, 1, 0));
            }
        }
        return false;
    }
}
