package GU.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.power.UtilPower;

public class GUPowerProvider extends PowerProvider {

    public GUPowerProvider(PowerClass powerClass, State state) {
        super(powerClass.getSuggestedMax(), powerClass);

        this.currentState = state;
    }

    public void movePower(World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            int[] coords = UtilDirection.translateDirectionToCoords(direction, tile);

            if (world.blockExists(coords[0], coords[1], coords[2])) {

                TileEntity tileToAffect = UtilDirection.translateDirectionToTile(tile, tile.worldObj,
                                direction);

                if (tileToAffect != null) {

                    if (tileToAffect instanceof IPowerMisc) {

                        IPowerMisc tileToAffectCasted = ((IPowerMisc) tileToAffect);

                        if (tileToAffectCasted.getPowerProvider() != null) {

                            switch (this.getCurrentState(direction)) {

                                case SINK: {

                                    if (tileToAffectCasted.getPowerProvider().getCurrentState() == State.SOURCE) {

                                        UtilPower.transferPower(tileToAffectCasted, direction, (IPowerMisc) tile);
                                    }
                                }
                                    break;

                                case SOURCE: {

                                    if (tileToAffectCasted.getPowerProvider().getCurrentState() == State.SINK) {

                                        UtilPower.transferPower((IPowerMisc) tile, direction, tileToAffectCasted);
                                    }
                                }
                                    break;

                                case OTHER:
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void movePower(World world, int x, int y, int z, boolean isExporting) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            int[] coords = UtilDirection.translateDirectionToCoords(direction, tile);

            if (world.blockExists(coords[0], coords[1], coords[2])) {

                TileEntity tileToAffect = UtilDirection.translateDirectionToTile(tile, world, direction);

                if (tileToAffect != null) {

                    if (tileToAffect instanceof IPowerMisc) {

                        IPowerMisc tileToAffectCasted = ((IPowerMisc) tileToAffect);

                        if (tileToAffectCasted.getPowerProvider() != null) {

                            if (isExporting) {

                                UtilPower.transferPower((IPowerMisc) tile, direction, tileToAffectCasted);
                            } else {

                                UtilPower.transferPower(tileToAffectCasted, direction, (IPowerMisc) tile);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public State getCurrentState() {

        if (currentState == null) {

            currentState = State.SINK;
        }
        return currentState;
    }
}
