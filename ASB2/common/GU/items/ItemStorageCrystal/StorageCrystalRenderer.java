package GU.items.ItemStorageCrystal;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;

public class StorageCrystalRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return type == ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {

        GL11.glPushMatrix();

        UtilRender.bindBlockTextures();
        UtilRender.renderIcon(0, 0, FluidRegistry.WATER.getIcon(), 16, 16);

        GL11.glPopMatrix();
    }
}
