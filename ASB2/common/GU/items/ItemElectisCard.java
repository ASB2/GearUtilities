package GU.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.Fluid;
import GU.items.ItemRenderers.FluidCrystalArrayRenderer;
import GU.utils.UtilGU;

public class ItemElectisCard extends ItemBase {
    
    public void postInitRender() {
        
        MinecraftForgeClient.registerItemRenderer(this, FluidCrystalArrayRenderer.instance);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        
        Fluid saved = UtilGU.getFluid(itemStack);
        
        if (saved != null) {
            
            par3List.add("Fluid: ".concat(saved.getLocalizedName()));
            par3List.add("Fluid ID: " + saved.getID());
        }
        // else
        // par3List.add("Fluid: ".concat("Empty"));
    }
    
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        Fluid saved = UtilGU.getFluid(itemStack);
        
        if (saved != null) {
            
            return "Fluid Crystal Array: ".concat(saved.getLocalizedName());
        }
        return "Fluid Crystal Array: Empty";
    }
}
