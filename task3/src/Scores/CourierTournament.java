package Scores;

import animals.Animal;
import mobility.Point;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a tournament where animals are competing in groups.
 * Extends the Tournament class and sets up the race with multiple threads for each animal.
 */
public class CourierTournament extends Tournament {

    /**
     * Constructs a CourierTournament with the specified distance.
     *
     * @param dis The distance of the tournament.
     */
    public CourierTournament(long dis) {
        super(dis);
    }

    /**
     * Sets up the tournament by initializing threads for each animal and referee.
     *
     * @param arrAnimals An ArrayList of arrays, where each array contains the animals competing in a group.
     */
    @Override
    public void setup(ArrayList<Animal[]> arrAnimals) {
        Scores scores = new Scores();

        Thread[][] animalThread = new Thread[arrAnimals.size()][];
        Thread[] referThread = new Thread[arrAnimals.size()];

        for (int i = 0; i < arrAnimals.size(); ++i) {
            AtomicBoolean startFlag = new AtomicBoolean(true);

            animalThread[i] = new Thread[arrAnimals.get(i).length];
            AtomicBoolean[] flags = new AtomicBoolean[arrAnimals.get(i).length];

            for (int j = 0; j < flags.length; ++j) {
                flags[j] = new AtomicBoolean(false);
            }

            // Start the first animal thread
            animalThread[i][0] = new Thread(new AnimalThread(arrAnimals.get(i)[0], this.getDis() / arrAnimals.get(i).length, startFlag, flags[0]));
            animalThread[i][0].start();

            // Start threads for the rest of the animals
            for (int j = 1; j < arrAnimals.get(i).length; ++j) {
                if(arrAnimals.get(i)[j].getType().equals("Terrestrial Animal")) {
                    arrAnimals.get(i)[j].setLocation(new Point(1078, 806));
                    animalThread[i][j] = new Thread(new AnimalThread(arrAnimals.get(i)[j], this.getDis() / arrAnimals.get(i).length, flags[j - 1], flags[j]));
                    animalThread[i][j].start();
                }else {

                arrAnimals.get(i)[j].getLocation().setX((int) (arrAnimals.get(i)[j - 1].getLocation().getX() + this.getDis() / arrAnimals.get(i).length));
                animalThread[i][j] = new Thread(new AnimalThread(arrAnimals.get(i)[j], this.getDis() / arrAnimals.get(i).length, flags[j - 1], flags[j]));
                animalThread[i][j].start();
                }
            }

            // Start the referee thread for the group
            referThread[i] = new Thread(new Referee("Group " + (i + 1), scores, flags[arrAnimals.get(i).length - 1]));
        }

        // Start the tournament thread
        tournThread = new TournamentThread(new AtomicBoolean(true), scores, arrAnimals.size());
    }
}
