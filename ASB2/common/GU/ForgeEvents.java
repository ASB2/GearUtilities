package GU;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import ASB2.utils.UtilBlock;

public class ForgeEvents {

    @ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event) {

        if(!event.entityLiving.worldObj.isRemote) {

            if(!event.isCanceled() && !(event.entityLiving instanceof EntityPlayer)) {

                    UtilBlock.spawnItemStackEntity(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 1);

                    if(event.entityLiving instanceof EntityPig 
                            || event.entityLiving instanceof EntitySheep 
                            || event.entityLiving instanceof EntityCow 
                            || event.entityLiving instanceof EntityChicken 
                            || event.entityLiving instanceof EntitySquid 
                            || event.entityLiving instanceof EntityWolf 
                            || event.entityLiving instanceof EntityMooshroom 
                            || event.entityLiving instanceof EntityOcelot 
                            || event.entityLiving instanceof EntityVillager
                            || event.entityLiving instanceof EntityHorse) {

                        event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ItemRegistry.ItemCrystal.ItemBloodCrystalShard));
                    }
            }
        }
    }
}