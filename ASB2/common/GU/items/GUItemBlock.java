package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import ASB2.utils.UtilMisc;
import GU.info.Reference;

public class GUItemBlock extends ItemBlock {
    
    public GUItemBlock(int id) {
        super(id);
        this.setHasSubtypes(true);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        // super.addInformation(itemStack, player, info, var1);
        
        info.add("From: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + Reference.NAME);
        
        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            
            this.addInformationSneaking(itemStack, player, info, var1);
        } else {
            
            info.add("Press " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Shift " + UtilMisc.getColorCode(EnumChatFormatting.GRAY) + "to show more info");
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        if (Block.blocksList[this.getBlockID()] instanceof IExtraItemBlockInfo) {
            
            ((IExtraItemBlockInfo) Block.blocksList[this.getBlockID()]).addInformationSneaking(itemStack, player, info, var1);
        }
    }
    
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        Block block = Block.blocksList[this.getBlockID()];
        if (!world.setBlock(x, y, z, this.getBlockID(), ((IExtraItemBlockInfo) block).getPlacedMetadata(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata), 3)) {
            
            return false;
        }
        
        if (world.getBlockId(x, y, z) == this.getBlockID()) {
            
            block.onBlockPlacedBy(world, x, y, z, player, stack);
            block.onPostBlockPlaced(world, x, y, z, metadata);
        }
        return true;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        Block block = Block.blocksList[this.getBlockID()];
        
        if (block != null && block instanceof IExtraItemBlockInfo) {
            
            String name = ((IExtraItemBlockInfo) block).getUnlocalizedName(itemStack);
            
            if (name.equalsIgnoreCase("")) {
                
                return super.getUnlocalizedName(itemStack);
            } else {
                
                return name;
            }
        }
        return super.getUnlocalizedName(itemStack);
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        Block block = Block.blocksList[this.getBlockID()];
        if (block != null && block instanceof IExtraItemBlockInfo) {
            
            String name = ((IExtraItemBlockInfo) block).getItemStackDisplayName(itemStack);
            
            if (name.equalsIgnoreCase("")) {
                
                return super.getItemStackDisplayName(itemStack);
            } else {
                
                return name;
            }
        }
        return super.getItemStackDisplayName(itemStack);
    }
}
