/*JETER GUTIERREZ CSC221 FALL2017 DUE NOVEMBER 11 2017, ASSIGNMENT 4 USING
-Create class Carnivore,Herbivore and Plant.
-Carnivore and Herbivore need energy to survive.
-Carnivore eats Herbivore and Herbivore eats Plant to get energy.
-Moving and reproduction consumes energy.
-Carnivores and Herbivores will reproduce a newborn if they are on a certain age and certain level of energy. The child will be placed next to the parent.
-The simulation environment is a 2D grid (nxn cells).
-Plants grow at random time, random locations.
-Herbivores and Carnivores can move to North, South, East, West. 
-Setup a 16 x 16 grid, initialize it by random numbers of Carnivore, Herbivore and Plants. Run it for a specific numbers of iterations (32 is the min).
-Print the result for each iteration on the console.
-Carnivore char: @
-Herbivore char: &
-Plant char: #
-Free space char: -
-You should be completely object oriented.
*
*-----------------------------------------------------------------------
* MY IMPLEMENTATION AND DESIGN
* Moving North, South, East or West, will cost 2 energy and gain 1 age.
* Eating will cost 1 energy and gain 5 energy for a total net gain of 4 energy it will increase age by 1.
* Carnivores will only eat if their energy is <=10
* Herbivores will only eat if their enrgy is <=15
* Plants do not move.
* Considering that Herbivore live on average 6-9 years longer than a Carnivore would in real life--
* Carnivore will die at age 15 or energy 0
* Herbivore will die at age 22 or energy 0
* Plant will die at age 5
* Carnivore will reproduce as long as age is >=10 and Energy is >=15 it will cost 5 energy and increase age by 1;
* Herbivore will reproduce as long as age is >=5 and Energy is >=10 it will cost 5 energy and increase age by 1;
 *****************************************************************************************/
import java.util.Random;

abstract class Life {//USING PLYMORPHISM USING THE LIFE CLASS AS THE SUPER CLASS.

    private int energy;
    private int age;
    private char symbol;
    private int x;
    private int y;

    Life() {
        energy = 50;
        age = 0;
    }

    public void setX(int x) {//SETTING THE X COORDINATE
        this.x = x;
    }

