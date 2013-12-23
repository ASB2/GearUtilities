package GU.entity.EntityQuanta;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.ResourcePacket;
import GU.api.EnumResourceType;
import GU.api.IResourcePacket;
import GU.api.power.IPowerHandler;
import GU.api.power.UtilPower;
import GU.entity.EntityBase;

public class EntityQuanta extends EntityBase {
    
    ForgeDirection direction;
    IResourcePacket resource;
    int maxDistance;
    
    public EntityQuanta(World world) {
        super(world);
    }
    
    public EntityQuanta(World world, Vector3 position, ForgeDirection direction, int maxDistance, IResourcePacket resource) {
        this(world);
        
        this.vectors = new Vector3[2];
        this.setPosition(position);
        vectors[0] = position.clone();
        vectors[1] = position;
        this.direction = direction;
        this.maxDistance = maxDistance;
        this.resource = resource;
        this.setSize(0.1f, 0.1f);
    }
    
    @Override
    public void onEntityUpdate() {
        
        if (!worldObj.isRemote) {
            
            if (vectors != null) {
                
                vectors[1].add(direction, .5);
                
                this.setPosition(vectors[1]);
                
                if (vectors[1].distanceTo(vectors[0]) > maxDistance) {
                    this.setDead();
                    return;
                }
                
                int around = 0;
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = UtilDirection.translateDirectionToTile(vectors[1], worldObj, direction);
                    if (tile != null) {
                        
                        if (tile instanceof IPowerHandler) {
                            
                            around++;
                        }
                    }
                }
                
                if (around > 0) {
                    
                    for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                        
                        TileEntity tile = UtilDirection.translateDirectionToTile(vectors[1], worldObj, direction);
                        
                        if (tile != null) {
                            
                            if (tile instanceof IPowerHandler) {
                                
                                if (UtilPower.addEnergyToProvider(((IPowerHandler) tile).getPowerProvider(), direction, this.resource.getPowerResource().get(EnumResourceType.EnumPowerType.GEAR_UTILITIES), true)) {
                                    
                                    this.setDead();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public IResourcePacket getResorce() {
        
        return resource;
    }
    
    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        
        tag.setCompoundTag("resource", resource.save(new NBTTagCompound()));
        tag.setInteger("direction", direction.ordinal());
        tag.setInteger("maxDistance", maxDistance);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        resource = new ResourcePacket();
        resource.load(tag.getCompoundTag("resource"));
        direction = ForgeDirection.values()[tag.getInteger("direction")];
        maxDistance = tag.getInteger("maxDistance");
    }
}
