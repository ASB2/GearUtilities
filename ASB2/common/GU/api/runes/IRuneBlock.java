package GU.api.runes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public interface IRuneBlock {
    
    ForgeDirection getOrientation();
    ArrayList<ItemStack> getRunes();
}
