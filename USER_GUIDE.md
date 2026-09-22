# User Guide

## Prerequisites

- A Spigot or Paper Minecraft server (1.13 or later).
- The Dan's Essentials plugin jar placed in your server's `plugins/` folder.
- Server operator (`op`) status or the appropriate permission nodes for commands you wish to use.

## First Steps

After installing the plugin and restarting your server:

1. Join your server.
2. Run `/de help` to see the list of available commands.
3. Review the [Configuration Guide](CONFIG.md) to adjust plugin settings if needed.

## Common Scenarios

### Toggling Flight

An operator can toggle flight for themselves or another player:

```
/de fly           # Toggle your own flight
/de fly <player>  # Toggle flight for another player (requires de.fly.others)
```

### Setting Fly Speed

Adjust your flight speed (1–10):

```
/de flyspeed <number>
```

### Switching Gamemode

```
/de gm 0   # Survival
/de gm 1   # Creative
/de gm 2   # Spectator
```

### Broadcasting a Server-Wide Message

```
/de broadcast "Your message here"
```

The quotes are required; a message typed without them is rejected.

### Muting and Unmuting Players

```
/de mute <player>    # Mute a player until the next server restart
/de unmute <player>  # Unmute a player
```

### Viewing Another Player's Inventory

```
/de invsee <player>
```

### Clearing a Player's Inventory

```
/de clearinv <player>
```

### Renaming an Item

Hold the item you want to rename and run:

```
/de label "New Name"
```

The quotes are required; a name typed without them is rejected.

### Returning to Your Last Location

After teleporting, return to your previous location:

```
/de back
```

If you have not teleported yet, this returns you to where you joined the server. If no previous location is known, an error message is shown instead.

### Getting Your Current Coordinates

```
/de getpos
```

### Warp Signs

Operators can place warp signs to allow players to teleport to predefined coordinates. Create a sign with the following format:

```
[Warp]
<x>
<y>
<z>
```

Each of lines 2–4 must contain an integer coordinate. Requires the `de.placeWarpSign` permission to create and `de.usewarpsign` to use.

### New Player Welcome

When a player joins the server for the first time, everyone online is sent `Welcome <name> to the server!`. This happens automatically and has no permission or configuration switch.

## Permissions

| Permission Node  | Default | Description                                      |
|------------------|---------|--------------------------------------------------|
| `de.help`        | true    | View the help message                            |
| `de.getpos`      | true    | Get your current coordinates                     |
| `de.usewarpsign` | true    | Use warp signs to teleport                       |
| `de.back`        | op      | Return to your last location after a teleport, or your join location if you haven't teleported yet |
| `de.broadcast`   | op      | Broadcast a message to all online players        |
| `de.clearinv`    | op      | Clear a player's inventory                       |
| `de.fly`         | op      | Toggle flight for yourself                       |
| `de.fly.others`  | op      | Toggle flight for another player                 |
| `de.flyspeed`    | op      | Set your fly speed                               |
| `de.gm`          | op      | Change gamemode                                  |
| `de.gm.0`        | op      | Change gamemode to survival                      |
| `de.gm.1`        | op      | Change gamemode to creative                      |
| `de.gm.2`        | op      | Change gamemode to spectator                     |
| `de.invsee`      | op      | View another player's inventory                  |
| `de.label`       | op      | Rename a held item                               |
| `de.mute`        | op      | Mute a player                                    |
| `de.unmute`      | op      | Unmute a player                                  |
| `de.vanish`      | op      | Reserved for future use; vanish is not currently implemented |
| `de.placeWarpSign` | op    | Place a warp sign                                |
