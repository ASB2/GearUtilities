package GU.blocks.containers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.GearUtilities;
import GU.info.Reference;
import GU.items.IExtraItemBlockInfo;
import GU.render.BlockSimpleRenderer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ContainerBase extends BlockContainer implements IExtraItemBlockInfo {

    protected boolean useStandardRendering = true;
    protected boolean specialMetadata = false;
    protected boolean dropMetadata = false;
    String[] textures = new String[0];
    protected Icon[] icons;

    protected float minWidth = 0, minHeight = 0;
    protected float maxWidth = 1, maxHeight = 1;

    public ContainerBase(int id, Material material) {
        super(id, material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(1.5f);
        setResistance(10F);
        this.setLightOpacity(16);
    }

    public void registerTile(Class<? extends TileEntity> tileClass) {

        GameRegistry.registerTileEntity(tileClass, tileClass.toString());
    }

    public ContainerBase setTextureString(String[] textures) {

        this.textures = textures;
        return this;
    }

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return lightOpacity[this.blockID] != 0;
    }

    @Override
    public boolean isOpaqueCube() {

        return true;
    }

    @Override
    public int getRenderType() {

        if (!useStandardRendering)
            return -1;
        return BlockSimpleRenderer.renderID;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {

        return new ItemStack(this, 1, specialMetadata ? world.getBlockMetadata(x, y, z) : 0);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":" + this.getTextureName());

        if (textures.length == 6) {
            icons = new Icon[6];
            for (int i = 0; i < textures.length; i++) {

                icons[i] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[i]);
            }
        } else if (textures.length == 1) {

            icons = new Icon[1];
            icons[0] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[0]);
        } else if (textures.length == 3) {

            icons = new Icon[3];
            icons[0] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[0]);
            icons[1] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[1]);
            icons[2] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[2]);
        }
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        if (icons != null) {

            if (textures.length == 1) {

                return icons[0];
            }
            if (textures.length == 6) {

                return icons[side];
            }
            if (textures.length == 3) {

                if (side == 0) {
                    return icons[0];
                }

                if (side == 1) {
                    return icons[1];
                }
                return icons[2];
            }
        }
        return blockIcon;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {

        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        if (minWidth == 0 && minHeight == 0 && maxHeight == 1 && maxWidth == 1) {
            this.setBlockBounds(0, 0, 0, 1, 1, 1);
            return;
        }
        switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

            case DOWN: {

                this.setBlockBounds(minWidth, 1 - minHeight, minWidth, maxWidth, 1 - maxHeight, maxWidth);
                break;
            }

            case UP: {

                this.setBlockBounds(minWidth, minHeight, minWidth, maxWidth, maxHeight, maxWidth);
                break;
            }

            case NORTH: {

                this.setBlockBounds(minWidth, minWidth, 1 - maxHeight, maxWidth, maxWidth, 1 - minHeight);
                break;
            }

            case SOUTH: {

                this.setBlockBounds(minWidth, minWidth, minHeight, maxWidth, maxWidth, maxHeight);
                break;
            }

            case WEST: {

                this.setBlockBounds(1 - maxHeight, minWidth, minWidth, 1 - minHeight, maxWidth, maxWidth);
                break;
            }

            case EAST: {

                this.setBlockBounds(maxHeight, minWidth, minWidth, minHeight, maxWidth, maxWidth);
                break;
            }

            default: {

                this.setBlockBounds(0, 0, 0, 1, 1, 1);
                break;
            }
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {

        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return sideHit;
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {

        this.dropItems(world, x, y, z);
        super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        this.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z) {

        if (world.getBlockTileEntity(x, y, z) instanceof IInventory) {

            IInventory tileEntity = (IInventory) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return;

            for (int slot = 0; slot < tileEntity.getSizeInventory(); slot++) {
                ItemStack item = tileEntity.getStackInSlot(slot);

                if (item != null && item.stackSize > 0) {

                    float rx = world.rand.nextFloat() * 0.8f + 0.1f;
                    float ry = world.rand.nextFloat() * 0.8f + 0.1f;
                    float rz = world.rand.nextFloat() * 0.8f + 0.1f;

                    EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());
                    world.spawnEntityInWorld(entityItem);
                    item.stackSize = 0;
                }
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, List info, boolean var1) {

        info.add("Made just for you " + player.username);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        return "";
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        return this.getUnlocalizedName();
    }

    @Override
    public int getPlacedMetadata(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {

        if (specialMetadata) {

            return stack.getItemDamage();
        }
        return side;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

        if (dropMetadata) {
            array.add(new ItemStack(this, 1, metadata));
            return array;
        }
        return super.getBlockDropped(world, x, y, z, metadata, fortune);
    }
}
