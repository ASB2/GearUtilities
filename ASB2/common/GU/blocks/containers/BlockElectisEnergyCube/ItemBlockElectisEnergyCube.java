package GU.blocks.containers.BlockElectisEnergyCube;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.GUItemBlock;
import GU.api.power.PowerNetAbstract.IItemPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerManager;

public class ItemBlockElectisEnergyCube extends GUItemBlock implements IItemPowerHandler {
    
    public ItemBlockElectisEnergyCube(Block block) {
        super(block);
        // TODO Auto-generated constructor stub
    }
    
    public void applyBlockStuff(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        
        if (block.getPlaceItemStackMetadata()) {
            
            world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 3);
        }
        
        // if (UtilItemStack.getNBTTagInt(stack, "energyStored")) {
        //
        // }
    }
    
    @Override
    public IPowerManager getPowerManager(ItemStack item) {
        // TODO Auto-generated method stub
        return null;
    }
}
