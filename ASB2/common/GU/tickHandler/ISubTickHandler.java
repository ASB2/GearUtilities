package GU.tickHandler;

import java.util.EnumSet;

import cpw.mods.fml.common.TickType;

public interface ISubTickHandler {

    public void tickStart(EnumSet<TickType> type, Object... tickData);

    public void tickEnd(EnumSet<TickType> type, Object... tickData);
}
