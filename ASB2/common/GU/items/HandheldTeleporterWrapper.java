package GU.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.TeleporterRenderer;

public class HandheldTeleporterWrapper extends ItemMetadataWrapper {
    
    public HandheldTeleporterWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(TeleporterRenderer.instance);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
                
                int dimensionID = UtilItemStack.getNBTTagInt(itemStack, "dimensionID");
                
                if (dimensionID != player.dimension) {
                    
                    player.travelToDimension(dimensionID);
                }
                
                player.setPositionAndUpdate(UtilItemStack.getNBTTagDouble(itemStack, "x"), UtilItemStack.getNBTTagDouble(itemStack, "y"), UtilItemStack.getNBTTagDouble(itemStack, "z"));
            } else {
                
                UtilItemStack.setNBTTagDouble(itemStack, "x", Math.floor(player.posX) + .5);
                UtilItemStack.setNBTTagDouble(itemStack, "y", player.posY);
                UtilItemStack.setNBTTagDouble(itemStack, "z", Math.floor(player.posZ) + .5);
                UtilItemStack.setNBTTagInt(itemStack, "dimensionID", player.dimension);
                UtilItemStack.setNBTTagBoolean(itemStack, "coordsSet", true);
            }
        }
        return itemStack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        
        if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
            
            info.add((int) UtilItemStack.getNBTTagDouble(itemStack, "x") + ", " + (int) UtilItemStack.getNBTTagDouble(itemStack, "y") + ", " + (int) UtilItemStack.getNBTTagDouble(itemStack, "z"));
            info.add("Dimension ID: " + UtilItemStack.getNBTTagInt(itemStack, "dimensionID"));
        } else {
            
            info.add("Right Click Me!");
        }
    }
}
