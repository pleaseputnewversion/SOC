package com.example.soccerballmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.server.command.CommandManager;
import com.mojang.brigadier.CommandDispatcher;

public class SoccerBallMod implements ModInitializer {
    public static final EntityType<SoccerBallEntity> SOCCER_BALL_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("soccerballmod", "soccer_ball"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SoccerBallEntity::new)
                    .dimensions(EntityType.Builder.create(SoccerBallEntity::new, SpawnGroup.MISC)
                            .fixedSize(0.25f, 0.25f))  // Small entity size (adjust if needed)
                    .build()
    );

    @Override
    public void onInitialize() {
        System.out.println("Soccer Ball Mod Loaded!");
        // Register the command
        CommandManager.DISPATCHER.register(
                CommandManager.literal("spawnSoccerBall")
                        .executes(context -> {
                            SoccerBallEntity soccerBall = new SoccerBallEntity(SoccerBallMod.SOCCER_BALL_ENTITY, context.getSource().getWorld());
                            soccerBall.refreshPositionAndAngles(context.getSource().getPosition(), 0, 0);
                            context.getSource().getWorld().spawnEntity(soccerBall);
                            context.getSource().sendFeedback("Soccer ball spawned!", false);
                            return 1;
                        })
        );
    }
}