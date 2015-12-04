package ASB2.utils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

import org.lwjgl.opengl.GL11;


public final class UtilRender {
    
    public static RenderItem renderItemInstance = new RenderItem();
    public static RenderBlocks renderBlockInstance = new RenderBlocks();
    
    /**
     * Used For a TESR
     */
    public static void renderCube(RenderBlocks renderBlocks, double xMin, double yMin, double zMin, double xMax, double yMax, double zMax, Block block, IIcon overrideTexture, int meta, int brightness) {
        
        GL11.glPushMatrix();
        Tessellator t = Tessellator.instance;
        
        GL11.glColor4f(1, 1, 1, 1);
        
        renderBlocks.setRenderBounds(xMin, yMin, zMin, xMax, yMax, zMax);
        
        t.startDrawingQuads();
        t.setBrightness(brightness);
        
        IIcon useTexture = overrideTexture != null ? overrideTexture : block.getIcon(0, meta);
        t.setNormal(0.0F, -1.0F, 0.0F);
        renderBlocks.renderFaceYNeg(block, 0, 0, 0, useTexture);
        
        useTexture = overrideTexture != null ? overrideTexture : block.getIcon(1, meta);
        t.setNormal(0.0F, 1.0F, 0.0F);
        renderBlocks.renderFaceYPos(block, 0, 0, 0, useTexture);
        
        useTexture = overrideTexture != null ? overrideTexture : block.getIcon(2, meta);
        t.setNormal(0.0F, 0.0F, -1.0F);
        renderBlocks.renderFaceZNeg(block, 0, 0, 0, useTexture);
        
        useTexture = overrideTexture != null ? overrideTexture : block.getIcon(3, meta);
        t.setNormal(0.0F, 0.0F, 1.0F);
        renderBlocks.renderFaceZPos(block, 0, 0, 0, useTexture);
        
        useTexture = overrideTexture != null ? overrideTexture : block.getIcon(4, meta);
        t.setNormal(-1.0F, 0.0F, 0.0F);
        renderBlocks.renderFaceXNeg(block, 0, 0, 0, useTexture);
        
        useTexture = overrideTexture != null ? overrideTexture : block.getIcon(5, meta);
        t.setNormal(1.0F, 0.0F, 0.0F);
        renderBlocks.renderFaceXPos(block, 0, 0, 0, useTexture);
        t.draw();
        
        GL11.glPopMatrix();
    }
    
    public static void renderCube(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax, Block block, IIcon overrideTexture, int meta, int brightness) {
        renderCube(renderBlockInstance, xMin, yMin, zMin, xMax, yMax, zMax, block, overrideTexture, meta, brightness);
    }
    
