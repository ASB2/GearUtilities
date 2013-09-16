package GU.items.ItemStorageCrystal;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class StorageCrystalRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
    }
}
