package GU.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilPlayers;
import GU.api.potion.IPotionIngredient;
import GU.api.runes.IRuneBlock;
import GU.api.runes.IRuneItem;

public class ItemTestItem extends ItemBase implements IRuneItem, IBlockCycle, IPotionIngredient {

    public ItemTestItem(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {

        return true;
    }

    @Override
    public void onUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {

        UtilBlock.cycle3DBlock(null, world, x + block.getOrientation().offsetX, y + block.getOrientation().offsetY, z + block.getOrientation().offsetZ, block.getOrientation().getOpposite(), 25, 25 + 1 + 25, this, 0);
    }

    @Override
    public boolean shouldUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {

        return true;
    }

    @Override
    public void randomUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public Item getItem() {

        return this;
    }

    @Override
    public boolean execute(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int id) {

        UtilBlock.breakBlock(world, x, y, z);
        return false;
    }

    @Override
    public int getPowerChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 200;
    }

    @Override
    public int getDurationChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public int getStrengthChange(ItemStack stack) {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public void onEntityDrinkPotion(World world, ItemStack potion, EntityLivingBase entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPotionThrown(World world, ItemStack potion, EntityLivingBase entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onThrownPotionHitEntity(World world, ItemStack potion, EntityLivingBase entity) {

        UtilPlayers.sendChatToPlayer(Minecraft.getMinecraft().thePlayer, "I Hit Something");
        entity.addPotionEffect(new PotionEffect(1, 1000, 3));
    }

    @Override
    public void onThrownPotionHitBlock(World world, ItemStack potion, int x, int y, int z) {

        world.setBlockToAir(x, y - 0, z);
    }

}
