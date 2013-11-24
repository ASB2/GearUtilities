package GU.items;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.api.embryo.EnumGenes;
import GU.api.embryo.EnumGrowthStage;
import GU.api.embryo.EnumType;
import GU.api.embryo.IFamilarEmbryo;

public class ItemFamilarEmbryo extends ItemBase implements IFamilarEmbryo {

    public ItemFamilarEmbryo(int id) {
        super(id);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if(player.isSneaking()) {

            this.grow(itemStack, EnumType.SCIENCE);
        }
        else {

            this.grow(itemStack, EnumType.MAGIC);
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
    }

    @Override
    public EnumSet<EnumGenes> getGenes(ItemStack stack) {
        // TODO Auto-generated method stub
        return null;
    }
}
