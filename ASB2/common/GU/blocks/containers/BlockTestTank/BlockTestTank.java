package GU.blocks.containers.BlockTestTank;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;
import GU.utils.UtilInventory;
import GU.utils.UtilRender;

public class BlockTestTank extends ContainerBase {

    private Icon[] icons = new Icon[16];
    private Icon top;
    private Icon bottom;

    private String folder = ":testConnectedTexture";

    public BlockTestTank(int id, Material material) {
        super(id, material);

        this.registerTile(TileTestTank.class);
        useStandardRendering = false;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

        }
        return super.getBlockDropped(world, x, y, z, metadata, fortune);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {

        return true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        if (world.getBlockTileEntity(x, y, z) instanceof TileTestTank) {

            TileTestTank tileTank = (TileTestTank) world.getBlockTileEntity(x, y, z);

            for(ForgeDirection direction: ForgeDirection.values()) {

                for(FluidTankInfo info: tileTank.getTankInfo(direction)) {

                    if(info != null) {

                        if(info.fluid != null && info.fluid.getFluid() != null) {

                            return info.fluid.getFluid().getLuminosity();
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int getRenderType() {

        return TestTankRenderer.tankModelID;
    }

    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {

        if(side == 0) {

            if(!(blockAccess.getBlockId(x, y - 1, z) == this.blockID)) {

                return bottom;
            }
        }

        if(side == 1) {

            if(!(blockAccess.getBlockId(x, y + 1, z) == this.blockID)) {

                return top;
            }
        }

        return UtilRender.renderConnectedTexture(blockAccess, icons, this.blockID, x, y, z, side);
    }


    @Override
    public Icon getIcon(int side, int metadata) {

        if(side == 0) {

            return bottom;
        }

        if(side == 1) {

            return top;
        }
        return icons[0];
    }

    @Override
    public void registerIcons(IconRegister iconRegistry) {

        top = iconRegistry.registerIcon(Reference.MODDID + ":BlockTestTankTop");
        bottom = iconRegistry.registerIcon(Reference.MODDID + ":BlockTestTankBottom");
        icons[0] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tankRegular");
        icons[1] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_d");
        icons[2] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_u");
        icons[3] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_l");
        icons[4] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_r");
        icons[5] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_h");
        icons[6] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_v");
        icons[7] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_dl");
        icons[8] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_dr");
        icons[9] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_ul");
        icons[10] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_ur");
        icons[11] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_d");
        icons[12] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_u");
        icons[13] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_l");
        icons[14] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_r");
        icons[15] = iconRegistry.registerIcon(Reference.MODDID + folder + "/blank");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) 
    {
        ItemStack current = entityplayer.inventory.getCurrentItem();

        if (current != null) {

            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
            TileTestTank tank = (TileTestTank) world.getBlockTileEntity(x, y, z);

            if (fluid != null) {

                int amount = tank.fluidTank.fill(fluid, true);

                if (amount != 0 && !entityplayer.capabilities.isCreativeMode) {

                    return UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                }
                return true;
            } 
            else {

                if(FluidContainerRegistry.isEmptyContainer(current)) {

                    if(tank.fluidTank.getFluid() != null) {

                        ItemStack filled = FluidContainerRegistry.fillFluidContainer(tank.fluidTank.getFluid(), current);

                        if(FluidContainerRegistry.getFluidForFilledItem(filled).amount <= tank.fluidTank.getFluidAmount()) {

                            if(!entityplayer.capabilities.isCreativeMode) {

                                if(UtilInventory.addItemStackToInventoryAndSpawnExcess(world, entityplayer.inventory, filled, x, y, z) && UtilInventory.consumeItemStack(entityplayer.inventory, current, 1)) {

                                    tank.fluidTank.drain(FluidContainerRegistry.getFluidForFilledItem(filled).amount, true);
                                }
                            }
                            else {

                                tank.fluidTank.drain(FluidContainerRegistry.getFluidForFilledItem(filled).amount, true);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileTestTank();
    }
}
