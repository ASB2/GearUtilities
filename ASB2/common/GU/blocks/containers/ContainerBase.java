package GU.blocks.containers;

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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.IExtraItemBlockInfo;
import GU.info.Reference;
import GU.render.BlockSimpleRenderer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ContainerBase extends BlockContainer implements IExtraItemBlockInfo {
    
    protected boolean useStandardRendering = true;
    String[] textures = new String[0];
    protected Icon[] icons;
    
    public ContainerBase(int id, Material material) {
        super(id, material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(1.5f);
        setResistance(10F);
    }
    
    public void registerTile(Class<? extends TileEntity> tileClass) {
        
        GameRegistry.registerTileEntity(tileClass, tileClass.toString());
    }
    
    public ContainerBase setTextureString(String[] textures) {
        
        this.textures = textures;
        return this;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        
        return true;
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
    public void registerIcons(IconRegister iconRegister) {
        
        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":" + this.getTextureName());
        
        icons = new Icon[textures.length];
        for (int i = 0; i < textures.length; i++) {
            
            icons[i] = iconRegister.registerIcon(Reference.MODDID + ":" + textures[i]);
        }
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        if (textures.length > 6) {
            
            return icons[side];
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
    
    @SuppressWarnings("rawtypes")
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, List info, boolean var1) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        return StatCollector.translateToLocal(itemStack.getItem().getUnlocalizedName(itemStack) + ".name");
    }
}
