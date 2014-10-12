package GU.blocks.containers.BlockElectisEnergyCube;

import java.awt.Color;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import UC.color.Color4i;
import GU.api.EnumSideState;
import GU.info.Models;
import GU.render.noise.NoiseManager;

public class ElectisEnergyCubeRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static ElectisEnergyCubeRenderer instance = new ElectisEnergyCubeRenderer();
    
    public ElectisEnergyCubeRenderer() {
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x + 0.5F, y + .5f, z + .5F);
        
        GL11.glScaled(.44, .44, .44);
        
        NoiseManager.bindImage();
        
        Models.ModelElectisEnergyCube.renderPart("Center");
        
        EnumSideState[] sideState = ((TileElectisEnergyCube) tileentity).sideState;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            Color4i color = sideState[direction.ordinal()].getColor();
            GL11.glColor3d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);
            Models.ModelElectisEnergyCube.renderPart(direction.toString());
        }
        GL11.glPopMatrix();
    }
    
    Color color = Color.WHITE;
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        
        switch (type) {
        
            case ENTITY: {
                
                renderItemSwitched(item, type, 0f, 0, 0f, .8F);
                return;
            }
            
            case EQUIPPED: {
                
                renderItemSwitched(item, type, 1f, .5f, 1f, .5F);
                return;
            }
            
            case INVENTORY: {
                
                renderItemSwitched(item, type, 0f, 0, 0f, .5F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON: {
                
                renderItemSwitched(item, type, -.5F, 1f, 0.5f, .2F);
                return;
            }
            
            default:
                return;
        }
    }
    
    private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glTranslated(x, y, z);
        GL11.glScaled(scale, scale, scale);
        
        NoiseManager.bindImage();
        
        Models.ModelElectisEnergyCube.renderAll();
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
