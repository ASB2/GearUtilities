package GU.api.wait;

import java.util.EnumSet;

import cpw.mods.fml.common.TickType;
import GU.tickHandler.*;
import GU.*;

public class Wait implements ISubTickHandler {

    long timeKeeper;
    long timeToWait;
    int id;
    IWaitTrigger thingToTrigger;

    public Wait(long timeToWait, IWaitTrigger thingToTrigger, int id) {

        GearUtilities.proxy.guTickHandler.thingsToTick.add(this);
        this.timeToWait = timeToWait;
        this.thingToTrigger = thingToTrigger;
        this.id = id;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        if(thingToTrigger != null) {

            if(thingToTrigger.shouldTick(id)) {

                timeKeeper++;
            }            
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        if(timeKeeper >= timeToWait) {

            if(thingToTrigger != null) {

                thingToTrigger.trigger(id); 
                timeKeeper = 0;
            }            
        }
    }
}
