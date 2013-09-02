package GU.color;

import java.awt.Color;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.ItemRegistry;
import GU.api.color.VanillaColor;
import GU.api.color.IColorable;
import GU.blocks.containers.ContainerBase;
import GU.utils.UtilMisc;
import GU.utils.UtilPlayers;

public abstract class BlockColorable extends ContainerBase {

    public BlockColorable(int id, Material material) {
        super(id, material);

        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IColorable) {

            if(player.inventory.getCurrentItem() != null) {

                if(VanillaColor.isItemDye(player.inventory.getCurrentItem())) {

                    if(!player.isSneaking()) {

                        ((IColorable)tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColorValue(player.getHeldItem())), ForgeDirection.getOrientation(side));
                        return true;
                    }
                    else {

                        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                            ((IColorable)tile).setColor(VanillaColor.getRGBValue(VanillaColor.getItemColorValue(player.getHeldItem())), direction);
                        }
                        return true;
                    }
                }
                else {

                    int amount = -50;

                    if(player.getHeldItem().getItem() == ItemRegistry.ItemEarthCrystalShard) {

                        Color color = UtilMisc.changeRed(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);                        
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side));   

                        color = UtilMisc.changeBlue(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);     
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side));
                        return true;
                    }
                    if(player.getHeldItem().getItem() == ItemRegistry.ItemFireCrystalShard) {

                        Color color = UtilMisc.changeGreen(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);                        
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side));    

                        color = UtilMisc.changeBlue(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);     
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side));
                        return true;                   
                    }
                    if(player.getHeldItem().getItem() == ItemRegistry.ItemWaterCrystalShard) {

                        if(player.isSneaking())
                        UtilPlayers.sendChatToPlayer(player, "Hi");
                        Color color = UtilMisc.changeRed(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);                        
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side)); 

                        color = UtilMisc.changeGreen(((IColorable)tile).getColor(ForgeDirection.getOrientation(side)), amount);     
                        ((IColorable)tile).setColor(color, ForgeDirection.getOrientation(side));
                        return true;                   
                    }
                }
            }
        }
        return false;
    }
}
