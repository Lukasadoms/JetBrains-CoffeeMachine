package machine;

import java.util.Scanner;

enum Drink {
    ESPRESSO("1", 250, 16, 0, 4, 1),
    LATTE("2", 350, 20, 75, 7, 1),
    CAPUCCINO("3", 200, 12, 100, 6, 1),
    BACK("back", 0, 0, 0, 0, 0);

    String command;
    int water;
    int beans;
    int milk;
    int money;
    int cups;

    Drink(String command, int water, int beans, int milk, int money, int cups) {
        this.command = command;
        this.water = water;
        this.beans = beans;
        this.milk = milk;
        this.money = money;
        this.cups = cups;
    }
}

enum State {
    BUY("buy"),
    FILL("fill"),
    REMAINING("remaining"),
    TAKE("take"),
    EXIT("exit");

    String command;

    State(String command) {
        this.command = command;
    }
}

public class CoffeeMachine {

    public static void main(String[] args) {
        RunningCoffeeMachine myMachine = new RunningCoffeeMachine();
        myMachine.makeCoffee();
    }
}

class RunningCoffeeMachine {
    int water;
    int milk;
    int beans;
    int cups;
    int money;

    RunningCoffeeMachine() {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }

    String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    void makeCoffee() {
        State STATE = null;
        String input = "";

        while (!input.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            input = getInput();
            for (State value: State.values()) {
                if (input.equals(value.command)) {
                    STATE = value;
                }
            }
            switch (STATE) {
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String coffeeType = getInput();
                    Drink COFFEE = null;
                    for (Drink value: Drink.values()) {
                        if (coffeeType.equals(value.command)) {
                            COFFEE = value;
                        }
                    }
                    if (!coffeeType.equals("back")) {
                        if (water < COFFEE.water) {
                            System.out.println("Sorry, not enough water!");
                            break;
                        } else if (beans < COFFEE.beans) {
                            System.out.println("Sorry, not enough coffee beans!");
                            break;
                        } else if (milk < COFFEE.milk) {
                            System.out.println("Sorry, not enough milk!");
                            break;
                        } else if (cups < COFFEE.cups) {
                            System.out.println("Sorry, not enough cups!");
                            break;
                        }
                        water -= COFFEE.water;
                        beans -= COFFEE.beans;
                        milk -= COFFEE.milk;
                        money += COFFEE.money;
                        cups -= COFFEE.cups;
                        System.out.println("I have enough resources, making you a coffee!");
                        break;
                    }
                    break;
                case FILL:
                    System.out.println("Write how many ml of water do you want to add:");
                    water += Integer.parseInt(getInput());
                    System.out.println("Write how many ml of milk do you want to add:");
                    milk += Integer.parseInt(getInput());
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    beans += Integer.parseInt(getInput());
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    cups += Integer.parseInt(getInput());
                    break;
                case REMAINING:
                    System.out.println("The coffee machine has:");
                    System.out.println(water + " of water");
                    System.out.println(milk + " of milk");
                    System.out.println(beans + " of coffee beans");
                    System.out.println(cups + " of disposable cups");
                    System.out.println(money + " of money");
                    break;
                case TAKE:
                    System.out.println("I gave you $" + money);
                    money = 0;
                    break;
            }
        }
    }
}
