package GU.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;

public class UtilPower {

    public static int TICKSTOPOWER = 10;

    public static boolean transferPower(IPowerMisc powerSource,
            ForgeDirection direction, IPowerMisc powerSink) {

        ForgeDirection opposite = direction.getOpposite();

        int amountOfPower = powerSource.getPowerProvider().getPowerClass()
                .getPowerValue();

        if (powerSink.getPowerProvider() != null
                && powerSource.getPowerProvider() != null) {

            if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, false)) {

                if (powerSource.getPowerProvider().usePower(amountOfPower, opposite, true)) {

                    if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, false)) {

                        powerSource.getPowerProvider().usePower(amountOfPower, opposite, true);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean transferPower(IPowerMisc powerSource,
            ForgeDirection direction, IPowerMisc powerSink, float amountOfPower) {

        ForgeDirection opposite = direction.getOpposite();

        if (powerSink.getPowerProvider() != null && powerSource.getPowerProvider() != null) {

            if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, false)) {

                if (powerSource.getPowerProvider().usePower(amountOfPower, opposite, false)) {

                    if (powerSink.getPowerProvider().gainPower(amountOfPower, direction, true)) {

                        powerSource.getPowerProvider().usePower(amountOfPower, opposite, true);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static IPowerMisc findConductor(ForgeDirection side, World worldObj, int distance, int x, int y, int z) {

        for (int i = 0; i <= distance; i++) {

            if (i > 0) {

                if (!(side.offsetX == 0)) {

                    if (side.offsetX > 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x + i, y,
                                z);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }

                    if (side.offsetX < 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x - i, y,
                                z);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }
                }

                if (!(side.offsetY == 0)) {

                    if (side.offsetY > 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x, y + i,
                                z);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }

                    if (side.offsetY < 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x, y - i,
                                z);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }
                }

                if (!(side.offsetZ == 0)) {

                    if (side.offsetZ > 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x, y, z
                                + i);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }

                    if (side.offsetZ < 0) {

                        TileEntity tile = worldObj.getBlockTileEntity(x, y, z
                                - i);

                        if (tile != null) {

                            if (tile instanceof IPowerMisc
                                    && ((IPowerMisc) tile).getPowerProvider() != null) {

                                return (IPowerMisc) tile;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
