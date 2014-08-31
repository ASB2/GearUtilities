package GU.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;

public class BreakBlockCommand extends CommandBase {
    
    public List<String> aliases, autoCompletePossibilies;
    
    public BreakBlockCommand() {
        aliases = new ArrayList<String>();
        aliases.add("breakBlock");
        aliases.add("breakblock");
        aliases.add("BreakBlock");
        aliases.add("Breakblock");
        
        autoCompletePossibilies = new ArrayList<String>();
        autoCompletePossibilies.add("br");
    }
    
    @Override
    public String getCommandName() {
        
        return "breakBlock";
    }
    
    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        
        return "gu.commands.breakBlock";
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
                    
                    int x = Integer.decode(command[0]);
                    
                    try {
                        
                        int y = Integer.decode(command[1]);
                        
                        try {
                            
                            int z = Integer.decode(command[2]);
                            
                            UtilBlock.breakBlockNoDrop(world, x, y, z);
                            UtilEntity.sendChatToPlayer(icommandsender, "Block broken at " + x + ", " + y + ", " + z);
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