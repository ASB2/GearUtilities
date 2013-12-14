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
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;

public class BlockSpacialProvider extends ContainerBase {
    
    public static final int STANDARD = 0;
    public static final int FLUID = 1;
    
    Icon standard, fluid;
    
    public BlockSpacialProvider(int id, Material material) {
        super(id, material);
        this.registerTile(TileSpacialProvider.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        // TODO Auto-generated method stub
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        standard = iconRegister.registerIcon(Reference.MODDID + ":BlockStandardSpecialProvider");
        fluid = iconRegister.registerIcon(Reference.MODDID + ":BlockFluidSpecialProvider");
    }
    
    @Override
    public Icon getIcon(int side, int metadata) {
        
        switch (metadata) {
        
            case STANDARD:
                return standard;
            case FLUID:
                return fluid;
            default:
                return super.getIcon(side, metadata);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        
        list.add(new ItemStack(this, 1, STANDARD));
        list.add(new ItemStack(this, 1, FLUID));
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case STANDARD:
                return "Standard Spacial Provider";
            case FLUID:
                return "Fluid Spacial Provider";
        }
        return "";
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        
        switch (itemStack.getItemDamage()) {
        
            case STANDARD:
                return "Standard Spacial Provider";
            case FLUID:
                return "Fluid Spacial Provider";
        }
        return "";
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileSpacialProvider();
    }
}
