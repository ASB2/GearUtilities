package GU.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.info.Models;
import GU.info.Textures;
import GU.render.NoiseManager;
import UC.math.vector.*;

public class EntityPhoton extends EntityBase {
    
    final Vector3d startPosition;
    Vector3i destination;
    
    public EntityPhoton(World par1World) {
        super(par1World);
        startPosition = Vector3d.ZERO.clone();
    }
    
    public EntityPhoton(World world, double x, double y, double z, int xD, int yD, int zD) {
        super(world);
        position = new Vector3d(x, y, z);
        destination = new Vector3i(xD, yD, zD);
        this.momentum = determineHeading(position, destination);
        startPosition = position.clone();
    }
    
    public EntityPhoton(World world, Vector3d position, Vector3i destination) {
        super(world);
        this.position = position;
        this.destination = destination;
        this.momentum = determineHeading(position, destination);
        startPosition = position.clone();
    }
    
    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        
        position.move(momentum);
        this.updateVinillaPosition();
        
        if (startPosition.distanceTo(position) > 1000) {
            this.setDead();
            GearUtilities.log("Dead");
        }
    }
    
    public Vector3d determineHeading(Vector3d position, Vector3i destination) {
        
        return destination.subtract(position).normalize();
    }
    
    public static class PhotonRenderer extends Render {
        
        public static final PhotonRenderer instance = new PhotonRenderer();
        
        @Override
        public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
            
            GL11.glPushMatrix();
            
            GL11.glTranslated(x + .5f, y + .5f, z + .5f);
            GL11.glScalef(.5f, .5f, .5f);
            
            NoiseManager.bindImage();
            Models.ModelCrystal1.renderAll();
            
            GL11.glPopMatrix();
        }
        
        @Override
        protected ResourceLocation getEntityTexture(Entity entity) {
            
            return Textures.BLANK;
        }
    }
    
}
