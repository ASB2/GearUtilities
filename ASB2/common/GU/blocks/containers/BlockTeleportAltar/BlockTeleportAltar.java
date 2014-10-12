package GU.blocks.containers.BlockTeleportAltar;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.ItemRegistry;
import GU.blocks.containers.BlockContainerBase;
import GU.info.Variables;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleportAltar extends BlockContainerBase {
    
    public BlockTeleportAltar(Material material) {
        super(material);
        this.registerTile(TileTeleportAltar.class);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileTeleportAltar.class, TeleportAltarRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), TeleportAltarRenderer.instance);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        TileTeleportAltar teleporter = (TileTeleportAltar) world.getTileEntity(x, y, z);
        
        if (teleporter != null) {
            
            if (player.inventory.getCurrentItem() == null && !Variables.TELEPORTER_RESET_BREAK_BLOCK) {
                
                if (player.isSneaking()) {
                    
                    teleporter.clearCoords();
                    
                    if (!world.isRemote) {
                        
                        UtilEntity.sendChatToPlayer(player, "Coords Cleared");
                    }
                    return true;
                }
            }
            if (teleporter.isCoordsSet()) {
                
                teleporter.teleport(player);
                
                if (!world.isRemote) {
                    
                    UtilEntity.sendChatToPlayer(player, "Teleported to ");
                    UtilEntity.sendChatToPlayer(player, (Math.floor(player.posX) + .5) + ", " + Math.floor(player.posY) + ", " + (Math.floor(player.posZ) + .5));
                }
            } else if (!teleporter.isCoordsSet()) {
                
                ItemStack stack = player.inventory.getCurrentItem();
                
                if (stack != null && stack.getItem() == ItemRegistry.METADATA_ITEM) {
                    
                    if (stack.getItemDamage() == 4) {
                        
                        if (UtilItemStack.getNBTTagBoolean(stack, "coordsSet")) {
                            
                            teleporter.setCoords(UtilItemStack.getNBTTagDouble(stack, "x"), UtilItemStack.getNBTTagDouble(stack, "y"), UtilItemStack.getNBTTagDouble(stack, "z"), UtilItemStack.getNBTTagInt(stack, "dimensionID"));
                            
                            if (!world.isRemote) {
                                
                                UtilEntity.sendChatToPlayer(player, "Coords Set");
                                UtilEntity.sendChatToPlayer(player, (Math.floor(player.posX) + .5) + ", " + Math.floor(player.posY) + ", " + (Math.floor(player.posZ) + .5));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Teleport Altar";
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        
        return new TileTeleportAltar();
    }
}
