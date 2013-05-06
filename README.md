# About

This repository contains the code for the project of the Distributed and Mobile Programming paradigms course. The goal of this project is to create a distributed scrabble game on android in ambienttalk.

# Assignment

## 1 Assignment

The purpose of this project is to create a digital decentralized distributed version of a word game
similar to Scrabble. This game works as follows: a number of players work collaboratively to
create words out of virtually lettered tiles. Players are organized in teams and each player has
a rack of letters. The goal of the game is to consume all the letters of the team by forming words.
In other words, the team that first consumes all its letters wins.

When two players of the same team are in communication range, they can see each others
letters. A player can then request particular letters of another team member to form a word.
Once a player forms a word, half of the letters forming the word get consumed for his/her team.
The player gets the opportunity to throw the other half of the letters to nearby opposite team
members. Those thrown letters get also consumed for the team when the opposite team members
acknowledge the letter(s) reception. If a player does not form a word for a while, a new letter
gets added to his/her rack.

When a player starts the weScrabble application, he/she chooses a team. The application
automatically generates a rack of (random) letters for that player. When two or more players of
the same team are in range, they can start forming words. Once a game has started other peers
can join the game, incrementing the number of letters that the team has to consume to win.


## 2 Non-Functional Requirements

The weScrabble game is a multi-player mobile application. Each participant of the game has
a (mobile) device running the application. There are some requirements w.r.t. the distributed
design of the game.

__Peer-to-peer design.__ The game should be designed in a peer-to-peer fashion. You can assume
that there is a WiFi network available where players meet and play together (e.g. a cafeteria
area). However, you __cannot__ assume a centralized server in your design to coordinate the
game, e.g to discover new players or to keep track of the game state (e.g. the consumed
letters of each team).

__Fault-tolerant design.__ You must assume that _every_ computational unit in the network can fail at
any point in time. In particular:

* Players may enter and leave the network at any point in time. However, you may
assume that player disconnections are temporal. If a player disconnects, other players
may have outdated game information, for example, about the state of his/her rack of
letters. The game information should get properly updated once players come online
again.

* Player disconnections should not hamper the game progress. For example, if a player
disconnects, the other team members can continue forming words.

* Message sends to remote peers can fail. Note that failures may be unreliably detected
using timeout as a heuristic.

Words are validated by means of a dictionary. You may use the provided dictionary.at
module.

You may assume that there is only one game being played at a time.

__Extra requirements (not obligatory):__

* Validate the words by achieving consensus amongst nearby players. For example, the
player needing to validate a word may start a poll with all nearby players.

* Explore team constraints to make the game more fair, challenging, competitive, etc..
For example, the application could adapt time period rates for the appearance of letters
so that small teams get less letters, or the time to form words so that bigger teams get
less time.

* Weaken the game assumptions. For example, adapt the application to take into account
permanent disconnections of players.

Being creative and adding additional requirements of your own or any of the extra requirements
to the project is appreciated.
