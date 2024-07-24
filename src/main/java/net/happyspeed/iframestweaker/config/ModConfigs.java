package net.happyspeed.iframestweaker.config;

import com.mojang.datafixers.util.Pair;
import net.happyspeed.iframestweaker.IFramesTweakerMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int MINIFRAMES;
    public static int MAXIFRAMES;
    public static double IFRAMESFACTOR;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(IFramesTweakerMod.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("miniframes", 4));
        configs.addKeyValuePair(new Pair<>("maxiframes", 24));
        configs.addKeyValuePair(new Pair<>("iframesfactor", 0.3));
    }

    private static void assignConfigs() {
        MINIFRAMES = CONFIG.getOrDefault("miniframes", 4);
        MAXIFRAMES = CONFIG.getOrDefault("maxiframes", 24);
        IFRAMESFACTOR = CONFIG.getOrDefault("iframesfactor", 0.3);



        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}