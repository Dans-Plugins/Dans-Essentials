# Copilot Instructions

This repository follows the DPC (Dans Plugins Community) conventions defined at
https://github.com/Dans-Plugins/dpc-conventions. Read those conventions before
making any changes.

## Technology Stack

- Language: Java
- Build tool: Maven
- Target platform: Spigot / Paper (Minecraft plugin, API version 1.13+)
- Test framework: JUnit 5 (`junit-jupiter`) with Mockito, both declared at `test` scope in `pom.xml`

## Project Structure

- `src/main/java/dansplugins/dansessentials/` – Plugin source code
  - `commands/` – Command executor classes (one class per sub-command)
  - `listeners/` – Bukkit event listener classes
  - `services/` – Service classes (e.g. `ConfigService`)
  - `data/` – Runtime data holders (e.g. `EphemeralData`)
  - `utils/` – Utility classes (e.g. `Logger`)
  - `bStats/` – bStats metrics integration
- `src/main/resources/` – `plugin.yml` and any other resources (create `config.yml` here if adding config defaults)
- `src/test/java/` – Unit tests, mirroring the package layout of `src/main/java/`

## Coding Conventions

- All sub-commands extend `AbstractPluginCommand` from the Ponder library and are registered via `CommandService`.
- The main plugin class is `DansEssentials`, which extends `PonderBukkitPlugin`.
- Prefer using `ConfigService` to read configuration values; avoid introducing new direct `getConfig()` calls outside that service, and gradually refactor existing ones when working on related code.
- User-facing messages are currently written inline; prefer `ChatColor` constants for colouring.
- Follow the existing package structure (`dansplugins.dansessentials.<subpackage>`) when adding new classes.

## Contribution Workflow

- Branch from `master` for all changes.
- Open a pull request against `master`, the repository's default and only long-lived branch.
- Reference the related GitHub issue in every pull request description.
