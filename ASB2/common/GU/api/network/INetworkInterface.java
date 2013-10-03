package GU.api.network;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public interface INetworkInterface extends IConductor {

    TileEntity[] getAvaliableTileEntities(ForgeDirection direction);
}
