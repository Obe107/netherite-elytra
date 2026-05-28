package net.obe107.netherite_plated_elytra;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.obe107.netherite_plated_elytra.item.ModItems;

@Mod(Constants.MOD_ID)
public class NetheritePlatedElytra {

    public static final String MOD_ID = "netherite_plated_elytra";

    public NetheritePlatedElytra(IEventBus eventBus) {
        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(this::addCreative);

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
}