package net.happyspeed.iframestweaker;

import net.fabricmc.api.ModInitializer;

import net.happyspeed.iframestweaker.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IFramesTweakerMod implements ModInitializer {
	public static final String MOD_ID = "iframestweaker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("IFramesTweaker is Loading");
		ModConfigs.registerConfigs();
	}
}