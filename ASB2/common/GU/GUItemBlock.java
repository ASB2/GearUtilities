package GU;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.blocks.BlockBase;

public class GUItemBlock extends ItemBlock {
    
    protected BlockBase block;
    
    public GUItemBlock(Block block) {
        super(block);
        
        this.block = (BlockBase) block;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
        block.addInformation(stack, player, par3List, par4);
    }
    
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        boolean worked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        
        if (worked && block.getPlaceItemStackMetadata()) {
            
            applyBlockStuff(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        }
        return worked;
    }
    
    public void applyBlockStuff(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        if (block.getPlaceItemStackMetadata()) {
            
            world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 3);
        }
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        if (field_150939_a instanceof BlockBase) {
            
            BlockBase block = (BlockBase) field_150939_a;
            
            return block.getBlockDisplayName(itemStack) == null ? super.getItemStackDisplayName(itemStack) : block.getBlockDisplayName(itemStack);
        }
        return super.getItemStackDisplayName(itemStack);
    }
}
