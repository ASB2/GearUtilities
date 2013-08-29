package GU.blocks.containers.BlockRunicCube;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.runes.IRuneItem;
import GU.blocks.containers.ContainerBase;
import GU.utils.UtilInventory;

public class BlockRunicCube extends ContainerBase {

    public BlockRunicCube(int id, Material material) {
        super(id, material);

        this.registerTile(TileRunicCube.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TileRunicCube rune = (TileRunicCube)world.getBlockTileEntity(x, y, z);

        if(player.inventory.getCurrentItem() != null) {

            if(player.inventory.getCurrentItem().getItem() instanceof IRuneItem) {

                if(UtilInventory.addItemStackToInventory(rune, player.inventory.getCurrentItem(), false)) {

                    if(UtilInventory.consumeItemStack(player.inventory, player.inventory.getCurrentItem(), 1)) {
                        
                        return UtilInventory.addItemStackToInventory(rune, player.inventory.getCurrentItem(), true);
                    }
                }
            }
            else {

                if(!rune.getRunes().isEmpty()) {

                    UtilInventory.addItemStackToInventoryAndSpawnExcess(world, player.inventory, rune.removeRune(), x, y, z);        
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileRunicCube(1);
    }
}
