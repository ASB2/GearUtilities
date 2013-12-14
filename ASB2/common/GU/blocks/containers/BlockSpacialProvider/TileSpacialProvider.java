package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.*;

public class TileSpacialProvider extends TileBase {
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[]{EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE};
    }
    
    @Override
    public void updateEntity() {
        // TODO Auto-generated method stub
        super.updateEntity();
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
            sideState[direction.ordinal()] = EnumState.NONE;
        } else {
            sideState[direction.ordinal()] = EnumState.OUTPUT;
        }
        world.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
}
