# PGP-Chat

PGP-Chat encrypts chat messages with PGP.

## Usage

You must have gpg installed on your system and in your PATH.
It may be that gpg is in your PATH but this mod doesnt work
I have had an issue where Prism Launcher where gpg is removed from the PATH
If you use Prism Launcher or another launcher and dont wan't to mess around, just run
```bash
which gpg
```
in a terminal and put the output in gpg-path in the config.

When chatting prefix a message with
```
enc: 
```
to send an
encrypted message.
You must have at least 1 recipient specified.

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
