package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
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

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz){

        if(player.isSneaking()) {

            UtilPlayers.sendChatToPlayer(player, "Block ID set to: " + world.getBlockId(x,y,z));
            this.setBlockIDAndMeta(itemStack, world.getBlockId(x,y,z), world.getBlockMetadata(x, y, z));

            return true;
        }

        else {

            if(this.getBlockID(itemStack)[0] > 0) {

                //this.cycle2DBlock(player, world, sideF, x, y, z, 1, 1, this.getBlockID(itemStack));            
                this.breakBlock(world, player, x, y, z, this.getBlockID(itemStack)[0], this.getBlockID(itemStack)[1]);
            }
        }
        return true;        
    }

    public void breakBlock(World world, EntityPlayer player, int x, int y, int z, int blockToBreak, int blockmeta) {

        if(world.blockExists(x, y, z)) {

            if(blockToBreak != Block.bedrock.blockID && world.getBlockId(x,y,z) != Block.bedrock.blockID) { 

                if(world.getBlockTileEntity(x, y,  z) == null) {

                    if(world.getBlockId(x, y, z) != blockToBreak) {

                        int id = world.getBlockId(x, y, z);

                        int meta = world.getBlockMetadata(x, y, z);

                        ItemStack blockToSet = new ItemStack(blockToBreak, 1, blockmeta);

                        if(player.inventory.hasItemStack(blockToSet)) {

                            player.inventory.getCurrentItem().damageItem(1, player);
                            world.playAuxSFX(2001, x, y, z, id + (meta << 12));

                            Block.blocksList[id].dropBlockAsItem(world, (int)player.posX, (int)player.posY, (int)player.posZ, meta, 0);
                            world.setBlockToAir(x, y, z);
                            world.setBlock(x, y, z, blockToBreak);

                            for(int i = 0; i < player.inventory.getSizeInventory(); i++) {

                                if(player.inventory.getStackInSlot(i) != null) {

                                    if(player.inventory.getStackInSlot(i).isItemEqual(blockToSet)) {

                                        if(!player.capabilities.isCreativeMode)
                                            player.inventory.decrStackSize(i, 1);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
