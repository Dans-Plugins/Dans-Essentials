# Commands Reference

All commands are sub-commands of `/de` (alias: `/dansessentials`).

## General Commands

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

**Description:** Teleport back to your location before your last teleport.  
**Permission:** `de.back`  
**Usage:** `/de back`  
**Example:** `/de back`

---

## Flight Commands

### /de fly [player]

**Description:** Toggle flight on or off for yourself or another player.  
**Permission:** `de.fly` (self), `de.fly.others` (other player)  
**Usage:** `/de fly [player]`  
**Example:** `/de fly` or `/de fly Steve`

---

### /de flyspeed \<number\>

**Description:** Set your fly speed. Accepts a value from 0 to 10.  
**Permission:** `de.flyspeed`  
**Usage:** `/de flyspeed <number>`  
**Example:** `/de flyspeed 5`

---

## Gamemode Commands

### /de gm \<mode\>

**Description:** Change your gamemode. Accepts `0` (Survival), `1` (Creative), or `2` (Spectator).  
**Permission:** `de.gm`, `de.gm.0`, `de.gm.1`, or `de.gm.2`  
**Usage:** `/de gm <mode>`  
**Example:** `/de gm 1`

---

## Admin Commands

### /de broadcast \<message\>

**Description:** Broadcast a message to all players currently online.  
**Permission:** `de.broadcast`  
**Usage:** `/de broadcast <message>`  
**Example:** `/de broadcast "Server restart in 5 minutes!"`

---

### /de mute \<player\>

**Description:** Mute a player, preventing them from chatting until the next server restart.  
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

**Description:** Clear all items from the target player's inventory.  
**Permission:** `de.clearinv`  
**Usage:** `/de clearinv <player>`  
**Example:** `/de clearinv Steve`

---

### /de label \<name\>

**Description:** Rename the item currently held in your main hand.  
**Permission:** `de.label`  
**Usage:** `/de label <name>`  
**Example:** `/de label "Magic Sword"`
