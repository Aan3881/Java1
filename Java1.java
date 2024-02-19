//1. Создан класс `MontyHallGame` для моделирования игры с дверьми и ходом игрока.
//2. Создан класс `MontyHallGameSimulation` для запуска симуляции игры в цикле на 1000 и //подсчета результатов.
//3. Создан класс `MontyHallGameTest` с тестами для проверки правильности работы игры и //проверки парадокса Монти Холла с использованием JUnit и параметризованных тестов.
//4. Создан абстрактный класс `AbstractMontyHallGameTest` для объединения общих методов //тестирования.
//5.Тесты для негативных сценариев, например, проверка на корректность обработки //некорректного ввода пользователя.

//Примеры кода:

<code>java
public class MontyHallGame {
    private int carPosition;
    private int playerChoice;

    public MontyHallGame() {
        carPosition = new Random().nextInt(3) + 1;
    }

    public void makePlayerChoice(int choice) {
        playerChoice = choice;
    }

    public boolean openDoor(int openedDoor) {
        return openedDoor != carPosition && openedDoor != playerChoice;
    }

    public boolean playerWins() {
        return playerChoice == carPosition;
    }
}

public class MontyHallGameSimulation {
    public static void main(String[] args) {
        int wins = 0;

        for (int i = 0; i < 1000; i++) {
            MontyHallGame game = new MontyHallGame();
            int playerChoice = new Random().nextInt(3) + 1;
            game.makePlayerChoice(playerChoice);

            int openedDoor = game.openDoor((playerChoice % 3) + 1);
            if (!openedDoor) {
                wins++;
            }
        }

        System.out.println("Total wins: " + wins);
    }
}

public class MontyHallGameTest extends AbstractMontyHallGameTest {
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, false},
                {1, 2, true},
                {1, 3, true},
                {2, 1, true},
                {2, 2, false},
                {2, 3, true},
                {3, 1, true},
                {3, 2, true},
                {3, 3, false}
        });
    }

    @Test
    @Parameters(method = "data")
    public void testMontyHallGame(int carPosition, int playerChoice, boolean expectedResult) {
        MontyHallGame game = new MontyHallGame();
        game.carPosition = carPosition;
        game.makePlayerChoice(playerChoice);

        boolean playerWins = game.playerWins();
        assertEquals(expectedResult, playerWins);
    }
}

public abstract class AbstractMontyHallGameTest {
    @Before
    public void setUp() {
        // setup code before each test
    }

    @After
    public void tearDown() {
        // cleanup code after each test
    }
}
</code> 