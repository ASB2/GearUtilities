package GU;

import GU.fluid.FluidBase;

public class FluidRegistry {

    public static FluidBase LiquidWood;
    public static FluidBase LiquidIron;
    public static FluidBase LiquidGold;
    public static FluidBase LiquidDiamond;
    public static FluidBase LiquidEmerald;
    
    public static FluidBase LiquidCheese;
    public static void initFluids() {

        LiquidWood = new FluidBase("liquidWood", 0x000000);
        LiquidIron = new FluidBase("liquidiron", 0x000000);
        LiquidGold = new FluidBase("liquidGold", 0x000000);
        LiquidDiamond = new FluidBase("liquidDiamond", 0x000000);
        LiquidEmerald = new FluidBase("liquidEmerald", 0x000000);

        LiquidCheese = new FluidBase("liquidCheese", 0x000000);
        
    }
}
