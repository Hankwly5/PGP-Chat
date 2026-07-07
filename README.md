# PGP-Chat
[Download on GitHub and verify the signature!](https://github.com/Hankwly5/PGP-Chat/releases)[![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/Hankwly5/PGP-Chat/total?style=flat-square&logo=github&label=Github%20Downloads)](https://github.com/Hankwly5/PGP-Chat/releases)  

PGP-Chat encrypts chat messages with PGP.\
<img src="https://cdn.modrinth.com/data/Opss0FQG/images/55a4e587d1b65575144ef7df189fef73b7136104.gif" width="500" alt="Mod demo">

## Advisories

You must have GUI Pinentry for password protected PGP keys.

When you have No Chat Reports installed decryption doesn't\
work in singleplayer, but still works in multiplayer.

## Usage
 
**Requirement:** GPG must be installed, with the keys of anyone you want to send encrypted messages to, imported.

**Sending encrypted messages:**
 
Prefix any chat message with `enc:` to encrypt it before sending:
 
```
enc:Hello, this is an encrypted message.
```

## Configuration

The config file is available, from your instance's root folder, at config/PGP-Chat/config.txt

Unconfigured, it looks like this:

```
# The fingerprint, email, or key id of your message's recipient's pgp key (must be in gpg keyring), you can add more recipient: lines to add more recipients
recipient: 
# The path to gpg, you probably don't need to put anything here if your gpg is at a standard path
gpg-path: 
```

To send an encrypted message you have to put recipient key info in the config and a player wanting to send an
encrypted message to you must do the same.

## Dependencies

Fabric API\
GPG

## License

This mod is available under the GPL-3.0 license. Feel free to learn from it and incorporate it in your own projects.
