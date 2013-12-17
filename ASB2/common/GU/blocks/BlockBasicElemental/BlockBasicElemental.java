package GU.blocks.BlockBasicElemental;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import GU.blocks.BlockBase;
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
            //
            // case VOID_CUBE: {
            //
            // return voidTextures[side];
            // }
            //
            // case METALLIC_CUBE: {
            //
            // return metallicTextures[side];
            // }
            //
            // case BLOOD_CUBE: {
            //
            // return bloodTextures[side];
            // }
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
        
        // case WATER_CUBE: {
        //
        // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
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
        //
        // switch (world.getBlockMetadata(x, y, z)) {
        //
        // case EARTH_CUBE: {
        //
        // }
        // }
        super.updateTick(world, x, y, z, rand);
    }
}
