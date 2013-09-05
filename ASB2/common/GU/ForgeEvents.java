package GU;

import java.util.Random;

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
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import ASB2.utils.UtilBlock;
import ASB2.vector.Vector3;

public class ForgeEvents {

    @ForgeSubscribe
    public void LivingDeathEven(LivingDeathEvent event) {

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

            Random rand = new Random();

            if(rand.nextInt(10) == 1) {

                if(!event.entityLiving.worldObj.isRemote) {

                    Vector3 entiyPos = new Vector3(event.entityLiving);
                    UtilBlock.spawnItemStackEntity(event.entityLiving.worldObj, entiyPos.intX(), entiyPos.intY(), entiyPos.intZ(), ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 1);
                }   
            }
        }
    }
}
