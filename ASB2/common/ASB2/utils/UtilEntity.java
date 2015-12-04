package ASB2.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import UC.math.vector.Vector3i;

public class UtilEntity {
    
    public static int[] getPayerCoords(Entity entity) {
        
        Vector3i position = new Vector3d(entity.posX, entity.posY, entity.posZ).toVector3iFloor();
        return new int[] { position.getX(), position.getY(), position.getX() };
    }
    
    public static ForgeDirection getEntityYawDirection(Entity entity) {
        
        int roatation = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        
        switch (roatation) {
        
            case 0:
                return ForgeDirection.SOUTH;
            case 1:
                return ForgeDirection.WEST;
            case 2:
                return ForgeDirection.NORTH;
            case 3:
                return ForgeDirection.EAST;
        }
        return ForgeDirection.UNKNOWN;
    }
    
    public static ForgeDirection getEntityPitchDirection(Entity entity) {
        
        if (entity.rotationPitch >= 90 && entity.rotationPitch <= 180) {
            
            return ForgeDirection.DOWN;
        }
        else if (entity.rotationPitch <= 90) {
            
            return ForgeDirection.UP;
        }
        return ForgeDirection.UNKNOWN;
    }
    
    public static void damageEntity(World world, Entity entity, DamageSource source, int damage) {
        
        entity.attackEntityFrom(source, damage);
    }
    
    public static void sendClientChat(String message) {
        
        if (FMLClientHandler.instance().getClient() != null) FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
    }
    
    public static void sendChatToPlayer(ICommandSender player, Object message) {
        
//        if (!player.worldObj.isRemote) {
            
            player.addChatMessage(new ChatComponentText(message != null ? message.toString() : "null"));
//        }
    }
    
    public static void spawnFX(EntityFX fx) {
        
        if (FMLClientHandler.instance().getClient() != null) {
            
            Minecraft mc = Minecraft.getMinecraft();
            int settings = mc.gameSettings.particleSetting;
            
            if (!(settings == 2 || settings == 1 && fx.worldObj.rand.nextInt(3) == 0)) {
                
                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            }
        }
    }
}
