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
