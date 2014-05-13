package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.GUItemBlock;

public class ItemBlockElectisCrystal extends GUItemBlock {
    
    public ItemBlockElectisCrystal(Block block) {
        super(block);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        boolean worked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        
        if (worked) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof TileElectisCrystal) {
                
                ((TileElectisCrystal) tile).setCrystalType(EnumElectisCrystalType.getCrystalType(stack));
            }
        }
        return worked;
    }
    
}
