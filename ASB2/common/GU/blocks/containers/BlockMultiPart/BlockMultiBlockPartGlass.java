package GU.blocks.containers.BlockMultiPart;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMultiBlockPartGlass extends BlockMultiBlockPart {
    
    public BlockMultiBlockPartGlass(Material material) {
        super(material);
        this.setLightOpacity(0);
    }
    
    @Override
    public boolean isOpaqueCube() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        
        return false;
    }
    
    @Override
    public boolean canRenderNoise(int metadata) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean canRenderNoise(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        par3List.add("Made just for you: ".concat(player.getDisplayName()));
    }
}
