package GU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import GU.info.Reference;
import GU.items.AdvancedStickWrapper;
import GU.items.DestructorWrapper;
import GU.items.ElectisShard.ElectisCrystalShardRenderer;
import GU.items.ElectisShard.ElectisShardWrapper;
import GU.items.FluidCrystalArrayRenderer;
import GU.items.GearReaderWrapper;
import GU.items.ItemBase;
import GU.items.ItemMetadata;
import GU.items.ItemMetadata.MetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;
import GU.items.TeleporterWrapper;
import GU.utils.UtilGU;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    
    private static final Map<String, Item> customItemMap = new HashMap<String, Item>();
    
    public static final ItemMetadata METADATA_ITEM = new ItemMetadata();
    public static final ItemBase ITEM_FLUID = new ItemBase() {
        
        public void postInit() {
            
            MinecraftForgeClient.registerItemRenderer(this, FluidCrystalArrayRenderer.instance);
            setCreativeTab(GearUtilities.tabGUFluids);
        };
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            
            Fluid saved = UtilGU.getFluid(itemStack);
            
            if (saved != null) {
                
                par3List.add("Fluid: ".concat(saved.getLocalizedName()));
                par3List.add("Fluid ID: " + saved.getID());
            }
            // else
            // par3List.add("Fluid: ".concat("Empty"));
        }
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public void getSubItems(Item item, CreativeTabs tab, List subItems) {
            
            subItems.add(new ItemStack(this));
            for (ItemStack itemStack : GU.FluidRegistry.FluidCrystalArrayList) {
                
                subItems.add(itemStack.copy());
            }
        }
        
        public String getItemStackDisplayName(ItemStack itemStack) {
            
            Fluid saved = UtilGU.getFluid(itemStack);
            
            if (saved != null) {
                
                return "Fluid Crystal Array: ".concat(saved.getLocalizedName());
            }
            return "Fluid Crystal Array: Empty";
        };
    };
    public static final ItemStack ELECTIS_CRYSTAL_SHARD = new ItemStack(METADATA_ITEM, 1, 0);
    public static final ItemStack GARNET = new ItemStack(METADATA_ITEM, 1, 1);
    
    static {
        
        customItemMap.put(Reference.MOD_ID.concat(":ItemMetadata"), METADATA_ITEM);
        customItemMap.put(Reference.MOD_ID.concat(":ItemMetadataFluid"), ITEM_FLUID);
        
        METADATA_ITEM.addWrapper(new ElectisShardWrapper("Electis Crystal Shard").setRenderer(ElectisCrystalShardRenderer.instance));
        METADATA_ITEM.addWrapper(new MetadataWrapper("Garnet").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new MetadataWrapper("Electis Controler").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new GearReaderWrapper("Gear Reader").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new TeleporterWrapper("Teleporter").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new AdvancedStickWrapper("Advanced Stick"));
        METADATA_ITEM.addWrapper(new DestructorWrapper("Destructor").setRenderer(GarnetRenderer.instance));
        // METADATA_ITEM.addWrapper(new
        // ColorModifierWrapper("Color Modifier").setRenderer(ColorModifierRenderer.instance));
    }
    
    private ItemRegistry() {
        // TODO Auto-generated constructor stub
    }
    
    public static void init(Configuration config) {
        
        for (Entry<String, Item> entry : customItemMap.entrySet()) {
            
            GameRegistry.registerItem(entry.getValue(), entry.getKey());
            
            if (entry.getValue() instanceof ItemBase) {
                
                ((ItemBase) entry.getValue()).postInit();
            }
        }
    }
}
