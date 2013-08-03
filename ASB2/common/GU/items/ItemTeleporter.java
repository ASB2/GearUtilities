package GU.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.utils.UtilItemStack;
import GU.utils.UtilPlayers;


public class ItemTeleporter extends ItemBase {

    protected double x = 0;
    protected double y = 0;
    protected double z = 0;
    protected boolean coodsSet = false;
    protected int dimentionID = 0;

    public ItemTeleporter(int par1) {
        super(par1);
        setMaxStackSize(1);
        setMaxDamage(10);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        
        itemIcon = iconRegister.registerIcon("TechCraft:ItemTeleporter");
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer player) {

        this.setXCoord(par1ItemStack,player.posX);
        this.setYCoord(par1ItemStack,player.posY);
        this.setZCoord(par1ItemStack,player.posZ);        
        this.setDimentionIDCoord(par1ItemStack,player.dimension);
        setCoodsSet(par1ItemStack, true);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
        if(isCoodsSet(par1ItemStack)) {

            if(player instanceof EntityPlayerMP) {

                for(int i = 0; i < 2; i++) {

                    if (player.dimension != this.getDimentionIDCoord(par1ItemStack)) {

                        par1ItemStack.damageItem(1, player);
                        player.travelToDimension(this.getDimentionIDCoord(par1ItemStack));
                    }

                    else {

                        par1ItemStack.damageItem(1, player);
                        player.setPositionAndUpdate(this.getXCoord(par1ItemStack), this.getYCoord(par1ItemStack), this.getZCoord(par1ItemStack));
                    }
                }
            }
        }

        else {

            this.setXCoord(par1ItemStack,player.posX);
            this.setYCoord(par1ItemStack,player.posY);
            this.setZCoord(par1ItemStack,player.posZ);
            this.setDimentionIDCoord(par1ItemStack,player.dimension);
            setCoodsSet(par1ItemStack, true);

            UtilPlayers.sendChatToPlayer(player, "Link Set");
        }

        return par1ItemStack;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer player,@SuppressWarnings("rawtypes") List info, boolean b){

        if(isCoodsSet(par1ItemStack)) {

            info.add("Destination X: " + (int)this.getXCoord(par1ItemStack)+" Y: " + (int)this.getYCoord(par1ItemStack) + " Z: " + (int)this.getZCoord(par1ItemStack));
            info.add("Dimention ID: " + this.getDimentionIDCoord(par1ItemStack));
        }

        if(!isCoodsSet(par1ItemStack)) {

            info.add("Link not set.");
        }
    }



    public int getDimentionIDCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null){
            return nbtTagCompound.getInteger("dimentionID");
        }
        return 0;
    }

    public void setDimentionIDCoord(ItemStack item, int x) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setInteger("dimentionID", x);
    }

    public boolean isCoodsSet(ItemStack item) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null){
            return nbtTagCompound.getBoolean("coodsSet");
        }
        return coodsSet;
    }

    public void setCoodsSet(ItemStack item,boolean coodsSet) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setBoolean("coodsSet", coodsSet);        
    }

    public double getXCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null){
            return nbtTagCompound.getDouble("X");
        }
        return 0;
    }

    public void setXCoord(ItemStack item, double x) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("X", x);
    }

    public double getYCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null){
            return nbtTagCompound.getDouble("Y");
        }
        return 0;
    }

    public void setYCoord(ItemStack item, double y) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("Y", y);
    }

    public double getZCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null){
            return nbtTagCompound.getDouble("Z");
        }
        return 0;
    }

    public void setZCoord(ItemStack item, double z) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("Z", z);
    }
}
