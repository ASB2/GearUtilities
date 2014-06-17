package GU.multiblock;

import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import GU.multiblock.construction.ChestConstructionManager;
import GU.multiblock.construction.ConstructionManager;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockChest extends MultiBlockInventory {
    
    public MultiBlockChest(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        inventory = new Inventory(8 * ((this.size.getX() + 1) * (this.size.getY() + 1) * (this.size.getZ() + 1)), "Inventory Base", true);
    }
    
    public MultiBlockChest(World world) {
        super(world);
        inventory = new Inventory(0, "Inventory Base", true);
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
        
        if (inventory != null && inventory.getSizeInventory() == 0) inventory.setSizeInventory(8 * ((this.size.getX() + 1) * (this.size.getY() + 1) * (this.size.getZ() + 1)));
    }
    
    @Override
    public boolean startCreation() {
        
        return size.getX() >= 2 && size.getY() >= 2 && size.getZ() >= 2 && super.startCreation();
    }
}