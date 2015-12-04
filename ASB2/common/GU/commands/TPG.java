package GU.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;

public class TPG extends CommandBase {
    
    public List<String> aliases, autoCompletePossibilies;
    
    public TPG() {
        aliases = new ArrayList<String>();
        aliases.add("tpg");
        aliases.add("t");
        aliases.add("tp");
        aliases.add("tpx");
        
        autoCompletePossibilies = new ArrayList<String>();
        autoCompletePossibilies.add("tp");
    }
    
    @Override
    public String getCommandName() {
        
        return "tpg";
    }
    
    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        
        return "gu.commands.tpg";
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List getCommandAliases() {
        
        return aliases;
    }
    
    @Override
    public void processCommand(ICommandSender icommandsender, String[] command) {
        
        World world = icommandsender.getEntityWorld();
        
        if (!world.isRemote) {
            
            if (command.length >= 3) {
                
                try {
                    
                    double x = Double.parseDouble(command[0]);
                    
                    try {
                        
                        double y = Double.parseDouble(command[1]);
                        
                        try {
                            
                            double z = Double.parseDouble(command[2]);
                            
//                            UtilBlock.breakBlockNoDrop(world, x, y, z);
//                            UtilEntity.sendChatToPlayer(icommandsender, "Teleported: " + x + ", " + y + ", " + z);
                        } catch (Exception e) {
                            
                            UtilEntity.sendChatToPlayer(icommandsender, "Third param needs to be a numer");
                        }
                    } catch (Exception e) {
                        
                        UtilEntity.sendChatToPlayer(icommandsender, "Second param needs to be a numer");
                    }
                } catch (Exception e) {
                    
                    UtilEntity.sendChatToPlayer(icommandsender, "First param needs to be a numer");
                }
            } else {
                
                UtilEntity.sendChatToPlayer(icommandsender, "/breakBlock x y z");
            }
        }
        // // if(astring.length > 1 && astring[0].equals("killthemall"))
        // {
        // @SuppressWarnings("unchecked")
        // List<Entity> list = player.worldObj.loadedEntityList;
        // for(Entity e : list) {
        // if(e instanceof EntityPot) {
        // e.setDead();
        // }
        // if(e instanceof EntityLesserSpectre) {
        // e.setDead();
        // }
        // if(e instanceof EntityWardrobe) {
        // e.setDead();
        // }
        // if(e instanceof EntityWisp) {
        // e.setDead();
        // }
        // }
        // icommandsender.sendChatToPlayer(ChatMessageComponent.createFromText("Killed all things!"));
        // }
        /*
         * EntityPot ghost = new EntityPot(player.worldObj);
         * ghost.setPositionAndRotation(player.posX, player.posY, player.posZ,
         * player.rotationYaw, player.rotationPitch);
         * player.worldObj.spawnEntityInWorld(ghost);
         * icommandsender.sendChatToPlayer("Spawned a Pot!");
         */
    }
    
    public int getRequiredPermissionLevel() {
        
        return 2;
    }
    
    // @Override
    // public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    // {
    //
    // return true;
    // }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
        
        return autoCompletePossibilies;
    }
}