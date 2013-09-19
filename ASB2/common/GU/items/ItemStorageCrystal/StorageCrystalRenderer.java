package GU.items.ItemStorageCrystal;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

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
            
            GL11.glPushMatrix();
            UtilRender.bindBlockTextures();
            Color color = new Color(((ItemStorageCrystal)stack.getItem()).getFluidStack(stack).getFluid().getColor());
            
            GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());            
            UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal)stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);            
            
            GL11.glColor3f(1, 1, 1);
            GL11.glPopMatrix();
        }

        UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
    }
}
