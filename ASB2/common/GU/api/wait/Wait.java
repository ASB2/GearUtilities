package GU.api.wait;

import java.util.EnumSet;

import cpw.mods.fml.common.TickType;
import GU.tickHandler.*;
import GU.*;

public class Wait implements ISubTickHandler {

    long timeKeeper;
    long timeToWait;
    IWaitTrigger thingToTrigger;

    public Wait(long timeToWait, IWaitTrigger thingToTrigger) {

        GearUtilities.proxy.guTickHandler.thingsToTick.add(this);
        this.timeToWait = timeToWait;
        this.thingToTrigger = thingToTrigger;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        if(thingToTrigger != null) {

            if(thingToTrigger.shouldTick()) {

                timeKeeper++;
            }            
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        if(timeKeeper >= timeToWait) {

            if(thingToTrigger != null) {

                thingToTrigger.trigger(); 
                timeKeeper = 0;
            }            
        }
    }
}
