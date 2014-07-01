package GU;

import java.util.ArrayList;
import java.util.List;

import UC.VariableIterator;
import net.minecraftforge.client.event.TextureStitchEvent;
import GU.info.MiscIcons;
import GU.info.Reference;
import GU.render.EnumInputIcon;
import GU.render.noise.NoiseManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventListener {
    
    public static EventListener instance = new EventListener();
    
    public List<VariableIterator> VARIABLES = new ArrayList<VariableIterator>();
    
    @SubscribeEvent
    public void logicUpdate(WorldTickEvent event) {
        
        for (VariableIterator var : VARIABLES) {
            
            var.update();
        }
    }
    
    @SubscribeEvent
    public void textureHook(TextureStitchEvent.Pre event) {
        
        if (event.map.getTextureType() == 0) {
            
            EnumInputIcon.INPUT.setStateIcon(event.map.registerIcon(Reference.MOD_ID.concat(":sides/BlockInput")));
            EnumInputIcon.OUTPUT.setStateIcon(event.map.registerIcon(Reference.MOD_ID.concat(":sides/BlockOutput")));
            EnumInputIcon.BOTH.setStateIcon(event.map.registerIcon(Reference.MOD_ID.concat(":sides/BlockBoth")));
            EnumInputIcon.NONE.setStateIcon(event.map.registerIcon(Reference.MOD_ID.concat(":sides/BlockNone")));
            
            for (MiscIcons icon : MiscIcons.values()) {
                
                icon.setIcon(event.map.registerIcon(Reference.MOD_ID.concat(":").concat(icon.getPath())));
            }
            
            event.map.setTextureEntry(NoiseManager.instance.blockNoiseIcon.getIconName(), NoiseManager.instance.blockNoiseIcon);
        }
        
        if (event.map.getTextureType() == 1) {
            
            event.map.setTextureEntry(NoiseManager.instance.blockNoiseIcon.getIconName(), NoiseManager.instance.itemNoiseIcon);
        }
    }
}
