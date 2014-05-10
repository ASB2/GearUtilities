package GU.blocks.containers.BlockElectisCrystal;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map.Entry;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilEntity;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.blocks.containers.BlockContainerBase;
import GU.blocks.containers.TileBase;
import GU.info.Models;
import GU.info.Reference;
import GU.render.NoiseManager;
import GU.render.Shader;
import UC.math.vector.Vector3i;
import cpw.mods.fml.client.registry.ClientRegistry;

public class BlockElectisCrystal extends BlockContainerBase {
    
    public BlockElectisCrystal(Material material) {
        super(material);
        this.registerTile(TileElectisCrystal.class);
        this.setLightOpacity(0);
        this.setLightLevel(.1f);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisCrystal.class, ElectisCrystalRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisCrystalRenderer.instance);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileElectisCrystal && player.getHeldItem() == null) {
            
            UtilEntity.sendChatToPlayer(player, "PowerStored: " + ((TileElectisCrystal) tile).powerManager.getStoredPower());
            return true;
        }
        return false;
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        
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
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Electis Crystal";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int meta) {
        
        return null;
    }
    
    public static class ElectisCrystalRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
        
        public static ElectisCrystalRenderer instance = new ElectisCrystalRenderer();
        
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
            for (Entry<Vector3i, WeakReference<ICrystalPowerHandler>> crystal : ((TileElectisCrystal) tileentity).powerHandlers) {
                
                GL11.glBegin(GL11.GL_LINES);
                GL11.glColor3f(1, 1, 0);
                GL11.glVertex3d(x + .5, y + .5, z + .5);
                GL11.glColor3f(1, 0, 0);
                GL11.glVertex3d((crystal.getKey().getX() - Minecraft.getMinecraft().thePlayer.posX) + .5, (crystal.getKey().getY() - Minecraft.getMinecraft().thePlayer.posY) + .5, (crystal.getKey().getZ() - Minecraft.getMinecraft().thePlayer.posZ) + .5);
                
                GL11.glEnd();
                
            }
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
}
