package GU.blocks.containers.BlockElectisCrystal;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;
import GU.render.Shader;

public class ElectisCrystalRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
    
    public static ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
    private static final ResourceLocation field_147523_b = new ResourceLocation("textures/entity/beacon_beam.png");
    
    int objectlist = -1;
    Shader testShader;
    final ResourceLocation shaderResource = new ResourceLocation(Reference.MOD_ID + ":shaders/basicFragment.fs");
    
    public ElectisCrystalRenderer() {
        
        testShader = new Shader().setShaderName("Test Shader");
        try {
            String shaderFile = Shader.loadShaderText(Minecraft.getMinecraft().getResourceManager().getResource(shaderResource).getInputStream());
            testShader.addFragmentShader(shaderFile);
            testShader.compileShader();
            testShader.setShaderName(testShader.getShaderName());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        if (objectlist == -1) {
            generateWorldRenderList();
        }
        
        GL11.glPushMatrix();
        // testShader.bind();
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
        GL11.glCallList(objectlist);
        // testShader.unBind();
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        // for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> crystal
        // : ((TileElectisCrystal) tileentity).powerHandlers) {
        //
        // GL11.glBegin(GL11.GL_LINES);
        // GL11.glColor3f(1, 1, 0);
        // GL11.glVertex3d(x + .5, y + .5, z + .5);
        // GL11.glColor3f(1, 0, 0);
        // GL11.glVertex3d((crystal.getKey().getX() -
        // Minecraft.getMinecraft().thePlayer.posX) + .5,
        // (crystal.getKey().getY() -
        // Minecraft.getMinecraft().thePlayer.posY) + .5,
        // (crystal.getKey().getZ() -
        // Minecraft.getMinecraft().thePlayer.posZ) + .5);
        //
        // GL11.glEnd();
        // }
        
        // GL11.glTranslated(x, y, z);
        // GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
        // 10497.0F);
        // GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
        // 10497.0F);
        // Tessellator tessellator = Tessellator.instance;
        // this.bindTexture(field_147523_b);
        // GL11.glDisable(GL11.GL_LIGHTING);
        // GL11.glDisable(GL11.GL_CULL_FACE);
        //
        // for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> crystal
        // : ((TileElectisCrystal) tileentity).powerHandlers) {
        //
        // if
        // (!tileentity.getWorldObj().isBlockIndirectlyGettingPowered(crystal.getKey().getX(),
        // crystal.getKey().getY(), crystal.getKey().getZ())) {
        // float x_ = (float) ((crystal.getKey().getX() -
        // Minecraft.getMinecraft().thePlayer.posX)), y_ = (float)
        // ((crystal.getKey().getY() -
        // Minecraft.getMinecraft().thePlayer.posY)), z_ = (float)
        // ((crystal.getKey().getZ() -
        // Minecraft.getMinecraft().thePlayer.posZ));
        //
        // tessellator.startDrawingQuads();
        // tessellator.addVertexWithUV(x+ .5, y + .5, z+ .5, 0, 0);
        // tessellator.addVertexWithUV(x_+ .5, y_ + .5, z_+ .5, -1, 0);
        // tessellator.addVertexWithUV(x_ + .54, y_ + .5, z_+ .5, -1, -1);
        // tessellator.addVertexWithUV(x + .54, y + .5, z+ .5, 0, -1);
        // tessellator.draw();
        // }
        // }
        // GL11.glEnable(GL11.GL_CULL_FACE);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    public void generateWorldRenderList() {
        
        objectlist = GL11.glGenLists(1);
        
        GL11.glNewList(objectlist, GL11.GL_COMPILE);
        
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
        GL11.glEndList();
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