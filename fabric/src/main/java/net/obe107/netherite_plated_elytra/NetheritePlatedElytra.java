package net.obe107.netherite_plated_elytra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.obe107.netherite_plated_elytra.item.ModItems;

public class NetheritePlatedElytra implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "netherite_plated_elytra";

    @Override
    public void onInitialize() {
        System.out.println("=========================================");
        System.out.println("NETHERITE PLATED ELYTRA IS LOADING!");
        System.out.println("=========================================");
        ModItems.register();
    }

    @Override
    public void onInitializeClient() {

    }
}
