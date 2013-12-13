package GU.blocks.containers.BlockBasicElemental;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockBasicElemental extends ContainerBase {
    
    public static final int FIRE_CUBE = 0;
    public static final int WATER_CUBE = 1;
    public static final int EARTH_CUBE = 2;
    
    Icon[] fireTextures = new Icon[6];
    Icon[] waterTextures = new Icon[6];
    Icon[] earthTextures = new Icon[6];
    
    public BlockBasicElemental(int id, Material material) {
        super(id, material);
        this.registerTile(TileBasicElemental.class);
        this.setTickRandomly(true);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        list.add(new ItemStack(this, 1, FIRE_CUBE));
        list.add(new ItemStack(this, 1, WATER_CUBE));
        list.add(new ItemStack(this, 1, EARTH_CUBE));
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
        }
        return fireTextures[0];
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileBasicElemental();
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
        }
        return "";
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case FIRE_CUBE:
                return "Fire Cube";
            case WATER_CUBE:
                return "Water Cube";
            case EARTH_CUBE:
                return "Earth Cube";
        }
        return "";
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neightborId) {
        
        switch (world.getBlockMetadata(x, y, z)) {
        
            case WATER_CUBE: {
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    int blockID = world.getBlockId(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
                    
                    if (blockID > 0) {
                        if (blockID == Block.lavaStill.blockID) {
                            
                            world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, Block.obsidian.blockID);
                        } else if (blockID == Block.lavaMoving.blockID) {
                            
                            world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, Block.cobblestone.blockID);
                        }
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, neightborId);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        
//        switch (world.getBlockMetadata(x, y, z)) {
//        
//            case WATER_CUBE: {
//                ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(6)];
//                UtilBlock.placeBlockInAir(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, Block.waterMoving.blockID, 8);
//            }
//        }
        super.updateTick(world, x, y, z, rand);
    }
}
