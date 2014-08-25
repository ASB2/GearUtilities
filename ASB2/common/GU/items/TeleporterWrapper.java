package GU.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.items.ItemMetadata.ItemMetadataWrapper;

public class TeleporterWrapper extends ItemMetadataWrapper {
    
    public TeleporterWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
                
                int dimetionID = UtilItemStack.getNBTTagInt(itemStack, "dimetionID");
                
                if (dimetionID != player.dimension) {
                    
                    player.travelToDimension(dimetionID);
                }
                
                player.setPositionAndUpdate(UtilItemStack.getNBTTagDouble(itemStack, "x"), UtilItemStack.getNBTTagDouble(itemStack, "y"), UtilItemStack.getNBTTagDouble(itemStack, "z"));
            }
            else {
                
                UtilItemStack.setNBTTagDouble(itemStack, "x", player.posX);
                UtilItemStack.setNBTTagDouble(itemStack, "y", player.posY);
                UtilItemStack.setNBTTagDouble(itemStack, "z", player.posZ);
                UtilItemStack.setNBTTagInt(itemStack, "dimetionID", player.dimension);
                UtilItemStack.setNBTTagBoolean(itemStack, "coordsSet", true);
            }
        }
        return itemStack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        
        if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
            
            info.add("Destination X: " + (int) UtilItemStack.getNBTTagDouble(itemStack, "x") + " Y: " + (int) UtilItemStack.getNBTTagDouble(itemStack, "y") + " Z: " + (int) UtilItemStack.getNBTTagDouble(itemStack, "z"));
            info.add("Dimention ID: " + UtilItemStack.getNBTTagInt(itemStack, "dimetionID"));
        }
        else {
            
            info.add("Coords Not Set.");
        }
    }
}
