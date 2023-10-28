# Doodle jump game

### GitHub repo

https://github.com/iyubondyrev/doodle_jump

### How to build and run

Easy! Just do the following command in the main game (/doodle_jump) directory: 

For MacOs:


```zsh gradlew runGame``` (sudo may be required)   

For Linux:

```bash gradlew runGame``` (sudo may be required) 

For Windows:

```.\gradlew runGame``` 


### How to play

Easy! Just use <- and -> keys to move the doodle and jump as high as you can, your score will be saved. You can use *esc* to return to the main menu.


## Topics which learning goals we cover
* Cradle/Maven
* Git (working with GitHub)
* Advanced object-oriented programming

## Game features
* The Doodle jumps from one platform to another platform.
* The Doodle is teleported to the other border of the screen when it reaches the opposite one.
* The main goal is to reach the highest point by jumping.
* Game saves user's best result.

## Backlog

1. **Render the main screen with a play button.**

    After app staring, the main screen should show up with a play button and the initial picture.

2. **Render a screen with platforms and Doodle after the play button was pressed.**

    Enter the game and press the play button.

3. **Move the user's screen with jumps of Doodle and render new platforms.**

    While  jumping up, the camera will follow the user.

4. **Render a fall of Doodle if it has missed a platform.**

    Just miss one of the platforms and Doodle will fall.

5. **Show the user's score in the corner.**

    In the top corner the user's score should be indicated. Score of the user depends on the height which the Doodle achieves in the game.

6. **Show screen with the highest score.**

    After the end of the game screen should be rendered with the user’s score.
