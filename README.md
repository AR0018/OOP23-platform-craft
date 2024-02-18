# OOP-23 Platform Craft

## Project for the Unibo OOP exam 2023.

Platform craft is a platform game where you can build and play your own levels.
The editor offers a variety of elements to include in the level, including enemies, obstacles, elements of the map.

The play functionality allows you to test and play your own levels.

## Authors:

- Gabriele Abeni gabriele.abeni@studio.unibo.it
- Simone Carpi simone.carpi@studio.unibo.it
- Alessandro Ronchi alessandro.ronchi7@studio.unibo.it
- Daniele Zecchini daniele.zecchini2@studio.unibo.it

## User guide

**Play function**

By clicking "PLAY" in the start menu, the application will open a file chooser from which you can choose the level to play.
The only accepted format for a file is .json, so any file without that extension will not be opened.
By choosing a file, the chosen level will be opened in another window. We recommend to resize the window once it opens for a correct visualization.

**Editor function**

By clicking "EDITOR" in the start menu, the application will open the editor window.
Once the window opens, we recommend to resize the window for a correct visualization.

In the Editor, you can select an object to add to the level by clicking on one of the buttons on the list to the right.
After selecting a button, you can click inside the level to add the selected object to the level.

To remove an object, click on "REMOVE" and then click on the object you want to remove.
The remove function stays active until a level object is chosen from the list of buttons.

The editor won't allow to insert an object in the same position as another objects (the images cannot overlap).

After creating a level, you can save it by clicking on "SAVE".
If the configuration of the level is valid, a file chooser will be opened.
From the file chooser, the user can choose the location of the level to be saved.
The name of the created level MUST contain the .json extension at the end, otherwise the level will not be saved.

A level configuration is considered valid if the level has one Character and one End.
You cannot add multiple Characters to the level and the same goes for the End.

In the editor you can also edit an existing level by clicking on "LOAD" and choosing the level to open.

The "RESET" button deletes all the objects in the editor screen.

**Demo level**

The first time the application starts, it installs in the home directory of the user a
folder named PlatformCraft containing a demo level.
This level can be loaded, played and edited.

**Commands**
During the running game, inside the game section, the player can move the character in each direction by pressing the WASD 
and the arrows keys to choose where to move it Also, by the space bar, the character jumps 
(same behaviour by pressing W and the up arrows). 
## Resources

- Character and enemy images: https://free-game-assets.itch.io/free-tiny-hero-sprites-pixel-art
- Map elements images: https://ma9ici4n.itch.io/grass-tileset-pixel-art
- Piggy image: https://caz-creates-games.itch.io/piggy
- Explosion image: https://pimen.itch.io/fire-spell-effect-02
- Protest Strike font: https://fonts.google.com/specimen/Protest+Strike?classification=Display&subset=latin&noto.script=Latn
- Bungee font: https://fonts.google.com/specimen/Bungee?preview.text=QUIT&classification=Display&subset=latin&noto.script=Latn
