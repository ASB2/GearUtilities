package GU.blocks.containers.BlockFluidProvider;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.blocks.containers.ContainerBase;
import GU.utils.*;

public class BlockFluidProvider extends ContainerBase {

    public BlockFluidProvider(int id, Material material) {
        super(id, material);

        this.registerTile(TileFluidProvider.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer entityplayer, int par6, float par7, float par8,
            float par9) {
        ItemStack current = entityplayer.inventory.getCurrentItem();

        if (current != null
                && FluidContainerRegistry.getFluidForFilledItem(current) != null) {

            FluidStack fluid = FluidContainerRegistry
                    .getFluidForFilledItem(current);
            TileFluidProvider tank = (TileFluidProvider) world
                    .getBlockTileEntity(x, y, z);

            if (fluid != null) {

                tank.fluidStack = fluid;
                return true;
            }
        } else {

            TileFluidProvider tank = (TileFluidProvider) world
                    .getBlockTileEntity(x, y, z);

            if (tank.fluidTank.getFluid() != null) {

                UtilPlayers
                        .sendChatToPlayer(entityplayer, "Current Fluids is: "
                                + tank.fluidStack.getFluid().getLocalizedName());
            } else {

                UtilPlayers.sendChatToPlayer(entityplayer, "No Fluid");
                return true;
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileFluidProvider();
    }
}
