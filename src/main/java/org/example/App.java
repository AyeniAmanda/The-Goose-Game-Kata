package org.example;


import java.util.*;


public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static String lastPlayer = "";
    private static Random random = new Random();
    private static String response1 = "players: ";
    private static String response2 = "%s: already existing player";
    private static final List<Integer> goosePositions = Arrays.asList(5, 9, 14, 18, 23, 27);
    public static List<String> players = new ArrayList<>();
    public static Map<String, Integer> positions = new HashMap<>();
    public static List<Integer> diceRoll = new ArrayList<>();

    public static void main(String[] args) {

        System.out.print("Welcome To The Goose Game:\n");
        String line = "";
        while (!line.equals("exit")) {
            diceRoll = new ArrayList<>();
            System.out.println("Write a command: ");
            line = scanner.nextLine();
            System.out.println("system >>");

            if (line.startsWith("add player") && line.split(" ").length == 3) {
                String str = line.replaceFirst("add player ", "");
                if (str.length() > 0) {
                    addPlayer(str);
                } else System.out.println("invalid command");
            } else if (line.startsWith("move") && players.size() == 2) {
                String[] parts = line.split(" ");

                if (positions.containsKey(parts[1]) && !lastPlayer.equals(parts[1]) && checks(parts)) {
                    String name = parts[1];
                    int lastPosition = positions.get(name);
                    String lastPos = lastPosition == 0 ? "Start" : String.valueOf(lastPosition);
                    int newPosition = lastPosition + diceRoll.get(0) + diceRoll.get(1);
                    if (newPosition == 63) {
                        System.out.println(String.format("%s rolls %d, %d. %s moves from %s to %d. %s Wins!!", parts[1],
                                diceRoll.get(0), diceRoll.get(1), parts[1], lastPos, newPosition, parts[1]));
                        break;
                    } else if (newPosition > 63) {
                        System.out.println(String.format("%s rolls %d, %d. Pippo moves from %s to %d. %s bounces! %s returns to %d",
                                name, diceRoll.get(0), diceRoll.get(1), lastPos, newPosition, parts[1], parts[1], lastPosition));
                    } else if (newPosition == 6) {
                        bridge(name);
                    } else if (goosePositions.contains(newPosition)) {
                        goose(name, newPosition, lastPos);
                    } else if (positions.containsValue(newPosition)) {
                        prank(name, newPosition, lastPosition, lastPos);
                    } else {
                        System.out.println(String.format("%s rolls %d, %d. %s moves from %s to %d", parts[1], diceRoll.get(0), diceRoll.get(1), parts[1], lastPos, newPosition));
                        lastPosition = newPosition;
                        positions.put(name, lastPosition);
                    }
                    lastPlayer = name;
                } else {
                    System.out.println("wrong player");
                }
            } else System.out.println(" wrong command");
        }
    }

    static void goose(String name, int newPosition, String lastPos) {
        String output = String.format("%s rolls %d, %d. %s moves from %s to %d. The Goose. %s moves again and goes to %d",
                name, diceRoll.get(0), diceRoll.get(1), name, lastPos, newPosition, name, newPosition + diceRoll.get(0) + diceRoll.get(1));
        newPosition = newPosition + diceRoll.get(0) + diceRoll.get(1);
        while (goosePositions.contains(newPosition)) {
            newPosition += diceRoll.get(0) + diceRoll.get(1);
            output += String.format(", The Goose. %s moves again and goes to %d", name, newPosition);
        }
        System.out.println(output);
        positions.put(name, newPosition);
    }

    static void prank(String name, int newPosition, int lastPosition, String lastPos) {
        String otherPlayer = "";
        for (String player : players) {
            if (!player.equals(name)) otherPlayer = player;
        }
        System.out.println(String.format("%s rolls %d, %d. %s moves from %s to %d. On %d there is %s, who returns to %s",
                name, diceRoll.get(0), diceRoll.get(1), name, lastPos, newPosition, newPosition, otherPlayer, lastPos));
        positions.put(name, newPosition);
        positions.put(otherPlayer, lastPosition);
    }

    public static void bridge(String name) {
        String lastPos2 = positions.get(name) == 0 ? "Start" : String.valueOf(positions.get(name));
        System.out.printf("%s rolls %d, %d. %s moves from %s to The Bridge. %s jumps to 12%n",
                name, diceRoll.get(0), diceRoll.get(1), name, lastPos2, name);
        positions.put(name, 12);
    }

    public static void addPlayer(String name) {
        if (players.size() == 2) {
            System.out.println("max players reached");
            return;
        }
        if (players.contains(name)) {
            System.out.println(String.format(response2, name));
        } else {
            players.add(name);
            String finalResponse = response1 + String.join(", ", players);
            System.out.println(finalResponse);
            positions.put(name, 0);
            if (players.size() == 2) {
                System.out.println("system >> start game");
                lastPlayer = name;
            }
        }
    }

    public static boolean checks(String[] parts) {
        if (parts.length == 4 && parts[2].endsWith(",")) {
            try {
                int dice1 = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
                int dice2 = Integer.parseInt(parts[3]);
                if ((dice1 > 0 && dice1 < 7) && (dice2 > 0 && dice2 < 7)) {
                    diceRoll.add(dice1);
                    diceRoll.add(dice2);
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        } else if (parts.length == 2) {
            diceRoll = rollDice();
            return true;
        }
        return false;
    }

    public static List<Integer> rollDice() {
        int firstDice = random.nextInt(6) + 1;
        int secondDice = random.nextInt(6) + 1;
        return Arrays.asList(firstDice, secondDice);
    }
}