package GU.blocks.containers.BlockMiniSteamBoiler;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilInventory;
import GU.blocks.containers.ContainerBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMiniSteamBoiler extends ContainerBase {

    public BlockMiniSteamBoiler(int id, Material material) {
        super(id, material);
        this.registerTile(TileMiniSteamBoiler.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        ItemStack stack = player.getCurrentEquippedItem();

        if(stack != null) {

            if(GameRegistry.getFuelValue(stack) > 0) {

                ((TileMiniSteamBoiler) world.getBlockTileEntity(x, y, z)).useFuel(GameRegistry.getFuelValue(stack));

                UtilInventory.removeItemStackFromInventory(player.inventory, stack, 1, true);
                return true;
            }
        }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileMiniSteamBoiler();
    }
}
