package GU.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;

public class UtilPower {

    public static int TICKSTOPOWER = 10;

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
