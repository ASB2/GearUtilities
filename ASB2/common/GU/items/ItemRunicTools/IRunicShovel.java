package GU.items.ItemRunicTools;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.items.ShovelBase;
import ASB2.utils.UtilMisc;
import GU.api.power.IPowerItem;
import GU.api.power.IPowerProvider;
import GU.api.power.ItemPowerProvider;
import GU.api.power.PowerHelper;
import GU.info.Reference;

public class IRunicShovel extends ShovelBase implements IPowerItem {

    public IRunicShovel(int id, EnumToolMaterial material, String iconLocation) {
        super(id, material, iconLocation);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if(player.isSneaking()) {
            
            if(this.getPowerProvider(itemStack).gainPower(1000, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 1000, false)) {

                this.getPowerProvider(itemStack).gainPower(1000, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 1000, true);
                
                return itemStack;
            }

            if(this.getPowerProvider(itemStack).gainPower(100, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 100, false)) {

                this.getPowerProvider(itemStack).gainPower(100, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 100, true);
                return itemStack;
            }

            if(this.getPowerProvider(itemStack).gainPower(50, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 50, false)) {

                this.getPowerProvider(itemStack).gainPower(50, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 50, true);
                return itemStack;
            }
            
            if(this.getPowerProvider(itemStack).gainPower(20, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 20, false)) {

                this.getPowerProvider(itemStack).gainPower(20, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 20, true);
                return itemStack;
            }
            
            if(this.getPowerProvider(itemStack).gainPower(10, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 10, false)) {

                this.getPowerProvider(itemStack).gainPower(10, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 10, true);
                return itemStack;
            }
            
            if(this.getPowerProvider(itemStack).gainPower(5, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 5, false)) {

                this.getPowerProvider(itemStack).gainPower(5, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 5, true);
                return itemStack;
            }
            
            if(this.getPowerProvider(itemStack).gainPower(1, ForgeDirection.UNKNOWN, false) && PowerHelper.useEnergyFromInventory(player.inventory, 1, false)) {

                this.getPowerProvider(itemStack).gainPower(1, ForgeDirection.UNKNOWN, true);
                PowerHelper.useEnergyFromInventory(player.inventory, 1, true);
                return itemStack;
            }
            return itemStack;
        }
        return super.onItemRightClick(itemStack, world, player);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        info.add("From: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + Reference.NAME);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            
            this.addInformationSneaking(itemStack, player, info, var1);
        }
        else {

            info.add("Press " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Shift "+ UtilMisc.getColorCode(EnumChatFormatting.GRAY) + "to show more info");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        info.add("Stored Power: " + this.getPowerProvider(itemStack).getPowerStored());
        info.add("Maximum Power: " + this.getPowerProvider(itemStack).getPowerMax());
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase entityHitting) {

        if(!getPowerProvider(stack).usePower(10, ForgeDirection.UNKNOWN, true)) {
            
            if(entityHitting instanceof EntityPlayer) {

                if(!PowerHelper.useEnergyFromInventory(((EntityPlayer)entityHitting).inventory, 10, true)) {

                    stack.damageItem(1, entityHitting);
                    return true;
                }            
            }
        }
        return false;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, int x, int y, int z, int side, EntityLivingBase entity) {

        if(!getPowerProvider(stack).usePower(10, ForgeDirection.UNKNOWN, true)) {

            if(entity instanceof EntityPlayer) {

                if(!PowerHelper.useEnergyFromInventory(((EntityPlayer)entity).inventory, 10, true)) {

                    if ((double)Block.blocksList[x].getBlockHardness(world, y, z, side) != 0.0D) {

                        stack.damageItem(1, entity);
                    }
                }          
            }
        }
        return false;
    }

    @Override
    public IPowerProvider getPowerProvider(ItemStack stack) {

        return new ItemPowerProvider(stack, 1000);
    }
}
