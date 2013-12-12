package GU.blocks.containers.BlockBasicElemental;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockBasicElemental extends ContainerBase {
    
    public static final int FIRE_CUBE = 0;
    public static final int WATER_CUBE = 1;
    public static final int EARTH_CUBE = 2;
    
    Icon[] fireTextures = new Icon[6];
    Icon[] waterTextures;
    Icon[] earthTextures;
    
    public BlockBasicElemental(int id, Material material) {
        super(id, material);
        this.registerTile(TileBasicElemental.class);
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
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        switch(metadata) {
            
            case FIRE_CUBE: {
                
                return fireTextures[side];
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
}
