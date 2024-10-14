package net.happyspeed.iframestweaker.config;

import com.mojang.datafixers.util.Pair;
import net.happyspeed.iframestweaker.IFramesTweakerMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int MINIFRAMES;
    public static int MAXIFRAMES;
    public static double IFRAMESFACTOR;
    public static int PROJECTILEIFRAMES;
    public static int FALLIFRAMES;
    public static int DIRECTMAGICIFRAMES;
    public static int INDIRECTMAGICIFRAMES;
    public static int FIREIFRAMES;
    public static int MISCIFRAMES;

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
        configs.addKeyValuePair(new Pair<>("projectileiframes", 0));
        configs.addKeyValuePair(new Pair<>("falliframes", 0));
        configs.addKeyValuePair(new Pair<>("directmagiciframes", 10));
        configs.addKeyValuePair(new Pair<>("indirectmagiciframes", 10));
        configs.addKeyValuePair(new Pair<>("fireiframes", 10));
        configs.addKeyValuePair(new Pair<>("misciframes", 10));
    }

    private static void assignConfigs() {
        MINIFRAMES = CONFIG.getOrDefault("miniframes", 4);
        MAXIFRAMES = CONFIG.getOrDefault("maxiframes", 24);
        IFRAMESFACTOR = CONFIG.getOrDefault("iframesfactor", 0.3);
        PROJECTILEIFRAMES = CONFIG.getOrDefault("projectileiframes", 0);
        FALLIFRAMES = CONFIG.getOrDefault("falliframes", 0);
        DIRECTMAGICIFRAMES = CONFIG.getOrDefault("directmagiciframes", 10);
        INDIRECTMAGICIFRAMES = CONFIG.getOrDefault("indirectmagiciframes", 10);
        FIREIFRAMES = CONFIG.getOrDefault("fireiframes", 10);
        MISCIFRAMES = CONFIG.getOrDefault("misciframes", 10);



        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}