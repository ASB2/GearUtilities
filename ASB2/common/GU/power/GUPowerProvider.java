package GU.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.utils.UtilDirection;
import GU.utils.UtilPower;

public class GUPowerProvider extends PowerProvider {

    public GUPowerProvider(int maximumPower, PowerClass powerClass, State state) {
        super(maximumPower, powerClass);

        this.currentState = state;
    }

    public void movePower(World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x,y,z);

        for(ForgeDirection direction: ForgeDirection.VALID_DIRECTIONS) {

            int[] coords = UtilDirection.translateDirectionToCoords(direction, tile);

            if(world.blockExists(coords[0], coords[1], coords[2])) {

                TileEntity tileToAffect = UtilDirection.translateDirectionToTile(tile, tile.worldObj, direction);

                if(tileToAffect != null) {

                    if(tileToAffect instanceof IPowerMisc) {

                        IPowerMisc tileToAffectCasted = ((IPowerMisc)tileToAffect);

                        if(tileToAffectCasted.getPowerProvider() != null) {

                            switch(this.getCurrentState()) {

                                case SINK: {

                                    if(tileToAffectCasted.getPowerProvider().getCurrentState() == State.SOURCE) {

                                        UtilPower.transferPower(tileToAffectCasted, (IPowerMisc)tile);
                                    }
                                }
                                break;

                                case SOURCE: {

                                    if(tileToAffectCasted.getPowerProvider().getCurrentState() == State.SINK) {

                                        UtilPower.transferPower((IPowerMisc)tile, tileToAffectCasted);
                                    }
                                }
                                break;

                                case OTHER: break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public State getCurrentState() {

        if(currentState == null) {

            currentState = State.SINK;
        }
        return currentState;        
    }
}
