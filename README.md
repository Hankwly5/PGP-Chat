# PGP-Chat

PGP-Chat encrypts chat messages with PGP.

## Usage
 
**Requirement:** GPG must be installed on your system.

**Sending encrypted messages:**
 
Prefix any chat message with `enc:` to encrypt it before sending:
 
```
enc:Hello, this is a secret message
```

## Config

The config is available in your instances config folder at PGP-Chat/config.txt

By default it looks like this

```
# The fingerprint, email, or key id of your message's recipient's pgp key (must be in gpg keyring), you can add more recipient: lines to add more recipients
recipient: 
# The path to gpg, you probably don't need to put anything here if your gpg is at a standard path
gpg-path: 
```

You must have encrypted message recipients entered in the config and a player wanting to send an
encrypted message to you must have you entered in his config.

## Dependencies

Fabric API\
GPG (System)

## License

This mod is available under the GPL-3.0 license. Feel free to learn from it and incorporate it in your own projects.
