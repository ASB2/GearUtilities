package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.power.IPowerMisc;
import GU.color.IVinillaColorable;
import GU.info.Reference;
import GU.utils.UtilPlayers;
import GU.color.*;

public class ItemGearReader extends ItemBase {

    public ItemGearReader(int id) {
        super(id);
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z, int side, float par8, float par9,
            float par10) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            if (tile instanceof IVinillaColorable) {

                IVinillaColorable mTile = (IVinillaColorable) tile;

                UtilPlayers.sendChatToPlayer(player, "Block has color: "
                        + mTile.getColorEnum().toString());
            }

            if (tile instanceof IPowerMisc) {

                IPowerMisc mTile = (IPowerMisc) tile;

                if (mTile.getPowerProvider() != null) {

                    UtilPlayers.sendChatToPlayer(player, "Tile has "
                            + mTile.getPowerProvider().getPowerStored()
                            + " out of "
                            + mTile.getPowerProvider().getPowerMax() + " "
                            + Reference.POWER_NAME + " Stored");
                    UtilPlayers.sendChatToPlayer(player, "Tile state: "
                            + mTile.getPowerProvider().getCurrentState());
                }
            }

            if (tile instanceof ISidedInventory) {

                ISidedInventory mTile = (ISidedInventory) tile;

                UtilPlayers.sendChatToPlayer(player, "Inventory name is: "
                        + mTile.getInvName());
                UtilPlayers.sendChatToPlayer(player, "Size of inventory is: "
                        + mTile.getSizeInventory());
                UtilPlayers
                        .sendChatToPlayer(
                                player,
                                "Accessible Slots From Side: "
                                        + mTile.getAccessibleSlotsFromSide(side).length);
                UtilPlayers.sendChatToPlayer(
                        player,
                        "Inventory stack limit is: "
                                + mTile.getInventoryStackLimit());
            }

            else if (tile instanceof IInventory) {

                IInventory mTile = (IInventory) tile;

                UtilPlayers.sendChatToPlayer(player, "Inventory name is: "
                        + mTile.getInvName());
                UtilPlayers.sendChatToPlayer(player, "Size of inventory is: "
                        + mTile.getSizeInventory());
                UtilPlayers.sendChatToPlayer(
                        player,
                        "Inventory stack limit is: "
                                + mTile.getInventoryStackLimit());
            }

            if (tile instanceof IFluidHandler) {

                IFluidHandler mTile = (IFluidHandler) tile;

                int loop = 0;

                if (mTile.getTankInfo(ForgeDirection.getOrientation(side)
                        .getOpposite()) != null) {

                    for (FluidTankInfo info : mTile.getTankInfo(ForgeDirection
                            .getOrientation(side).getOpposite())) {

                        loop++;

                        if (info != null) {

                            UtilPlayers.sendChatToPlayer(player,
                                    "Tanks For Direction: " + loop);

                            if (info.fluid != null
                                    && info.fluid.getFluid() != null) {

                                UtilPlayers.sendChatToPlayer(player,
                                        "Fluid Stored: " + info.fluid.amount);
                                UtilPlayers.sendChatToPlayer(player,
                                        "Fluid Conained: "
                                                + info.fluid.getFluid()
                                                        .getName()
                                                        .toUpperCase());
                            } else {

                                UtilPlayers.sendChatToPlayer(player,
                                        "Fluid Conained: " + info.fluid);
                            }

                            UtilPlayers.sendChatToPlayer(player, "Capasity: "
                                    + info.capacity);
                        }
                    }
                }
            }

            if (tile instanceof IColorable) {

                IColorable mTile = (IColorable) tile;

                if (mTile.getColor(ForgeDirection.getOrientation(side)) != null) {

                    UtilPlayers
                            .sendChatToPlayer(
                                    player,
                                    "Red: "
                                            + mTile.getColor(
                                                    ForgeDirection
                                                            .getOrientation(side))
                                                    .getRed());
                    UtilPlayers
                            .sendChatToPlayer(
                                    player,
                                    "Green: "
                                            + mTile.getColor(
                                                    ForgeDirection
                                                            .getOrientation(side))
                                                    .getGreen());
                    UtilPlayers
                            .sendChatToPlayer(
                                    player,
                                    "Blue: "
                                            + mTile.getColor(
                                                    ForgeDirection
                                                            .getOrientation(side))
                                                    .getBlue());
                    UtilPlayers
                            .sendChatToPlayer(
                                    player,
                                    "Alpha: "
                                            + mTile.getColor(
                                                    ForgeDirection
                                                            .getOrientation(side))
                                                    .getAlpha());
                }
            }
        }
        UtilPlayers.sendChatToPlayer(player, "Block" + " has metadata: "
                + world.getBlockMetadata(x, y, z));
        UtilPlayers.sendChatToPlayer(player, "--------");
        return true;
    }
}
