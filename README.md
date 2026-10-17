# Alien Craft for Forge 1.20.1

This is a source rebuild of the supplied AlienCraft 1.4.1 Minecraft 1.12.2 JAR. The JAR contains compiled MCreator classes and assets, not the original editable project. The rebuild retains the original resource pack, selected blocks and items, core crafting recipes, mod ID, creator credits, and the included MCreator distribution license.

## Build

Install a Java 17 JDK. From this directory run `gradlew.bat build` on Windows or `./gradlew build` on Linux/macOS; the distributable JAR will be in `build/libs`.

## Port status

The legacy dimension providers, teleport GUI, custom entity AI/models, UFO vehicle behavior, and several block interactions depended on removed 1.12.2 APIs. They are not represented as working features in this initial rebuild. The old art assets are retained for subsequent implementation. This project is a non-commercial fan port; keep `MCreator-README.txt` with any distribution and retain MCreator attribution.
