package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.GearUtilities;
import GU.info.Reference;
import GU.render.BlockSimpleRenderer;

public class BlockBase extends Block {
    
    public boolean useStandardRendering = true;
    protected boolean useEnumStateRendering = false;
    String[] textures = new String[0];
    Icon[] icons;
    
    public BlockBase(int id, Material material) {
        super(id, material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(1.5f);
        setResistance(10.0F);
    }
    
    @Override
    public int getLightOpacity(World world, int x, int y, int z) {
        
        if (useStandardRendering)
            return super.getLightOpacity(world, x, y, z);
        
        return 0;
    }
    
    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        
        return true;
    }
    
    public BlockBase setTextureString(String[] textures) {
        
        this.textures = textures;
        return this;
    }
    
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        
        return useStandardRendering;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public int getRenderType() {
        
        if (!useStandardRendering)
            return -1;
        
        return BlockSimpleRenderer.renderID;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }
    
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
        
        return false;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {
        
        return sideHit;
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
}
