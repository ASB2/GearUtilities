package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilMisc;
import ASB2.utils.UtilEntity;

public class ItemTradeStick extends ItemBase {

    public ItemTradeStick(int par1) {
        super(par1);
        this.setMaxDamage(150);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Idea Source: " + UtilMisc.getColorCode(EnumChatFormatting.DARK_AQUA) + "Thaumcraft 3");
    }

    public void setBlockIDAndMeta(ItemStack item, int id, int meta) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setInteger("id", id);
        nbtTagCompound.setInteger("meta", meta);
    }

    public int[] getBlockID(ItemStack item) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);

        return new int[]{nbtTagCompound.getInteger("id"), nbtTagCompound.getInteger("meta")};
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        if(player.isSneaking()) {

            this.setBlockIDAndMeta(itemStack, world.getBlockId(x, y, z), world.getBlockMetadata(x, y, z));
            UtilEntity.sendChatToPlayer(player, "Block ID set to: " + world.getBlockId(x, y, z));
            return true;
        }

        else {

            if(this.getBlockID(itemStack)[0] > 0) {

                this.breakBlock(world, player, x, y, z, this.getBlockID(itemStack)[0], this.getBlockID(itemStack)[1]);
                return true;
            }
        }
        return false;
    }

    public void breakBlock(World world, EntityPlayer player, int x, int y, int z, int blockToPlace, int blockMeta) {

        if(world.blockExists(x, y, z)) {

            if(UtilBlock.isBreakable(world, x, y, z)) {

                if(world.getBlockId(x, y, z) == blockToPlace && world.getBlockMetadata(x, y, z) == blockMeta) {

                    return;
                }
                else {

                    ItemStack blockToSet = new ItemStack(Block.blocksList[blockToPlace], 1, blockMeta);

                    if(!player.capabilities.isCreativeMode) {

                        if(UtilInventory.consumeItemStack(player.inventory, blockToSet, 1)) {

                            player.inventory.getCurrentItem().damageItem(1, player);
                            UtilBlock.breakAndAddToInventorySpawnExcess(player.inventory, world, x, y, z, 1, true);
                            world.setBlock(x, y, z, blockToPlace, blockMeta, 3);
                        }
                    }
                    else {

                        UtilBlock.breakBlock(world, x, y, z);
                        world.setBlock(x, y, z, blockToPlace, blockMeta, 3);
                    }
                }
            }
        }
    }
}
