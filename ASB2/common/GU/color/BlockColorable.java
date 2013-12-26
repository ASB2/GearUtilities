package GU.color;

import java.awt.Color;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.color.IColorable;
import GU.api.color.VanillaColor;
import GU.blocks.containers.ContainerBase;

public abstract class BlockColorable extends ContainerBase {
    
    public BlockColorable(int id, Material material) {
        super(id, material);
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection side) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null && tile instanceof IColorable) {
            
            ((IColorable) tile).setColor(Color.WHITE, side);
        }
        return false;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IColorable) {
            
            if (player.getHeldItem() != null) {
                
                if (VanillaColor.isItemDye(player.getHeldItem())) {
                    
                    if (!player.isSneaking()) {
                        
                        ((IColorable) tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColorValue(player.getHeldItem())), ForgeDirection.getOrientation(side));
                        return true;
                    } else {
                        
                        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                            
                            ((IColorable) tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColorValue(player.getHeldItem())), direction);
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
                
            } else {
                
                Color color = ((IColorable) tile).getColor(ForgeDirection.getOrientation(side));
                
                if (player.isSneaking()) {
                    
                    ((IColorable) tile).setColor(color.darker(), ForgeDirection.getOrientation(side));
                } else {
                    
                    ((IColorable) tile).setColor(color.brighter(), ForgeDirection.getOrientation(side));
                }
            }
        }
        return false;
    }
}
