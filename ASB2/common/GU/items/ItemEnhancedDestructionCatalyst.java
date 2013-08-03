package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.utils.IBlockCycle;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;
import GU.utils.UtilItemStack;
import GU.utils.UtilPlayers;

public class ItemEnhancedDestructionCatalyst extends ItemBase implements IBlockCycle {

    public ItemEnhancedDestructionCatalyst(int par1) {
        super(par1);
        this.setMaxDamage(300);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if(player.isSneaking()) {

            this.decrementLength(player);

            UtilPlayers.sendChatToPlayer(player, "Debth of tunnel == " + UtilItemStack.getNBTTagInt(itemStack, "length"));

            return itemStack;
            //player.openGui(TechCraft.instance, -1, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }

        this.incrementLength(player);
        UtilPlayers.sendChatToPlayer(player, "Debth of tunnel == " + UtilItemStack.getNBTTagInt(itemStack, "length"));

        return itemStack;
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz){

        ForgeDirection sideF = UtilDirection.translateNumberToDirection(side);

        UtilItemStack.setNBTTagInt(itemStack, "id", world.getBlockId(x, y, z));

        boolean isSucesful = UtilBlock.cycle3DBlock(player, world, x, y, z, sideF, 1,UtilItemStack.getNBTTagInt(itemStack, "length"), this, 0);

        UtilItemStack.setNBTTagInt(itemStack, "id", 0);  
        return isSucesful;        
    }

    public void decrementLength(EntityPlayer player) {

        ItemStack cItem = player.inventory.getCurrentItem();

        UtilItemStack.setNBTTagInt(cItem, "length", UtilItemStack.getNBTTagInt(cItem, "length") - 1);
    }

    public void incrementLength(EntityPlayer player) {

        ItemStack cItem = player.inventory.getCurrentItem();

        UtilItemStack.setNBTTagInt(cItem, "length", UtilItemStack.getNBTTagInt(cItem, "length") + 1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) 
    {
        super.addInformation(itemStack, player, info, var1);
        info.add("Idea Source: EE2");
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int mid) {

        int blockToBreak = UtilItemStack.getNBTTagInt(player.inventory.getCurrentItem(), "id");

        if(world.blockExists(x, y, z)) {

            if(!(Block.blocksList[blockToBreak].getBlockHardness(world, x, y, z) == -1)) {

                if(world.getBlockTileEntity(x, y,  z) == null) {

                    if(world.getBlockId(x, y, z) == blockToBreak) {

                        int id = world.getBlockId(x, y, z);

                        if (id > 0) {

                            int meta = world.getBlockMetadata(x, y, z);

                            player.inventory.getCurrentItem().damageItem(1, player);
                            world.playAuxSFX(2001, x, y, z, id + (meta << 12));
                            Block.blocksList[id].dropBlockAsItem(world, (int)player.posX, (int)player.posY, (int)player.posZ, meta, 0);
                            world.setBlockToAir(x, y, z);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
