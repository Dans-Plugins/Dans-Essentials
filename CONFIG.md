# Configuration Guide

The configuration file is located at `plugins/DansEssentials/config.yml` and is created automatically on first run.

Options are listed in the order they appear in the default `config.yml`.

---

## version

**Type:** string  
**Default:** set automatically to the current plugin version  
**Description:** Records the plugin version that last wrote this config file. Used internally to detect version mismatches and apply defaults for new options. Do not edit this value manually.

**Example:**

```yaml
version: v3.0.0-SNAPSHOT-8-8-2026
```

---

## debugMode

**Type:** boolean  
**Default:** `false`  
**Description:** When set to `true`, the plugin outputs additional diagnostic messages to the server console. Useful for troubleshooting. Disable in production.

**Example:**

```yaml
debugMode: false
```

---

## usage-reporting

**Type:** section  
**Default:** `enabled: true`, `endpoint: https://trace.danielstephenson.dev`, `key:` the plugin's key  
**Description:** When the plugin is enabled, and each time one of its commands is used, a small event is sent to the author's [trace](https://github.com/Stephenson-Software/trace-client-java) server so it is known which plugins are actually in use. An event carries the plugin's name, the event name (`startup` or `command`), and either the plugin version or the command name — nothing about players, the world, or the server. Sending happens off the main thread, never delays a tick, and is dropped silently if the server cannot be reached.

| Key | Default | Description |
|-----|---------|-------------|
| `usage-reporting.enabled` | `true` | Whether the plugin reports usage events. Set to `false` to turn it off. |
| `usage-reporting.endpoint` | `https://trace.danielstephenson.dev` | The trace server events are sent to. |
| `usage-reporting.key` | the plugin's key | Identifies this plugin to the trace server so reports are attributed to it. Not a secret: it ships in the default config and can only report as DansEssentials. Empty means reporting is off regardless of `enabled`. |

Servers upgraded from a version before this block existed keep their `config.yml` as it is; the plugin reads the bundled defaults for any key the file lacks, so reporting is active there too unless turned off.

**Example:**

```yaml
usage-reporting:
  enabled: false
```
