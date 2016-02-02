package GU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

public class PlayerRegistry {
    
    private static final Map<String, PlayerList> playerMap = new HashMap<String, PlayerList>();
    
    public static void init() {
        checkTag("colorfulTeleporter");
    	PlayerList list = playerMap.get("colorfulTeleporter".toLowerCase());
        list.add("Dr_DooManiC");
        list.add("Dark_Raven87");
        list.add("ASB2");
    }
    
    public static boolean isPlayerValid(String tag, String name) {
        
        checkTag(tag);
        PlayerList list = playerMap.get(tag.toLowerCase());
        return list.contains(name);
    }
    
    public static void checkTag(String tagName)
    {
        if(!playerMap.containsKey(tagName.toLowerCase()))
        {
        	playerMap.put(tagName.toLowerCase(), new PlayerList());
        }
    }
    
    public static class PlayerList
    {
        List<String> list = new ArrayList<String>();
        
        public void add(String par1)
        {
            if(!contains(par1) && !Strings.isNullOrEmpty(par1))
            {
                list.add(par1);
            }
        }
        
        public boolean contains(String par1)
        {
            if(Strings.isNullOrEmpty(par1))
            {
                return false;
            }
            for(int i = 0;i<list.size();i++)
            {
                String name = list.get(i);
                if(!Strings.isNullOrEmpty(name) && name.equalsIgnoreCase(par1))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
