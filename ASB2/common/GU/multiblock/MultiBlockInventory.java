package GU.multiblock;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import UC.math.vector.Vector3i;

public abstract class MultiBlockInventory extends MultiBlockBase implements IInventoryMultiBlock {
    
    Inventory inventory;
    
    public MultiBlockInventory(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
    }
    
    public MultiBlockInventory(World world) {
        super(world);
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (inventory != null) tag.setTag("Inventory", inventory.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (inventory != null) inventory.load(tag.getCompoundTag("Inventory"));
        super.load(tag);
    }
    
    @Override
    public IInventory getInventory(Vector3i tilePosition) {
        
        return inventory;
    }
}