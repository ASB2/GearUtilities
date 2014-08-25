package GUOLD;

import javax.swing.Icon;

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
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import ASB2.utils.UtilBlock;
import GUOLD.fluid.FluidBase;
import GUOLD.info.Particles;
import GUOLD.info.Reference;
import GUOLD.render.NoiseManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeEvents {

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

            EnumState.SIDES[0] = event.map.registerIcon(Reference.MODDID + ":sides/BlockInput");
            EnumState.SIDES[1] = event.map.registerIcon(Reference.MODDID + ":sides/BlockOutput");
            EnumState.SIDES[2] = event.map.registerIcon(Reference.MODDID + ":sides/BlockBoth");
            EnumState.SIDES[3] = event.map.registerIcon(Reference.MODDID + ":sides/BlockNone");
        }
        event.map.setTextureEntry(NoiseManager.instance.iconTexture.getIconName(), NoiseManager.instance.iconTexture);
        Particles.register(event);
    }

    @ForgeSubscribe
    public void onEntityDrop(LivingDropsEvent event) {

        if (!event.entityLiving.worldObj.isRemote) {

            if (!event.isCanceled() && !(event.entityLiving instanceof EntityPlayer)) {

                UtilBlock.spawnItemStackEntity(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 1);

                if (event.entityLiving instanceof EntityPig || event.entityLiving instanceof EntitySheep || event.entityLiving instanceof EntityCow || event.entityLiving instanceof EntityChicken || event.entityLiving instanceof EntitySquid || event.entityLiving instanceof EntityWolf || event.entityLiving instanceof EntityMooshroom || event.entityLiving instanceof EntityOcelot || event.entityLiving instanceof EntityVillager || event.entityLiving instanceof EntityHorse) {

                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ItemRegistry.ItemCrystal.ItemBloodCrystalShard));
                }
            }
        }
    }
}