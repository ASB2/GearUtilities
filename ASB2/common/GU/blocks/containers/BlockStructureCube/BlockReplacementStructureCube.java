package GU.blocks.containers.BlockStructureCube;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import GU.EnumState;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;
import GU.blocks.containers.ContainerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReplacementStructureCube extends ContainerBase {

    public BlockReplacementStructureCube(int id, Material material) {
        super(id, material);

        specialMetadata = true;
        this.registerTile(TileReplacementStructureCube.class);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        IMultiBlockPart tile = (IMultiBlockPart) world.getBlockTileEntity(x, y, z);

        if (tile != null) {

            if (!tile.getComprisedMultiBlocks().isEmpty()) {

                for (IMultiBlock multi : tile.getComprisedMultiBlocks()) {

                    multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {

        return true;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {

        return ReplacementStructureCubeRenderer.renderID;
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getBlockHardness(world, x, y, z);
        }
        return block.getBlockHardness(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getBlockBrightness(world, x, y, z);
        }
        return block.getBlockBrightness(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getMixedBrightnessForBlock(world, x, y, z);
        }

        return block.getMixedBrightnessForBlock(world, x, y, z);

    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.shouldSideBeRendered(world, x, y, z, side);
        }
        return block.shouldSideBeRendered(world, x, y, z, side);
    }

    /**
     * Returns Returns true if the given side of this block type should be rendered (if it's solid or not), if the adjacent block is at the
     * given coordinates. Args: blockAccess, x, y, z, side
     */
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockSolid(world, x, y, z, side);
        }

        return block.isBlockSolid(world, x, y, z, side);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return EnumState.BOTH.getStateIcon();
        }

        return block.getBlockTexture(world, x, y, z, side);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getSelectedBoundingBoxFromPool(world, x, y, z);
        }

        return block.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
        return block.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            super.randomDisplayTick(world, x, y, z, random);
            return;
        }
        block.randomDisplayTick(world, x, y, z, random);
    }

    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
        }
        return block.getPlayerRelativeBlockHardness(player, world, x, y, z);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            super.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
            return;
        }

        block.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
    }

    // TODO CHECK THIS OUT
    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the block and l is the
     * block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int par6) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            super.harvestBlock(world, player, x, y, z, par6);
            return;
        }
        block.harvestBlock(world, player, x, y, z, par6);
    }

    /**
     * Returns the default ambient occlusion value based on block opacity
     */
    public float getAmbientOcclusionLightValue(IBlockAccess world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getAmbientOcclusionLightValue(world, x, y, z);
        }

        return block.getAmbientOcclusionLightValue(world, x, y, z);
    }

    @SuppressWarnings("rawtypes")
    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {

    }

    /**
     * Get a light value for the block at the specified coordinates, normal ranges are between 0 and 15
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @return The light value
     */
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof TileReplacementStructureCube) {

            Block block = Block.blocksList[((TileReplacementStructureCube) tile).getSavedID()];

            if(block != null) {
                
                return lightValue[((TileReplacementStructureCube)tile).getSavedID()];
            }
        }
        return super.getLightValue(world, x, y, z);
    }

    /**
     * Return true if the block is a normal, solid cube. This determines indirect power state, entity ejection from blocks, and a few
     * others.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @return True if the block is a full cube
     */
    public boolean isBlockNormalCube(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockNormalCube(world, x, y, z);
        }

        return block.isBlockNormalCube(world, x, y, z);
    }

    /**
     * Checks if the block is a solid face on the given side, used by placement logic.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @param side
     *            The side to check
     * @return True if the block is solid on the specified side.
     */
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockSolidOnSide(world, x, y, z, side);
        }

        return block.isBlockSolidOnSide(world, x, y, z, side);
    }

    /**
     * Determines if a new block can be replace the space occupied by this one, Used in the player's placement code to make the block act
     * like water, and lava.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @return True if the block is replaceable by another block
     */
    public boolean isBlockReplaceable(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockReplaceable(world, x, y, z);
        }

        return block.isBlockReplaceable(world, x, y, z);
    }

    /**
     * Determines if this block should set fire and deal fire damage to entities coming into contact with it.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @return True if the block should deal damage
     */
    public boolean isBlockBurning(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockBurning(world, x, y, z);
        }

        return block.isBlockBurning(world, x, y, z);
    }

    /**
     * Determines this block should be treated as an air block by the rest of the code. This method is primarily useful for creating pure
     * logic-blocks that will be invisible to the player and otherwise interact as air would.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y position
     * @param z
     *            Z position
     * @return True if the block considered air
     */
    public boolean isAirBlock(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.isBlockBurning(world, x, y, z);
        }
        return block.isAirBlock(world, x, y, z);
    }

    /**
     * This returns a complete list of items dropped from this block.
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y Position
     * @param z
     *            Z Position
     * @param metadata
     *            Current metadata
     * @param fortune
     *            Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getBlockDropped(world, x, y, z, metadata, fortune);
        }
        return block.getBlockDropped(world, x, y, z, metadata, fortune);
    }

    /**
     * Called when a user uses the creative pick block button on this block
     * 
     * @param target
     *            The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getPickBlock(target, world, x, y, z);
        }
        return block.getPickBlock(target, world, x, y, z);
    }

    /**
     * Spawn a digging particle effect in the world, this is a wrapper around EffectRenderer.addBlockHitEffects to allow the block more
     * control over the particles. Useful when you have entirely different texture sheets for different sides/locations in the world.
     * 
     * @param world
     *            The current world
     * @param target
     *            The target the player is looking at {x/y/z/side/sub}
     * @param effectRenderer
     *            A reference to the current effect renderer.
     * @return True to prevent vanilla digging particles form spawning.
     */
    @SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer) {

        Block block = this.getFalseBlock(world, target.blockX, target.blockY, target.blockZ);

        if (block == null) {

            return super.addBlockHitEffects(world, target, effectRenderer);
        }
        return block.addBlockHitEffects(world, target, effectRenderer);
    }

    /**
     * Spawn particles for when the block is destroyed. Due to the nature of how this is invoked, the x/y/z locations are not always
     * guaranteed to host your block. So be sure to do proper sanity checks before assuming that the location is this block.
     * 
     * @param world
     *            The current world
     * @param x
     *            X position to spawn the particle
     * @param y
     *            Y position to spawn the particle
     * @param z
     *            Z position to spawn the particle
     * @param meta
     *            The metadata for the block before it was destroyed.
     * @param effectRenderer
     *            A reference to the current effect renderer.
     * @return True to prevent vanilla break particles from spawning.
     */
    @SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
        }

        return block.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    /**
     * Determines if this block can support the passed in plant, allowing it to be planted and grow. Some examples: Reeds check if its a
     * reed, or if its sand/dirt/grass and adjacent to water Cacti checks if its a cacti, or if its sand Nether types check for soul sand
     * Crops check for tilled soil Caves check if it's a colid surface Plains check if its grass or dirt Water check if its still water
     * 
     * @param world
     *            The current world
     * @param x
     *            X Position
     * @param y
     *            Y Position
     * @param z
     *            Z position
     * @param direction
     *            The direction relative to the given position the plant wants to be, typically its UP
     * @param plant
     *            The plant that wants to check
     * @return True to allow the plant to be planted/stay.
     */
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.canSustainPlant(world, x, y, z, direction, plant);
        }

        return block.canSustainPlant(world, x, y, z, direction, plant);
    }

    @Override
    public int getLightOpacity(World world, int x, int y, int z) {

        Block block = this.getFalseBlock(world, x, y, z);

        if (block == null) {

            return super.getLightOpacity(world, x, y, z);
        }
        return block.getLightOpacity(world, x, y, z);
    }

    // Breaker

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explostion) {
        destryLogic(world, x, y, z);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {
        destryLogic(world, x, y, z);
    }

    public void destryLogic(World world, int x, int y, int z) {

        TileReplacementStructureCube tile = (TileReplacementStructureCube) world.getBlockTileEntity(x, y, z);

        if (tile == null) {
            return;
        }
        Block block = Block.blocksList[tile.getSavedID()];

        if (tile.getSavedID() == 0 || block == null) {

            return;
        }

        world.setBlock(x, y, z, tile.getSavedID(), tile.getSavedMetadata(), 3);
    }

    public Block getFalseBlock(IBlockAccess world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile != null && tile instanceof TileReplacementStructureCube) {

            Block block = Block.blocksList[((TileReplacementStructureCube) tile).getSavedID()];
//            UtilEntity.sendClientChat("Saved ID " + ((TileReplacementStructureCube) tile).getSavedID() + " " + "Saved Meatadata: " + ((TileReplacementStructureCube) tile).getSavedMetadata());
            return block != null ? block : null;
        }
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileReplacementStructureCube();
    }
}
