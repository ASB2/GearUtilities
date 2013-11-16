package GU.blocks.containers.BlockDissolver;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import ASB2.utils.UtilInventory;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileFluidBase;
import GU.blocks.containers.BlockEnhancedBricks.TileEnhancedBricks;

public class TileDissolver extends TileFluidBase implements IPowerMisc {

    public TileDissolver() {

        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        waitTimer = new Wait(10, this, 0);
    }

    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        if(this.multiBlockCheck()) {

            int waterStored = 0;
            int lavaStored = 0;

            for(IFluidHandler tank : this.getMultiBlockFluidTanks()) {

                FluidTankInfo[] infoArray = tank.getTankInfo(ForgeDirection.DOWN);

                if(infoArray != null) {

                    for(FluidTankInfo info : infoArray) {

                        if(info != null && info.fluid != null) {

                            if(info.fluid.getFluid() == FluidRegistry.WATER) {

                                waterStored++;
                            }
                            else if(info.fluid.getFluid() == FluidRegistry.LAVA) {

                                lavaStored++;
                            }
                        }
                    }
                }
            }

            if(!(lavaStored == 0 || waterStored == 0)) {

                if(waterStored > lavaStored) {

                    for(IInventory tank : this.getMultiBlockInventorys()) {

                        if(UtilInventory.addItemStackToInventory(tank, new ItemStack(Block.stone, 16), true)) {

                            return;
                        }
                    }
                }
                else if(waterStored < lavaStored) {

                    for(IFluidHandler tank : this.getMultiBlockFluidTanks()) {

                        if(UtilFluid.removeFluidFromTank(tank, ForgeDirection.DOWN, new FluidStack(FluidRegistry.LAVA, 1000), true)) {

                            for(IInventory inventory : this.getMultiBlockInventorys()) {

                                if(UtilInventory.addItemStackToInventory(inventory, new ItemStack(Block.obsidian, 1), true)) {

                                    return;
                                }
                            }
                        }
                    }
                }
                else if(waterStored == lavaStored) {

                    for(IInventory tank : this.getMultiBlockInventorys()) {

                        if(UtilInventory.addItemStackToInventory(tank, new ItemStack(Block.cobblestone, 16), true)) {

                            return;
                        }
                    }
                }
            }
        }
    }

    public boolean multiBlockCheck() {

        boolean layer1Check = false;
        boolean layer2Check = false;
        boolean layer3Check = false;
        boolean layer4Check = false;

        TileEntity layer1 = worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord + 1);

        if(layer1 != null && layer1 instanceof TileEnhancedBricks) {

            layer1 = worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord + 1);

            if(layer1 != null && layer1 instanceof TileEnhancedBricks) {

                layer1 = worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord - 1);

                if(layer1 != null && layer1 instanceof TileEnhancedBricks) {

                    layer1 = worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord - 1);

                    if(layer1 != null && layer1 instanceof TileEnhancedBricks) {

                        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                            if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                                TileEntity furanceTile = UtilDirection.translateDirectionToTile(this, worldObj, direction);

                                if(furanceTile != null && furanceTile instanceof TileEnhancedBricks) {

                                    layer1Check = true;
                                }
                                else {

                                    layer1Check = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        TileEntity layer2 = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 1, zCoord + 1);

        if(layer2 != null && layer2 instanceof IFluidHandler) {

            layer2 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 1, zCoord + 1);

            if(layer2 != null && layer2 instanceof IFluidHandler) {

                layer2 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 1, zCoord - 1);

                if(layer2 != null && layer2 instanceof IFluidHandler) {

                    layer2 = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 1, zCoord - 1);

                    if(layer2 != null && layer2 instanceof IFluidHandler) {

                        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                            if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                                TileEntity chestTile = UtilDirection.translateDirectionToTile(worldObj, direction, xCoord, yCoord + 1, zCoord);

                                if(chestTile != null && chestTile instanceof IInventory) {

                                    layer2Check = true;
                                }
                                else {

                                    layer2Check = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        TileEntity layer3 = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 2, zCoord + 1);

        if(layer3 != null && layer3 instanceof TileEnhancedBricks) {

            layer3 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 2, zCoord + 1);

            if(layer3 != null && layer3 instanceof TileEnhancedBricks) {

                layer3 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 2, zCoord - 1);

                if(layer3 != null && layer3 instanceof TileEnhancedBricks) {

                    layer3 = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 2, zCoord - 1);

                    if(layer3 != null && layer3 instanceof TileEnhancedBricks) {

                        layer3 = worldObj.getBlockTileEntity(xCoord, yCoord + 2, zCoord);

                        if(layer3 != null && layer3 instanceof IPowerMisc) {

                            if(((IPowerMisc) layer3).getPowerProvider() != null) {

                                if(((IPowerMisc) layer3).getPowerProvider().getState() == State.SOURCE || ((IPowerMisc) layer3).getPowerProvider().getState() == State.OTHER) {

                                    layer3Check = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        TileEntity layer4 = worldObj.getBlockTileEntity(xCoord, yCoord + 3, zCoord);

        if(layer4 != null && layer4 instanceof TileEnhancedBricks) {

            if(layer4 != null && layer4 instanceof TileEnhancedBricks) {

                layer4 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 3, zCoord + 1);

                if(layer4 != null && layer4 instanceof TileEnhancedBricks) {

                    layer4 = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 3, zCoord - 1);

                    if(layer4 != null && layer4 instanceof TileEnhancedBricks) {

                        layer4 = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 3, zCoord - 1);

                        if(layer4 != null && layer4 instanceof TileEnhancedBricks) {

                            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

                                if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                                    TileEntity fourthLevel = UtilDirection.translateDirectionToTile(worldObj, direction, xCoord, yCoord + 3, zCoord);

                                    if(fourthLevel != null && fourthLevel instanceof TileEnhancedBricks) {

                                        layer4Check = true;
                                    }
                                    else {

                                        layer4Check = false;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        return layer1Check && layer2Check && layer3Check && layer4Check;
    }

    public ArrayList<IFluidHandler> getMultiBlockFluidTanks() {

        ArrayList<IFluidHandler> fluidArray = new ArrayList<IFluidHandler>();

        TileEntity tile = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 1, zCoord + 1);

        if(tile != null && tile instanceof IFluidHandler) {

            fluidArray.add((IFluidHandler) tile);
            tile = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 1, zCoord + 1);

            if(tile != null && tile instanceof IFluidHandler) {

                fluidArray.add((IFluidHandler) tile);
                tile = worldObj.getBlockTileEntity(xCoord - 1, yCoord + 1, zCoord - 1);

                if(tile != null && tile instanceof IFluidHandler) {

                    fluidArray.add((IFluidHandler) tile);
                    tile = worldObj.getBlockTileEntity(xCoord + 1, yCoord + 1, zCoord - 1);

                    if(tile != null && tile instanceof IFluidHandler) {

                        fluidArray.add((IFluidHandler) tile);
                    }
                }
            }
        }
        return fluidArray;
    }

    public ArrayList<IInventory> getMultiBlockInventorys() {

        ArrayList<IInventory> itemArray = new ArrayList<IInventory>();

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            if(direction != ForgeDirection.UP && direction != ForgeDirection.DOWN) {

                TileEntity chestTile = UtilDirection.translateDirectionToTile(worldObj, direction, xCoord, yCoord + 1, zCoord);

                if(chestTile != null && chestTile instanceof IInventory) {

                    itemArray.add((IInventory) chestTile);
                }
            }
        }
        return itemArray;
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
