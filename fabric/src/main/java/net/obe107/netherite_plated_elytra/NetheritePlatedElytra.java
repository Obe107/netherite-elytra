package net.obe107.netherite_plated_elytra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.obe107.netherite_plated_elytra.item.ModItems;

public class NetheritePlatedElytra implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "netherite_plated_elytra";

    @Override
    public void onInitialize() {
        ModItems.register();

        // compatibility datapack
        if (FabricLoader.getInstance().isModLoaded("elytratrims")) {
            FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer -> {
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Identifier.fromNamespaceAndPath(MOD_ID, "elytratrims_compat"),
                        modContainer,
                        ResourcePackActivationType.ALWAYS_ENABLED
                );
            });
        }
    }

    @Override
    public void onInitializeClient() {

    }
}
