package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilMisc;
import GU.api.color.IColorable;
import GU.api.color.IVanillaColorable;
import GU.api.network.IConductor;
import GU.api.power.IPowerMisc;
import GU.info.Reference;

public class ItemGearReader extends ItemBase {

    public ItemGearReader(int id) {
        super(id);
        setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            if (tile instanceof IVanillaColorable) {

                IVanillaColorable mTile = (IVanillaColorable) tile;

                UtilEntity.sendChatToPlayer(player, "Block has color: " + mTile.getColorEnum().toString());
            }

            if (tile instanceof IPowerMisc) {

                IPowerMisc mTile = (IPowerMisc) tile;

                if (mTile.getPowerProvider() != null) {

                    UtilEntity.sendChatToPlayer(player, "Tile has " + mTile.getPowerProvider().getPowerStored() + " out of " + mTile.getPowerProvider().getPowerMax() + " " + Reference.POWER_NAME + " Stored");
                }
            }

            if (tile instanceof ISidedInventory) {

                ISidedInventory mTile = (ISidedInventory) tile;

                UtilEntity.sendChatToPlayer(player, "Inventory name is: " + mTile.getInvName());
                UtilEntity.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                UtilEntity.sendChatToPlayer(player, "Accessible Slots From Side: " + mTile.getAccessibleSlotsFromSide(side).length);
                UtilEntity.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
            }

            else if (tile instanceof IInventory) {

                IInventory mTile = (IInventory) tile;

                UtilEntity.sendChatToPlayer(player, "Inventory name is: " + mTile.getInvName());
                UtilEntity.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                UtilEntity.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
            }

            if (tile instanceof IFluidHandler) {

                IFluidHandler mTile = (IFluidHandler) tile;

                int loop = 0;

                if (mTile.getTankInfo(ForgeDirection.getOrientation(side) .getOpposite()) != null) {

                    for (FluidTankInfo info : mTile.getTankInfo(ForgeDirection .getOrientation(side).getOpposite())) {

                        loop++;

                        if (info != null) {

                            UtilEntity.sendChatToPlayer(player, "Tanks For Direction: " + loop);

                            if (info.fluid != null && info.fluid.getFluid() != null) {

                                UtilEntity.sendChatToPlayer(player, "Fluid Stored: " + info.fluid.amount);
                                UtilEntity.sendChatToPlayer(player, "Fluid Conained: " + UtilMisc.capitilizeFirst(info.fluid.getFluid().getName()));
                            } else {

                                UtilEntity.sendChatToPlayer(player, "Fluid Conained: " + info.fluid);
                            }

                            UtilEntity.sendChatToPlayer(player, "Capasity: " + info.capacity);
                        }
                    }
                }
            }

            if (tile instanceof IColorable) {

                IColorable mTile = (IColorable) tile;

                if (mTile.getColor(ForgeDirection.getOrientation(side)) != null) {

                    UtilEntity.sendChatToPlayer(player, "Red: " + mTile.getColor(ForgeDirection.getOrientation(side)).getRed());
                    UtilEntity.sendChatToPlayer(player, "Green: " + mTile.getColor(ForgeDirection.getOrientation(side)).getGreen());
                    UtilEntity.sendChatToPlayer(player, "Blue: " + mTile.getColor(ForgeDirection.getOrientation(side)).getBlue());
                    UtilEntity.sendChatToPlayer(player, "Alpha: " + mTile.getColor(ForgeDirection.getOrientation(side)).getAlpha());
                }
            }
            
            if (tile instanceof IConductor) {

                IConductor mTile = (IConductor) tile;

                if (mTile.getNetwork() != null) {

                    UtilEntity.sendChatToPlayer(player, "Conduit Network: It Works");
                    UtilEntity.sendChatToPlayer(player, "Conduit Network Size: " + mTile.getNetwork().getAvaliableConductors().size());
                }
                else {
                    
                    UtilEntity.sendChatToPlayer(player, "Conduit Network: null");
                }
            }
        }
        UtilEntity.sendChatToPlayer(player, "Block ID: " + world.getBlockId(x, y, z));
        UtilEntity.sendChatToPlayer(player, "Block has metadata: " + world.getBlockMetadata(x, y, z));
        UtilEntity.sendChatToPlayer(player, "Block has direction: " + ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)));
        UtilEntity.sendChatToPlayer(player, "Block brightness: " + Block.blocksList[world.getBlockId(x, y, z)].getMixedBrightnessForBlock(world, x, y, z));
        UtilEntity.sendChatToPlayer(player, "--------");
        return true;
    }
}
