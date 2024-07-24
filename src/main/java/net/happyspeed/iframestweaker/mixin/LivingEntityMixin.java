package net.happyspeed.iframestweaker.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.happyspeed.iframestweaker.IFramesTweakerMod;
import net.happyspeed.iframestweaker.config.ModConfigs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Objects;

@Mixin(value = LivingEntity.class, priority = 1400)
public abstract class LivingEntityMixin extends Entity {
	@Shadow public abstract boolean damage(DamageSource source, float amount);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@ModifyConstant(method = "damage", constant = @Constant(intValue = 20, ordinal = 0))
	public int changeIFrames(int constant, @Local(ordinal = 0) final DamageSource source, @Local(ordinal = 0) final float amount) {
		Entity entity2 = source.getAttacker();
		int invulnerableTime;
		if (source.isOf(DamageTypes.PLAYER_ATTACK) && entity2 instanceof PlayerEntity player) {
			int base = (int) player.getAttackCooldownProgressPerTick();
			if (base > 0) {
				int base_half = Math.round((float) base / 2);
				invulnerableTime = Math.min(ModConfigs.MAXIFRAMES, Math.max(ModConfigs.MINIFRAMES, (int) (base_half + (Math.round(base * ModConfigs.IFRAMESFACTOR)))));

			}
			else {
				invulnerableTime = 15;
			}
		}
		else if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
			invulnerableTime = 0;
		}
		else if (source.isIn(DamageTypeTags.IS_FALL)) {
			invulnerableTime = 0;
		}
		else if (source.isIn(DamageTypeTags.BYPASSES_COOLDOWN)) {
			invulnerableTime = 0;
		}
		else if (source.isIn(DamageTypeTags.IS_FIRE)) {
			invulnerableTime = 10;
		}
		else if (source.isOf(DamageTypes.INDIRECT_MAGIC)) {
			invulnerableTime = 10;
		}
		else if (source.isOf(DamageTypes.INDIRECT_MAGIC)) {
			invulnerableTime = 10;
		}
		else if (Objects.equals(source.getType().msgId(), "slashdamage")) {
			invulnerableTime = 5;
		}
		else {
			invulnerableTime = 20;
		}
		return invulnerableTime;
	}

	@ModifyExpressionValue(method = "damage", at = @At(value = "CONSTANT", args = "floatValue=10.0F", ordinal = 0))
	public float changeIFrames(float constant) {
		return constant - 10;
	}
}