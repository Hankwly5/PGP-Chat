# PGP-Chat

PGP-Chat encrypts chat messages with PGP.

## Usage
 
**Requirement:** GPG must be installed on your system.
 
**Finding your GPG path:**
 
Some launchers (e.g. Prism Launcher) strip GPG from the system PATH, which will prevent the mod from working. Run this to find your GPG executable:
 
```bash
which gpg
```
 
If the mod isn't working, paste that output into the `gpg-path` field in the config. If it works without doing this, you can leave `gpg-path` blank.
 
**Sending encrypted messages:**
 
Prefix any chat message with `enc: ` to encrypt it before sending:
 
```
enc: Hello, this is a secret message
```

## Config

The config is available in your instances config folder at PGP-Chat/config.txt

By default it looks like this

```
# The fingerprint, email, or key id of your message's recipient's pgp key (must be in gpg keyring), you can add more recipient: lines to add more recipients
recipient: 
# The path to gpg, find this out by running which gpg in a terminal and putting the result here, if the mod works without doing this then there is no reason to do this
gpg-path:
```

You must have encrypted message recipients entered in the config and a player wanting to send an
encrypted message to you must have you entered in his config.

## Dependencies

Fabric API\
GPG (System)  

## License

This mod is available under the GPL-3.0 license. Feel free to learn from it and incorporate it in your own projects.
