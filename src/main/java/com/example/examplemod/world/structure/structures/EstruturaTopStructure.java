package com.example.examplemod.world.structure.structures;

import org.apache.logging.log4j.Level;

import com.example.examplemod.ExampleMod;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class EstruturaTopStructure extends Structure<NoFeatureConfig> {
	public EstruturaTopStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }
	
	@Override
	public  IStartFactory<NoFeatureConfig> getStartFactory() {
		return EstruturaTopStructure.Start::new;
	}
	
	@Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }
	
	@Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        // Grab height of land. Will stop at first non-air block.
        int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Type.WORLD_SURFACE_WG);

        // Grabs column of blocks at given position. In overworld, this column will be made of stone, water, and air.
        // In nether, it will be netherrack, lava, and air. End will only be endstone and air. It depends on what block
        // the chunk generator will place for that dimension.
        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());

        // Combine the column of blocks with land height and you get the top block itself which you can test.
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        // Now we test to make sure our structure is not spawning on water or other fluids.
        // You can do height check instead too to make it spawn at high elevations.
        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
    }
	
	public static class Start extends StructureStart<NoFeatureConfig>  {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

		@Override
		public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
			 // Turns the chunk coordinates into actual coordinates we can use
            int x = chunkX * 16;
            int z = chunkZ * 16;

            /*
             * We pass this into addPieces to tell it where to generate the structure.
             * If addPieces's last parameter is true, blockpos's Y value is ignored and the
             * structure will spawn at terrain height instead. Set that parameter to false to
             * force the structure to spawn at blockpos's Y value instead. You got options here!
             */
            BlockPos centerPos = new BlockPos(x, 0, z);

            /*
             * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
             * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
             * Then loop through it and look for land with air above it and set blockpos's Y value to it.
             * Make sure to set the final boolean in JigsawManager.addPieces to false so
             * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
             */
            //IBlockReader blockReader = chunkGenerator.getBaseColumn(blockpos.getX(), blockpos.getZ());

            // All a structure has to do is call this method to turn it into a jigsaw based structure!
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(new ResourceLocation(ExampleMod.MOD_ID, "estrutura_top/start_pool")),
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    centerPos, // Position of the structure. Y value is ignored if last parameter is set to true.
                    this.pieces, // The list that will be populated with the jigsaw pieces after this method.
                    this.random,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    true);  // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                         // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
            
            // **THE FOLLOWING LINE IS OPTIONAL**
            //
            // Right here, you can do interesting stuff with the pieces in this.pieces such as offset the
            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
            // holds just the piece's size and positions. Blocks will be placed much later by the game.
            //
            // In this case, we do `piece.offset` to raise pieces up by 1 block so that the house is not right on
            // the surface of water or sunken into land a bit. NOTE: land added by Structure.NOISE_AFFECTING_FEATURES
            // will also be moved alongside the piece. If you do not want this land, do not add your structure to the
            // Structure.NOISE_AFFECTING_FEATURES field and now your pieces can be set on the regular terrain instead.
            this.pieces.forEach(piece -> piece.move(0, 1, 0));

            // Since by default, the start piece of a structure spawns with it's corner at centerPos
            // and will randomly rotate around that corner, we will center the piece on centerPos instead.
            // This is so that our structure's start piece is now centered on the water check done in isFeatureChunk.
            // Whatever the offset done to center the start piece, that offset is applied to all other pieces
            // so the entire structure is shifted properly to the new spot.
            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
            int xOffset = centerPos.getX() - structureCenter.getX();
            int zOffset = centerPos.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.pieces){
                structurePiece.move(xOffset, 0, zOffset);
            }

            // Sets the bounds of the structure once you are finished.
            this.calculateBoundingBox();

            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            // This is returning the coordinates of the center starting piece.
            ExampleMod.LOGGER.log(Level.DEBUG, "Structure at " +
                            this.pieces.get(0).getBoundingBox().x0 + " " +
                            this.pieces.get(0).getBoundingBox().y0 + " " +
                            this.pieces.get(0).getBoundingBox().z0);
		}

    }
}
