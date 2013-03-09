Breakout
========

This program is a reimplementation of the classic Breakout game, which was released in 1976 by Atari. The player bounces a ball on a paddle controlled by the keyboard, which bounces off the top and side walls of the screen. Rows of bricks exist at the top of the screen which are destroyed when the ball hits them. Points are awarded to the player for each brick destroyed and when all are destroyed, the next level, which is randomly generated, is presented. The player has 3 lives, which are lost when the ball touches the bottom of the screen. 
The player is presented with a main menu at the start of the game, which starts when <SPACE> is pressed. If the player loses, he has the option to restart. As in the original, there is no end to the game. In this implementation, levels are randomly generated, meaning that the game ends only when the player dies.

![Example](https://raw.github.com/zacoppotamus/Breakout/master/Screenshot%202.png)


To-Do/Bugs
---

There are two bugs currently in the game. When the player destroys 2 bricks at the same time, a third one, seemingly random, is destroyed as well. This happens because they are removed from the ArrayList, which is referenced by another thread milliseconds afterwards. But, of course for the time being this is a feature and not a bug. 

The most urgent bug-fix includes packaging the fonts used in the game with the source files to enable a uniform experience to anyone playing the game from a variety of different operating systems. In this installment of the game, the two fonts used in the game are provided along with the source code.
Potential enhancements to the game include the ability for new levels to be created by reading in a text file and the introduction of various power-ups, such as extra lives, slowing down the ball or faster paddle movement. There could also be an added audio component to the game, such as a soundtrack and sound effects for when the paddle hits the ball.

How To Play
---
```
javac Play.java
java Play
```

![Example](https://raw.github.com/zacoppotamus/Breakout/master/Screenshot%201.png)
