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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import ASB2.items.HoeBase;
import ASB2.utils.UtilMisc;
import GU.api.power.IPowerItem;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerHelper;
import GU.info.Reference;
import GU.power.ItemPowerProvider;

public class ItemRunicHoe extends HoeBase implements IPowerItem {

    public ItemRunicHoe(int id, EnumToolMaterial material, String iconLocation) {
        super(id, material, iconLocation);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        if (player.canPlayerEdit(x, y, z, side, stack)) {

            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event)) {

                return false;
            }

            if (event.getResult() == Result.ALLOW) {

                stack.damageItem(1, player);
                return true;
            }

            int i1 = world.getBlockId(x, y, z);
            boolean air = world.isAirBlock(x, y + 1, z);

            if (side != 0 && air && (i1 == Block.grass.blockID || i1 == Block.dirt.blockID))
            {
                Block block = Block.tilledField;
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                if (world.isRemote) {
                    
                    return true;
                }
                else {
                    
                    world.setBlock(x, y, z, block.blockID);

                    if(!getPowerProvider(stack).usePower(10, ForgeDirection.UNKNOWN, true)) {

                        if(!PowerHelper.useEnergyFromInventory(player.inventory, 10, true)) {

                            stack.damageItem(1, player);
                        }
                    }
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        return false;
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
