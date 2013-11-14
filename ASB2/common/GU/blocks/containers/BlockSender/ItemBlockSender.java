package GU.blocks.containers.BlockSender;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.GUItemBlock;

public class ItemBlockSender extends GUItemBlock {

    public ItemBlockSender(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {

        switch(UtilItemStack.getNBTTagInt(itemStack, "mode")) {

            case 0:
                info.add("Un-attuned");
                break;

            case 1:
                info.add("Item Mover");
                break;

            case 2:
                info.add("Fluid Mover");
                break;

            case 3:
                info.add("Grinder");
                break;

            case 4:
                info.add("Block Placer");
                break;

            case 5:
                info.add("Block Breaker");
                break;

            case 6:
                info.add("Smelting");
                break;

            case 7:
                info.add("Custom");
                break;
        }
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {

        boolean itWorked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof TileSender) {

            TileSender tank = (TileSender) tile;

            tank.setMode(UtilItemStack.getNBTTagInt(stack, "mode"));
        }

        return itWorked;
    }
}
