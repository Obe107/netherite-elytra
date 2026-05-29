package net.obe107.netherite_plated_elytra;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.obe107.netherite_plated_elytra.item.ModItems;

@Mod(Constants.MOD_ID)
public class NetheritePlatedElytra {

    public static final String MOD_ID = "netherite_plated_elytra";

    public NetheritePlatedElytra(IEventBus eventBus) {
        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(this::addCreative);
        eventBus.addListener(this::addFeaturePacks);
        ModItems.register(eventBus);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.NETHERITE_ELYTRA);
        }
    }

    // compatibility datapack
    public void addFeaturePacks(final AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA && ModList.get().isLoaded("elytratrims")) {
            event.addPackFinders(
                    Identifier.fromNamespaceAndPath(Constants.MOD_ID, "elytratrims_compat"),
                    PackType.SERVER_DATA,
                    Component.literal("Elytra Trims Compat"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
    }
}