import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class TreasureHunt1 {
    private static final int GRID_SIZE = 10;
    private static final char EMPTY = '.';
    private static final char PLAYER = 'P';

    private static class Question {
        String question;
        String[] options;
        char correctOption;

        Question(String question, String[] options, char correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    private static List<Question> questions = new ArrayList<>(Arrays.asList(
    new Question("What does CPU stand for?", new String[]{"Central Processing Unit", "Central Processing Union", "Computer Personal Unit", "Central Processor Unit"}, 'a'),
    new Question("What is a large storage device?", new String[]{"RAM", "SSD", "Harddisk", "Flash Drive"}, 'c'),
    new Question("What is the main circuit board?", new String[]{"Motherboard", "Processor", "Chipset", "RAM"}, 'a'),
    new Question("What does SSD stand for?", new String[]{"Solid State Drive", "Solid Storage Disk", "Secondary Storage Device", "Small Storage Device"}, 'a'),
    new Question("What does RAM stand for?", new String[]{"Random Access Memory", "Read Access Memory", "Rapid Access Memory", "Run Access Memory"}, 'a'),
    new Question("What does GPU stand for?", new String[]{"General Processing Unit", "Graphics Processing Unit", "General Processor Unit", "Graphics Processor Unit"}, 'b'),
    new Question("What is the primary OS for Mac?", new String[]{"Windows", "Linux", "MacOS", "Unix"}, 'c'),
    new Question("What is a common web browser?", new String[]{"Chrome", "Firefox", "Edge", "Safari"}, 'b'),
    new Question("What does HTTP stand for?", new String[]{"HyperText Transfer Protocol", "HyperText Transmission Protocol", "HyperText Transfer Program", "HyperText Transport Protocol"}, 'a'),
    new Question("What is the primary language for web development?", new String[]{"Python", "Java", "JavaScript", "C++"}, 'c'),
    new Question("What does URL stand for?", new String[]{"Uniform Resource Locator", "Uniform Resource Link", "Uniform Reference Locator", "Uniform Reference Link"}, 'a'),
    new Question("What is something that can be copied but never seen, can be modified but never touched, and can be destroyed but never broken?", new String[]{"Memory", "Code", "Shadow", "Image"}, 'b'),
    new Question("I am a structure where information is stored and retrieved. What am I, if I can be a stack, queue, or tree?", new String[]{"Pointer", "Database", "Algorithm", "Data structure"}, 'd'),
    new Question("I can be split into 0s and 1s, but I am not binary myself. I am essential for instructions but am not a CPU. What am I?", new String[]{"Byte", "Bit", "Hardware", "Software"}, 'a'),
    new Question("What allows you to perform multiple tasks simultaneously, but never truly does any task at the same time?", new String[]{"Time-sharing", "Hyper-threading", "Parallel processing", "Multitasking in computers"}, 'd'),
    new Question("I am a system that can solve complex problems quickly, but when the problem is simple, I slow down. What am I?", new String[]{"A computer", "An algorithm", "A neural network", "A processor"}, 'b'),
    new Question("What is a device that listens to all your commands but can’t speak back to you?", new String[]{"A keyboard", "A touchpad", "A speaker", "A microphone"}, 'd'),
    new Question("What grows only when it is cut, and thrives when you use it less?", new String[]{"A beard", "A firewall", "A hedge", "A tree"}, 'a'),
    new Question("I can hold immense amounts of data, yet I am neither heavy nor tangible. What am I?", new String[]{"A hard drive", "A USB stick", "RAM", "Cloud storage"}, 'd'),
    new Question("What can be used to encrypt your secrets but never needs a key to unlock?", new String[]{"A password", "A lock", "A safe", "A cipher algorithm"}, 'd'),
    new Question("I am invisible but essential, and without me, no website will function. What am I?", new String[]{"JavaScript", "CSS", "HTML", "Internet protocol"}, 'd'),
    new Question("I travel around the world but stay in one corner. What am I?", new String[]{"A map", "A globe", "A postage stamp", "A satellite"}, 'c'),
    new Question("I am both a wave and a particle, and without me, you wouldn't see colors. What am I?", new String[]{"Electricity", "Light", "Heat", "Sound"}, 'b'),
    new Question("I am invisible but exert force, and I can bend the path of objects. What am I?", new String[]{"Magnetism", "Electricity", "Gravity", "Wind"}, 'c'),
    new Question("The more you have of me, the less you see. What am I?", new String[]{"Fog", "Smoke", "Darkness", "Light"}, 'c'),
    new Question("I am measured in joules, and I can cause change but never be created or destroyed. What am I?", new String[]{"Power", "Energy", "Work", "Force"}, 'b'),
    new Question("I am essential for life, yet you can’t see me or touch me, and I am part of every cell in your body. What am I?", new String[]{"Oxygen", "Carbon", "Nitrogen", "Water"}, 'a'),
    new Question("I am not alive, yet I grow; I don't have lungs, but I need air; I don’t have a mouth, but water kills me. What am I?", new String[]{"A virus", "A tree", "Fire", "A fungus"}, 'c'),
    new Question("I can turn into gas, liquid, or solid, yet I’m always the same element. What am I?", new String[]{"Water", "Carbon", "Nitrogen", "Oxygen"}, 'b'),
    new Question("I am used to make energy, but I am not consumed; I am found in every living thing. What am I?", new String[]{"Oxygen", "Glucose", "ATP (adenosine triphosphate)", "Carbon dioxide"}, 'c'),
    new Question("I react with metals and produce hydrogen gas; my number is 1 on the periodic table. What am I?", new String[]{"Helium", "Oxygen", "Nitrogen", "Hydrogen"}, 'd'),
    new Question("I can live without a brain but need a heart. What am I?", new String[]{"A tree", "A fungus", "A jellyfish", "A bacteria"}, 'c'),
    new Question("I can be inherited but not always seen, and I influence how you look and act. What am I?", new String[]{"Culture", "Genes", "Environment", "Diet"}, 'b'),
    new Question("I am made of cells, but I am not alive. I grow and change, but I am not an organism. What am I?", new String[]{"A crystal", "A virus", "A plant", "A mineral"}, 'b'),
    new Question("I help you breathe but am not part of your body. I can be found in the air and without me, life is difficult. What am I?", new String[]{"Carbon dioxide", "Water vapor", "Nitrogen", "Oxygen"}, 'd'),
    new Question("I exist in a cycle of life and death, and I am essential for the flow of energy. What am I?", new String[]{"A carbon cycle", "A nitrogen cycle", "A food chain", "A water cycle"}, 'c'),
    new Question("I am not a number, but I help you measure things. I can be found in a triangle and a circle. What am I?", new String[]{"Degree", "Angle", "Pi", "Radius"}, 'c'),
    new Question("I am both a number and a position, and I have a square but never a cube. What am I?", new String[]{"A fraction", "A root", "A whole number", "A prime number"}, 'b'),
    new Question("What is the smallest positive integer that can be divided by all numbers from 1 to 9 without leaving a remainder?", new String[]{"1260", "5040", "630", "2520"}, 'd'),
    new Question("I have keys but no locks. I have space but no room. You can enter, but not leave. What am I?", new String[]{"A map", "A piano", "A house", "A keyboard"}, 'd'),
    new Question("What comes once in a minute, twice in a moment, but never in a thousand years?", new String[]{"A second", "The letter 'M'", "A decade", "A century"}, 'b'),
    new Question("I can be cracked, made, told, and played. What am I?", new String[]{"A riddle", "A code", "A joke", "A song"}, 'c'),
    new Question("I am tall when I’m young, and I’m short when I’m old. What am I?", new String[]{"A building", "A candle", "A tree", "A shadow"}, 'b'),
    new Question("I am light as a feather, yet the strongest man can't hold me for much longer than a minute. What am I?", new String[]{"A leaf", "A feather", "A balloon", "Breath"}, 'd'),
    new Question("What can be broken, but is never held?", new String[]{"A rule", "A promise", "A heart", "A glass"}, 'b'),
    new Question("The more you take, the more you leave behind. What am I?", new String[]{"Knowledge", "Money", "Time", "Footsteps"}, 'd'),
    new Question("I am full of keys but can't open any door. What am I?", new String[]{"A lock", "A piano", "A code", "A keyboard"}, 'b'),
    new Question("What has a heart that doesn't beat?", new String[]{"An artichoke", "A statue", "A clock", "A painting"}, 'a'),
    new Question("I have cities but no houses, forests but no trees, and rivers but no water. What am I?", new String[]{"A globe", "A map", "A picture", "A puzzle"}, 'b'),
    new Question("I have a neck but no head, two arms but no hands. What am I?", new String[]{"A shirt", "A tree", "A robot", "A clock"}, 'a'),
    new Question("I can travel around the world but stay in one corner. What am I?", new String[]{"A letter", "A map", "A postcard", "A postage stamp"}, 'd')
 ));

    private static boolean askQuestion(JFrame frame) {
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No more questions available.");
            return true;
        }

        Random random = new Random();
        int index = random.nextInt(questions.size());
        Question question = questions.remove(index);

        String answer = JOptionPane.showInputDialog(frame, question.question + "\na) " + question.options[0] + "\nb) " + question.options[1] + "\nc) " + question.options[2] + "\nd) " + question.options[3]);

        if (answer != null) {
            char userAnswer = answer.toLowerCase().charAt(0);
            if (userAnswer == question.correctOption) {
                return true;
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect! Next question.");
                return askQuestion(frame);
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Treasure Hunt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        frame.add(panel);

        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        int playerX = 0, playerY = 0;
        int treasureX, treasureY;

        Random random = new Random();
        treasureX = random.nextInt(GRID_SIZE);
        treasureY = random.nextInt(GRID_SIZE);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = EMPTY;
                JButton button = new JButton(String.valueOf(grid[i][j]));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        int x = panel.getComponentZOrder(button) / GRID_SIZE;
                        int y = panel.getComponentZOrder(button) % GRID_SIZE;

                        if (askQuestion(frame)) {
                            grid[x][y] = PLAYER;
                            button.setText(String.valueOf(PLAYER));

                            if (x == treasureX && y == treasureY) {
                                JOptionPane.showMessageDialog(frame, "Congratulations! You found the treasure!");
                                System.exit(0);
                            }
                        }
                    }
                });
                panel.add(button);
            }
        }

        frame.setVisible(true);
    }
}