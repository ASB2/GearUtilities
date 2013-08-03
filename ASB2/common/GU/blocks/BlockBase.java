package GU.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.info.Reference;

public class BlockBase extends Block {

    public boolean useStandardRendering = true;
    public boolean useDefaultTexture = false;  
    Icon texture;
    String blockName = "";

    public BlockBase(int id, Material material) {
        super(id, material);


        GameRegistry.registerBlock(this, GUItemBlock.class, this.getUnlocalizedName());
        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);        
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(3.5f);
        setResistance(1.0F);
    }

    public boolean renderAsNormalBlock() {

        return useStandardRendering;
    }

    public boolean isOpaqueCube() {

        return useStandardRendering;
    }
    
    public boolean canCreatureSpawn() {

        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":GearBlock");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + blockName);
    }

    public void setBlockName(String texture) {
     
        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
    }
    
    public Icon getIcon(int side, int metadata)
    {
        if(useDefaultTexture || texture == null) 
            return this.blockIcon;

        return texture;
    }
}
