package GU.multiblock;

import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockStructure;
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
    public IMultiBlockStructure getStructure() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.GREEN;
    }
}