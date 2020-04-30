AdvancedAFK
===========

High performance Anti AFK plugin, for 1.15.2

Info
------
v0.0.1-Alpha

Developed by SebbaIndustries

Website: http://sebbaindustries.com

Source code: https://github.com/SebbaIndustries/AdvancedAFK


```
    Copyright 2020 SebbaIndustries
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```

|Command|Alias|Permission|Use|Additional Flags|
|-------|:---:|:--------:|---|:--------------:|
|/aafk --help|`--h`|`aafk.flag.help`|Shows all commands for AdvancedAFK|`N/A`|
|/aafk -lookup -p \<player>|`-l`|`aafk.flag.lookup`|Shows player status of the player and AFK time|`N/A`|
|/aafk -debug -p \<player>|`-d`|`aafk.flag.debug`|Shows all data AAFK has on that player|`N/A`|
|/aafk -cleanup (-force)|`-c`|`aafk.flag.cleanup`|Removes all AFK players from the server, `-force` removes players with bypass permission|`-f`|
|/aafk -list|`-ls`|`aafk.list`|Lists all AFK players, `-all` show all players|`-all`|
|/aafk -reload|`-r`|`aafk.flag.reload`|Reloads the plugin|`N/A`|

|Permission|Use|
|----------|---|
|aafk.*|Permission for all|
|aafk.kick.bypass|Plugin does not kick AFK players with this permission|
|aafk.kick.bypass.cleanup|Player does not get kicked by /aafk -c command (/aafk -c -f will kick all afk players!)|
|aafk.flag.*|Permission for all flags that plugin has|
|aafk.flag.help|Permission for help flag|
|aafk.flag.lookup|Permission for lookup flag|
|aafk.flag.debug|Permission for debug flag|
|aafk.flag.cleanup|Permission for cleanup flag|
|aafk.flag.cleanup.force|Permission for -f flag in cleanup flag|
|aafk.flag.list|Permission for list flag|
|aafk.flag.reload|Permission for reload flag|

