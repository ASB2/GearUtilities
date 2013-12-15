package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
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
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.spacial.ISpacialProvider;
import GU.blocks.containers.ContainerBase;
import GU.entity.fx.FXBeamOld;
import GU.info.Reference;
import GU.multiblock.*;

public class BlockSpacialProvider extends ContainerBase {
    
    public static final int STANDARD = 0;
    public static final int FLUID = 1;
    
    Icon standard, fluid;
    
    public BlockSpacialProvider(int id, Material material) {
        super(id, material);
        this.registerTile(TileSpacialProvider.class);
        this.registerTile(TileFluidSpacialProvider.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (player.getHeldItem() == null) {
            
            if (world.getBlockMetadata(x, y, z) == STANDARD) {
                TileSpacialProvider tile = (TileSpacialProvider) world.getBlockTileEntity(x, y, z);
                
                Set<Vector3> multiBlockList = new HashSet<Vector3>();
                
                boolean hasAll = false;
                multiBlockList.clear();
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        TileEntity foundTile = tile.getNearestProvider(direction);
                        if (foundTile != null) {
                            
                            multiBlockList.add(new Vector3(foundTile));
                            hasAll = true;
                        } else {
                            hasAll = false;
                        }
                    }
                }
                
                if (hasAll) {
                    
                    Set<Vector3> buffer = new HashSet<Vector3>();
                    
                    for (Vector3 vector : multiBlockList) {
                        
                        Set<Vector3> tiles = ((ISpacialProvider) vector.getTileEntity(world)).getProvidedTiles();
                        buffer.addAll(tiles);
                        for (Vector3 vector2 : tiles) {
                            
                            Set<Vector3> tiles2 = ((ISpacialProvider) vector2.getTileEntity(world)).getProvidedTiles();
                            buffer.addAll(tiles2);
                        }
                    }
                    multiBlockList.addAll(buffer);
                    
                    for (Vector3 vector : multiBlockList) {
                        
                        if (!new Vector3(x + .5, y + .5, z + .5).equals(vector)) {
                            FXBeamOld beam = new FXBeamOld(world, new Vector3(x + .5, y + .5, z + .5), vector, 255, 255, 255, 205);
                            UtilEntity.spawnFX(beam);
                        }
                    }
                    
                    player.addChatMessage("--------");
                    player.addChatMessage("We have " + multiBlockList.size() + " tiles in the array");
                    player.addChatMessage("--------");
                }
            } else if (world.getBlockMetadata(x, y, z) == FLUID) {
                
                TileSpacialProvider tile = (TileSpacialProvider) world.getBlockTileEntity(x, y, z);
                
                Set<Vector3> multiBlockList = new HashSet<Vector3>();
                
                boolean hasAll = false;
                multiBlockList.clear();
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        TileEntity foundTile = tile.getNearestProvider(direction);
                        if (foundTile != null) {
                            
                            multiBlockList.add(new Vector3(foundTile));
                            hasAll = true;
                        } else {
                            hasAll = false;
                        }
                    }
                }
                
                if (hasAll) {
                    if (!world.isRemote) {
                        MultiBlockTank tank = new MultiBlockTank(world, new Vector3(x, y, z), tile.getMultiBlockXChange(), tile.getMultiBlockHeight(), tile.getMultiBlockZChange());
                        UtilEntity.sendClientChat(tank.isMultiBlockAreaValid() + "");
                        tank.makeMultiBlockValid();
                        UtilEntity.sendClientChat(tank.isMultiBlockAreaValid() + "");
                    }
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
    public TileEntity createTileEntity(World world, int metadata) {
        
        switch (metadata) {
        
            case STANDARD:
                return new TileSpacialProvider();
            case FLUID:
                return new TileFluidSpacialProvider();
        }
        return null;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }
}
