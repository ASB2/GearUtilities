package GU.entity.EntityFamilar;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class EntityFamilars extends EntityLiving {

    Vector3 position;
    String owner;

    public EntityFamilars(World world) {
        super(world);

        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    public EntityFamilars(World world, Vector3 position, String owner) {
        this(world);

        this.position = position;
        this.posX = position.x;
        this.posY = position.y;
        this.posZ = position.z;
        this.owner = owner;

    }

    public void setPosition(Vector3 position) {

        this.setPosition(position.x, position.y, position.z);
    }

    @Override
    public void onUpdate() {

        if(Minecraft.getMinecraft().thePlayer != null) {

            Minecraft.getMinecraft().thePlayer.addChatMessage(position.toString());
        }
        super.onUpdate();
    }

    @Override
    public AxisAlignedBB getBoundingBox() {

        return boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {

        if(entity != riddenByEntity) {

            return entity.boundingBox;
        }
        return null;
    }

    @Override
    public boolean canBeCollidedWith() {

        return !isDead;
    }
}
