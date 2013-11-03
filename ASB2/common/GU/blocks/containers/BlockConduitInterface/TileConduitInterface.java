package GU.blocks.containers.BlockConduitInterface;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import ASB2.vector.Vector3;
import GU.api.MiscHelpers;
import GU.api.network.INetwork;
import GU.api.network.INetworkInterface;
import GU.api.network.UniversalConduitNetwork;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerHelper;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileConduitInterface extends TileBase implements INetworkInterface {

    INetwork network;
    boolean[] importing = new boolean[7];

    public TileConduitInterface() {

        this.waitTimer = new Wait(20, this, 0);
        this.setNetwork(new UniversalConduitNetwork());
    }

    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(!isSneaking) {

            if(importing[side]) {

                importing[side] = false;

                if(this.getNetwork() != null) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.getOrientation(side));

                    if(tile != null) {

                        if(tile instanceof IInventory) {

                            this.getNetwork().removeItemInterface(new Vector3(tile));
                        }

                        if(tile instanceof IFluidHandler) {

                            this.getNetwork().removeFluidInterface(new Vector3(tile));
                        }

                        if(tile instanceof IPowerMisc) {

                            this.getNetwork().removeGUUPowerInterface(new Vector3(tile));
                        }
                    }
                }
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
        else {

            side = ForgeDirection.getOrientation(side).getOpposite().ordinal();

            if(importing[side]) {

                importing[side] = false;

                if(this.getNetwork() != null) {

                    TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, ForgeDirection.getOrientation(side));

                    if(tile != null) {

                        if(tile instanceof IInventory) {

                            this.getNetwork().removeItemInterface(new Vector3(tile));
                        }

                        if(tile instanceof IFluidHandler) {

                            this.getNetwork().removeFluidInterface(new Vector3(tile));
                        }

                        if(tile instanceof IPowerMisc) {

                            this.getNetwork().removeGUUPowerInterface(new Vector3(tile));
                        }
                    }
                }
                return;
            }
            else {

                importing[side] = true;
                return;
            }
        }
    }

    @Override
    public void trigger(int id) {

        updateClients();

        if(this.getNetwork() != null) {

            MiscHelpers.addConductorsAround(this, worldObj, this.getNetwork());

            if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                for(ForgeDirection blockDirection : ForgeDirection.VALID_DIRECTIONS) {

                    TileEntity destinationTile = UtilDirection.translateDirectionToTile(this, worldObj, blockDirection);

                    if(destinationTile != null) {

                        if(importing[blockDirection.ordinal()]) {

                            if(destinationTile instanceof IInventory) {

                                this.getNetwork().addItemInterface(new Vector3(this));
                            }

                            if(destinationTile instanceof IFluidHandler) {

                                this.getNetwork().addFluidInterface(new Vector3(this));
                            }

                            if(destinationTile instanceof IPowerMisc) {

                                this.getNetwork().addGUUPowerInterface(new Vector3(this));
                            }
                        }
                        else {

                            if(destinationTile instanceof IInventory) {

                                for(Vector3 vector : this.getNetwork().getItemInterfaces()) {

                                    INetworkInterface interfaceTesting = (INetworkInterface) vector.getTileEntity(worldObj);

                                    for(ForgeDirection interfaceDirections : ForgeDirection.VALID_DIRECTIONS) {

                                        TileEntity[] avaliableTiles = interfaceTesting.getAvaliableTileEntities(interfaceDirections);

                                        for(TileEntity sourceTile : avaliableTiles) {

                                            if(sourceTile != null) {

                                                if(sourceTile instanceof IInventory) {

                                                    if(sourceTile instanceof ISidedInventory) {

                                                        if(destinationTile instanceof ISidedInventory) {

                                                            UtilInventory.moveEntireISidedInventory((ISidedInventory) destinationTile, blockDirection, interfaceDirections, (ISidedInventory) destinationTile);
                                                        }
                                                        else {

                                                            UtilInventory.moveEntireISidedInventory((IInventory) destinationTile, interfaceDirections, (ISidedInventory) destinationTile);
                                                        }
                                                    }
                                                    else {

                                                        if(destinationTile instanceof ISidedInventory) {

                                                            UtilInventory.moveEntireISidedInventory((ISidedInventory) destinationTile, blockDirection, (IInventory) destinationTile);
                                                        }
                                                        else {

                                                            UtilInventory.moveEntireInventory((IInventory) destinationTile, (IInventory) destinationTile);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if(destinationTile instanceof IFluidHandler) {

                                for(Vector3 vector : this.getNetwork().getFluidInterfaces()) {

                                    INetworkInterface interfaceTesting = (INetworkInterface) vector.getTileEntity(worldObj);

                                    for(ForgeDirection interfaceDirections : ForgeDirection.VALID_DIRECTIONS) {

                                        TileEntity[] avaliableTiles = interfaceTesting.getAvaliableTileEntities(interfaceDirections);

                                        for(TileEntity sourceTile : avaliableTiles) {

                                            if(sourceTile != null) {

                                                if(sourceTile instanceof IFluidHandler) {

                                                    UtilFluid.moveFluid((IFluidHandler) sourceTile, blockDirection, (IFluidHandler) destinationTile, interfaceDirections, true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if(destinationTile instanceof IPowerMisc) {

                                for(Vector3 vector : this.getNetwork().getGUUPowerInterfaces()) {

                                    INetworkInterface interfaceTesting = (INetworkInterface) vector.getTileEntity(worldObj);

                                    for(ForgeDirection interfaceDirections : ForgeDirection.VALID_DIRECTIONS) {

                                        TileEntity[] avaliableTiles = interfaceTesting.getAvaliableTileEntities(interfaceDirections);

                                        for(TileEntity sourceTile : avaliableTiles) {

                                            if(sourceTile != null) {

                                                if(sourceTile instanceof IPowerMisc) {

                                                    PowerHelper.moveEnergy(((IPowerMisc)sourceTile).getPowerProvider(), ((IPowerMisc)destinationTile).getPowerProvider(), blockDirection, interfaceDirections, true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean setNetwork(INetwork network) {

        this.network = network;
        

        if(network != null) {            
            
            if(!network.getConductors().contains(new Vector3(this))) {
                
                network.addConductor(new Vector3(this));
            }
        }
        return true;
    }

    @Override
    public INetwork getNetwork() {

        return network;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            importing[i] = tag.getBoolean("importing " + i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        for(int i = 0; i < importing.length; i++) {

            tag.setBoolean("importing " + i, importing[i]);
        }
    }

    @Override
    public TileEntity[] getAvaliableTileEntities(ForgeDirection direction) {

        TileEntity[] tileList = new TileEntity[7];

        if(this.importing[direction.ordinal()]) {

            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

            if(tile != null && (tile instanceof IInventory || tile instanceof IFluidHandler || tile instanceof IPowerMisc)) {

                tileList[direction.ordinal()] = tile;
            }
        }
        return tileList;
    }
}
