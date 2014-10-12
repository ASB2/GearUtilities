package GU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRegistry {
    
    private static final Map<String, List<String>> playerMap = new HashMap<String, List<String>>();
    
    public static void init() {
        
        List<String> list = new ArrayList<String>();
        list.add("Dr_DooManiC".toLowerCase());
        list.add("Dark_Raven87".toLowerCase());
        playerMap.put("colorfulTeleporter", list);
    }
    
    public static boolean isPlayerValid(String tag, String name) {
        
        List<String> list = playerMap.get(tag.toLowerCase());
        
        if (list != null) {
            
            return list.contains(name);
        }
        return false;
    }
}
