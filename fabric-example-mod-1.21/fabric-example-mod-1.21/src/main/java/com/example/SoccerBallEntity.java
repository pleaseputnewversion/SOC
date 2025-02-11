package com.example.soccerballmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class SoccerBallEntity extends Entity {

    public SoccerBallEntity(EntityType<?> type, World world) {
    super(type, world);
}

@Override
public void tick() {
    super.tick();

    // Example behavior: soccer ball moves slightly over time
    if (this.age % 5 == 0) {
        this.setVelocity(this.getVelocity().multiply(1.1, 1, 1.1));  // Apply slight movement to soccer ball
    }
}

@Override
public void onCollision(Entity entity) {
    super.onCollision(entity);
    if (entity instanceof PlayerEntity) {
        // Apply knockback to soccer ball when it collides with a player
        this.setVelocity(this.getVelocity().add(0.5, 0, 0.5));  // Soccer ball moves upon collision
    }
}

@Override
public Text getDisplayName() {
    return Text.of("Soccer Ball");
}
}