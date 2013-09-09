package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilEntity;

public class ItemEnhancedDestructionCatalyst extends ItemBase implements IBlockCycle {

    public ItemEnhancedDestructionCatalyst(int id) {
        super(id);
        this.setMaxDamage(300);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if (player.isSneaking()) {

            this.decrementLength(player);

            UtilEntity.sendChatToPlayer(player, "Debth of tunnel == " + UtilItemStack.getNBTTagInt(itemStack, "length"));

            return itemStack;
        }

        this.incrementLength(player);
        UtilEntity.sendChatToPlayer(player, "Debth of tunnel == " + UtilItemStack.getNBTTagInt(itemStack, "length"));

        return itemStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        UtilItemStack.setNBTTagInt(itemStack, "id", world.getBlockId(x, y, z));

        UtilBlock.cycle3DBlock(player, world, x, y, z, ForgeDirection.getOrientation(side), 100, UtilItemStack.getNBTTagInt(itemStack, "length"), this, 0);

        UtilItemStack.setNBTTagInt(itemStack, "id", 0);
        return true;
    }

    public void decrementLength(EntityPlayer player) {

        ItemStack cItem = player.inventory.getCurrentItem();

        if (UtilItemStack.getNBTTagInt(cItem, "length") > 0) {

            UtilItemStack.setNBTTagInt(cItem, "length", UtilItemStack.getNBTTagInt(cItem, "length") - 1);
        }
    }

    public void incrementLength(EntityPlayer player) {

        ItemStack cItem = player.inventory.getCurrentItem();

        UtilItemStack.setNBTTagInt(cItem, "length", UtilItemStack.getNBTTagInt(cItem, "length") + 1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformation(itemStack, player, info, var1);
        info.add("Idea Source: EE2");
    }

    @Override
    public boolean execute(EntityLivingBase player, World world, int x, int y, int z, ForgeDirection side, int mid) {

        int blockToBreak = UtilItemStack.getNBTTagInt(((EntityPlayer)player).inventory.getCurrentItem(), "id");

        if (world.blockExists(x, y, z)) {

            if(Block.blocksList[blockToBreak] != null) {
                
                if (Block.blocksList[blockToBreak].getBlockHardness(world, x, y, z) != -1) {

                    if (world.getBlockTileEntity(x, y, z) == null) {

                        if (world.getBlockId(x, y, z) == blockToBreak || (world.getBlockId(x, y, z) == Block.oreRedstone.blockID && blockToBreak == Block.oreRedstoneGlowing.blockID)) {

                            if(!((EntityPlayer)player).capabilities.isCreativeMode) {

                                if (UtilItemStack.damageItem(player, ((EntityPlayer)player).inventory.getCurrentItem(), 1)) {

                                    UtilBlock.breakAndAddToInventory(((EntityPlayer)player).inventory, world, x, y, z, true);
                                    return true;
                                }
                            }
                            else {

                                world.setBlockToAir(x, y, z);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
