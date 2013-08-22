package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.blocks.containers.*;

public class BlockAdvancedPotionBrewery extends ContainerBase {

    public BlockAdvancedPotionBrewery(int id, Material material) {
        super(id, material);
    this.registerTile(TileAdvancedPotionBrewery.class);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        
        return new TileAdvancedPotionBrewery();
    }
}
