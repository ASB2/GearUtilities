package GU.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.network.IConductor;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;

public class MiscHelpers {

    public static void addConductorsAround(Vector3 tile, World world, INetwork network) {

        MiscHelpers.addConductorsAround(tile.intX(), tile.intY(), tile.intZ(), world, network);
    }
    
    public static void addConductorsAround(TileEntity tile, World world, INetwork network) {

        MiscHelpers.addConductorsAround(tile.xCoord, tile.yCoord, tile.zCoord, world, network);
    }

    public static void addConductorsAround(int x, int y, int z, World world, INetwork network) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(world, direction,  x,  y, z);

            if(tile != null && tile instanceof IConductor) {

                if(((IConductor)tile).getNetwork() != null) {

                    ((IConductor)tile).getNetwork().mergeNetworks(world, network.getAvaliableConductors());
                }
                else {

                    ((IConductor)tile).setNetwork(network);
                }               
            }
        }
    }
    
    public static void addNetworkInterfacesAround(Vector3 tile, World world, INetwork network) {

        MiscHelpers.addNetworkInterfacesAround(tile.intX(), tile.intY(), tile.intZ(), world, network);
    }
    
    public static void addNetworkInterfacesAround(TileEntity tile, World world, INetwork network) {

        MiscHelpers.addNetworkInterfacesAround(tile.xCoord, tile.yCoord, tile.zCoord, world, network);
    }

    public static void addNetworkInterfacesAround(int x, int y, int z, World world, INetwork network) {

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            TileEntity tile = UtilDirection.translateDirectionToTile(world, direction,  x,  y, z);

            if(tile != null && tile instanceof INetworkInterface) {

                if(((INetworkInterface)tile).getNetwork() != null) {

                    ((INetworkInterface)tile).getNetwork().mergeNetworks(world, network.getAvaliableConductors());
                }
                else {

                    ((INetworkInterface)tile).setNetwork(network);
                }               
            }
        }
    }
}
