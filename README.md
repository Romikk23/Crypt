# Crypt
Created by [@Romikk23](https://t.me/Romiikk), August 2022.
## Encrypter and decrypter text ##

Simple algorithm, that encrypt text into picture and create new encrypted image, which differs by 0.5% by sight!
With encrypted image and seed-key that generated automaticaly, it can decrypt image and get the text.

String is split into characters, char is split into three, two, and three bites, and this block of bites one by one randomly insert into RGB pixel instead of the lower bits. It's provide minimal differents ( around 0.5% ) from original and encrypted image.