    /**
     *It creates a wireframe box
     */
    public static void renderBox(double xStart, double yStart, double zStart, double xEnd, double yEnd, double zEnd) {
        
        GL11.glColor3d(1, 1, 1);
        
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16733525);
        tessellator.addVertex(xStart, yStart, zStart);
        tessellator.addVertex(xStart + xEnd, yStart, zStart);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16777184);
        tessellator.addVertex(xStart, yStart, zStart);
        tessellator.addVertex(xStart, yStart, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16755370);
        tessellator.addVertex(xStart, yStart, zStart);
        tessellator.addVertex(xStart, yStart + yEnd, zStart);
        
        GL11.glColor3f(1.0F, 1.0F, 0.8F);
        
        tessellator.draw();
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16777184);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart + zEnd);
        tessellator.addVertex(xStart, yStart + yEnd, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16733525);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart + zEnd);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16755370);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart + zEnd);
        tessellator.addVertex(xStart + xEnd, yStart, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16733525);
        tessellator.addVertex(xStart, yStart + yEnd, zStart);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16777184);
        tessellator.addVertex(xStart, yStart + yEnd, zStart);
        tessellator.addVertex(xStart, yStart + yEnd, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16733525);
        tessellator.addVertex(xStart + xEnd, yStart, zStart);
        tessellator.addVertex(xStart + xEnd, yStart + yEnd, zStart);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16777184);
        tessellator.addVertex(xStart, yStart, zStart + zEnd);
        tessellator.addVertex(xStart, yStart + yEnd, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16777184);
        tessellator.addVertex(xStart + xEnd, yStart, zStart + zEnd);
        tessellator.addVertex(xStart, yStart, zStart + zEnd);
        tessellator.draw();
        
        tessellator.startDrawing(3);
        tessellator.setColorOpaque_I(16733525);
        tessellator.addVertex(xStart + xEnd, yStart, zStart + zEnd);
        tessellator.addVertex(xStart + xEnd, yStart, zStart);
        tessellator.draw();
        
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
    }
    
    public static void renderIIcon(double x, double y, IIcon IIcon, double xChange, double yChange) {
        
        int zLevel = 1;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + yChange, zLevel, IIcon.getMinU(), IIcon.getMaxV());
        tessellator.addVertexWithUV(x + xChange, y + yChange, zLevel, IIcon.getMaxU(), IIcon.getMaxV());
        tessellator.addVertexWithUV(x + xChange, y, zLevel, IIcon.getMaxU(), IIcon.getMinV());
        tessellator.addVertexWithUV(x, y, zLevel, IIcon.getMinU(), IIcon.getMinV());
        tessellator.draw();
    }
    
    public static void renderIIcon(double x, double y, double z, IIcon IIcon, double xChange, double yChange, double zChange) {
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + yChange, z + zChange, IIcon.getMinU(), IIcon.getMaxV());
        tessellator.addVertexWithUV(x + xChange, y + yChange, z + zChange, IIcon.getMaxU(), IIcon.getMaxV());
        tessellator.addVertexWithUV(x + xChange, y, z + zChange, IIcon.getMaxU(), IIcon.getMinV());
        tessellator.addVertexWithUV(x, y, z + zChange, IIcon.getMinU(), IIcon.getMinV());
        tessellator.draw();
    }
    
    public static void bindBlockTextures() {
        
        UtilRender.renderTexture(TextureMap.locationBlocksTexture);
    }
    
    public static void bindItemTextures() {
        
        UtilRender.renderTexture(TextureMap.locationItemsTexture);
    }
    
    public static void renderTexture(ResourceLocation texture) {
        
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
    }
    
    public static void renderByOrientation(double x, double y, double z, ForgeDirection direction) {
        
        switch (direction.getOpposite()) {
        
            case DOWN: {// Down
                GL11.glTranslatef((float) x + 0.5F, (float) y + -.5F, (float) z + .5F);
                break;
            }
            case UP: {// Up
                GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            
            case NORTH: {// South
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z - 0.5F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case SOUTH: {// North
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 1.5F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case EAST: {// West
                GL11.glTranslatef((float) x + 1.5F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case WEST: {// East
                GL11.glTranslatef((float) x - .5F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {// Other
                break;
            }
        }
    }
    
    public static void renderByOrientation(double x, double y, double z, int metadata) {
        
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        
        switch (metadata) {
        
            case 0: {// Down
                GL11.glTranslatef((float) x + 0.5F, (float) y + -.5F, (float) z + .5F);
                break;
            }
            case 1: {// Up
                GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + .5F);
                GL11.glRotatef(180F, 1F, 0F, 0F);
                break;
            }
            
            case 2: {// South
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z - 0.5F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                break;
            }
            case 3: {// North
                GL11.glTranslatef((float) x + 0.5F, (float) y + .5F, (float) z + 1.5F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                break;
            }
            case 5: {// West
                GL11.glTranslatef((float) x + 1.5F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                break;
            }
            case 4: {// East
                GL11.glTranslatef((float) x - .5F, (float) y + .5F, (float) z + .5F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                break;
            }
            default: {// Other
                break;
            }
        }
    }
    
    public static void renderFakeBlock(RenderBlocks renderer, Block block, int x, int y, int z, IIcon IIcon, int red, int green, int blue, int alfa, int brightness) {
        
        Tessellator tess = Tessellator.instance;
        
        tess.setBrightness(brightness);
        tess.setColorRGBA(red, green, blue, alfa);
        
        renderer.renderFaceXNeg(block, x, y, z, IIcon);
        renderer.renderFaceXPos(block, x, y, z, IIcon);
        
        renderer.renderFaceYNeg(block, x, y, z, IIcon);
        renderer.renderFaceYPos(block, x, y, z, IIcon);
        
        renderer.renderFaceZNeg(block, x, y, z, IIcon);
        renderer.renderFaceZPos(block, x, y, z, IIcon);
    }
    
    public static void renderFakeSide(RenderBlocks renderer, Block block, ForgeDirection direction, int x, int y, int z, IIcon IIcon, int red, int green, int blue, int alfa, int brightness) {
        
        if (IIcon != null) {
            
            Tessellator tess = Tessellator.instance;
            
            tess.setBrightness(brightness);
            tess.setColorRGBA(red, green, blue, alfa);
            
            switch (direction) {
            
                case DOWN:
                    renderer.renderFaceYNeg(block, x, y, z, IIcon);
                    break;
                case UP:
                    renderer.renderFaceYPos(block, x, y, z, IIcon);
                    break;
                case NORTH:
                    renderer.renderFaceZNeg(block, x, y, z, IIcon);
                    break;
                case SOUTH:
                    renderer.renderFaceZPos(block, x, y, z, IIcon);
                    break;
                case WEST:
                    renderer.renderFaceXNeg(block, x, y, z, IIcon);
                    break;
                case EAST:
                    renderer.renderFaceXPos(block, x, y, z, IIcon);
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta) {
        
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, IIcon IIcon) {
        
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, IIcon IIcon, int red, int green, int blue, int alfa) {
        
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(red, green, blue, alfa);
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, IIcon);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
}
