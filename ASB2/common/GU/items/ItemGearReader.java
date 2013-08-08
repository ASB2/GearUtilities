package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.IDirectionSpecific;
import GU.api.color.IColorable;
import GU.api.power.IPowerMisc;
import GU.info.Variables;
import GU.utils.UtilDirection;
import GU.utils.UtilPlayers;

public class ItemGearReader extends ItemBase {

    public ItemGearReader(int id) {
        super(id);
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10){

        TileEntity tile = world.getBlockTileEntity(x,y,z);

        if(tile != null) {

            if(tile instanceof IDirectionSpecific) {

                IDirectionSpecific mTile = (IDirectionSpecific)tile;

                UtilPlayers.sendChatToPlayer(player,"Block is at orientation: " + UtilDirection.translateDirectionToString(mTile.getOrientation()));
            }

            if(tile instanceof IColorable) {

                IColorable mTile = (IColorable)tile;

                UtilPlayers.sendChatToPlayer(player,"Block has color: " + mTile.getColorEnum().toString());
            }

            if(tile instanceof IPowerMisc) {

                IPowerMisc mTile = (IPowerMisc)tile;

                if(mTile.getPowerProvider() != null) {

                    if(player.isSneaking() && Variables.TESTING_MODE)
                        mTile.getPowerProvider().gainPower(10);

                    UtilPlayers.sendChatToPlayer(player, mTile.getName()+" has "+mTile.getPowerProvider().getPowerStored()+" out of "+mTile.getPowerProvider().getPowerMax() + " TCU Stored");
                    UtilPlayers.sendChatToPlayer(player, mTile.getName()+ " State: " + mTile.getPowerProvider().getCurrentState());
                }
            }

            if(tile instanceof ISidedInventory) {

                ISidedInventory mTile = (ISidedInventory)tile;

                UtilPlayers.sendChatToPlayer(player, "Inventory name is: " + mTile.getInvName());
                UtilPlayers.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());                
                UtilPlayers.sendChatToPlayer(player, "Accessible Slots From Side: " + mTile.getAccessibleSlotsFromSide(side).length);
                UtilPlayers.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
            }

            else if(tile instanceof IInventory) {

                IInventory mTile = (IInventory)tile;

                UtilPlayers.sendChatToPlayer(player, "Inventory name is: " + mTile.getInvName());
                UtilPlayers.sendChatToPlayer(player, "Size of inventory is: " + mTile.getSizeInventory());
                UtilPlayers.sendChatToPlayer(player, "Inventory stack limit is: " + mTile.getInventoryStackLimit());
            }

            if(tile instanceof IFluidHandler) {

                IFluidHandler mTile = (IFluidHandler)tile;

                int loop = 0;

                for(FluidTankInfo info: mTile.getTankInfo(UtilDirection.translateDirectionToOpposite(UtilDirection.translateNumberToDirection(side)))) {

                    loop++;

                    if(info != null) {

                        UtilPlayers.sendChatToPlayer(player, "Tanks For Direction: " + loop); 
                        if(info.fluid != null) {
                            UtilPlayers.sendChatToPlayer(player, "Fluid Stored: " + info.fluid.amount); 
                            UtilPlayers.sendChatToPlayer(player, "Fluid Conained: " + info.fluid.getFluid().getName().toUpperCase());
                            UtilPlayers.sendChatToPlayer(player, "Fluid Stored: " + info.fluid.amount); 
                        }
                        else {
                            UtilPlayers.sendChatToPlayer(player, "Fluid Conained: " + info.fluid);
                        }

                        UtilPlayers.sendChatToPlayer(player, "Capasity: " + info.capacity);
                    }
                }
            }
        }

        UtilPlayers.sendChatToPlayer(player, "Block" + " has metadata: " + world.getBlockMetadata(x, y, z));
        UtilPlayers.sendChatToPlayer(player, "--------");
        return true;
    }
}
