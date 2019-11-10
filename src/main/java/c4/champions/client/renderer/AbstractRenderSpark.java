package c4.champions.client.renderer;

import c4.champions.common.entity.AbstractEntitySpark;
import javax.annotation.Nonnull;
import net.minecraft.client.model.ModelShulkerBullet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class AbstractRenderSpark<T extends AbstractEntitySpark> extends Render<T> {

  private final ModelShulkerBullet model = new ModelShulkerBullet();

  public AbstractRenderSpark(RenderManager manager) {
    super(manager);
  }

  private float rotLerp(float p_188347_1_, float p_188347_2_, float p_188347_3_) {
    float f;

    for (f = p_188347_2_ - p_188347_1_; f < -180.0F; f += 360.0F) {
    }

    while (f >= 180.0F) {
      f -= 360.0F;
    }
    return p_188347_1_ + p_188347_3_ * f;
  }

  @Override
  public void doRender(@Nonnull T entity, double x, double y, double z, float entityYaw, float partialTicks) {
    GlStateManager.pushMatrix();
    float f = this.rotLerp(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
    float f1 =
        entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
    float f2 = (float) entity.ticksExisted + partialTicks;
    GlStateManager.translate((float) x, (float) y + 0.15F, (float) z);
    GlStateManager.rotate(MathHelper.sin(f2 * 0.1F) * 180.0F, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(MathHelper.cos(f2 * 0.1F) * 180.0F, 1.0F, 0.0F, 0.0F);
    GlStateManager.rotate(MathHelper.sin(f2 * 0.15F) * 360.0F, 0.0F, 0.0F, 1.0F);
    float f3 = 0.03125F;
    GlStateManager.enableRescaleNormal();
    GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    this.bindEntityTexture(entity);
    this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
    GlStateManager.enableBlend();
    GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
    GlStateManager.scale(1.5F, 1.5F, 1.5F);
    this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }

  @Override
  protected abstract ResourceLocation getEntityTexture(@Nonnull T entity);
}
