package GU.items;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.api.EnumSimulationType;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.UtilPower;
import GU.info.Models;
import GU.info.Reference;
import GU.render.noise.NoiseManager;

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
                
                IPowerManager powerHandler = null;
                
                if (tile != null) {
                    
                    if (tile instanceof ITilePowerHandler) {
                        
                        powerHandler = ((ITilePowerHandler) tile).getPowerManager();
                    }
                    if (tile instanceof ICrystalPowerHandler) {
                        
                        powerHandler = ((ICrystalPowerHandler) tile).getPowerManager();
                    }
                }
                else {
                    
                    Block block = world.getBlock(x, y, z);
                    
                    if (block != null && block instanceof IBlockPowerHandler) {
                        
                        powerHandler = ((IBlockPowerHandler) block).getPowerManager(world, x, y, z);
                    }
                }
                
                if (powerHandler != null) {
                    
                    int powerSaved = UtilItemStack.getNBTTagInt(itemStack, "inputPower");
                    
                    if (player.isSneaking()) {
                        
                        UtilPower.removePower(powerHandler, powerSaved, EnumSimulationType.FORCED);
                    }
                    else {
                        
                        UtilPower.addPower(powerHandler, powerSaved, EnumSimulationType.FORCED);
                    }
                    
                    UtilEntity.sendChatToPlayer(player, "Power Stored: " + powerHandler.getStoredPower());
                }
            }
            return true;
        }
        
        @Override
        public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
            
            // if (false) {
            
            int powerSaved = UtilItemStack.getNBTTagInt(itemStack, "inputPower");
            
            if (player.isSneaking()) {
                
                powerSaved--;
            }
            else {
                
                powerSaved++;
            }
            UtilItemStack.setNBTTagInt(itemStack, "inputPower", Math.max(powerSaved, 0));
            UtilEntity.sendChatToPlayer(player, "Power To Move: " + Math.max(powerSaved, 0));
            // }
            // else {
            //
            // NoiseManager.instance.generateNoiseImage();
            // }
            return super.onItemRightClick(itemStack, world, player);
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
            
            Models.ModelElectisShard.renderPart("Center");
            GL11.glPopMatrix();
            
            color = color.darker().darker().darker();
            
            GL11.glPushMatrix();
            GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
            Models.ModelElectisShard.renderPart("Outside");
            GL11.glPopMatrix();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
