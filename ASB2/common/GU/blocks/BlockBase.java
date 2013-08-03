package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import GU.GearUtilities;
import GU.info.Reference;

public class BlockBase extends Block {

    public boolean useStandardRendering = true;

    public BlockBase(int id, Material material) {
        super(id, material);

        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(3.5f);
        setResistance(1.0F);
    }

    public boolean canCreatureSpawn() {

        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":GearBlock");
    }
}
