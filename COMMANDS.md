# Commands Reference

All commands are sub-commands of `/de` (alias: `/dansessentials`).

## General Commands

### /de

**Description:** Display basic plugin information, including version, author, and wiki link.  
**Usage:** `/de`  
**Example:** `/de`

---

### /de help

**Description:** Display the list of available commands.  
**Permission:** `de.help`  
**Usage:** `/de help`  
**Example:** `/de help`

---

### /de getpos

**Description:** Display your current world coordinates.  
**Permission:** `de.getpos`  
**Usage:** `/de getpos`  
**Example:** `/de getpos`

---

### /de back

**Description:** Teleport back to your location before your last teleport or to where you last died, whichever happened most recently, or to where you joined the server if neither has happened yet.  
**Permission:** `de.back`  
**Usage:** `/de back`  
**Example:** `/de back`

---

### /de tppos \<x\> \<y\> \<z\> [world]

**Description:** Teleport to the given coordinates, keeping the direction you are facing. Coordinates may be decimals; relative coordinates (`~`) are not supported. Without a world name you stay in your current world. A coordinate that is not a number is rejected with `Coordinates must be numbers.`, and an unknown world with `There is no world named <world>.`; neither moves you. If another plugin cancels the teleport, the command replies `The teleport was cancelled.`. Afterwards, `/de back` returns you to where you were.  
**Permission:** `de.tppos`  
**Usage:** `/de tppos <x> <y> <z> [world]`  
**Example:** `/de tppos 100 64 -250` or `/de tppos 0 70 0 world_nether`

---

## Flight Commands

### /de fly [player]

**Description:** Toggle flight on or off for yourself or another player.  
**Permission:** `de.fly` (self), `de.fly.others` (other player)  
**Usage:** `/de fly [player]`  
**Example:** `/de fly` or `/de fly Steve`

---

### /de flyspeed \<number\>

**Description:** Set your fly speed. Accepts a value from 1 to 10.  
**Permission:** `de.flyspeed`  
**Usage:** `/de flyspeed <number>`  
**Example:** `/de flyspeed 5`

---

## Gamemode Commands

### /de gm \<mode\>

**Description:** Change your gamemode. Accepts `0` (Survival), `1` (Creative), or `2` (Spectator); any other value is rejected with the usage line.  
**Permission:** `de.gm`, `de.gm.0`, `de.gm.1`, or `de.gm.2`  
**Usage:** `/de gm <mode>`  
**Example:** `/de gm 1`

---

## Admin Commands

### /de broadcast "\<message\>"

**Description:** Broadcast a message to all players currently online. The message must be wrapped in double quotes; without them the command replies `Message must be specified in between quotation marks.` and sends nothing.  
**Permission:** `de.broadcast`  
**Usage:** `/de broadcast "<message>"`  
**Example:** `/de broadcast "Server restart in 5 minutes!"`

---

### /de mute \<player\>

**Description:** Mute a player, preventing them from chatting until they are unmuted. Mutes are kept across server restarts.  
**Permission:** `de.mute`  
**Usage:** `/de mute <player>`  
**Example:** `/de mute Steve`

---

### /de unmute \<player\>

**Description:** Unmute a previously muted player.  
**Permission:** `de.unmute`  
**Usage:** `/de unmute <player>`  
**Example:** `/de unmute Steve`

---

### /de invsee \<player\>

**Description:** Open and interact with another player's inventory.  
**Permission:** `de.invsee`  
**Usage:** `/de invsee <player>`  
**Example:** `/de invsee Steve`

---

### /de clearinv \<player\>

**Description:** Clear all items from the target player's inventory. The sender is told `Cleared <player>'s inventory.` and the target is told `Your inventory has been cleared.`. If the target is not online, the command replies `That player isn't online.` and changes nothing.  
**Permission:** `de.clearinv`  
**Usage:** `/de clearinv <player>`  
**Example:** `/de clearinv Steve`

---

### /de label "\<name\>"

**Description:** Rename the item currently held in your main hand. The new name must be wrapped in double quotes; without them the command replies `New label must be specified between double quotes.` and changes nothing. With an empty main hand it replies `You must be holding an item in your main hand!`.  
**Permission:** `de.label`  
**Usage:** `/de label "<name>"`  
**Example:** `/de label "Magic Sword"`
