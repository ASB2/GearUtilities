package GU.blocks.containers.BlockEnhancedBricks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import GU.api.color.VanillaColor;
import GU.color.BlockColorable;
import GU.info.Reference;

public class BlockEnhancedBricks extends BlockColorable {
    
    public Icon overlay;
    
    public BlockEnhancedBricks(int id, Material material) {
        
        super(id, material);
        this.registerTile(TileEnhancedBricks.class);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        
        overlay = iconRegister.registerIcon(Reference.MODDID + ":BlockEnhancedBricksOverlay");
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (tile == null) {
            
            if (player.getHeldItem() != null && VanillaColor.isItemDye(player.getHeldItem()) && VanillaColor.WHITE != VanillaColor.getItemColorValue(player.getHeldItem())) {
                
                world.setBlockTileEntity(x, y, z, new TileEnhancedBricks());
                return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
            }
        }
        return false;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        
        int id = UtilDirection.translateDirectionToBlockId(world, ForgeDirection.getOrientation(side), x, y, z);
        
        if (id == 0 || (!Block.blocksList[id].isOpaqueCube() && id != this.blockID)) {
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        
        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return null;
    }
}
