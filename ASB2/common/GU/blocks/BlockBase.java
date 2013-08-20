package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBase extends Block {

    public boolean useStandardRendering = true;
    public boolean useDefaultTexture = false;
    Icon texture;
    public String blockName = "";

    public BlockBase(int id, Material material) {
        super(id, material);

        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(1.5f);
        setResistance(8.0F);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {

        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return useStandardRendering;
    }

    @Override
    public boolean isOpaqueCube() {

        return useStandardRendering;
    }

    @Override
    public int getRenderType() {

        if (!useStandardRendering)
            return -1;

        return 0;
    }

    public boolean canCreatureSpawn() {

        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit,
            float hitX, float hitY, float hitZ, int metaData) {

        return sideHit;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":GearBlock");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + blockName);
    }

    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, GUItemBlock.class,
                this.getUnlocalizedName());
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (useDefaultTexture || texture == null)
            return this.blockIcon;

        return texture;
    }
}
