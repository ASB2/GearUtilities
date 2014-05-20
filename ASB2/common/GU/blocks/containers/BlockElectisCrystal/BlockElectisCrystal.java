package GU.blocks.containers.BlockElectisCrystal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilEntity;
import ASB2.utils.UtilItemStack;
import GU.api.crystals.CrystalNetwork;
import GU.api.crystals.ICrystalNetworkPart;
import GU.api.crystals.ICrystalPowerHandler;
import GU.api.power.PowerNetAbstract.IPowerAttribute;
import GU.api.power.PowerNetAbstract.IPowerManager;
import GU.blocks.containers.BlockContainerBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockElectisCrystal extends BlockContainerBase {
    
    public final ItemStack TYPE1 = EnumElectisCrystalType.TYPE1.setCrystalType(new ItemStack(this));
    public final ItemStack TYPE2 = EnumElectisCrystalType.TYPE2.setCrystalType(new ItemStack(this));
    public final ItemStack TYPE3 = EnumElectisCrystalType.TYPE3.setCrystalType(new ItemStack(this));
    public final ItemStack TYPE4 = EnumElectisCrystalType.TYPE4.setCrystalType(new ItemStack(this));
    
    public BlockElectisCrystal(Material material) {
        super(material);
        this.registerTile(TileElectisCrystal.class);
        this.setLightOpacity(0);
        this.setLightLevel(.3f);
        this.setHardness(2);
    }
    
    @Override
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, ItemBlockElectisCrystal.class, entry);
    }
    
    @Override
    public void postInit() {
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileElectisCrystal.class, ElectisCrystalRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), ElectisCrystalRenderer.instance);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        if (!world.isRemote) {
            
            UtilEntity.sendChatToPlayer(player, "---------");
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            UtilEntity.sendChatToPlayer(player, "CrystalType: " + ((TileElectisCrystal) tile).getCrystalType());
            
            if (tile != null && tile instanceof TileElectisCrystal) {
                
                CrystalLogic logic = ((TileElectisCrystal) tile).crystalLogic;
                
                if (player.getHeldItem() == null) {
                    
                    if (tile instanceof ICrystalPowerHandler) {
                        
                        ICrystalPowerHandler mTile = (ICrystalPowerHandler) tile;
                        
                        IPowerManager manager = mTile.getPowerManager();
                        IPowerAttribute attribute = mTile.getPowerAttribute();
                        
                        if (manager != null) {
                            
                            UtilEntity.sendChatToPlayer(player, "Power Stored: " + manager.getStoredPower());
                            UtilEntity.sendChatToPlayer(player, "Max Power: " + manager.getMaxPower());
                            UtilEntity.sendChatToPlayer(player, "Power Difference: " + (manager.getMaxPower() - manager.getStoredPower()));
                        }
                        else {
                            
                            UtilEntity.sendChatToPlayer(player, "Power Manager: null");
                        }
                        
                        if (attribute != null) {
                            
                            UtilEntity.sendChatToPlayer(player, "Power Status: " + attribute.getPowerStatus());
                        }
                        else {
                            
                            UtilEntity.sendChatToPlayer(player, "Power Attribute: null");
                        }
                    }
                    
                    if (tile instanceof ICrystalNetworkPart) {
                        
                        ICrystalNetworkPart mTile = (ICrystalNetworkPart) tile;
                        
                        CrystalNetwork network = mTile.getNetwork();
                        
                        if (network != null) {
                            
                            UtilEntity.sendChatToPlayer(player, "Network Size: " + network.getNetworkSize());
                            UtilEntity.sendChatToPlayer(player, "Network Core: " + network.getCorePosition().toString());
                        }
                    }
                    UtilEntity.sendChatToPlayer(player, "---------");
                }
                
                if (logic != null) {
                    
                    return logic.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
                }
                return false;
            }
        }
        return false;
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        
    }
    
    @Override
    public int getRenderType() {
        
        return -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        
        return false;
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        
        return side;
    }
    
    @Override
    public String getBlockDisplayName(ItemStack stack) {
        
        return "Electis Crystal";
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        super.setBlockBoundsBasedOnState(world, x, y, z);
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null) {
            
            ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
            return world.isSideSolid(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction);
        }
        return false;
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side).getOpposite();
        return world.isSideSolid(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite());
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).getOpposite();
        
        if (!world.isRemote && !world.isSideSolid(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite())) {
            
            UtilBlock.breakBlock(world, x, y, z);
        }
    }
    
    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        // TODO Auto-generated method stub
        super.onBlockPlacedBy(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);
    }
    
    @Override
    public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_) {
        
        return false;
    }
    
    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        
        return false;
    }
    
    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        
        return false;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        
        if (entity instanceof EntityLivingBase) {
            
            ((EntityLivingBase) entity).attackEntityFrom(DamageSource.magic, 1.0F);
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        
        for (EnumElectisCrystalType type : EnumElectisCrystalType.values()) {
            
            if (type != EnumElectisCrystalType.BROKEN) {
                
                ItemStack stack = new ItemStack(this, 1, 0);
                UtilItemStack.setNBTTagInt(stack, "crystalType", type.ordinal());
                list.add(stack);
            }
        }
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        ItemStack stack = new ItemStack(this);
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof TileElectisCrystal) {
            
            EnumElectisCrystalType type = ((TileElectisCrystal) tile).getCrystalType();
            UtilItemStack.setNBTTagInt(stack, "crystalType", type.ordinal());
        }
        return stack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
        par3List.add("Crystal Type: " + EnumElectisCrystalType.values()[UtilItemStack.getNBTTagInt(stack, "crystalType")].toString());
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int meta) {
        
        return new TileElectisCrystal();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        
        if (!world.isRemote) {
            
            TileEntity tile = world.getTileEntity(x, y, z);
            
            if (tile != null && tile instanceof TileElectisCrystal && ((TileElectisCrystal) tile).getCrystalType() != EnumElectisCrystalType.BROKEN) {
                
                world.spawnEntityInWorld(new EntityItem(world, x + .5, y + .5, z + .5, EnumElectisCrystalType.setCrystalType(new ItemStack(this), ((TileElectisCrystal) tile).getCrystalType())));
            }
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        return new ArrayList<ItemStack>();
    }
}
