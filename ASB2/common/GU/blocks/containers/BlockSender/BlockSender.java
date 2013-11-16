package GU.blocks.containers.BlockSender;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilItemStack;
import GU.GearUtilities;
import GU.blocks.containers.ContainerBase;
import GU.info.Gui;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSender extends ContainerBase {

    public BlockSender(int id, Material material) {
        super(id, material);

        this.useStandardRendering = false;
        this.registerTile(TileSender.class);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

        return array;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null) {

            ItemStack stack = new ItemStack(this);
            UtilItemStack.setNBTTagInt(stack, "mode", ((TileSender) tile).getMode());
            return stack;
        }
        return new ItemStack(this);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {

        ItemStack stack = new ItemStack(this);

        UtilItemStack.setNBTTagInt(stack, "mode", 0);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 1);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 2);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 3);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 4);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 5);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 6);
        subItems.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "mode", 7);
        subItems.add(stack.copy());
    }

    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {

        return ForgeDirection.VALID_DIRECTIONS;
    }

    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, ItemBlockSender.class, this.getUnlocalizedName());
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        float minWidth = 0, minHeight = 0;

        float maxWidth = 1, maxHeight = .65F;

        switch(ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

            case DOWN: {

                this.setBlockBounds(minWidth, maxWidth - maxHeight, minWidth, maxWidth, 1, maxWidth);
                return;
            }

            case UP: {

                this.setBlockBounds(minWidth, minHeight, minWidth, maxWidth, maxHeight, maxWidth);
                break;
            }

            case NORTH: {

                this.setBlockBounds(minWidth, minWidth, maxWidth - maxHeight, maxWidth, maxWidth, maxWidth);
                break;
            }

            case SOUTH: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxWidth, maxWidth, maxHeight);
                break;
            }

            case WEST: {

                this.setBlockBounds(1 - maxHeight, minWidth, minWidth, maxWidth, maxWidth, maxWidth);
                break;
            }

            case EAST: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxHeight, maxWidth, maxWidth);
                break;
            }

            default: {

                this.setBlockBounds(0, 0, 0, 1, 1, 1);
                break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int hitX, float hitY, float hitZ, float par9) {

        if(!player.isSneaking()) {

            player.openGui(GearUtilities.instance, Gui.SENDER, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileSender();
    }
}
