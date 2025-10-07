package net.obe107.netherite_plated_elytra;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.obe107.netherite_plated_elytra.item.ModItems;

@Mod(Constants.MOD_ID)
public class NetheritePlatedElytra {

    public static final String MOD_ID = "netherite_plated_elytra";

    public NetheritePlatedElytra(IEventBus eventBus) {

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(this::addCreative);

        ModItems.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            new ClientModEvents(eventBus);
        }

    }

    @SubscribeEvent
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.NETHERITE_ELYTRA);
        }
    }

    public static class ClientModEvents {

        public ClientModEvents(IEventBus modBus) {
            modBus.addListener(this::onAddLayers);
        }

        private void onAddLayers(EntityRenderersEvent.AddLayers event) {
            for (PlayerSkin.Model skin : event.getSkins()) {

                // PlayerRenderer
                if (event.getSkin(skin) instanceof PlayerRenderer playerRenderer) {
                    playerRenderer.addLayer(new NetheritePlatedElytraLayer<>(playerRenderer, event.getEntityModels()));

                }

                // ArmorStandRenderer
                if (event.getRenderer(EntityType.ARMOR_STAND) instanceof ArmorStandRenderer armorStandRenderer) {
                    armorStandRenderer.addLayer(new NetheritePlatedElytraLayer<>(armorStandRenderer, event.getEntityModels()));
                }
            }
        }
    }
}