package GU.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.api.EnumSimulationType;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IBlockPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.api.power.PowerNetAbstract.ITilePowerHandler;
import GU.api.power.PowerNetObject.UtilPower;
import GU.items.ItemRenderers.ElectisCrystalShardRenderer;

public class ElectisShardWrapper extends GU.items.ItemMetadata.ItemMetadataWrapper {
    
    public ElectisShardWrapper(String ign) {
        super(ign);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void postInitRender() {
        
        this.setRenderer(ElectisCrystalShardRenderer.instance);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, net.minecraft.entity.player.EntityPlayer player, net.minecraft.world.World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        if (player.capabilities.isCreativeMode) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            IPowerManager powerHandler = null;
            
            if (tile != null) {
                
                if (tile instanceof ITilePowerHandler) {
                    
                    powerHandler = ((ITilePowerHandler) tile).getPowerManager();
                }
                if (tile instanceof ICrystalPowerHandler) {
                    
                    powerHandler = ((ICrystalPowerHandler) tile).getPowerManager();
                }
            } else {
                
                Block block = world.getBlock(x, y, z);
                
                if (block != null && block instanceof IBlockPowerHandler) {
                    
                    powerHandler = ((IBlockPowerHandler) block).getPowerManager(world, x, y, z);
                }
            }
            
            if (powerHandler != null) {
                
                int powerSaved = UtilItemStack.getNBTTagInt(itemStack, "inputPower");
                
                if (player.isSneaking()) {
                    
                    UtilPower.removePower(powerHandler, powerSaved, EnumSimulationType.FORCED);
                } else {
                    
                    UtilPower.addPower(powerHandler, powerSaved, EnumSimulationType.FORCED);
                }
                
                UtilEntity.sendChatToPlayer(player, "Power Stored: " + powerHandler.getStoredPower());
            }
        }
        return true;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        // if (false) {
        if (player.capabilities.isCreativeMode) {
            
            int powerSaved = UtilItemStack.getNBTTagInt(itemStack, "inputPower");
            
            if (player.isSneaking()) {
                
                powerSaved--;
            } else {
                
                powerSaved++;
            }
            UtilItemStack.setNBTTagInt(itemStack, "inputPower", Math.max(powerSaved, 0));
            UtilEntity.sendChatToPlayer(player, "Power To Move: " + Math.max(powerSaved, 0));
        }
        // }
        // else {
        //
        // NoiseManager.instance.generateNoiseImage();
        // }
        return super.onItemRightClick(itemStack, world, player);
    }
}