package GU.blocks.containers.BlockSpacialProvider;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockSpacialProvider extends ContainerBase {
    
    public static final int STANDARD = 0, FLUID = 1, FURNACE = 2;
    
    Icon standard, fluid, furnace;
    
    public BlockSpacialProvider(int id, Material material) {
        super(id, material);
        specialMetadata = true;
        this.registerTile(TileSpacialProvider.class);
        this.registerTile(TileFluidSpacialProvider.class);
        this.registerTile(TileFurnaceSpacialProvider.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (player.getHeldItem() == null) {
            
            TileSpacialProvider tile = (TileSpacialProvider) world.getBlockTileEntity(x, y, z);
            
            if (tile.getComprisedMultiBlocks().isEmpty()) {
                
                return tile.createMultiBlock();
            } else {
                
                for (IMultiBlock multi : tile.getComprisedMultiBlocks()) {
                    
                    multi.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        standard = iconRegister.registerIcon(Reference.MODDID + ":BlockStandardSpecialProvider");
        fluid = iconRegister.registerIcon(Reference.MODDID + ":BlockFluidSpecialProvider");
        furnace = iconRegister.registerIcon(Reference.MODDID + ":BlockFurnaceSpecialProvider");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        switch (metadata) {
        
            case STANDARD:
                return standard;
            case FLUID:
                return fluid;
            case FURNACE:
                return furnace;
            default:
                return super.getIcon(side, metadata);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        list.add(new ItemStack(this, 1, STANDARD));
        list.add(new ItemStack(this, 1, FLUID));
        list.add(new ItemStack(this, 1, FURNACE));
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case STANDARD:
                return "Standard Spacial Provider";
            case FLUID:
                return "Fluid Spacial Provider";
            case FURNACE:
                return "Furnace Spacial Provider";
        }
        return "";
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case STANDARD:
                return "BlockStandardSpacialProvider";
            case FLUID:
                return "BlockFluidSpacialProvider";
            case FURNACE:
                return "BlockFurnaceSpacialProvider";
        }
        return "";
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        
        switch (metadata) {
        
            case STANDARD:
                return new TileSpacialProvider();
            case FLUID:
                return new TileFluidSpacialProvider();
            case FURNACE:
                return new TileFurnaceSpacialProvider();
        }
        return null;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}
