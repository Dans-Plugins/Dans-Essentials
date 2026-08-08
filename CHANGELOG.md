# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [3.0.0-SNAPSHOT-8-8-2026] – 2026-08-08

### Changed
- Dans-Essentials is now developed AI-first. Day-to-day feature work, grooming, review and maintenance run through AI agents working directly against this repository, with the maintainers setting direction and approving what lands. The major version bump marks that change in how the project is built — it is not a break in behaviour, configuration or stored data, and existing installations can upgrade in place. Released as `3.0.0-SNAPSHOT-8-8-2026`: the AI-first line has not yet been verified in live operation, and the dated snapshot designation stays until it has.

### Fixed

- Build workflow now triggers on `master` (the repo's actual default branch) instead of `main`/`develop`, so CI actually runs on pushes and pull requests.
- `/de back` no longer throws `IllegalArgumentException` when used by a player with no stored previous location; it now sends a friendly error message instead. Players also get a join location tracked as their initial "back" destination, so the command is meaningful even before their first teleport.

## [2.3.0]

### Added

- Initial documented release.
- Commands: `back`, `broadcast`, `clearinv`, `fly`, `flyspeed`, `gm`, `getpos`, `help`, `invsee`, `label`, `mute`, `unmute`.
- Warp sign support (`de.placeWarpSign`, `de.usewarpsign`).
- bStats metrics integration (plugin ID 9527).
- `debugMode` configuration option.
