package GU.blocks.containers.BlockElectisEnergyCube;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElectisEnergyCube extends BlockContainerBase {
    
    public BlockElectisEnergyCube(Material material) {
        super(material);
        this.registerTile(TileElectisEnergyCube.class);
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, ItemBlockElectisEnergyCube.class, entry);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisEnergyCube.class, ElectisEnergyCubeRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisEnergyCubeRenderer.instance);
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Electis Energy Cube";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileElectisEnergyCube();
    }
}
