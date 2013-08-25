package GU.blocks.containers.BlockBlockBreaker;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.api.WhiteLists;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;
import GU.info.Reference;

public class BlockBlockBreaker extends ContainerBase {

    Icon front;

    public BlockBlockBreaker(int id, Material material) {
        super(id, material);
        this.registerTile(TileBlockBreaker.class);
        this.useStandardRendering = false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);

        front = iconRegister.registerIcon(Reference.MODDID + ":BlockBlockBreakerFront");
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

            if(((TileBlockBreaker)tile).getOrientation().ordinal() == side) {
                return front;
            }
        }
        return super.getBlockTexture(world, x, y, z, side);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {

        if(!WhiteLists.getInstance().isWrench(player.inventory.getCurrentItem())) {
            
            if (!player.isSneaking()) {

                player.openGui(GearUtilities.instance, Gui.BLOCK_BREAKER, world, x, y, z);
                return true;
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileBlockBreaker();
    }
}
