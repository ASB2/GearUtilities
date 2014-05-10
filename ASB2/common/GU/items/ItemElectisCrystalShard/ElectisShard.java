package GU.items.ItemElectisCrystalShard;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilEntity;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.blocks.containers.BlockElectisCrystal.TileElectisCrystal;
import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;

public class ElectisShard {
    
    public static class ElectisShardWrapper extends GU.items.ItemMetadata.MetadataWrapper {
        
        public ElectisShardWrapper(String ign) {
            super(ign);
            // TODO Auto-generated constructor stub
        }
        
        @Override
        public boolean onItemUse(ItemStack itemStack, net.minecraft.entity.player.EntityPlayer player, net.minecraft.world.World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            
            if (player.capabilities.isCreativeMode) {
                
                TileEntity tile = world.getTileEntity(x, y, z);
                
                if (tile != null && tile instanceof TileElectisCrystal) {
                    
                    IPowerManager power = ((TileElectisCrystal) tile).getPowerManager();
                    
                    if (power != null) {
                        
                        if (player.isSneaking()) {
                            
                            power.decreasePower(1, EnumSimulationType.FORCED);
                        }
                        else {
                            
                            power.increasePower(1, EnumSimulationType.FORCED);
                        }
                        
                        UtilEntity.sendChatToPlayer(player, "Stored Power: " + power.getStoredPower());
                        return true;
                    }
                    
                }
            }
            return false;
        }
    }
    
    public static class ElectisCrystalShardRenderer implements IItemRenderer {
        
        public static final ElectisCrystalShardRenderer instance = new ElectisCrystalShardRenderer();
        
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
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .45F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(item, type, 0f, 1f, 0f, .7F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(item, type, 0f, 0f, 0f, .9F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(item, type, -.5F, 1f, 0.5f, .5F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemStack item, ItemRenderType type, float x, float y, float z, float scale) {
            
            color = Color.WHITE;
            
            NoiseManager.bindImage();
            
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glTranslatef(x, y, z);
            GL11.glScalef(scale, scale, scale);
            GL11.glRotated(90, 0, 0, 1);
            GL11.glRotated(90, 0, 1, 0);
            
            GL11.glPushMatrix();
            GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 0F, 1F);
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            
            GL11.glScalef(.97f, .97f, .97f);
            
            Models.ModelFlameShard.renderPart("Center");
            GL11.glPopMatrix();
            
            color = color.darker().darker().darker();
            
            GL11.glPushMatrix();
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            Models.ModelFlameShard.renderPart("Outside");
            GL11.glPopMatrix();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
