package com.example.examplemod.world.structure;

import com.example.examplemod.ExampleMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {
	public static StructureFeature<?, ?> CONFIGURED_ESTRUTURA_TOP = ModStrucures.ESTRUTURA_TOP.get().configured(IFeatureConfig.NONE);
	
	public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(ExampleMod.MOD_ID, "configured_estrutura_top"), CONFIGURED_ESTRUTURA_TOP);

        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
        * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
        * FlatGenerationSettings.STRUCTURE_FEATURES in it and you don't add your structure to it, the game
        * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
        *
        * (It would also crash with superflat worldtype if you omit the below line
        * and attempt to add the structure's StructureSeparationSettings to the world)
        *
        * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
        * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
        * and re-enter it and your structures will be spawning. I could not figure out why it needs
        * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
        *
        * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
        */
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStrucures.ESTRUTURA_TOP.get(), CONFIGURED_ESTRUTURA_TOP);
    }
}
