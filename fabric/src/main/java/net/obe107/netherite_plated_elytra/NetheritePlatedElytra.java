package net.obe107.netherite_plated_elytra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.obe107.netherite_plated_elytra.item.ModItems;

public class NetheritePlatedElytra implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "netherite_plated_elytra";

    @Override
    public void onInitialize() {
        ModItems.register();
    }

    @Override
    public void onInitializeClient() {

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
                (entityType, entityRenderer, registrationHelper, context) -> {
                    if (entityType == EntityType.PLAYER) {
                        if (entityRenderer instanceof PlayerRenderer playerRenderer) {
                            registrationHelper.register(new NetheritePlatedElytraLayer(playerRenderer, context.getModelSet()));
                        }
                    }

                    if (entityType == EntityType.ARMOR_STAND) {
                        if (entityRenderer instanceof ArmorStandRenderer armorStandRenderer) {
                            registrationHelper.register(new NetheritePlatedElytraLayer(armorStandRenderer, context.getModelSet()));
                        }
                    }
                }
        );
    }
}
