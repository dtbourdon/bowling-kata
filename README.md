# Bowling Kata

This is an OOPish solution to the famous Bowling Score Keeper Kata.

This is a deceptively challenging Kata due to a few interesting quirks
of 10 pin bowling scoring.

1. A given roll can affect the score in as many as 3 different frames depending on the result of the frame.
2. The score keeper needs to be smart enough to manage the state of these multiple frames which participate in an individual frame's score.
3. From a display standpoint, the tenth frame is slightly different than the others.

The implementation in the oop package represents a typical OOP solution where a Frame is smart enough
reason about it's own state. The ScoreKeeper communicates with each Frame to keep the score.