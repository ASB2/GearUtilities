package GU.blocks.containers.BlockSender;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.api.power.PowerClass;
import GU.api.power.State;
import GU.power.GUPowerProvider;

public class SenderPowerProvider extends GUPowerProvider {

    World world;
    Vector3 coords;

    public SenderPowerProvider(World world, Vector3 coords, PowerClass powerClass, State state) {
        super(powerClass, state);

        this.world = world;
        this.coords = coords;
    }

    public boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse) {

        if(world != null) {
            if(direction.getOpposite() == ForgeDirection.getOrientation(coords.getBlockMetadata(world))) {

                return false;
            }
        }
        return super.gainPower(PowerGained, direction, doUse);
    }
}
