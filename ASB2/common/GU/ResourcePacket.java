package GU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import GU.api.EnumResourceType.EnumPowerType;
import GU.api.IResourcePacket;

public class ResourcePacket implements IResourcePacket {
    
    List<ItemStack> items = new ArrayList<ItemStack>();
    List<FluidStack> fluids = new ArrayList<FluidStack>();
    Map<EnumPowerType, Float> power = new HashMap<EnumPowerType, Float>();
    
    public ResourcePacket() {
    }
    
    @Override
    public boolean addResource(ItemStack stack) {
        
        return items.add(stack);
    }
    
    @Override
    public ItemStack[] getItemResourse() {
        
        return (ItemStack[]) Collections.unmodifiableList(items).toArray();
    }
    
    @Override
    public boolean addResource(FluidStack stack) {
        
        return fluids.add(stack);
    }
    
    @Override
    public FluidStack[] getFluidResourse() {
        
        return (FluidStack[]) Collections.unmodifiableList(fluids).toArray();
    }
    
    @Override
    public boolean addResource(EnumPowerType powerType, float amount) {
        
        return power.put(powerType, amount) != null;
    }
    
    @Override
    public Map<EnumPowerType, Float> getPowerResource() {
        
        return Collections.unmodifiableMap(power);
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        return tag;
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
    }
}
