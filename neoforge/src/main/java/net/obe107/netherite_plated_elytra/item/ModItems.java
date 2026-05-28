package net.obe107.netherite_plated_elytra.item;

import com.mojang.datafixers.util.Unit;
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
import net.obe107.netherite_plated_elytra.Constants;
import net.obe107.netherite_plated_elytra.NetheritePlatedElytra;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NetheritePlatedElytra.MOD_ID);

    public static final ResourceLocation ARMOR_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(NetheritePlatedElytra.MOD_ID, "netherite_elytra_armor");

    public static final DeferredItem<NetheritePlatedElytraItem> NETHERITE_ELYTRA = ITEMS.register("netherite_elytra",
            () -> new NetheritePlatedElytraItem(new Item.Properties()
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}