    public void setY(int y) {//SETTING THE Y COORDINATE
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSymbol(char newSymbol) {//SETTING THE CLASS SYMBOL
        symbol = newSymbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void increaseEnergy() {
        this.energy = this.energy + 1;
    }

    public void decreaseEnergy() {
        this.energy = this.energy - 1;
    }

    public void increaseAge() {
        this.age = this.age + 1;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void reprocuce() {//REPRODUCE WILL BE CALLED WHEN REPRODUCTION IS DONE.
        this.energy = this.energy - 5;
    }

    public void eat() {//CALLED WHEN AN OBJECT EATS.
        this.energy = this.energy + 4;
    }

    public void moveNorth() {//MOING NORTH AND AFFECTING THE ENERGY.
        this.x = this.x - 1;
        decreaseEnergy();
        decreaseEnergy();
    }

    public void moveSouth() {//MOVING SOUTH AND AFFECTING THE ENERGY.
        this.x = this.x + 1;
        decreaseEnergy();
        decreaseEnergy();
    }

    public void moveEast() {//MOVING EAST AND AFFECTING THE ENERGY.
        this.y = this.y + 1;
        decreaseEnergy();
        decreaseEnergy();
    }

    public void moveWest() {//MOVING WEST AND AFFECTING THE ENERGY.
        this.y = this.y - 1;
        decreaseEnergy();
        decreaseEnergy();
    }
}

class Carnivore extends Life {//CREATING THE CARNIVORE CLASS USING POLYMORPHISM

    Carnivore() {
        super();
        super.setSymbol('@');
    }
}

class Herbivore extends Life {//CREATING THE HERBIVORE CLASS USING POLYMORPHISM

    Herbivore() {
        super();
        super.setSymbol('&');
    }
}

class Plant extends Life {//CREATING THE PLANT CLASS USING POLYMORPHISM

    Plant() {
        super();
        super.setSymbol('#');
    }
}

class Free extends Life {//CREATING AN EMPTY SLOT USING POLYMORPHISM

    Free() {
        super();
        super.setSymbol('-');
        super.setAge(0);
        super.setEnergy(0);
    }
}

public class GutierrezAssignment4 {

    public static Life[][] World = new Life[16][16];//THE 2D ARRAY THAT IS USED TO RUN THE SIMULATION.

    public static Life[][] aYearHasPassed(Life[][] Array) {//A FUNCTION THAT INCREASES THE OF EVERYTHING IN THE SIMULATION.
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Array[i][j].increaseAge();
            }
        }
        return Array;
    }

    public static void printArray(Life[][] Array) {//FUNCTION TO OUTPUT THE FUNCTION
        System.out.print("\n");
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(Array[i][j].getSymbol() + " ");
            }
            System.out.print("\n");
        }
    }

    public static Life[][] removingTheDead(Life[][] Array) {//FUNCTION THAT REMOVES THE DEAD CELLS FROM THE SIMULATION.
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (Array[i][j].getSymbol() == '@') {//REMOVES CARNIVORES IF THEY ARE 15 OR OLDER OR ENERGY IS 0;
                    if ((Array[i][j].getAge() >= 15) | (Array[i][j].getEnergy() <= 0)) {
                        Free temp = new Free();
                        Array[i][j] = temp;
                    }

                }
                if (Array[i][j].getSymbol() == '&') {//REMOVES HERBIVORES IF THEY ARE 22 OR OLDER OR ENERGY IS 0;
                    if ((Array[i][j].getAge() >= 22) | (Array[i][j].getEnergy() <= 0)) {
                        Free temp = new Free();
                        Array[i][j] = temp;

                    }

                }
                if (Array[i][j].getSymbol() == '#') {//REMOVES PLANTS AFTER AGE IS 5 OR GREATER
                    if (Array[i][j].getAge() >= 5) {
                        Free temp = new Free();
                        Array[i][j] = temp;
                    }
                }
            }
        }
        return Array;
    }

    public static Life[][] creatingGrid(Life[][] Array) {//CREATES THE GRID FOR THE SIMULATION.
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Random generator = new Random();
                int r = generator.nextInt(4);//RANDOMLY PICKS A NUMBER BETWEEN 0 AND 3 IN ORDER TO CHOOSE TO SET
                //A POSITION ON THE GIRD TO ONE OF THE FOLLOWING: CARNIVORE, HERBIVORE, PLANT,FREE
                if (r == 0) {
                    Herbivore temp = new Herbivore();
                    Array[i][j] = temp;
                }
                if (r == 1) {
                    Carnivore temp = new Carnivore();
                    Array[i][j] = temp;
                }
                if (r == 2) {
                    Plant temp = new Plant();
                    Array[i][j] = temp;
                }
                if (r == 3) {
                    Free temp = new Free();
                    Array[i][j] = temp;
                }
            }
        }
        return Array;
    }

    public static Life[][] movingPieces(Life[][] Array) {//FUNCTION TO MOVE THE CELLS ON THE GRID ALSO IMPLEMENTS EATING.
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Random generator = new Random();
                int r = generator.nextInt(4);
                if (r == 0) {//North
                    if (Array[i][j].getSymbol() == '@') {//Moving North For A Carnivore
                        if (i > 0) {
                            if (Array[i - 1][j].getSymbol() == ('-')) {//If it is empty it moves
                                Carnivore temp = new Carnivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveNorth();
                                Free temp1 = new Free();
                                Array[i - 1][j] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i - 1][j].getSymbol() == '&') {//IF HERBIVORE AND ENERGY IS <= 10 IT EATS IT.
                                if (Array[i][j].getEnergy() <= 10) {
                                    Carnivore temp = new Carnivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveNorth();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i - 1][j] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                    if (Array[i][j].getSymbol() == '&') {//Moving North for an Herbivore
                        if (i > 0) {
                            if (Array[i - 1][j].getSymbol() == '-') {//MOVES IF FREE
                                Herbivore temp = new Herbivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveNorth();
                                Free temp1 = new Free();
                                Array[i - 1][j] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i - 1][j].getSymbol() == '#') {//EATS PLANT IF ENERGY<=15
                                if (Array[i][j].getEnergy() <= 15) {
                                    Herbivore temp = new Herbivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveNorth();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i - 1][j] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                }
                if (r == 1) {//South
                    if (Array[i][j].getSymbol() == '@') {
                        if (i < 15) {
                            if (Array[i + 1][j].getSymbol() == '-') {//MOVES IF FREE SPACE.
                                Carnivore temp = new Carnivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveNorth();
                                Free temp1 = new Free();
                                Array[i + 1][j] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i + 1][j].getSymbol() == '&') {
                                if (Array[i][j].getEnergy() <= 10) {//EATS IF ENERGY <=10
                                    Carnivore temp = new Carnivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveNorth();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i + 1][j] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                    if (Array[i][j].getSymbol() == '&') {
                        if (i < 15) {
                            if (Array[i + 1][j].getSymbol() == '-') {//MOVES IF FREE
                                Herbivore temp = new Herbivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveSouth();
                                Free temp1 = new Free();
                                Array[i + 1][j] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i + 1][j].getSymbol() == '#') {
                                if (Array[i][j].getEnergy() <= 15) {//EATS IF ENERGY <=15
                                    Herbivore temp = new Herbivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveSouth();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i + 1][j] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                }
                if (r == 2) {//East
                    if (Array[i][j].getSymbol() == '@') {
                        if (j < 15) {
                            if (Array[i][j + 1].getSymbol() == '-') {//MOVES IF FREE
                                Carnivore temp = new Carnivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveEast();
                                Free temp1 = new Free();
                                Array[i][j + 1] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i][j + 1].getSymbol() == '&') {
                                if (Array[i][j].getEnergy() <= 10) {//EATS IF ENERGY <=10
                                    Carnivore temp = new Carnivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveEast();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i][j + 1] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                    if (Array[i][j].getSymbol() == '&') {
                        if (j < 15) {
                            {
                                if (Array[i][j + 1].getSymbol() == '-') {//MOVES IF FREE
                                    Herbivore temp = new Herbivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveEast();
                                    Free temp1 = new Free();
                                    Array[i][j + 1] = temp;
                                    Array[i][j] = temp1;
                                }
                                if (Array[i][j + 1].getSymbol() == '#') {
                                    if (Array[i][j].getEnergy() <= 15) {//EATS IF ENERGY <=15
                                        Herbivore temp = new Herbivore();
                                        temp.setAge(Array[i][j].getAge());
                                        temp.setEnergy(Array[i][j].getEnergy());
                                        temp.moveEast();
                                        temp.eat();
                                        Free temp1 = new Free();
                                        Array[i][j + 1] = temp;
                                        Array[i][j] = temp1;
                                    }
                                }
                            }
                        }
                    }
                }
                if (r == 3) {//West
                    if (Array[i][j].getSymbol() == '@') {
                        if (j > 0) {
                            if (Array[i][j - 1].getSymbol() == '-') {//MOVES IF FREE
                                Carnivore temp = new Carnivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveEast();
                                Free temp1 = new Free();
                                Array[i][j - 1] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i][j - 1].getSymbol() == '&') {
                                if (Array[i][j].getEnergy() <= 10) {//EATS IF ENERGY <=10
                                    Carnivore temp = new Carnivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveEast();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i][j - 1] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                    if (Array[i][j].getSymbol() == '&') {
                        if (j > 0) {
                            if (Array[i][j - 1].getSymbol() == '-') {//MOVES IF FREE
                                Herbivore temp = new Herbivore();
                                temp.setAge(Array[i][j].getAge());
                                temp.setEnergy(Array[i][j].getEnergy());
                                temp.moveEast();
                                Free temp1 = new Free();
                                Array[i][j - 1] = temp;
                                Array[i][j] = temp1;
                            }
                            if (Array[i][j - 1].getSymbol() == '#') {
                                if (Array[i][j].getEnergy() <= 15) {//EATS IF ENERGY <=15
                                    Herbivore temp = new Herbivore();
                                    temp.setAge(Array[i][j].getAge());
                                    temp.setEnergy(Array[i][j].getEnergy());
                                    temp.moveEast();
                                    temp.eat();
                                    Free temp1 = new Free();
                                    Array[i][j - 1] = temp;
                                    Array[i][j] = temp1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return Array;
    }

    public static Life[][] randomPlants(Life[][] Array) {//RANDOMLY PLACES A RANDOM NUMBER OF PLANTS IN RANDOM LOCATIONS.
        Random generator = new Random();
        int newPlants = generator.nextInt(31);//CAN PLACE BETWEEN 0 AND 30 PLANTS PER CALL.
        for (int i = 0; i < newPlants; i++) {
            int x = generator.nextInt(16);
            int y = generator.nextInt(16);
            if (Array[x][y].getSymbol() == '-') {
                Plant temp = new Plant();
                temp.setX(x);
                temp.setY(y);
                Array[x][y] = temp;
            }
        }
        return Array;
    }

    public static Life[][] reproduction(Life[][] Array) {//function for reproducing.
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (Array[i][j].getSymbol() == '@') {
                    if ((Array[i][j].getAge() >= 10) && (Array[i][j].getEnergy() >= 15)) {//CAN REPORDUCE IF AGE >=10 AND ENERGY >=15
                        Carnivore temp = new Carnivore();
                        if (i > 0) {
                            if (Array[i - 1][j].getSymbol() == (' ' | '#')) {//CAN PLACE CHILD ABOVE IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i - 1);
                                temp.setY(j);
                                Array[i - 1][j] = temp;
                            }
                        }
                        if (i < 15) {
                            if (Array[i + 1][j].getSymbol() == (' ' | '#')) {//CAN PLACE CHILD BELOW IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i + 1);
                                temp.setY(j);
                                Array[i + 1][j] = temp;
                            }
                        }
                        if (j < 15) {
                            if (Array[i][j + 1].getSymbol() == (' ' | '#')) {//CAN PLACE CHILD TO THE RIGHT IF FREE OR PLANT 
                                Array[i][j].reprocuce();
                                temp.setX(i);
                                temp.setY(j + 1);
                                Array[i][j + 1] = temp;
                            }
                        }
                        if (j > 0) {
                            if (Array[i][j - 1].getSymbol() == (' ' | '#')) {//CAN PLACE LEFT IF PLANT OR FREE
                                Array[i][j].reprocuce();
                                temp.setX(i);
                                temp.setY(j - 1);
                                Array[i][j - 1] = temp;
                            }
                        }
                    }

                }
                if (Array[i][j].getSymbol() == '&') {
                    if ((Array[i][j].getAge() >= 5) && (Array[i][j].getEnergy() >= 10)) {//CAN REPRODUCE IF AGE >=5 AND ENERGY >=10
                        Herbivore temp = new Herbivore();
                        if (i > 0) {
                            if (Array[i - 1][j].getSymbol() == (' ' | '#')) {//CAN PLACE ABOVE IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i - 1);
                                temp.setY(j);
                                Array[i - 1][j] = temp;
                            }
                        }
                        if (i < 15) {
                            if (Array[i + 1][j].getSymbol() == (' ' | '#')) {//CAN PLACE BELOW IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i + 1);
                                temp.setY(j);
                                Array[i + 1][j] = temp;
                            }
                        }
                        if (j < 15) {
                            if (Array[i][j + 1].getSymbol() == (' ' | '#')) {//CAN PLACE RIGHT IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i);
                                temp.setY(j + 1);
                                Array[i][j + 1] = temp;
                            }
                        }
                        if (j > 0) {
                            if (Array[i][j - 1].getSymbol() == (' ' | '#')) {//CAN PLACE LEFT IF FREE OR PLANT
                                Array[i][j].reprocuce();
                                temp.setX(i);
                                temp.setY(j - 1);
                                Array[i][j - 1] = temp;
                            }
                        }
                    }
                }
            }
        }
        return Array;
    }

    public static void simulation(Life[][] Array,int iterations){//INTPUT THE ARRAY AND THE AMOUNT OF ITERATIONS TO RUN THE SIMULATION
        System.out.println("ASSIGNMENT 4 JETER GUTIERREZ WE WILL BE RUNNING THE SIMULATION WITH "+ iterations +  " ITERATIONS.");
        for (int iteration = 0; iteration < iterations; iteration++) {
            Random generator = new Random();//GENERATES A RANDOM NUMBER TO DETERMINE IF PLANTING WILL HAPPEN
            int Planting = generator.nextInt(3);
            System.out.println("After Iteration: " + iteration);
            Array = movingPieces(Array);//MOVES ALL THE PIECES IN THE ARRAY CAN EITHER MOVE, REMAIN STILL OR EAT.
            Array = removingTheDead(Array);//REMOVES THE CELLS THAT ARE DEAD.
            Array = reproduction(Array);//ALLOWS PARENTS THAT CAN REPRODUCE TO REPRODUCE.
            Array = aYearHasPassed(Array);//INCREASES THE AGE OF EVERYTHING BY 1
            if (Planting == 1) {//THERE IS A 1/3 CHANCE OF PLANTING AT A RANDOM TIME.
                Array = randomPlants(Array);
            }
            printArray(Array);//PRINTS THE 16X16 ARRAY.
        }
    }
    public static void main(String[] args) {
        World = creatingGrid(World);//CREATES THE 16X16 GRID TO RUN THE SIMULATION ON.
        simulation(World, 101);//RUNS THE SIMULATION.
        System.out.println("WE ARE DONE WITH THE SIMULATION");
        
    }
}
