package GU.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import GU.info.*;

public class UtilPlayers {

    private static String[] specialPlayers = new String[] {"ASB2", "Proswhere", "iOverpowered"};

    public static double[] getPlayerCursorCoords(World world, EntityPlayer entityplayer) {

        float f = 1.0F;
        float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
        float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
        double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
        double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)entityplayer.yOffset;
        double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
        Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        double d3 = 5000D;
        Vec3 vec3d1 = vec3d.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
        MovingObjectPosition movingobjectposition = world.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);

        if (!(movingobjectposition == null)) {

            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                return new double[] {i, j, k};
            }
        }        

        return null;
    }

    public static void damagePlayer(World world, int x, int y, int z, Entity entity, DamageSource source, int damage) {
        
        if(entity instanceof EntityPlayer) {

            if(!(UtilPlayers.isSpecialPlayer(((EntityPlayer)entity).username))) {

                entity.attackEntityFrom(source, damage); 
            }
        }
        else {
            entity.attackEntityFrom(source, damage); 
        }
    }
    
    public static boolean hasItemStack(EntityPlayer player, ItemStack itemStack) {

        if(itemStack != null) {

            for(int i = 0; i < player.inventory.getSizeInventory(); i++ ) {

                if(player.inventory.getStackInSlot(i) != null) {

                    if(player.inventory.getStackInSlot(i).equals(itemStack)) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int slotWithItem(EntityPlayer player, ItemStack itemStack) {

        if(itemStack != null) {

            for(int i = 0; i < player.inventory.getSizeInventory(); i++ ) {

                if(player.inventory.getStackInSlot(i) != null) {

                    if(player.inventory.getStackInSlot(i).equals(itemStack)) {

                        return i;
                    }
                }
            }
        }
        return 0;
    }

    @SuppressWarnings("unused")
    public static boolean isSpecialPlayer(String player) {

        if(Variables.MODDER_APPRECATION) {

            for(int p = 0; p < UtilPlayers.specialPlayers.length; p++) {

                if(UtilPlayers.specialPlayers[p].equals(player)) {    

                    return true;
                }   

                else {

                    return false;
                }
            }        
        }
        return false;
    }

    public static void sendChatToPlayer(EntityPlayer player, String message) {

        if(!player.worldObj.isRemote) {

            player.addChatMessage(message);
        }
    }
}
