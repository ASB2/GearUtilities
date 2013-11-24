package GU.blocks.containers.BlockEnergyCube;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import ASB2.vector.Vector3;
import GU.api.MiscHelpers;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;
import GU.api.network.UniversalConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerHelper;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileEnergyCube extends TileBase implements IPowerMisc, INetworkInterface {

    INetwork network;

    public TileEnergyCube() {

        this.powerProvider = new PowerProvider(4000, PowerClass.LOW, State.OTHER);
        this.waitTimer = new Wait(10, this, 0);
        network = new UniversalConduitNetwork();
    }

    @Override
    public void updateEntity() {    

        waitTimer.update();
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(isSneaking) {

            world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.getOrientation(side).ordinal(), 3);
        }
        else {

            world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ForgeDirection.getOrientation(side).getOpposite().ordinal(), 3);
        }
    }

    @Override
    public void trigger(int id) {

        if(this.getNetwork() != null) {

            MiscHelpers.addConductorsAround(this, worldObj, this.getNetwork());        
        }
        
        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

        if(tile != null && tile instanceof IPowerMisc) {

            PowerHelper.moveEnergy(this.getPowerProvider(), ((IPowerMisc)tile).getPowerProvider(), this.getOrientation(), this.getOrientation().getOpposite(), true);
        }
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }

    @Override
    public boolean setNetwork(INetwork network) {

        this.network = network;
        
        if(network != null) {            

            if(!network.getConductors().contains(new Vector3(this))) {

                network.addConductor(new Vector3(this));
            }
            
            if(!network.getGUUPowerInterfaces().contains(new Vector3(this))) {

                network.addGUUPowerInterface(new Vector3(this));
            }
        }
        return true;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public TileEntity[] getAvaliableTileEntities(ForgeDirection direction) {

        return new TileEntity[]{this};
    }
}
