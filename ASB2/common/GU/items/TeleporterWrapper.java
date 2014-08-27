package GU.items;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilItemStack;
import GU.info.Models;
import GU.info.Reference;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.render.noise.NoiseManager;

public class TeleporterWrapper extends ItemMetadataWrapper {
    
    public TeleporterWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        return 1;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
                
                int dimensionID = UtilItemStack.getNBTTagInt(itemStack, "dimensionID");
                
                if (dimensionID != player.dimension) {
                    
                    player.travelToDimension(dimensionID);
                }
                
                player.setPositionAndUpdate(UtilItemStack.getNBTTagDouble(itemStack, "x"), UtilItemStack.getNBTTagDouble(itemStack, "y"), UtilItemStack.getNBTTagDouble(itemStack, "z"));
            } else {
                
                UtilItemStack.setNBTTagDouble(itemStack, "x", Math.floor(player.posX) + .5);
                UtilItemStack.setNBTTagDouble(itemStack, "y", player.posY);
                UtilItemStack.setNBTTagDouble(itemStack, "z", Math.floor(player.posZ) + .5);
                UtilItemStack.setNBTTagInt(itemStack, "dimensionID", player.dimension);
                UtilItemStack.setNBTTagBoolean(itemStack, "coordsSet", true);
            }
        }
        return itemStack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        
        if (UtilItemStack.getNBTTagBoolean(itemStack, "coordsSet")) {
            
            info.add((int) UtilItemStack.getNBTTagDouble(itemStack, "x") + ", " + (int) UtilItemStack.getNBTTagDouble(itemStack, "y") + ", " + (int) UtilItemStack.getNBTTagDouble(itemStack, "z"));
            info.add("Dimension ID: " + UtilItemStack.getNBTTagInt(itemStack, "dimensionID"));
        } else {
            
            info.add("Right Click Me!");
        }
    }
    
    public static class TeleporterRenderer implements IItemRenderer {
        
        public static final TeleporterRenderer instance = new TeleporterRenderer();
        
        public TeleporterRenderer() {
            
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
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .5F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, 0f, 0f + 1, 0f, .7F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .6F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(item, type, 0f - .5F, 1f, 0 + .5f, .5F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
            
            Color color = Color.GREEN;
            
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glTranslatef(x, y, z);
            GL11.glScalef(scale, scale, scale);
            
            GL11.glPushMatrix();
            GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1, 1F);
            // UtilRender.renderTexture(Textures.FLAME_FOCUS_CUBE);
            
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            NoiseManager.bindImage();
            Models.ModelFlameFocus.renderPart("Cube");
            GL11.glPopMatrix();
            
            if (UtilItemStack.getNBTTagBoolean(item, "coordsSet")) {
                
                color = Color.DARK_GRAY.darker();
            } else {
                
                color = Color.WHITE.darker();
            }
            
            GL11.glColor3d(1, 1, 1);
            GL11.glPushMatrix();
            GL11.glRotatef(-Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 1F, 1F, 1F);
            // UtilRender.renderTexture(Textures.FLAME_FOCUS_HEXAGON);
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            NoiseManager.bindImage();
            Models.ModelFlameFocus.renderPart("Hexagon");
            GL11.glPopMatrix();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
