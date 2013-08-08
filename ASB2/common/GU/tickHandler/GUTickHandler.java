package GU.tickHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import GU.info.Reference;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import GU.api.power.*;

public class GUTickHandler implements ITickHandler {

    public List<ISubTickHandler> thingsToTick = new ArrayList<ISubTickHandler>();

    public GUTickHandler() {

        thingsToTick.add(PowerManager.getInstance());
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        for(ISubTickHandler trigger: thingsToTick) {

            if(trigger != null) {

                trigger.tickStart(type, tickData);
            }
            else {

                thingsToTick.remove(trigger);           
            }
        }        
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        for(ISubTickHandler trigger: thingsToTick) {

            if(trigger != null) {

                trigger.tickEnd(type, tickData);
            }
            else {

                thingsToTick.remove(trigger);           
            }
        } 
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return Reference.NAME + " Tick Handler";
    }

}
