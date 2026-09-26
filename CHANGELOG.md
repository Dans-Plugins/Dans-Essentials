# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Changed

- The command documentation now shows that `/de broadcast` and `/de label` only accept their argument inside double quotes. `COMMANDS.md`, `USER_GUIDE.md`, and the in-game `/de help` output wrote the label as `/de label <name>`, and the reference wrote the broadcast usage as `/de broadcast <message>`, which read as if quotes were optional; typed that way both commands reject the input. The in-game help line for `/de label` also now says it renames the item in the main hand, and the `/de broadcast` help line has its missing space restored.
- `USER_GUIDE.md` now mentions the welcome message that is broadcast to everyone online when a player joins for the first time, which was not documented anywhere.
- `CHANGELOG.md` now records the `2.4.0` release. The entries now under `[2.4.0]` were published as `v2.4.0` on 2026-09-19 but were still listed as unreleased, and their two `Changed` headings are merged into one.

### Fixed

- A player without permission who places a `[Warp]` sign is now told the node they need, `de.placeWarpSign`. The denial message named `medievalessentials.placeWarpSign`, which does not exist, so granting it did nothing; it also called the sign a "spawn selection sign" and left the quote around the node unclosed.
- Right-clicking a `[Warp]` sign whose lines 2–4 are not all integers now tells the player `This warp sign's coordinates are not valid.` Previously nothing happened for the player, and a line without a plugin prefix or log level was printed to the console via `System.out`. The console now gets a warning through the plugin's logger that names the sign's location and the offending value. Only coordinate parsing is caught, so a failure during the teleport itself is no longer hidden behind the same message.
- `/de label "<name>"` with an empty main hand now replies `You must be holding an item in your main hand!` and stops. The empty-hand check compared the held item to `null`, but the server hands back an `AIR` stack rather than `null`, so the check never matched and the command threw a `NullPointerException` instead. An item that cannot carry a name now gets `That item can't be renamed.` rather than the same exception.
- `/de gm` with a mode other than `0`, `1`, or `2` (for example `/de gm 3` or `/de gm creative`) now replies with the usage line. Previously it changed nothing, said nothing, and still reported success.
- The usage lines for `/de flyspeed` and `/de gm` now include the `/de` prefix. They read `/flyspeed` and `/gm`, which the server answers with `Unknown command`.

## [2.4.0] – 2026-09-19

### Added

- The plugin now reports usage events — `startup` on enable, `command` on each of its commands — to the author's trace server so it is known which plugins are in use. Events carry the plugin name, the event name, and the plugin version or command name; nothing about players or the server. Reporting runs off the main thread, never delays a tick, drops silently when the server is unreachable, and is turned off with `usage-reporting.enabled: false` in `config.yml`. The default config carries the plugin's key, so reporting is active out of the box unless turned off — including on servers upgraded from a version before the `usage-reporting` block existed, whose `config.yml` is never rewritten: the plugin reads the bundled defaults for any key the file lacks.
- A `Dev Release` workflow, which republishes a rolling `dev` prerelease of `master` on every non-documentation push. This is what Dan's Plugin Manager's experimental channel installs from: `/dpm get dansessentials --experimental` reads `releases/tags/dev`, so without it there is nothing for that command to download. The prerelease is unreleased, unreviewed code and is marked as such.

### Changed

- Usage reporting is now disclosed on every startup: the plugin logs whether reporting is on (and what is sent, and how to turn it off) or off (and why). A `config.yml` from before the `usage-reporting` block existed is completed with the bundled values so the switch is visible on disk. Two new ways to turn reporting off: `enabled: false` in `plugins/trace/config.yml` (created on first start, shared by every plugin that reports this way) and the environment variables `TRACE_USAGE_REPORTING=off` or `DO_NOT_TRACK=1`. The vendored trace client is 0.2.0. Nothing about what is sent changed; see the README's Usage reporting section.
- The contributor documentation now describes the repository as it actually is. `CONTRIBUTING.md` and `.github/copilot-instructions.md` routed contributors through a `develop` branch that no longer exists, so the very first command in the "Making Changes" walkthrough failed; both now name `master`, the repository's default and only long-lived branch. `CONTRIBUTING.md` and `README.md` also stated that no automated tests were configured, which stopped being true once the JUnit 5 and Mockito suite under `src/test/java/` was added; both now describe that suite and the `mvn test` command that runs it.

### Fixed

- `/de mute <player>` and `/de unmute <player>` now key the muted-player list on the resolved player's name rather than the spelling that was typed. Because the server resolves a name case-insensitively and by prefix, `/de mute ste` against `Steve` previously stored `ste`, reported success, and left the player able to chat, since the chat listener looks players up under their canonical name. The same spelling mismatch made a muted player impossible to unmute under a different spelling, allowed one player to be muted twice under two spellings, and let a partial name that resolved back to the sender slip past the "You can't mute yourself!" guard.
- `/de fly <player>` no longer throws a `NullPointerException` when the named player is offline; it now reports `That player isn't online.` and stops, matching the guard every other target-taking command already had.
- The permission-denied message for `/de fly <player>` now names `de.fly.others`, the node the command actually checks and the one declared in `plugin.yml`. It previously named `me.fly.others`, a node that does not exist, so a player who asked an administrator for exactly what the message said would still have been refused.
- The `Dev Release` workflow now retries publishing the `dev` prerelease before giving up. The release and its tag have to be deleted and recreated for the tag to move to the new commit, and a transient API failure inside that window previously left the repository with no `dev` release at all until the workflow was re-run by hand. Each attempt now starts from a clean slate, and an exhausted retry fails loudly.
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
