package GU.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.info.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpeedyRoad extends BlockBase {

    private Icon top;
    private Icon side;

    public BlockSpeedyRoad(int par1, Material par2Material) {
        super(par1, par2Material);
        setLightValue(1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {

        top = par1IconRegister.registerIcon(Reference.MODDID + ":BlockSpeedyRoadTop");
        side = par1IconRegister.registerIcon(Reference.MODDID + ":BlockSpeedyRoadSide2");
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        switch (side) {

            case 1: {
                return top;
            }
            default:
                return this.side;
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {

        if (!world.isBlockIndirectlyGettingPowered(x, y, z)) {

            if (entity instanceof EntityLivingBase) {

                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10, 3));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.jump.id, 10, 3));

                if (((EntityLivingBase)entity).isBurning()) {

                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 4));
                }

                if (((EntityLivingBase)entity).isInWater()) {

                    ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 10, 4));
                }
            }
        }
    }
}
