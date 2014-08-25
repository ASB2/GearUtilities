package GUOLD.api;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public interface IResourcePacket {
    
    boolean addResource(ItemStack stack);
    
    ItemStack[] getItemResourse();
    
    boolean addResource(FluidStack stack);
    
    FluidStack[] getFluidResourse();
    
    boolean addResource(EnumResourceType.EnumPowerType powerType, float amount);
    
    Map<EnumResourceType.EnumPowerType, Float> getPowerResource();

    NBTTagCompound save(NBTTagCompound tag);
    void load(NBTTagCompound tag);
}
