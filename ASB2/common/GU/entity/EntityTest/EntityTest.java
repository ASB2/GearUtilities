package GU.entity.EntityTest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import GU.entity.*;

public class EntityTest extends EntityBase {

    public EntityTest(World world) {
        super(world);
        this.setSize(.7F, .7F);
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();

        this.updateMovement();    
    }
    
    @Override
    protected void entityInit() {
        
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {

    }

}
