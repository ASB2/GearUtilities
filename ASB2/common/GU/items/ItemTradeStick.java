package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.utils.UtilBlock;
import GU.utils.UtilInventory;
import GU.utils.UtilItemStack;
import GU.utils.UtilPlayers;

public class ItemTradeStick extends ItemBase {

    public ItemTradeStick(int par1) {
        super(par1);
        this.setMaxDamage(150);
        this.setMaxStackSize(1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) 
    {
        super.addInformation(itemStack, player, info, var1);
        info.add("Idea Source: Thaumcraft 3");
    }

    public void setBlockIDAndMeta(ItemStack item, int id, int meta) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setInteger("id", id);
        nbtTagCompound.setInteger("meta", meta);
    }

    public int[] getBlockID(ItemStack item) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);

        return new int[] {nbtTagCompound.getInteger("id"), nbtTagCompound.getInteger("meta")};
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz){

        if(player.isSneaking()) {

            this.setBlockIDAndMeta(itemStack, world.getBlockId(x,y,z), world.getBlockMetadata(x, y, z));
            UtilPlayers.sendChatToPlayer(player, "Block ID set to: " + world.getBlockId(x,y,z));
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

            if(!(Block.blocksList[blockToPlace].equals(Block.bedrock))) {

                if(Block.blocksList[world.getBlockId(x,y,z)].getBlockHardness(world, x, y, z) != -1) { 

                    if(world.getBlockTileEntity(x, y,  z) == null) {

                        if(world.getBlockId(x, y, z) != blockToPlace || world.getBlockMetadata(x, y, z) != blockMeta) {

                            ItemStack blockToSet = new ItemStack(blockToPlace, 1, blockMeta);

                            if(!player.capabilities.isCreativeMode) {

                                if(UtilItemStack.damageItem(player, player.inventory.getCurrentItem(), 1)) {

                                    if(UtilInventory.consumeItemStack(player.inventory, blockToSet, 1)) {

                                        UtilBlock.breakAndAddToInventory(player.inventory, world, x, y, z, 1, true);
                                        world.setBlock(x, y, z, blockToPlace, blockMeta, 3);                                                             
                                    }
                                }
                            }
                            else {
                                world.playAuxSFX(2001, x, y, z, world.getBlockId(x,y,z) + (world.getBlockMetadata(x, y, z) << 12));
                                world.setBlockToAir(x, y, z);
                                world.setBlock(x, y, z, blockToPlace, blockMeta, 3);      
                            }
                        }
                    }
                }
            }
        }
    }
}
