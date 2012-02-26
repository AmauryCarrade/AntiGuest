AntiGuest
=========

Description:
------------
AntiGuest prevents players from doing specific things like building and fighting, which can be used to grief.

Every prevention has its own configurable message which will be printed to the player. This message supports color codes (&0, &1, ..., &e, &f, &k)

**How does this work?**

AntiGuest uses permissions to determine whether a player is allowed to do a specific action.
So you should (not necessarily needed, but recommended) install a permission manager like bPermissions or GroupManager along with AntiGuest.
Usually you only want to prevent your guests from doing things like breaking and placing blocks (griefing),
so should ***not*** give your guests group the permission to do so.
However your accepted players should by able to do these actions.
That can be achieved by adding the permission "antiguest.preventions.*" or a action specific permission to the player or his group.
As you might see, this system allows more than just "guests can nothing, members everything".
You could for example stagger the permission like "guests can nothing but chat; trails can chat, place and break blocks; members can everything"
and even more complex setups, it's completely up to you.

**Watch out! Tips for common mistakes**

- All prevents are disabled by default since version 3.0
- The permissions are all lowercase, check that before you report AntiGuest not working

Preventions:
---------
- **drop** -- prevents from dropping specific items *
- **fish** -- prevents from fishing
- **vehicle** -- prevents from using vehicles (entering, placing, destroying, pushing)
- **door** -- prevents from opening or closing doors (including fence gates)
- **chest** -- prevents from accessing chests
- **chat** -- prevents from chatting
- **changesign** -- prevents from changing signs
- **jukebox** -- prevents from using jukeboxes
- **repeater** -- prevents from changing the repeater delays
- **button** --  prevents from pushing buttons
- **brew** -- prevents from brewing
- **command** -- prevents from using specific commands *
- **furnace** -- prevents from accessing furnaces
- **dispenser** -- prevents from accessing dispensers
- **workbench** -- prevents from accessing workbenches
- **breakblock** -- prevents from breaking blocks
- **lever** -- prevents from using levers
- **waterbucket** -- prevents from using water buckets
- **move** -- prevents from moving too far
- **placeblock** -- prevents from placing blocks
- **lavabucket** -- prevents from using lava buckets
- **cake** -- prevents from eating cakes
- **pvp** -- prevents from damaging other players
- **monster** -- prevents from getting targeted by mosters
- **pickup** -- prevents from picking up specific items *
- **bed** -- prevents from sleeping
- **pressureplate** -- prevents from triggering pressure plates
- **tame** -- prevents from taming animals
- **hunger** -- prevents from starvation
- **noteblock** -- prevents from using noteblocks
- **item** -- prevents from using specific items *
- **shear** -- prevents from shearing animals
- **bow** -- prevents from shooting bows
- **spam** -- prevents from spam
- **sneak** -- prevents from sneaking (the player will still duck, but the name above him stays visible)
- **enchant** -- prevents from enchanting specific items *
- **afk** -- prevents from idling players by kicking them after a configured time
- **damage** -- prevents from getting damaged *

\* These preventions are filterable which means that they're able to prevent a subset of actions

For example the command preventions which can either prevent the usage of all commands or just a specific set of commands based on a whitelist or blacklist

These preventions usually have at least 2 additional config entries: mode and list

- mode:
    - none: everything will be prevented (aliases: -1, nolist, all)
    - whitelist: the listed entries are ***NOT*** prevented (aliases: 0, white, positivlist)
    - blacklist: only the listed entries will be prevented (aliases: 1, black, negativlist)
- list:
    - the list of entries. this might be commands, item names, ...depending on what the prevention does
    - this list will be ignored if the mode is set to none

Commands:
---------
General syntax: **/antiguest <command\>** or **/ag <command\>**

- **help** -- prints a help text
- **can [player] <preventionname\>** -- checks whether the player (or another player) passes a prevention
- **list [all|*]** -- lists the active or all registered preventions
- **reload** -- reloads the plugin
- **debug** -- toggles the debug mode

Installation:
-------------
- Just put the AntiGuest into you plugins folder
- If your upgrading from an older version you might remove you config to get a fresh one
- restart/reload your server
- The configuration file will be generated/updated as soon as the plugin gets enabled

Permissions:
------------
- **antiguest.\*** -- Allows the player to do everything
    - **antiguest.commands.\*** -- Allows the player to run all commands
        - ** antiguest.commands.<command\>** -- Allows the player to run the specific command
    - **antiguest.preventions.\*** -- Allows the player to pass all preventions
        - **antiguest.preventions.<preventionname\>** -- Allows the player to pass the specific prevention

Configuration:
--------------
Self-explanatory

***[Source on Github](https://github.com/quickwango/AntiGuest)***

Plugin developed by Quick_Wango - [Minecraft Portal](http://mc-portal.de)
