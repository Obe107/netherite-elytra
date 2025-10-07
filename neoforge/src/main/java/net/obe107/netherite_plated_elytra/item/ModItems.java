package net.obe107.netherite_plated_elytra.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.obe107.netherite_plated_elytra.NetheritePlatedElytra;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NetheritePlatedElytra.MOD_ID);

    public static final ResourceLocation ARMOR_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(NetheritePlatedElytra.MOD_ID, "netherite_elytra_armor");

    public static final DeferredItem<NetheritePlatedElytraItem> NETHERITE_ELYTRA = ITEMS.register("netherite_elytra",
            () -> new NetheritePlatedElytraItem(new Item.Properties()
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}