package GU;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import GU.info.MiscIcons;
import GU.info.Reference;
import GU.render.EnumInputIcon;
import GU.render.noise.NoiseManager;
import UC.VariableIterator;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    @SideOnly(Side.CLIENT)
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
    
    @SubscribeEvent
    public void noAttack(LivingSetAttackTargetEvent event) {
        
        if (event.target instanceof EntityPlayer) {
            
            ItemStack stack = ((EntityPlayer) event.target).inventory.getCurrentItem();
            
            if (stack != null && stack.getItem() == ItemRegistry.METADATA_ITEM && stack.getItemDamage() == 0) {
                
                event.setResult(Result.DENY);
            }
        }
    }
    
    @SubscribeEvent
    public void noDrops(HarvestDropsEvent event) {
        
    }
}
