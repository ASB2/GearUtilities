package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.power.IPowerMisc;
import GU.utils.UtilDirection;
import GU.utils.UtilPlayers;

public class ItemGearReader extends ItemBase {

    public ItemGearReader(int id) {
        super(id);
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10){

        TileEntity tile = world.getBlockTileEntity(x,y,z);

        if(tile != null) {

            if(tile instanceof IPowerMisc) {

                IPowerMisc mTile = (IPowerMisc) world.getBlockTileEntity(x, y, z);

                if(mTile.getPowerProvider() != null) {

                    if(player.isSneaking())
                        mTile.getPowerProvider().gainPower(10, UtilDirection.translateNumberToDirection(side));

                    UtilPlayers.sendChatToPlayer(player, mTile.getName()+" has "+mTile.getPowerProvider().getPowerStored()+" out of "+mTile.getPowerProvider().getPowerMax() + " TCU Stored");
                    UtilPlayers.sendChatToPlayer(player, mTile.getName()+ " State: " + mTile.getPowerProvider().getCurrentState()); 
                    UtilPlayers.sendChatToPlayer(player, mTile.getName()+ " is at orientation: " + UtilDirection.translateDirectionToString(mTile.getOrientation()));
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
        }
        UtilPlayers.sendChatToPlayer(player, "Block" + " has metadata: " + world.getBlockMetadata(x, y, z));
        UtilPlayers.sendChatToPlayer(player, "--------");
        return true;        

    }
}
