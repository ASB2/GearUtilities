package GU.blocks.containers.BlockGyro.BlockSteamGyro;

import net.minecraft.util.ResourceLocation;
import GU.blocks.containers.BlockGyro.GyroRenderer;
import GU.info.Textures;

public class SteamGyroRenderer extends GyroRenderer {

    @Override
    public ResourceLocation getBaseTexture() {

        return Textures.STEAM_GYRO_BASE;
    }

    @Override
    public ResourceLocation getCenterTexture() {

        return Textures.STEAM_GYRO_CENTER;
    }

    @Override
    public ResourceLocation getOuterTexture() {

        return Textures.STEAM_GYRO_OUTER;
    }
}
