package GU;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidStack;
import GU.info.Reference;
import GU.info.Variables;
import GU.proxy.CommonProxy;
import GU.render.noise.NoiseManager;
import GU.worldGen.WorldGenBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION)
public final class GearUtilities {
    
    @Instance(Reference.MOD_ID)
    public static GearUtilities instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;
    
    private static Logger GU_LOGGER = Logger.getLogger(Reference.NAME);
    private static final SimpleNetworkWrapper packetPipeline = new SimpleNetworkWrapper(Reference.MOD_CHANNEL);
    
    public static CreativeTabs tabGUBlocks = new GUCreativeTab(Reference.NAME.concat(": Blocks"));
    public static CreativeTabs tabGUItems = new GUCreativeTab(Reference.NAME.concat(": Items"));
    public static CreativeTabs tabGUFluids = new GUCreativeTab(Reference.NAME.concat(": Fluids"));
    public static CreativeTabs tabGUStructureCubes = new GUCreativeTab(Reference.NAME.concat(": Structure Cubes"));
    
    public GearUtilities() {
        // TODO Auto-generated constructor stub
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        
        instance = this;
        
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        proxy.register();
        
        Variables.updateVariables(config);
        ItemRegistry.init(config);
        BlockRegistry.init(config);
        FluidRegistry.initFluids();
        RetroGenManager.init();
        EntityRegistry.init();
        MultiRegistry.init();
        
        MinecraftForge.EVENT_BUS.register(new EventListener());
        FMLCommonHandler.instance().bus().register(NoiseManager.instance);
        
        GameRegistry.registerFuelHandler(new IFuelHandler() {
            
            @Override
            public int getBurnTime(ItemStack fuel) {
                
                return fuel == net.minecraftforge.fluids.FluidContainerRegistry.fillFluidContainer(new FluidStack(net.minecraftforge.fluids.FluidRegistry.LAVA, 1000), new ItemStack(ItemRegistry.ITEM_FLUID)) ? 10000000 : 0;
            }
        });
        
        ModMetadata metadata = event.getModMetadata();
        metadata.autogenerated = false;
        metadata.authorList = Arrays.asList("ASB2");
        metadata.description = "A mod from ASB2 to ASB2";
        metadata.logoFile = Reference.MOD_ID + ":assets/" + Reference.MOD_ID + "/banner.png";
        
        config.save();
    }
    
    @EventHandler
    public void mainInit(FMLInitializationEvent event) {
        
        GameRegistry.registerWorldGenerator(new WorldGenBase() {
            
            @Override
            public void generate(World world, Random random, int x, int y, int z) {
                
                new WorldGenMinable(BlockRegistry.METADATA_ORE, 0, 21, Blocks.stone).generate(world, random, x, y, z);
            }
        }, 0);
        
        GameRegistry.registerWorldGenerator(new WorldGenBase() {
            
            @Override
            public void generate(World world, Random random, int x, int y, int z) {
                
                new WorldGenMinable(BlockRegistry.METADATA_ORE, 1, 21, Blocks.stone).generate(world, random, x, y, z);
            }
        }, 0);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
        FluidRegistry.registerFluidContainers();
        CraftRegistry.init();
        PacketRegistry.init();
        
        // WhiteLists.getInstance().addWrench(new
        // ItemStack(ItemRegistry.ItemAdvancedStick));
    }
    
    public static SimpleNetworkWrapper getPipeline() {
        
        return packetPipeline;
    }
    
    public static void log(Object message) {
        
        GU_LOGGER.log(Level.INFO, message != null ? message.toString() : "null");
    }
}
