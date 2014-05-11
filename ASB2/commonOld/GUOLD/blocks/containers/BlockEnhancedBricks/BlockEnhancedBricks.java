package GUOLD.blocks.containers.BlockEnhancedBricks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilDirection;
import GUOLD.api.color.VanillaColor;
import GUOLD.color.BlockColorable;
import GUOLD.info.Reference;

public class BlockEnhancedBricks extends BlockColorable {
    
    public IIcon overlay;
    
    public BlockEnhancedBricks(Material material) {
        super(material);
        this.registerTile(TileEnhancedBricks.class);
        specialMetadata = true;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        
        overlay = iconRegister.registerIcon(Reference.MODDID + ":BlockEnhancedBricksOverlay");
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile == null) {
            
            VanillaColor color = VanillaColor.getItemColorValue(player.getHeldItem());
            
            if (player.getHeldItem() != null && VanillaColor.isItemDye(player.getHeldItem()) && VanillaColor.WHITE != color && VanillaColor.NONE != color) {
                
                world.setTileEntity(x, y, z, new TileEnhancedBricks());
            }
        }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        
        Block block = UtilDirection.translateDirectionToBlock(world, ForgeDirection.getOrientation(side), x, y, z);
        
        if (block == null || (block.isOpaqueCube() && block != this)) {
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        
        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        
        return new TileEnhancedBricks();
    }
}
