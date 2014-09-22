package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import ASB2.utils.UtilEntity;
import GU.GUGuiHandler;
import GU.GearUtilities;
import GU.api.multiblock.MultiBlockAbstract.IGuiMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.multiblock.construction.ChestConstructionManager;
import GU.multiblock.construction.ConstructionManager;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockChest extends MultiBlockInventory implements IRedstoneMultiBlock, IGuiMultiBlock {
    
    public MultiBlockChest(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        inventory = new Inventory("Inventory Base");
    }
    
    public MultiBlockChest(World world) {
        super(world);
        inventory = new Inventory("Inventory Base");
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.GREEN;
    }
    
    @Override
    public ConstructionManager getConstructionManager() {
        
        return new ChestConstructionManager(world, this, positionRelativeTo, size);
    }
    
    @Override
    public void onSetSize() {
        
        if (inventory != null && inventory.getSizeInventory() == 0)
            inventory.setSizeInventory(16 * ((this.size.getX() - 1) * (this.size.getY() - 1) * (this.size.getZ() - 1)));
    }
    
    @Override
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2 && super.startCreation();
    }
    
    @Override
    public boolean openGui(Vector3i position, EntityPlayer player, int side) {
        
        if (!world.isRemote) {
            
            if (!player.isSneaking()) {
                
                player.openGui(GearUtilities.instance, GUGuiHandler.MULTI_BLOCK_CHEST, world, position.getX(), position.getY(), position.getZ());
                return true;
            } else {
                
                UtilEntity.sendChatToPlayer(player, "Chest: Stop Shifitng");
                return false;
            }
        }
        return false;
    }
    
    @Override
    public int getRedstoneLevel(Vector3i tilePosition) {
        // TODO Auto-generated method stub
        return 0;
    }
}