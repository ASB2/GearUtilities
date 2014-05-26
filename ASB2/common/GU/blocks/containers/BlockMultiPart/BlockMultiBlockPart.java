package GU.blocks.containers.BlockMultiPart;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.color.VanillaColor;
import GU.blocks.containers.BlockMultiMetadataContainerBase;
import GU.render.BlockSimpleRenderer.INoiseBlockRender;
import UC.color.Color4i;

public class BlockMultiBlockPart extends BlockMultiMetadataContainerBase implements INoiseBlockRender {
    
    public BlockMultiBlockPart(Material material) {
        super(material);
        
        this.registerTile(TileMultiPart.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IColorableTile) {
            
            if (player.getHeldItem() != null) {
                
                if (VanillaColor.isItemDye(player.getHeldItem())) {
                    
                    if (!player.isSneaking()) {
                        
                        return ((IColorableTile) tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColor4iValue(player.getHeldItem())), ForgeDirection.getOrientation(side));
                    }
                    else {
                        
                        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                            
                            ((IColorableTile) tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColor4iValue(player.getHeldItem())), direction);
                        }
                        return true;
                    }
                }
                // else {
                //
                // Color color = ((IColorable)
                // tile).getColor(ForgeDirection.getOrientation(side));
                // ((IColorable) tile).setColor(UtilMisc.changeRed(color, -1),
                // ForgeDirection.getOrientation(side));
                // }
                
            }
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileMultiPart();
    }
    
    @Override
    public Color4i getColor(int metadata) {
        
        return Color4i.WHITE;
    }
    
    @Override
    public Color4i getColor(IBlockAccess world, int x, int y, int z) {
        
        return ((TileMultiPart) world.getTileEntity(x, y, z)).getColor(ForgeDirection.UNKNOWN);
    }
}
