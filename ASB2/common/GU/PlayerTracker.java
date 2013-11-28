package GU;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker {

    ArrayList<String> playerList = new ArrayList<String>();
    
    PlayerTracker() {
    
        playerList.add("ASB2");
    }
    
    @Override
    public void onPlayerLogin(EntityPlayer player) {
//        
//        switch(player.username) {
//            
//            case "ASB2": {
//                
//                player.addChatMessage("Welcome Master");
//            }
//        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
}
