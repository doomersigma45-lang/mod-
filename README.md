# Alien Craft for Forge 1.20.1

This is a source rebuild of the supplied AlienCraft 1.4.1 Minecraft 1.12.2 JAR. The JAR contains compiled MCreator classes and assets, not the original editable project. The rebuild retains the original models, textures, structures, selected blocks and items, core crafting recipes, mod ID, creator credits, and the included MCreator distribution license. Legacy audio files are omitted from this GitHub build; the old sound playback behavior is not implemented in the port.

## Build

Install a Java 17 JDK. From this directory run `gradlew.bat build` on Windows or `./gradlew build` on Linux/macOS; the distributable JAR will be in `build/libs`.

## Port status

The port now includes custom alien spawn-egg entities rendered with the original alien textures, functional door and storage-crate blocks, block drops, and updated crafting recipes. Their body model and AI are simplified compared with the 1.12.2 originals. The legacy dimension providers, teleport GUI, and UFO vehicle behavior still need a separate port. This project is a non-commercial fan port; keep `MCreator-README.txt` with any distribution and retain MCreator attribution.
