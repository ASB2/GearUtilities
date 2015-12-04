package GU;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import GU.blocks.containers.TileBase;
import GU.multiblock.gui.ContainerMultiBlockChest;
import GU.multiblock.gui.ContainerMultiBlockTank;
import GU.multiblock.gui.GuiMultiBlockChest;
import GU.multiblock.gui.GuiMultiBlockTank;

public class GUGuiHandler implements IGuiHandler {
    
    public static final int MULTI_BLOCK_CHEST = 0;
    public static final int MULTI_BLOCK_FURNACE = 1;
    public static final int MULTI_BLOCK_TANK = 2;
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        
        switch (ID) {
        
            case MULTI_BLOCK_CHEST: {
                
                return new ContainerMultiBlockChest(tile);
            }
            
            case MULTI_BLOCK_TANK: {
                
                return new ContainerMultiBlockTank(tile);
            }
        }
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        
        switch (ID) {
        
            case MULTI_BLOCK_CHEST: {
                
                return new GuiMultiBlockChest(new ContainerMultiBlockChest(tile), tile);
            }
            
            case MULTI_BLOCK_TANK: {
                
                return new GuiMultiBlockTank(new ContainerMultiBlockTank(tile), tile);
            }
        }
        return null;
    }
    
}
