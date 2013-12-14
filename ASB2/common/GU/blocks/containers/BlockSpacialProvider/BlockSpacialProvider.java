package GU.blocks.containers.BlockSpacialProvider;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.ISpacialProvider;
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
        
        if (!world.isRemote) {
            
            if (player.getHeldItem() == null) {
                
                TileSpacialProvider tile = (TileSpacialProvider) world.getBlockTileEntity(x, y, z);
                
                boolean hasAll = false;
                tile.multiBlockList.clear();
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        TileEntity foundTile = tile.getNearestProvider(direction);
                        if (foundTile != null) {
                            
                            tile.multiBlockList.add(new Vector3(foundTile));
                            hasAll = true;
                        } else {
                            hasAll = false;
                        }
                    }
                }
                
                if (hasAll) {
                    
                    Iterator<Vector3> firstLoop = tile.multiBlockList.iterator();
                    
                    while (firstLoop.hasNext()) {
                        
                        Vector3 vector = firstLoop.next();
                        
                        Set<Vector3> tiles = ((ISpacialProvider) vector.getTileEntity(world)).getProvidedTiles();
                        tile.multiBlockList.addAll(tiles);
                        
                        Iterator<Vector3> secondLoop = tiles.iterator();
                        
                        while (secondLoop.hasNext()) {
                            
                            Vector3 vector2 = secondLoop.next();
                            Set<Vector3> tiles2 = ((ISpacialProvider) vector2.getTileEntity(world)).getProvidedTiles();
                            tile.multiBlockList.addAll(tiles2);
                        }
                    }
                    
                    player.addChatMessage("--------");
                    player.addChatMessage("We have " + tile.multiBlockList.size() + " tiles in the array");
                    player.addChatMessage("--------");
                }
            }
        }
        return false;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
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
                return "BlockStandardSpacialProvider";
            case FLUID:
                return "BlockFluidSpacialProvider";
        }
        return "";
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileSpacialProvider();
    }
}
