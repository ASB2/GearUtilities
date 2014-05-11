package GU.blocks.containers.BlockCreativeMetadata;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.api.EnumSimulationType;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.DefaultPowerManager;
import GU.api.power.PowerNetObject.UtilPower;
import GU.blocks.containers.BlockMetadataContainerBase;
import GU.blocks.containers.TileBase;
import UC.Wait;
import UC.Wait.IWaitTrigger;
import UC.math.vector.Vector3i;

public class BlockCreativeMetadata extends BlockMetadataContainerBase {
    
    public BlockCreativeMetadata(Material material) {
        super(material);
        
        wrappers.put(0, new MetadataWrapper() {
            
            @Override
            public String getDisplayName() {
                
                return "Creative Crystal Energy";
            }
            
            @Override
            public TileEntity createNewTileEntity(World var1, int metadata) {
                
                return new TileCreativePower();
            }
        }.setIconNames(new String[] { "BlockCreativeMetadata0" }));
    }
    
    public static class TileCreativePower extends TileBase {
        
        Wait sendEnergyValidNodes;
        
        public TileCreativePower() {
            
            sendEnergyValidNodes = new Wait(new SendEnergyPacketWait(), 10, 0);
        }
        
        @Override
        public void updateEntity() {
            
            sendEnergyValidNodes.update();
        }
        
        private class SendEnergyPacketWait implements IWaitTrigger {
            
            @Override
            public void trigger(int id) {
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        IPowerManager manager = null;
                        
                        if (tile instanceof ICrystalPowerHandler) {
                            
                            manager = ((ICrystalPowerHandler) tile).getPowerManager();
                        }
                        else if (tile instanceof ITilePowerHandler) {
                            
                            manager = ((ITilePowerHandler) tile).getPowerManager();
                        }
                        else if (tile instanceof IBlockPowerHandler) {
                            
                            manager = ((IBlockPowerHandler) tile).getPowerManager(worldObj, xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                        }
                        
                        if (manager != null) {
                            
                            final int powerToMove = 1;
                            
                            if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
                                
                                UtilPower.addPower(manager, powerToMove, EnumSimulationType.LIGITIMATE);
                            }
                            else {
                                
                                UtilPower.removePower(manager, powerToMove, EnumSimulationType.LIGITIMATE);
                                
                            }
                        }
                    }
                }
            }
            
            @Override
            public boolean shouldTick(int id) {
                // TODO Auto-generated method stub
                return true;
            }
        }
    }
}
