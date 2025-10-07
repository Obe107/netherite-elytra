package net.obe107.netherite_plated_elytra;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.obe107.netherite_plated_elytra.item.ModItems;

import java.lang.reflect.Method;

@OnlyIn(Dist.CLIENT)
public class NetheritePlatedElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation WINGS_LOCATION = ResourceLocation.fromNamespaceAndPath(NetheritePlatedElytra.MOD_ID, "textures/item/netherite_elytra_model.png");

    private final ElytraModel<T> elytraModel;

    private static Method renderFeaturesMethod = null;
    private static boolean elytraTrimsAvailable = false;

    static {
        try {
            Class<?> apiClass = Class.forName("dev.kikugie.elytratrims.api.ElytraTrimsAPI");
            for (Method m : apiClass.getDeclaredMethods()) {
                if (m.getName().equals("renderFeatures") && m.getParameterCount() == 10) {
                    renderFeaturesMethod = m;
                    renderFeaturesMethod.setAccessible(true);
                    elytraTrimsAvailable = true;
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            elytraTrimsAvailable = false;
        }
    }


    public NetheritePlatedElytraLayer(RenderLayerParent<T, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.elytraModel = new ElytraModel(modelSet.bakeLayer(ModelLayers.ELYTRA));
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
        if (this.shouldRender(itemstack, livingEntity)) {
            ResourceLocation resourcelocation;
            if (livingEntity instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)livingEntity;
                PlayerSkin playerskin = abstractclientplayer.getSkin();
                if (playerskin.elytraTexture() != null) {
                    resourcelocation = playerskin.elytraTexture();
                } else if (playerskin.capeTexture() != null && abstractclientplayer.isModelPartShown(PlayerModelPart.CAPE)) {
                    resourcelocation = playerskin.capeTexture();
                } else {
                    resourcelocation = this.getElytraTexture(itemstack, livingEntity);
                }
            } else {
                resourcelocation = this.getElytraTexture(itemstack, livingEntity);
            }

            poseStack.pushPose();
            poseStack.translate(0.0F, 0.0F, 0.125F);
            this.getParentModel().copyPropertiesTo(this.elytraModel);
            this.elytraModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(resourcelocation), itemstack.hasFoil());
            this.elytraModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);


            if (elytraTrimsAvailable) {
                try {
                    renderFeaturesMethod.invoke(null, this.elytraModel, poseStack, buffer, livingEntity, itemstack, packedLight, 1f, 1f, 1f, 1f);
                } catch (Exception e) {
                    //e.printStackTrace(); // TEMP Remove in release builds
                }
            }


            poseStack.popPose();
        }

    }

    public boolean shouldRender(ItemStack stack, T entity) {
        return stack.getItem() == ModItems.NETHERITE_ELYTRA.get();
    }

    public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
        return WINGS_LOCATION;
    }
}
