package GU.blocks.containers.BlockPlaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import GU.blocks.containers.ContainerBase;

public class BlockPlaceHolder extends ContainerBase {

    public BlockPlaceHolder(int id, Material material) {
        super(id, material);
        this.useStandardRendering = false;
        this.registerTile(TilePlaceHolder.class);
        this.setTickRandomly(true);
    }

    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile instanceof TilePlaceHolder) {

            if(tile.getBlock() != null) {

                return tile.getBlock().getBlockTexture(world, x, y, z, side);
            }
        }  
        return super.getBlockTexture(world, x, y, z, side);
    }

    @SuppressWarnings("rawtypes")
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity) {


        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile instanceof TilePlaceHolder) {

            if(tile.getBlock() != null) {

                tile.getBlock().addCollisionBoxesToList(world, x, y, z, axis, list, entity);
                return;
            }
        }
        super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile instanceof TilePlaceHolder) {

            if(tile.getBlock() != null) {

                return tile.getBlock().getSelectedBoundingBoxFromPool(world, x, y, z);                
            }
        }
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile instanceof TilePlaceHolder) {

            if(tile.getBlock() != null) {

                return tile.getBlock().getCollisionBoundingBoxFromPool(world, x, y, z);                
            }
        }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);        
    }

    public boolean isOpaqueCube() {

        return false;
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            if(tile.getBlock().getTickRandomly()) {

                tile.getBlock().updateTick(world, x, y, z, rand);
                return;
            }            
        }
        super.updateTick(world, x, y, z, rand);
    }

    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().randomDisplayTick(world, x, y, z, random);
            return;
        }
        super.randomDisplayTick(world, x, y, z, random);
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onBlockDestroyedByPlayer(world, x, y, z, metaData);
            return;
        }
        super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onNeighborBlockChange(world, x, y, z, neighborBlockID);
            return;
        }
        super.onNeighborBlockChange(world, x, y, z, neighborBlockID);
    }

    public void onBlockAdded(World world, int x, int y, int z) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onBlockAdded(world, x, y, z);
            return;
        }
        super.onBlockAdded(world, x, y, z);
    }

    public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            return tile.getBlock().getPlayerRelativeBlockHardness(player, world, x, y, z);
        }
        return super.getPlayerRelativeBlockHardness(player, world, x, y, z);
    }

    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
            return;
        }
        super.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec1, Vec3 vec2) { 

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            return tile.getBlock().collisionRayTrace(world, x, y, z, vec1, vec2);
        }
        return super.collisionRayTrace(world, x, y, z, vec1, vec2);
    }

    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onBlockDestroyedByExplosion(world, x, y, z, explosion);
            return;
        }
        super.onBlockDestroyedByExplosion(world, x, y, z, explosion);
    }

    public int getRenderBlockPass() {
        
        return PlaceHolderRenderer.placeHolderID;
    }

    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            return tile.getBlock().canPlaceBlockOnSide(world, x, y, z, side);
        }
        return super.canPlaceBlockOnSide(world, x, y, z, side);
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            return tile.getBlock().canPlaceBlockAt(world, x, y, z);
        }
        return super.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            return tile.getBlock().onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
        }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onEntityWalking(world, x, y, z, entity);
            return;
        }
        super.onEntityWalking(world, x, y, z, entity);   
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entity) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onBlockClicked(world, x, y, z, entity);
            return;
        }
        super.onBlockClicked(world, x, y, z, entity);   
    }

    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec3) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().velocityToAddToEntity(world, x, y, z, entity, vec3);
            return;
        }
        super.velocityToAddToEntity(world, x, y, z, entity, vec3);  
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().setBlockBoundsBasedOnState(world, x, y, z);
            return;
        }
        super.setBlockBoundsBasedOnState(world, x, y, z);  
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().onEntityCollidedWithBlock(world, x, y, z, entity);
            return;
        }
        super.onEntityCollidedWithBlock(world, x, y, z, entity);  
    }

    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int damage) {
        
        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile.getBlock() != null) {

            tile.getBlock().harvestBlock(world, player, x, y, z, damage);
            return;
        }
        super.harvestBlock(world, player, x, y, z, damage);  
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {}


    public void onPostBlockPlaced(World par1World, int par2, int par3, int par4, int par5) {}

    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {}

    public float getAmbientOcclusionLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {}

    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {}

    public int idPicked(World par1World, int par2, int par3, int par4)
    {

    }

    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {}

    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {}

    public void onSetBlockIDWithMetaData(World par1World, int par2, int par3, int par4, int par5) {}

    public void fillWithRain(World par1World, int par2, int par3, int par4) {}

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {}

    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        TilePlaceHolder tile = (TilePlaceHolder)world.getBlockTileEntity(x, y, z);

        if (tile instanceof TilePlaceHolder) {

            if(tile.getBlock() != null) {

                return tile.getBlock().getLightValue(world, x, y, z);
            }        
        }
        return 0;
    }

    public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity)
    {}

    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {}

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {}

    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {}

    public boolean isBlockBurning(World world, int x, int y, int z)
    {}

    public boolean isAirBlock(World world, int x, int y, int z)
    {}

    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {}

    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {}

    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {}

    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {}

    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
    {}

    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {}

    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {}

    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {}

    public boolean isWood(World world, int x, int y, int z)
    {}

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {}

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {}

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {}

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {}

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {}

    public boolean isBlockFoliage(World world, int x, int y, int z)
    {}

    @SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {}

    @SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {}

    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {}

    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {}

    public boolean isFertile(World world, int x, int y, int z)
    {}

    public int getLightOpacity(World world, int x, int y, int z)
    {}

    public boolean canEntityDestroy(World world, int x, int y, int z, Entity entity)
    {}

    public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {}

    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {}

    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
    {}

    public float getEnchantPowerBonus(World world, int x, int y, int z)
    {}

    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour)
    {}

    public void onNeighborTileChange(World world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {}


    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TilePlaceHolder();
    }
}
