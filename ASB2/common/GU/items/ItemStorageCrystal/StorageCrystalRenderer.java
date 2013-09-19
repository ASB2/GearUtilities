package GU.items.ItemStorageCrystal;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import ASB2.utils.UtilRender;

public class StorageCrystalRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {

        if(((ItemStorageCrystal)stack.getItem()).getFluidStack(stack) != null && ((ItemStorageCrystal)stack.getItem()).getFluidStack(stack).getFluid().getStillIcon() != null) {

            UtilRender.bindBlockTextures();
            UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal)stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);            
        }

        UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
    }
}
