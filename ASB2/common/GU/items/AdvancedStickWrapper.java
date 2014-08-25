package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.EventListener;
import GU.api.IWrenchable;
import GU.info.Models;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.render.noise.NoiseManager;
import UC.VariableIterator;

public class AdvancedStickWrapper extends ItemMetadataWrapper {
    
    public AdvancedStickWrapper(String ign) {
        super(ign);
        this.setRenderer(AdvancedStickRenderer.instance);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        IWrenchable toWrench = null;
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IWrenchable) {
            
            toWrench = (IWrenchable) tile;
        }
        else {
            
            Block block = world.getBlock(x, y, z);
            
            if (block != null && block instanceof IWrenchable) {
                
                toWrench = (IWrenchable) block;
            }
        }
        
        if (toWrench != null) {
            
            return toWrench.triggerBlock(world, player.isSneaking(), itemStack, x, y, z, side);
        }
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
    
    public static class AdvancedStickRenderer implements IItemRenderer {
        
        public static final AdvancedStickRenderer instance = new AdvancedStickRenderer();
        VariableIterator var = new VariableIterator(.0003, 0, .25);
        
        public AdvancedStickRenderer() {
            
            EventListener.instance.VARIABLES.add(var);
        }
        
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
                    
                    renderItemSwitched(item, type, 0f, 0.034f, 0f, .39F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, .5f, 1f, .5f, 1F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .7F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(item, type, -.5F, 1.2f, 0.5f, .5F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
            
            GL11.glPushMatrix();
            
            GL11.glTranslatef(x, y, z);
            
            GL11.glScaled(scale, scale, scale);
            
            NoiseManager.bindImage();
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            Models.ModelAdvancedStick.renderPart("Shaft");
            
            GL11.glColor4f(1, 1, 1, .8f);
            
            Models.ModelAdvancedStick.renderPart("Top_Cap");
            Models.ModelAdvancedStick.renderPart("Bottom_Cap");
            
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            
            GL11.glPopMatrix();
        }
    }
}
