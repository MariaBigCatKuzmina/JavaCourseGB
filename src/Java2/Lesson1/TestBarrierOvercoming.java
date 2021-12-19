package Java2.Lesson1;

import java.util.Arrays;
import java.util.Random;

public class TestBarrierOvercoming {
    private static Athletics[] athletes = new Athletics[5];
    private static Barrier[] barriers;
    private static Random rnd = new Random();

    public static void setBarriers(int barrierCount) {
        barriers  = new Barrier[barrierCount];
        for (int i = 0; i <= (barriers.length/2); i++) {
            barriers[i] = new Track(rnd.nextInt(2000));
        }
        for (int i = (barriers.length/2) + 1; i < barriers.length; i++){
            barriers[i] = new Wall(rnd.nextInt(250));
        }

        for (Barrier barriersItm: barriers) {

            System.out.println("["+barriersItm+"] ");
        }

    }

    public static void setAthletes(){
        athletes[0] = new Human("Vova", 1000, 120);
        athletes[1] = new Human("Sasha", 1500, 180);
        athletes[2] = new Cat("Barsic", 2000, 200);
        athletes[3] = new Cat("Murka", 2500, 180);
        athletes[4] = new Robot("R2D2", 10000,0);
    }
    public static String getAthleteName(Athletics athlete){
         return  athlete instanceof Cat ? ((Cat) athlete).getName():
                athlete instanceof Human ? ((Human) athlete).getName() :
                athlete instanceof Robot ? ((Robot) athlete).getModel() : "noName";

    }

    public static void main(String[] args) {
        setBarriers(5);
        setAthletes();
        for (Athletics athleteItm:athletes) {
            for (Barrier barrierItm:barriers) {
                 if (barrierItm.canOvercome(athleteItm)){
                    System.out.println(athleteItm+ " преодолел препятствие: " + barrierItm);
                }
                else
                {
                    System.out.println(athleteItm+ " не преодолел препятствие: " + barrierItm + " и выбывает из соревнований.");
                    break;
                }
            }
        }
    }
}
