# Ultra Pickaxe

## Description

This mod adds a tool called the "Ultra Pickaxe". This pickaxe can break barrier blocks,
bedrock, and end portal frames. It is very slow at mining, very difficult to craft, and
can only be used once.

## Downloads

You can download stable releases of the mod from [Github Releases](https:/github.com/DragonEggBedrockBreaking/UltraPickaxe/releases).

## Compiling

Unlike most minecraft mods, this mod does not use the maven or gradle build systems.
This mod uses a new, faster, more minecraft-focused build system called [Brachyura](https://github.com/CoolCrabs/brachyura).

To compile the project, run the commands (prequisite: you need Java version 17 or above installed and in path):
```
git clone https://github.com/DragonEggBedrockBreaking/UltraPickaxe.git/
cd UltraPickaxe
java -jar brachyura-bootstrap-0.jar build
```

The artifacts will be in `build/libs/`, just like with the other builds systems.

## License

This mod is available under the [MPL](LICENSE.txt) license.