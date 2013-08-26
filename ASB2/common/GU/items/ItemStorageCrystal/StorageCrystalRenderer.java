package GU.items.ItemStorageCrystal;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class StorageCrystalRenderer implements IItemRenderer {

    private static RenderItem renderItem = new RenderItem();
    
    public StorageCrystalRenderer() {

    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
       
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        ItemStorageCrystal crystal = (ItemStorageCrystal) item.getItem();
//        Tessellator tess = Tessellator.instance;
        Icon icon = crystal.getIcon(item, 0);
        
        renderItem.renderIcon(0, 0, icon, 16, 16);
    }
}
