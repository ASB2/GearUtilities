package GU.blocks.containers.BlockItemSender;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.GearUtilities;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;

public class BlockItemSender extends ContainerBase {

    public BlockItemSender(int id, Material material) {
        super(id, material);
        
        this.useStandardRendering = false;
        this.registerTile(TileItemSender.class);
    }

    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {

        return ForgeDirection.VALID_DIRECTIONS;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {

        if (!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.ITEM_SENDER, world, x, y, z);
            return true;
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileItemSender();
    }
}
