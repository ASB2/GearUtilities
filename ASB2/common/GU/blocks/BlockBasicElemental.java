package GU.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GU.info.Reference;

public class BlockBasicElemental extends BlockBase {
    
    public static final int FIRE_CUBE = 0;
    public static final int WATER_CUBE = 1;
    public static final int EARTH_CUBE = 2;
    public static final int AIR_CUBE = 3;
    public static final int VOID_CUBE = 4;
    public static final int METALLIC_CUBE = 5;
    public static final int BLOOD_CUBE = 6;
    
    Icon[] fireTextures = new Icon[6];
    Icon[] waterTextures = new Icon[6];
    Icon[] earthTextures = new Icon[6];
    Icon[] airTextures = new Icon[6];
    Icon[] voidTextures = new Icon[6];
    Icon[] metallicTextures = new Icon[6];
    Icon[] bloodTextures = new Icon[6];
    
    public BlockBasicElemental(int id, Material material) {
        super(id, material);
        this.setTickRandomly(true);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        list.add(new ItemStack(this, 1, FIRE_CUBE));
        list.add(new ItemStack(this, 1, WATER_CUBE));
        list.add(new ItemStack(this, 1, EARTH_CUBE));
        list.add(new ItemStack(this, 1, AIR_CUBE));
        list.add(new ItemStack(this, 1, VOID_CUBE));
        list.add(new ItemStack(this, 1, METALLIC_CUBE));
        list.add(new ItemStack(this, 1, BLOOD_CUBE));
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        
        String type = "Fire";
        
        fireTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        fireTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        fireTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        fireTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        fireTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        fireTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Water";
        
        waterTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        waterTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        waterTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        waterTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        waterTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        waterTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Earth";
        
        earthTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        earthTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        earthTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        earthTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        earthTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        earthTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Air";
        
        airTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        airTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        airTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        airTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        airTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        airTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Void";
        
        voidTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        voidTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        voidTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        voidTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        voidTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        voidTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Metallic";
        
        metallicTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        metallicTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        metallicTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        metallicTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        metallicTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        metallicTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        
        type = "Blood";
        
        bloodTextures[0] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeBottom");
        bloodTextures[1] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeTop");
        bloodTextures[2] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        bloodTextures[3] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        bloodTextures[4] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
        bloodTextures[5] = iconRegister.registerIcon(Reference.MODDID + ":Block" + type + "CubeSides");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        switch (metadata) {
        
            case FIRE_CUBE: {
                
                return fireTextures[side];
            }
            
            case WATER_CUBE: {
                
                return waterTextures[side];
            }
            
            case EARTH_CUBE: {
                
                return earthTextures[side];
            }
            
            case AIR_CUBE: {
                
                return airTextures[side];
            }
            
            case VOID_CUBE: {
                
                return voidTextures[side];
            }
            
            case METALLIC_CUBE: {
                
                return metallicTextures[side];
            }
            
            case BLOOD_CUBE: {
                
                return bloodTextures[side];
            }
        }
        return fireTextures[0];
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case FIRE_CUBE:
                return "Fire Cube";
            case WATER_CUBE:
                return "Water Cube";
            case EARTH_CUBE:
                return "Earth Cube";
            case AIR_CUBE:
                return "Air Cube";
            case VOID_CUBE:
                return "Void Cube";
            case METALLIC_CUBE:
                return "Metallic Cube";
            case BLOOD_CUBE:
                return "Blood Cube";
        }
        return "Somethings broken";
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case FIRE_CUBE:
                return "BlockFireCube";
            case WATER_CUBE:
                return "BlockWaterCube";
            case EARTH_CUBE:
                return "BlockEarthCube";
            case AIR_CUBE:
                return "BlockAirCube";
            case VOID_CUBE:
                return "BlockVoidCube";
            case METALLIC_CUBE:
                return "BlockMetallicCube";
            case BLOOD_CUBE:
                return "BlockBloodCube";
        }
        return "Unlocalized Name Broken";
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neightborId) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case FIRE_CUBE: {
                
                if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
                    
                    UtilBlock.placeBlockInAir(world, x, y + 1, z, Block.fire.blockID, 0);
                } else {
                    
                    if (world.getBlockId(x, y + 1, z) == Block.fire.blockID) {
                        
                        world.setBlockToAir(x, y + 1, z);
                    }
                }
                break;
            }
            // case WATER_CUBE: {
            //
            // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
            // {
            //
            // int blockID = world.getBlockId(x + direction.offsetX, y +
            // direction.offsetY, z + direction.offsetZ);
            //
            // if (blockID > 0) {
            // if (blockID == Block.lavaStill.blockID) {
            //
            // world.setBlock(x + direction.offsetX, y + direction.offsetY, z +
            // direction.offsetZ, Block.obsidian.blockID);
            // } else if (blockID == Block.lavaMoving.blockID) {
            //
            // world.setBlock(x + direction.offsetX, y + direction.offsetY, z +
            // direction.offsetZ, Block.cobblestone.blockID);
            // }
            // }
            // }
            // }
        }
        super.onNeighborBlockChange(world, x, y, z, neightborId);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case EARTH_CUBE: {
                
                int change = world.isBlockIndirectlyGettingPowered(x, y, z) ? -1 : 1;
                
                Block block = Block.blocksList[world.getBlockId(x, y + change, z)];
                
                if (block instanceof IPlantable) {
                    
                    block.updateTick(world, x, y + 1, z, rand);
                } else if (block == this && world.getBlockMetadata(x, y + change, z) == EARTH_CUBE) {
                    
                    block.updateTick(world, x, y + 1, z, rand);
                }
                break;
            }
            
            case BLOOD_CUBE: {
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(world, direction, x, y, z);
                    
                    if (tile != null && tile instanceof IFluidHandler) {
                        
                        UtilFluid.addFluidToTank((IFluidHandler) tile, direction.getOpposite(), new FluidStack(GU.FluidRegistry.Blood, 100), true);
                    }
                }
                break;
            }
        }
        super.updateTick(world, x, y, z, rand);
    }
    
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
        
        if (face == ForgeDirection.UP) {
            
            switch (metadata) {
            
                case FIRE_CUBE: {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
        
        switch (metadata) {
        
            case FIRE_CUBE: {
                
                return 100;
            }
        }
        return 0;
    }
    
    @Override
    public boolean isFertile(World world, int x, int y, int z) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case EARTH_CUBE: {
                
                return true;
            }
            
            case BLOOD_CUBE: {
                
                return true;
            }
        }
        return super.isFertile(world, x, y, z);
    }
    
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case EARTH_CUBE: {
                
                return true;
            }
            
            case BLOOD_CUBE: {
                
                return true;
            }
        }
        return false;
    }
    
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ) {
    }
    
    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side) {
        
        switch (metadata) {
        
            case FIRE_CUBE: {
                
                return side == ForgeDirection.UP;
            }
        }
        return false;
    }
    
    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case BLOOD_CUBE: {
                
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public int getPlacedMetadata(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        return stack.getItemDamage();
    }
}
