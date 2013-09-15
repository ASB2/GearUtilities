package GU.blocks.containers.BlockPowerTest;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilDirection;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerHelper;
import GU.api.power.PowerProvider;
import GU.blocks.containers.TileBase;

public class TilePowerTest extends TileBase implements IPowerMisc {

    public TilePowerTest() {

        this.powerProvider = new PowerProvider(PowerClass.LOW);
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            this.getPowerProvider().setPowerStored(this.getPowerProvider().getPowerMax());         
        }
        else {
            
            this.getPowerProvider().setPowerStored(0);   
        }
        
        this.movePower(worldObj, xCoord, yCoord, zCoord, !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord));
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

                                PowerHelper.moveEnergy((IPowerMisc) tile, tileToAffectCasted, direction, true);
                            } 
                            else {

                                PowerHelper.moveEnergy(tileToAffectCasted, (IPowerMisc)tile, direction, true);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }
}
