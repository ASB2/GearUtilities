package GU;

import net.minecraftforge.client.event.TextureStitchEvent;
import GU.info.Reference;
import GU.render.EnumInputIcon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventListener {
    
    @SubscribeEvent
    public void textureHook(TextureStitchEvent.Pre event) {
        
        if (event.map.getTextureType() == 0) {
            
            EnumInputIcon.INPUT.setStateIcon(event.map.registerIcon(Reference.MOD_ID + ":sides/BlockInput"));
            EnumInputIcon.OUTPUT.setStateIcon(event.map.registerIcon(Reference.MOD_ID + ":sides/BlockOutput"));
            EnumInputIcon.BOTH.setStateIcon(event.map.registerIcon(Reference.MOD_ID + ":sides/BlockBoth"));
            EnumInputIcon.NONE.setStateIcon(event.map.registerIcon(Reference.MOD_ID + ":sides/BlockNone"));
        }
    }
}
