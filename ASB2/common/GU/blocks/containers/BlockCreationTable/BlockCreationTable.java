package GU.blocks.containers.BlockCreationTable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;
import GU.info.*;
import GU.*;

public class BlockCreationTable extends ContainerBase {

    Icon top;
    Icon sides;
    Icon bottom; 

    public BlockCreationTable(int id, Material material) {
        super(id, material);

        this.registerTile(TileCreationTable.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) 
    {
        if(!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.CREATION_TABLE, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegistry) {

        sides = iconRegistry.registerIcon(Reference.MODDID + ":BlockCreationTableSide");
        bottom = iconRegistry.registerIcon(Reference.MODDID + ":DefaultTexture");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        switch(side) {

            case 0: return bottom;
            case 1: return Block.workbench.getIcon(side, metadata);
            default: return sides;
        }
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCreationTable();
    }
}