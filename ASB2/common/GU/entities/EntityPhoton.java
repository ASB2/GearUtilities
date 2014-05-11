package GU.entities;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilVector;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
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
        position = Vector3d.ZERO.clone();
        momentum = Vector3d.ZERO.clone();
        destination = Vector3i.ZERO.clone();
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
    
    public EntityPhoton(World world, int x, int y, int z, Vector3i destination) {
        super(world);
        this.position = new Vector3d(x, y, z);
        this.destination = destination;
        this.momentum = determineHeading(position, destination);
        startPosition = position.clone();
        endPosition = destination.toVector3d();
    }
    
    @Override
    public void onUpdate() {
        
        position.move(momentum.multiply(.3));
        this.updateVinillaPosition();
        
        Vector3i positionFloor = position.toVector3iFloor();
        
        if (!positionFloor.equals(startPosition)) {
            
            TileEntity tileSink = UtilVector.getTileAtPostion(worldObj, positionFloor);
            TileEntity tileSource = UtilVector.getTileAtPostion(worldObj, startPosition.toVector3iFloor());
            
            if (tileSink != null && tileSource != null) {
                
                if (tileSink instanceof ICrystalPowerHandler && tileSource instanceof ICrystalPowerHandler) {
                    
                    IPowerManager sinkManager = ((ICrystalPowerHandler) tileSink).getPowerManager();
                    IPowerManager sourceManager = ((ICrystalPowerHandler) tileSource).getPowerManager();
                    
                    if (sinkManager != null && sourceManager != null) {
                        
                        if (UtilPower.movePower(sourceManager, sinkManager, powerCarried, EnumSimulationType.FORCED)) {
                            this.setDead();
                        }
                    }
                }
            }
        }
        if (startPosition.distanceTo(position) >= maxDistance) {
            this.setDead();
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
            
            float scale = .1f;
            GL11.glScalef(scale, scale, scale);
            GL11.glColor3d(1, 0, 0);
            NoiseManager.bindImage();
            Models.ModelRhombicuboctahedron.renderAll();
            
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
