package GU.blocks.containers.BlockElectisCrystal;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import GU.blocks.containers.BlockContainerBase;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.render.NoiseManager;
import cpw.mods.fml.client.registry.ClientRegistry;

public class BlockElectisCrystal extends BlockContainerBase {
    
    public BlockElectisCrystal(Material material) {
        super(material);
        this.registerTile(TileElectisCrystal.class);
        this.setLightOpacity(0);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisCrystal.class, ElectisCrystalRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisCrystalRenderer.instance);
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        
        return side;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int meta) {
        
        return new TileElectisCrystal();
    }
    
    public static class ElectisCrystalRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
        
        public static ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
        
        @Override
        public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
            
            GL11.glPushMatrix();
            
            float translationAmount = .2f;
            
            switch (((TileBase) tileentity).getOrientation()) {
            
                case UP: {
                    
                    GL11.glTranslated(x + 0.5F, y + translationAmount, z + .5F);
                    break;
                }
                case DOWN: {
                    
                    GL11.glTranslated(x + 0.5F, y + (1 - translationAmount), z + .5F);
                    GL11.glRotatef(180F, 1F, 0F, 0F);
                    break;
                }
                
                case SOUTH: {
                    
                    GL11.glTranslated(x + 0.5F, y + .5F, z + translationAmount);
                    GL11.glRotatef(90F, 1F, 0F, 0F);
                    break;
                }
                case NORTH: {
                    
                    GL11.glTranslated(x + 0.5F, y + .5F, z + (1 - translationAmount));
                    GL11.glRotatef(-90F, 1F, 0F, 0F);
                    break;
                }
                case WEST: {
                    
                    GL11.glTranslated(x + (1 - translationAmount), y + .5F, z + .5F);
                    GL11.glRotatef(90F, 0F, 0F, 1F);
                    break;
                }
                case EAST: {
                    
                    GL11.glTranslated(x + translationAmount, y + .5F, z + .5F);
                    GL11.glRotatef(-90F, 0F, 0F, 1F);
                    break;
                }
                default: {
                    break;
                }
            }
            
            {
                GL11.glPushMatrix();
                float scale = .15f;
                GL11.glScalef(scale, scale, scale);
                
                NoiseManager.bindImage();
                Models.ModelCrystal2.renderPart("Crystal");
                GL11.glPopMatrix();
            }
            
            final float secondCrystalScale = .35f;
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, .25F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(.25, -0.1, -.025F);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -.25F);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-.25, -0.1, .025F);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
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
                    
                    renderItemSwitched(type, item, 0f, 0, 0f, .2F);
                    return;
                }
                
                case EQUIPPED: {
                    
                    renderItemSwitched(type, item, 0f, 1, .5f, .2F);
                    return;
                }
                
                case INVENTORY: {
                    
                    renderItemSwitched(type, item, 0f, -.2f, 0f, .2F);
                    return;
                }
                
                case EQUIPPED_FIRST_PERSON: {
                    
                    renderItemSwitched(type, item, -.5F, 1f, .5F, .12F);
                    return;
                }
                
                default:
                    return;
            }
        }
        
        private void renderItemSwitched(ItemRenderType type, ItemStack item, float x, float y, float z, float scale) {
            
            GL11.glPushMatrix();
            
            GL11.glTranslatef(x, y, z);
            
            {
                GL11.glPushMatrix();
                GL11.glScalef(scale, scale, scale);
                NoiseManager.bindImage();
                Models.ModelCrystal2.renderPart("Crystal");
                GL11.glPopMatrix();
            }
            
            final float secondCrystalScale = .3f;
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, .35F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(.25, -0.1, -.035F);
                
                GL11.glRotatef(90F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(0, -0.1, -.35F);
                
                GL11.glRotatef(180F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            
            {
                GL11.glPushMatrix();
                
                GL11.glTranslated(-.25, -0.1, .035F);
                
                GL11.glRotatef(270F, 0F, 1F, 0F);
                
                GL11.glScalef(secondCrystalScale, secondCrystalScale, secondCrystalScale);
                
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(25F, 1F, 0F, 0F);
                
                Models.ModelFlameShard.renderAll();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
    }
}
