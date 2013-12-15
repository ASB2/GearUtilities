package GU.multiblock;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockMultiBlockBuilders extends ContainerBase {
    
    public static final int GLASS = 0;
    public static final int CORNER = 1;
    
    public BlockMultiBlockBuilders(int id, Material material) {
        super(id, material);
        this.registerTile(TileMultiBlockBuilders.class);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        return false;
    }
    
    
    @Override
    public void addCreativeItems(ArrayList itemList) {
       itemList.add(new ItemStack(this, 1, GLASS));
    }
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return super.getPickBlock(target, world, x, y, z);
    }
    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileMultiBlockBuilders();
    }
}
