package GUOLD.render;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureUtil;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class NoiseRenderer implements ITickHandler {

    boolean down = true;
    int position = 0;

    public NoiseRenderer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        List<int[]> data = NoiseManager.instance.imageDataArray;

        if (type.equals(this.ticks())) {

            TextureUtil.uploadTexture(NoiseManager.instance.textureImage.getGlTextureId(), data.get(position), NoiseManager.instance.originalImage.getWidth(), NoiseManager.instance.originalImage.getHeight());

            if (down) {

                if (position >= data.size() - 1) {
                    down = false;
                    position = data.size() - 1;
                } else {
                    // position = Math.round((position + (1 * (float) tickData[0])));
                    position++;
                }

            } else {

                if (position <= 0) {
                    down = true;
                    position = 0;
                } else {
                    // position = Math.round((position - (1 * (float) tickData[0])));
                    position--;
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        // TODO Auto-generated method stub
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.RENDER);
    }

    @Override
    public String getLabel() {

        return "Noise Renderer";
    }

}
