package GU.entity.EntityTest;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Vector3;
import GU.entity.EntityBase;
import GU.api.wait.*;

public class EntityTestEntity extends EntityBase {

    public EntityTestEntity(World world) {
        super(world);

        waits = new Wait[2];
        vectors = new Vector3[2];
    }

    public EntityTestEntity(World world, double x, double y, double z, double x2, double y2, double z2) {
        this(world);

        this.setPosition(x, y, z);
        waits[0] = new Wait(20, this, 0);
        vectors[0] = new Vector3(this);
        vectors[1] = new Vector3(x2, y2, z2);
        vectors[1] = vectors[1].normalize().multiply(.2d);
        motionX = vectors[1].x;
        motionY = vectors[1].y;
        motionZ = vectors[1].z;
    }

    @Override
    public void onEntityUpdate() {


        this.updateMovement();
        
        if(waits != null && waits[0] != null) {

            waits[0].update();
        }
    }

    public void trigger(int id) {
    }

    @Override
    protected void entityInit() {

    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data) {
        super.writeSpawnData(data);

        data.writeDouble(motionX);
        data.writeDouble(motionY);
        data.writeDouble(motionZ);
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data) {
        super.readSpawnData(data);

        motionX = data.readDouble();
        motionY = data.readDouble();
        motionZ = data.readDouble();
    }

    @Override
    protected void onImpactEntity(Entity entity) {
        // TODO Auto-generated method stub

    }

}
