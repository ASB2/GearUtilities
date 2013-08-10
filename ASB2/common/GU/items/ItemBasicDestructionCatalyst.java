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

public class ItemBasicDestructionCatalyst extends ItemBase implements IBlockCycle {

    public ItemBasicDestructionCatalyst(int par1) {
        super(par1);
        this.setMaxDamage(150);
        this.setMaxStackSize(1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) 
    {
        super.addInformation(itemStack, player, info, var1);
        info.add("Idea Source: EE2");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz){

        ForgeDirection sideF = UtilDirection.translateNumberToDirection(side);

        UtilItemStack.setNBTTagInt(itemStack, "id", world.getBlockId(x, y, z));        
        UtilBlock.cycle2DBlock(player, world, x, y, z, sideF, 1, this, 0); 

        return true;        
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int mid) {

        int blockToBreak = UtilItemStack.getNBTTagInt(player.inventory.getCurrentItem(), "id");

        if(world.blockExists(x, y, z)) {

            if(blockToBreak != Block.bedrock.blockID) { 

                if(world.getBlockTileEntity(x, y,  z) == null) {

                    if(world.getBlockId(x, y, z) == blockToBreak || (world.getBlockId(x, y, z) == Block.oreRedstone.blockID && blockToBreak == Block.oreRedstoneGlowing.blockID)) {

                        int id = world.getBlockId(x, y, z);

                        if (id > 0) {

                            if(UtilItemStack.damageItem(player, player.inventory.getCurrentItem(), 1)) {
                                
                                UtilBlock.breakAndAddToInventory(player.inventory, world, x, y, z, 1, true);
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
