package GU.entity.EntityPotion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import GU.api.potion.IPotion;
import GU.api.potion.IPotionIngredient;
import GU.sounds.Sounds;

public class EntityModularPotion extends EntityThrowable {

    ItemStack stack;

    public EntityModularPotion(World world) {
        super(world);
    }

    public EntityModularPotion(World world, EntityLivingBase entity, ItemStack itemStack) {
        super(world, entity);

        this.stack = itemStack;        
    }

    @Override
    protected float getGravityVelocity() {

        return 0.05F;
    }

    @Override
    protected float func_70182_d() {

        return 01.5F;
    }

    @Override
    protected float func_70183_g() {

        return super.func_70183_g();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void onImpact(MovingObjectPosition movingobjectposition) {

        if(stack != null) {
            
            IPotion potion = ((IPotion)stack.getItem());
            ArrayList<ItemStack> itemLists = ((IPotion)stack.getItem()).getModules(stack);

            if(!this.worldObj.isRemote) {

                if(!itemLists.isEmpty()) {

                    AxisAlignedBB area = this.boundingBox.expand(potion.getStrength(stack), potion.getStrength(stack), potion.getStrength(stack));

                    List entitysWithin = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, area);

                    if(entitysWithin != null && !entitysWithin.isEmpty()) {

                        Iterator iterator = entitysWithin.iterator();

                        while (iterator.hasNext()) {

                            EntityLivingBase entity = (EntityLivingBase)iterator.next();

                            if(this.getDistanceSqToEntity(entity) <= potion.getStrength(stack)) {

                                for(ItemStack itemStack : itemLists) {

                                    if(itemStack.getItem() instanceof IPotionIngredient) {

                                        ((IPotionIngredient)itemStack.getItem()).onThrownPotionHitEntity(worldObj, stack, entity);
                                    }
                                }
                            }
                            else {
                                
                                for(ItemStack itemStack : itemLists) {

                                    if(itemStack.getItem() instanceof IPotionIngredient) {

                                        ((IPotionIngredient)itemStack.getItem()).onThrownPotionHitBlock(worldObj, stack, movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
                                    }
                                }
                            }
                        }
                    }
                    else {

                        for(ItemStack itemStack : itemLists) {

                            if(itemStack.getItem() instanceof IPotionIngredient) {

                                ((IPotionIngredient)itemStack.getItem()).onThrownPotionHitBlock(worldObj, stack, movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
                            }
                        }
                    }
                }
            }

            Sounds.POTION_HIT.play(this.posX, this.posY, this.posZ, this.worldObj.rand.nextFloat(), this.worldObj.rand.nextFloat());
            this.setDead();
        }
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);

        if (tag.hasKey("Potion")) {

            this.stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Potion"));
        }

        if (this.stack == null) {

            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {

        super.writeEntityToNBT(tag);

        if (this.stack != null) {

            tag.setTag("Potion", this.stack.writeToNBT(new NBTTagCompound()));
        }
    }
}
