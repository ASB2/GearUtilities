package GU.blocks.containers.BlockSpeedyFurnace;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.blocks.containers.*;
import GU.info.Gui;

public class BlockSpeedyFurnace extends ContainerBase {

    public BlockSpeedyFurnace(int id, Material material) {
        super(id, material);
        this.registerTile(TileSpeedyFurnace.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) 
    {
        if(!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.SPEEDY_FURNACE, world, x, y, z);
            return true;
        }
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileSpeedyFurnace();
    }
}
