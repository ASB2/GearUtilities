package GU.entities;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilVector;
import GU.GearUtilities;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.info.Models;
import GU.info.Textures;
import GU.render.NoiseManager;
import UC.math.vector.Vector3d;
import UC.math.vector.Vector3i;

public class EntityPhoton extends EntityBase {
    
    final Vector3d startPosition, endPosition;
    Vector3i destination;
    int maxDistance;
    int powerCarried;
    
    public EntityPhoton(World par1World) {
        super(par1World);
        startPosition = Vector3d.ZERO.clone();
        endPosition = Vector3d.ZERO.clone();
    }
    
    public EntityPhoton(World world, double x, double y, double z, int xD, int yD, int zD) {
        super(world);
        position = new Vector3d(x, y, z);
        destination = new Vector3i(xD, yD, zD);
        this.momentum = determineHeading(position, destination);
        startPosition = position.clone();
        endPosition = destination.toVector3d();
    }
    
    public EntityPhoton(World world, Vector3d position, Vector3i destination) {
        super(world);
        this.position = position;
        this.destination = destination;
        this.momentum = determineHeading(position, destination);
        startPosition = position.clone();
        endPosition = destination.toVector3d();
    }
    
    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        
        position.move(momentum.multiply(.3));
        this.updateVinillaPosition();
        
        Vector3i positionFloor = position.toVector3iFloor();
        TileEntity tile = UtilVector.getTileAtPostion(worldObj, positionFloor);
        
        if (tile != null) {
            
            onImpact(positionFloor);
            this.setDead();
        }
        
        if (startPosition.distanceTo(position) > maxDistance) {
            this.setDead();
            GearUtilities.log("Dead");
        }
    }
    
    public void onImpact(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(worldObj, startPosition.toVector3iFloor());
        
        if (tile != null && tile instanceof IPhotonImpact) {
            
            ((IPhotonImpact) tile).onImpact(powerCarried, position);
        }
    }
    
    public Vector3d determineHeading(Vector3d position, Vector3i destination) {
        
        return destination.subtract(position).normalize();
    }
    
    public EntityPhoton setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
        return this;
    }
    
    public int getMaxDistance() {
        return maxDistance;
    }
    
    public EntityPhoton setPowerCarried(int powerCarried) {
        this.powerCarried = powerCarried;
        return this;
    }
    
    public int getPowerCarried() {
        return powerCarried;
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
    
    public static interface IPhotonImpact {
        
        void onImpact(int powerCarried, Vector3i impactPosition);
    }
}
