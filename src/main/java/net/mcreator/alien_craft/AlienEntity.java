package net.mcreator.alien_craft;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/** Alien Craft alien mob. Uses the original mod's 32px skin resources. */
public class AlienEntity extends Monster {
    public AlienEntity(EntityType<? extends AlienEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 32.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() { return AlienCraft.ALIEN_VOICE.get(); }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return net.minecraft.sounds.SoundEvents.GENERIC_HURT; }

    @Override
    protected SoundEvent getDeathSound() { return net.minecraft.sounds.SoundEvents.GENERIC_DEATH; }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        if (getType() != AlienCraft.ALIENCIVIL_ENTITY.get()) {
            goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.15D, false));
            targetSelector.addGoal(1, new HurtByTargetGoal(this));
            targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        }
    }
}
