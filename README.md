# jtidy5
HTML5 Tidy for Java (vers. 2017-09-05) 
JTidy is a Java port of HTML Tidy, a HTML5 syntax checker and pretty printer. Like its non-Java cousin, JTidy can be used as a tool for cleaning up malformed and faulty HTML. In addition, JTidy provides a DOM interface to the document that is being processed, which effectively makes you able to use JTidy as a DOM parser for real-world HTML.

JTidy was written by Andy Quick, who later stepped down from the maintainer position. Now JTidy is maintained by a group of volunteers. 


Recently I moved all my sites onto a new server. I use Duplicity and Backupninja to perform weekly backups of my server. While configuring backups on the new server, I kept encountering a strange error:
1
2
3
Error: gpg: using "D5673F3E" as default secret key for signing 
Error: gpg: signing failed: Inappropriate ioctl for device 
Error: gpg: [stdin]: sign+encrypt failed: Inappropriate ioctl for device
It turns out this error is due to changes in GnuPG 2.1, which only recently landed in Debian Testing. The error occurs because GnuPG 2.1 by default ignores passphrases passed in via environment variables or stdin, and is trying to show a pinentry prompt. "Inappropriate ioctl for device" is thrown because the Backupninja script is not running through a TTY, so there's no way to actually render the prompt.
To solve the problem, you need to enable loopback pinentry mode. Add this to ~/.gnupg/gpg.conf:
1
2
use-agent 
pinentry-mode loopback
And add this to ~/.gnupg/gpg-agent.conf, creating the file if it doesn't already exist:
1
allow-loopback-pinentry
Then restart the agent with echo RELOADAGENT | gpg-connect-agent and you should be good to go!
