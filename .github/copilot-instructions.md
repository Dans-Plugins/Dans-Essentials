# Copilot Instructions

This repository follows the DPC (Dans Plugins Community) conventions defined at
https://github.com/Dans-Plugins/dpc-conventions. Read those conventions before
making any changes.

## Technology Stack

- Language: Java
- Build tool: Maven
- Target platform: Spigot / Paper (Minecraft plugin, API version 1.13+)
- Test framework: JUnit

## Project Structure

- `src/main/java/dansplugins/dansessentials/` – Plugin source code
  - `commands/` – Command executor classes (one class per sub-command)
  - `listeners/` – Bukkit event listener classes
  - `services/` – Service classes (e.g. `ConfigService`)
  - `data/` – Runtime data holders (e.g. `EphemeralData`)
  - `utils/` – Utility classes (e.g. `Logger`)
  - `bStats/` – bStats metrics integration
- `src/main/resources/` – `plugin.yml` and `config.yml`
- `src/test/java/` – Unit tests

## Coding Conventions

- All sub-commands extend `AbstractPluginCommand` from the Ponder library and are registered via `CommandService`.
- The main plugin class is `DansEssentials`, which extends `PonderBukkitPlugin`.
- Use `ConfigService` to read all configuration values; never call `getConfig()` directly from outside that service.
- User-facing messages are currently written inline; prefer `ChatColor` constants for colouring.
- Follow the existing package structure (`dansplugins.dansessentials.<subpackage>`) when adding new classes.

## Contribution Workflow

- Branch from `develop` for all changes.
- Open a pull request against `develop`, not `main`.
- Reference the related GitHub issue in every pull request description.
