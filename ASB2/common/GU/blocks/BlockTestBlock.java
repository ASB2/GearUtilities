package GU.blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilInventory;

public class BlockTestBlock extends BlockBase {

    public BlockTestBlock(int id, Material material) {
        super(id, material);
        this.useDefaultTexture = true;
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {

        if(player.inventory.getCurrentItem() != null) { 

            if(player.inventory.getCurrentItem().getItem() == Item.flint) {

                ItemStack stack = player.inventory.getCurrentItem().copy();
                stack.stackSize = 1;

                UtilInventory.removeItemStackFromInventory(player.inventory, stack, 1, true);
                UtilInventory.addItemStackToInventoryAndSpawnExcess(world, player.inventory, new ItemStack(Item.diamond, 1, 1), x, y, z);
            }
        }           
    }


    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

        array.add(new ItemStack(this));
        array.add(new ItemStack(Item.diamond));
        return array;
    }
}
