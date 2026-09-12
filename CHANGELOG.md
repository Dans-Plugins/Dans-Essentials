# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Changed

- The contributor documentation now describes the repository as it actually is. `CONTRIBUTING.md` and `.github/copilot-instructions.md` routed contributors through a `develop` branch that no longer exists, so the very first command in the "Making Changes" walkthrough failed; both now name `master`, the repository's default and only long-lived branch. `CONTRIBUTING.md` and `README.md` also stated that no automated tests were configured, which stopped being true once the JUnit 5 and Mockito suite under `src/test/java/` was added; both now describe that suite and the `mvn test` command that runs it.

### Fixed

- `/de mute <player>` and `/de unmute <player>` now key the muted-player list on the resolved player's name rather than the spelling that was typed. Because the server resolves a name case-insensitively and by prefix, `/de mute ste` against `Steve` previously stored `ste`, reported success, and left the player able to chat, since the chat listener looks players up under their canonical name. The same spelling mismatch made a muted player impossible to unmute under a different spelling, allowed one player to be muted twice under two spellings, and let a partial name that resolved back to the sender slip past the "You can't mute yourself!" guard.
- `/de fly <player>` no longer throws a `NullPointerException` when the named player is offline; it now reports `That player isn't online.` and stops, matching the guard every other target-taking command already had.
- The permission-denied message for `/de fly <player>` now names `de.fly.others`, the node the command actually checks and the one declared in `plugin.yml`. It previously named `me.fly.others`, a node that does not exist, so a player who asked an administrator for exactly what the message said would still have been refused.
- The `Dev Release` workflow now retries publishing the `dev` prerelease before giving up. The release and its tag have to be deleted and recreated for the tag to move to the new commit, and a transient API failure inside that window previously left the repository with no `dev` release at all until the workflow was re-run by hand. Each attempt now starts from a clean slate, and an exhausted retry fails loudly.

### Added

- The plugin now reports usage events — `startup` on enable, `command` on each of its commands — to the author's trace server so it is known which plugins are in use. Events carry the plugin name, the event name, and the plugin version or command name; nothing about players or the server. Reporting runs off the main thread, never delays a tick, drops silently when the server is unreachable, and is turned off with `usage-reporting.enabled: false` in `config.yml`. The default config carries the plugin's key, so reporting is active out of the box unless turned off — including on servers upgraded from a version before the `usage-reporting` block existed, whose `config.yml` is never rewritten: the plugin reads the bundled defaults for any key the file lacks.
- A `Dev Release` workflow, which republishes a rolling `dev` prerelease of `master` on every non-documentation push. This is what Dan's Plugin Manager's experimental channel installs from: `/dpm get dansessentials --experimental` reads `releases/tags/dev`, so without it there is nothing for that command to download. The prerelease is unreleased, unreviewed code and is marked as such.

### Fixed

- `/de back` no longer throws `IllegalArgumentException` when used by a player with no stored previous location; it now sends a friendly error message instead. Players also get a join location tracked as their initial "back" destination, so the command is meaningful even before their first teleport.
- `/de help` now lists `/de back`, which was missing from the in-game help output despite being a registered command.

## [3.0.0-SNAPSHOT-8-8-2026] – 2026-08-08

### Changed
- Dans-Essentials is now developed AI-first. Day-to-day feature work, grooming, review and maintenance run through AI agents working directly against this repository, with the maintainers setting direction and approving what lands. The major version bump marks that change in how the project is built — it is not a break in behaviour, configuration or stored data, and existing installations can upgrade in place. Released as `3.0.0-SNAPSHOT-8-8-2026`: the AI-first line has not yet been verified in live operation, and the dated snapshot designation stays until it has.

### Fixed

- Build workflow now triggers on `master` (the repo's actual default branch) instead of `main`/`develop`, so CI actually runs on pushes and pull requests.

## [2.3.0]

### Added

- Initial documented release.
- Commands: `back`, `broadcast`, `clearinv`, `fly`, `flyspeed`, `gm`, `getpos`, `help`, `invsee`, `label`, `mute`, `unmute`.
- Warp sign support (`de.placeWarpSign`, `de.usewarpsign`).
- bStats metrics integration (plugin ID 9527).
- `debugMode` configuration option.
