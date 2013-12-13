package GU;

import net.minecraft.block.Block;
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
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import ASB2.utils.UtilBlock;
import GU.fluid.FluidBase;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeEvents {
    
    public static Icon blankImage;
    
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Pre event) {
        
        String still = ":fluids/FluidBlankStill";
        String flowing = ":fluids/FluidBlankFlowing";
        
        if (event.map.textureType == 0) {
            
            Icon stillIcon = event.map.registerIcon(Reference.MODDID + still);
            Icon flowingIcon = event.map.registerIcon(Reference.MODDID + flowing);
            
            for (FluidBase base : FluidRegistry.GU_FLUIDS) {
                
                base.setStillIcon(stillIcon);
                base.setFlowingIcon(flowingIcon);
            }
            
            FluidRegistry.CapturedSoul.setStillIcon(Block.slowSand.getIcon(0, 0));
            FluidRegistry.CapturedSoul.setFlowingIcon(Block.slowSand.getIcon(0, 0));
            
            blankImage = event.map.registerIcon(Reference.MODDID + ":LargeBlankTexture");
        }
    }
    
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