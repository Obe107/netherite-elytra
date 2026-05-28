package net.obe107.netherite_plated_elytra.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.DamageResistant;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.obe107.netherite_plated_elytra.Constants;

public class ModItems {
    public static final Identifier ARMOR_MODIFIER_ID = Identifier.fromNamespaceAndPath(
            Constants.MOD_ID, "netherite_elytra_armor");

    public static final ResourceKey<Item> NETHERITE_ELYTRA_KEY = ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "netherite_elytra"));

    public static final NetheritePlatedElytraItem NETHERITE_ELYTRA = register(NETHERITE_ELYTRA_KEY,
            new NetheritePlatedElytraItem(new Item.Properties()
                    .setId(NETHERITE_ELYTRA_KEY)
                    .durability(864)
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.DAMAGE_RESISTANT, new DamageResistant(DamageTypeTags.IS_FIRE))
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .component(DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                    .setDamageOnHurt(false)
                                    .setAsset(ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "netherite_elytra")))
                                    .build())

                    .repairable(Items.PHANTOM_MEMBRANE)
                    .component(DataComponents.ATTRIBUTE_MODIFIERS,
                            ItemAttributeModifiers.builder()
                                    .add(Attributes.ARMOR,
                                            new AttributeModifier(ARMOR_MODIFIER_ID,
                                                    4.0,
                                                    AttributeModifier.Operation.ADD_VALUE),
                                            EquipmentSlotGroup.CHEST)
                                    .build())
            ));

    private static <T extends Item> T register(ResourceKey<Item> key, T item) {
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(entries -> entries.accept(NETHERITE_ELYTRA));
    }
}