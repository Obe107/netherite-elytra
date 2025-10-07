package net.obe107.netherite_plated_elytra.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.obe107.netherite_plated_elytra.NetheritePlatedElytra;
import net.obe107.netherite_plated_elytra.Constants;

public class ModItems {
    public static final ResourceLocation ARMOR_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "netherite_elytra_armor");

    public static final NetheritePlatedElytraItem NETHERITE_ELYTRA = register("netherite_elytra",
            new NetheritePlatedElytraItem(new Item.Properties()
                    .durability(864)
                    .stacksTo(1)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.ATTRIBUTE_MODIFIERS,
                            ItemAttributeModifiers.builder()
                                    .add(Attributes.ARMOR,
                                            new AttributeModifier(ARMOR_MODIFIER_ID,
                                                    4.0,
                                                    AttributeModifier.Operation.ADD_VALUE),
                                            EquipmentSlotGroup.CHEST)
                                    .build())
            ));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(entries -> entries.accept(NETHERITE_ELYTRA));
    }
}
