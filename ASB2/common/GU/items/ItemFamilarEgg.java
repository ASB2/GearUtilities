package GU.items;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.api.familar.EnumGenes;
import GU.api.familar.EnumGrowthStage;
import GU.api.familar.EnumType;
import GU.api.familar.IFamilarEgg;

public class ItemFamilarEgg extends ItemBase implements IFamilarEgg {

    public ItemFamilarEgg(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if(UtilItemStack.getNBTTagString(itemStack, "owner") == "") {

            UtilItemStack.setNBTTagString(itemStack, "owner", player.username);
        }
        else {

            if(UtilItemStack.getNBTTagString(itemStack, "owner") == player.username) {

                if(!world.isRemote) {
                    
//                    world.spawnEntityInWorld(new EntityFamilars(world, new Vector3(player).add(ForgeDirection.UP), UtilItemStack.getNBTTagString(itemStack, "owner")));
                    player.addChatMessage("Spawned");
                }
            }
        }
        return super.onItemRightClick(itemStack, world, player);
    }

    @Override
    public boolean grow(ItemStack stack, EnumType type) {

        if(this.getGrowthStange(stack) != EnumGrowthStage.MATURE) {

            UtilItemStack.getTAGfromItemstack(stack).setInteger("stage", this.getGrowthStange(stack).getNextGrowthStage().ordinal());
        }

        switch(type) {

            case SCIENCE:
                UtilItemStack.setNBTTagInt(stack, "science", UtilItemStack.getNBTTagInt(stack, "science") + 1);
                break;

            case MAGIC:
                UtilItemStack.setNBTTagInt(stack, "magic", UtilItemStack.getNBTTagInt(stack, "magic") + 1);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {

        if(entity != null && entity instanceof EntityPlayer) {

            if(((EntityPlayer) entity).username != "" && ((EntityPlayer) entity).username == UtilItemStack.getNBTTagString(itemStack, "owner")) {

                if(world.rand.nextInt(100) == 1) {

                    this.grow(itemStack, EnumType.MAGIC);
                }
            }
        }
        super.onUpdate(itemStack, world, entity, par4, par5);
    }

    @Override
    public EnumType getFamilarType(ItemStack stack) {

        return EnumType.values()[UtilItemStack.getTAGfromItemstack(stack).getInteger("familar_type")];
    }

    @Override
    public EnumGrowthStage getGrowthStange(ItemStack stack) {

        return EnumGrowthStage.values()[UtilItemStack.getTAGfromItemstack(stack).getInteger("stage")];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, List info, boolean var1) {

        info.add("Familar Type: " + this.getFamilarType(itemStack));
        info.add("Growth Stage: " + this.getGrowthStange(itemStack));
        info.add("Magic Chance: " + UtilItemStack.getNBTTagInt(itemStack, "magic"));
        info.add("Science Chance: " + UtilItemStack.getNBTTagInt(itemStack, "science"));
        info.add("Owner: " + UtilItemStack.getNBTTagString(itemStack, "owner"));
    }

    @Override
    public EnumSet<EnumGenes> getGenes(ItemStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
