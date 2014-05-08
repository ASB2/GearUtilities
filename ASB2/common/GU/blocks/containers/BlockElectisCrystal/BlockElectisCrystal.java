package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.info.Reference;
import GU.info.Textures;
import GU.render.NoiseManager;

public class BlockElectisCrystal extends BlockMetadataContainerBase {
    
    public BlockElectisCrystal(Material material) {
        super(material);
        
        super.wrappers.put(0, new MetadataWrapper().setDisplayName("Electis Crystal").setMetadata(0).setHardness(50));
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static class ElectisCrystalRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
        
        public static ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
        
        @Override
        public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
            
            GL11.glPushMatrix();
            
            switch (((TileBase) tileentity).getOrientation()) {
            
                case UP: {
                    
                    GL11.glTranslatef((float) x + 0.5F, (float) y + .5f, (float) z + .5F);
                    break;
                }
                case DOWN: {
                    
                    GL11.glTranslatef((float) x + 0.5F, (float) y + .5f, (float) z + .5F);
                    GL11.glRotatef(180F, 1F, 0F, 0F);
                    break;
                }
                
                case SOUTH: {
                    
                    GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 0.5F);
                    GL11.glRotatef(90F, 1F, 0F, 0F);
                    break;
                }
                case NORTH: {
                    
                    GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + .5f);
                    GL11.glRotatef(-90F, 1F, 0F, 0F);
                    break;
                }
                case WEST: {
                    
                    GL11.glTranslatef((float) x + .5f, (float) y + .5F, (float) z + .5F);
                    GL11.glRotatef(90F, 0F, 0F, 1F);
                    break;
                }
                case EAST: {
                    
                    GL11.glTranslatef((float) x + .5f, (float) y + .5F, (float) z + .5F);
                    GL11.glRotatef(-90F, 0F, 0F, 1F);
                    break;
                }
                default: {
                    break;
                }
            }
            
            GL11.glScalef(.3f, .35f, .3f);
            
            UtilRender.renderTexture(Textures.CENTRIFUGE_OUTSIDE_TOP);
            Models.ModelCentrifgue.renderPart("Top");
            UtilRender.renderTexture(Textures.CENTRIFUGE_OUTSIDE_BOTTOM);
            Models.ModelCentrifgue.renderPart("Bottom");
            
            NoiseManager.bindImage();
            // UtilRender.renderTexture(Textures.CENTRIFUGE_CENTER);
            Models.ModelCentrifgue.renderPart("Center");
            
            GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
            
            UtilRender.renderTexture(Textures.CENTRIFUGE_ROTATING);
            
            for (int i = 0; i < 4; i++) {
                
                // GL11.glRotatef(45 * i, 0, 0, 0);
                // Models.ModelCentrifgue.renderPart("Rotating");
                
                switch (i) {
                
                    case 0: {
                        
                        GL11.glRotatef(0F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 1: {
                        
                        GL11.glRotatef(90F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 2: {
                        
                        GL11.glRotatef(180F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 3: {
                        
                        GL11.glRotatef(90F, 0F, -1F, 0F);
                        break;
                    }
                }
                Models.ModelCentrifgue.renderPart("Rotating");
            }
            GL11.glPopMatrix();
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
                    
                    renderItemSwitched(type, item, 0f, 0, 0f, .7F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(type, item, 0f, 1, .5f, .5F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(type, item, 0f, 0f, 0f, .4F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(type, item, -.5F, 1f, .5F, .3F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemRenderType type, ItemStack item, float x, float y, float z, float scale) {
            
            GL11.glPushMatrix();
            
            GL11.glTranslatef(x, y, z);
            GL11.glScalef(scale, scale + .1f, scale);
            
            UtilRender.renderTexture(Textures.CENTRIFUGE_OUTSIDE_TOP);
            Models.ModelCentrifgue.renderPart("Top");
            UtilRender.renderTexture(Textures.CENTRIFUGE_OUTSIDE_BOTTOM);
            Models.ModelCentrifgue.renderPart("Bottom");
            
            UtilRender.renderTexture(Textures.CENTRIFUGE_CENTER);
            Models.ModelCentrifgue.renderPart("Center");
            
            GL11.glRotatef(Minecraft.getSystemTime() / Reference.ANIMATION_SPEED, 0F, 1F, 0F);
            
            UtilRender.renderTexture(Textures.CENTRIFUGE_ROTATING);
            
            for (int i = 0; i < 4; i++) {
                
                switch (i) {
                
                    case 0: {
                        
                        GL11.glRotatef(0F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 1: {
                        
                        GL11.glRotatef(90F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 2: {
                        
                        GL11.glRotatef(180F, 0F, 1F, 0F);
                        break;
                    }
                    
                    case 3: {
                        
                        GL11.glRotatef(90F, 0F, -1F, 0F);
                        break;
                    }
                }
                Models.ModelCentrifgue.renderPart("Rotating");
            }
            GL11.glPopMatrix();
        }
    }
}
