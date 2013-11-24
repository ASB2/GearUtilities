package GU.blocks.containers.BlockConnectableTank;

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
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilRender;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockConnectableTank extends ContainerBase {

    private Icon[] icons = new Icon[16];
    private Icon[] iconsTop = new Icon[16];
    private Icon top;
    private Icon bottom;

    private String folder = ":tankConnected";
    private String folderTop = ":tankConnected/topConnected";

    public BlockConnectableTank(int id, Material material) {
        super(id, material);

        this.registerTile(TileConnectableTank.class);
        useStandardRendering = false;
    }

    @Override
    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, ItemBlockConnectableTank.class, this.getUnlocalizedName());
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        
        return new ArrayList<ItemStack>();
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        if(world.getBlockTileEntity(x, y, z) instanceof TileConnectableTank) {

            TileConnectableTank tileTank = (TileConnectableTank) world.getBlockTileEntity(x, y, z);

            for(ForgeDirection direction : ForgeDirection.values()) {

                for(FluidTankInfo info : tileTank.getTankInfo(direction)) {

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

        return ConnectableTankRenderer.tankModelID;
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

                return UtilRender.renderConnectedTexture(blockAccess, iconsTop, this.blockID, x, y, z, side);
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
        bottom = iconRegistry.registerIcon(Reference.MODDID + ":DefaultTexture");
        icons[0] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connectedRegular");
        icons[1] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_d");
        icons[2] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_u");
        icons[3] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_l");
        icons[4] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_1_r");
        icons[5] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_h");
        icons[6] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_v");
        icons[7] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dl");
        icons[8] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_dr");
        icons[9] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ul");
        icons[10] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_2_ur");
        icons[11] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_d");
        icons[12] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_u");
        icons[13] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_l");
        icons[14] = iconRegistry.registerIcon(Reference.MODDID + folder + "/connected_3_r");
        icons[15] = iconRegistry.registerIcon(Reference.MODDID + folder + "/blank");

        iconsTop[0] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connectedRegular");
        iconsTop[1] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_1_d");
        iconsTop[2] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_1_u");
        iconsTop[3] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_1_l");
        iconsTop[4] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_1_r");
        iconsTop[5] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_h");
        iconsTop[6] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_v");
        iconsTop[7] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_dl");
        iconsTop[8] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_dr");
        iconsTop[9] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_ul");
        iconsTop[10] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_2_ur");
        iconsTop[11] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_3_d");
        iconsTop[12] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_3_u");
        iconsTop[13] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_3_l");
        iconsTop[14] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/connected_3_r");
        iconsTop[15] = iconRegistry.registerIcon(Reference.MODDID + folderTop + "/blank");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {

        ItemStack current = entityplayer.inventory.getCurrentItem();

        if(current != null) {

            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
            TileConnectableTank tank = (TileConnectableTank) world.getBlockTileEntity(x, y, z);

            if(fluid != null) {

                if(!entityplayer.capabilities.isCreativeMode) {

                    if(UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, false)) {

                        if(UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, true)) {

                            UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                        }
                    }
                }

                else {

                    UtilFluid.addFluidToTank(tank, ForgeDirection.getOrientation(side), fluid, true);
                }
                return true;
            }
            else {

                if(FluidContainerRegistry.isEmptyContainer(current)) {

                    if(tank.fluidTank.getFluid() != null) {

                        ItemStack filled = FluidContainerRegistry.fillFluidContainer(tank.fluidTank.getFluid(), current);

                        if(!entityplayer.capabilities.isCreativeMode) {

                            if(UtilFluid.removeFluidFromTank(tank, ForgeDirection.getOrientation(side), FluidContainerRegistry.getFluidForFilledItem(filled), true)) {

                                if(UtilInventory.addItemStackToInventoryAndSpawnExcess(world, entityplayer.inventory, filled, x, y, z)) {

                                    UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                                }
                            }
                        }
                        else {

                            UtilFluid.removeFluidFromTank(tank, ForgeDirection.getOrientation(side), fluid, true);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileConnectableTank();
    }
}
