package GUOLD.items.ItemStorageCrystal;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;

public class StorageCrystalRenderer implements IItemRenderer {
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        
        return type == ItemRenderType.ENTITY;
        // return false;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        
        switch (type) {
        
            case ENTITY: {
                
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
//                GL11.glRotated(180, 1, 0, 0);
                GL11.glTranslated(-.8, 0, 0);
                GL11.glScalef(.1f, .1f, .1f);
                
                if (((ItemStorageCrystal) stack.getItem()).getFluidStack(stack) != null && ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon() != null) {
                    
                    GL11.glPushMatrix();
                    UtilRender.bindBlockTextures();
                    Color color = new Color(((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getColor());
                    
                    GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
                    UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);
                    
                    GL11.glColor3f(1, 1, 1);
                    GL11.glPopMatrix();
                }
                GL11.glTranslatef(0, 0, -0.01f);
                UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED:
                break;
            case EQUIPPED_FIRST_PERSON: {
                
                GL11.glPushMatrix();
                GL11.glTranslatef(0, -10, 10);
                if (((ItemStorageCrystal) stack.getItem()).getFluidStack(stack) != null && ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon() != null) {
                    
                    GL11.glPushMatrix();
                    UtilRender.bindBlockTextures();
                    Color color = new Color(((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getColor());
                    
                    GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
                    UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);
                    
                    GL11.glColor3f(1, 1, 1);
                    GL11.glPopMatrix();
                }
                GL11.glTranslatef(0, 0, -0.01f);
                UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
                GL11.glPopMatrix();
            }
                break;
            case FIRST_PERSON_MAP: {
                
                GL11.glPushMatrix();
                GL11.glTranslatef(10, 10, 10);
                if (((ItemStorageCrystal) stack.getItem()).getFluidStack(stack) != null && ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon() != null) {
                    
                    GL11.glPushMatrix();
                    UtilRender.bindBlockTextures();
                    Color color = new Color(((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getColor());
                    
                    GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
                    UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);
                    
                    GL11.glColor3f(1, 1, 1);
                    GL11.glPopMatrix();
                }
                UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
                GL11.glPopMatrix();
            }
                break;
            case INVENTORY: {
                
                if (((ItemStorageCrystal) stack.getItem()).getFluidStack(stack) != null && ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon() != null) {
                    
                    GL11.glPushMatrix();
                    UtilRender.bindBlockTextures();
                    Color color = new Color(((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getColor());
                    
                    GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
                    UtilRender.renderItemInstance.renderIcon(6, 2, ((ItemStorageCrystal) stack.getItem()).getFluidStack(stack).getFluid().getStillIcon(), 4, 11);
                    
                    GL11.glColor3f(1, 1, 1);
                    GL11.glPopMatrix();
                }
                
                UtilRender.renderItemInstance.renderIcon(0, 0, stack.getItem().getIcon(stack, 1), 16, 16);
            }
                break;
            default:
                break;
        
        }
    }
}
