# Dan's Essentials

## Description

Dan's Essentials is a Minecraft plugin that provides a collection of miscellaneous utility commands for server operators and players, including fly toggles, gamemode switching, broadcasting, muting, inventory management, warp signs, and more.

## Installation

### First Time Installation

1. Download the plugin from [SpigotMC](https://www.spigotmc.org/resources/dans-essentials.98503/).
2. Place the jar in the `plugins` folder of your server.
3. Restart your server.

## Usage

### Documentation

- [User Guide](USER_GUIDE.md) – Getting started and common scenarios
- [Commands Reference](COMMANDS.md) – Complete list of all commands
- [Configuration Guide](CONFIG.md) – Detailed configuration options

### Wiki & Additional Resources

- [Wiki Guide](https://github.com/Dans-Plugins/Dans-Essentials/wiki)

## Support

You can find the support Discord server [here](https://discord.gg/xXtuAQ2).

### Experiencing a bug?

Please fill out a bug report [here](https://github.com/Dans-Plugins/Dans-Essentials/issues/new).

- [Known Bugs](https://github.com/Dans-Plugins/Dans-Essentials/issues?q=is%3Aopen+is%3Aissue+label%3Abug)

## Contributing

- [CONTRIBUTING.md](CONTRIBUTING.md)
- [Notes for Developers](https://github.com/Dans-Plugins/Dans-Essentials/wiki)

## Testing

### Unit Tests

Linux:

    mvn clean test

Windows:

    mvn clean test

If you see `BUILD SUCCESS`, the tests have passed.

## Development

### Local Test Server

You can test changes to the plugin using a local Spigot or Paper server.

#### Setup

1. Build the plugin: `mvn clean package`
2. Locate the built JAR in the `target` directory.
3. Copy the JAR into the `plugins` folder of your local Minecraft server.
4. Start or restart your server to load the updated plugin.

#### Iterating on Changes

1. Make your code changes.
2. Rebuild the plugin: `mvn clean package`
3. Replace the existing JAR in the `plugins` folder with the newly built JAR.
4. Restart or reload your server to apply changes.

## Authors and Acknowledgement

### Developers

| Name | Main Contributions |
|------|-------------------|
| Daniel Stephenson | Original author and primary developer |

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE) (GPL-3.0).

You are free to use, modify, and distribute this software, provided that:
- Source code is made available under the same license when distributed.
- Changes are documented and attributed.
- No additional restrictions are applied.

See the [LICENSE](LICENSE) file for the full text of the GPL-3.0 license.

## Project Status

This project is in active development.

### bStats

You can view the bStats page for the plugin [here](https://bstats.org/plugin/bukkit/Dans-Essentials/9527).

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a release-by-release summary of changes.
