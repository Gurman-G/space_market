import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class player {String name; double money; private byte golod; private double jajda; int rez; Map<String, Integer> inventory; boolean life;
    // конструктор класса player (игрок)
    public player (String name, int rez, double money, byte golod, double jajda, boolean life/*уровень жажды воды */) {
        this.name = name; 
        setGolod(golod);
        setJajda(jajda);
        this.money = money; 
        this.rez = rez;
        this.inventory = new HashMap<>();
        this.life = life;}
    // метод для вывода всех переменных объекта игрока     
    public String toString() {
        return "Ваше имя: " + name + " | У вас на балансе:  " + money + " | Ваши уровень голода: "
        + golod + " | Ваш уровень жажды: " + jajda;
    }  

    // гетеры для переменных private golod и jajda
    public byte getGolod() {return this.golod;}
    public double getJajda() {return this.jajda;}

    // сеттеры для переменных private golod jajda
    public void setGolod(byte golod) {
        if (golod > 25) {this.golod = 25;}
        else {this.golod = golod;}
    }

    public void setJajda(double jajda) {
        if (jajda > 35) {this.jajda = 35;}
        else {this.jajda = jajda;}
    }
    //метод для проверки уровня голода и жады, для вывода предупреждений 
    public void cheekStatus() {
        if (golod == 3 || golod == 2) {System.out.println("Ты проголодался, пора подкрепится");}
        else if (golod == 1) {System.out.println("Ты голоден, надо сроно поесть ");}
        else if (golod < 1) {System.out.println("Ты умер от голода, игра окончена. Твой рейтинг: " + rez);}
        else if (jajda == 7) {System.out.println("Ты хочешь пить, но это не критично");}
        else if (jajda == 4) {System.out.println("Тебя мучает жажда, выпей воды");}
        else if (jajda ==1) {System.out.println("Срочно выпей воды");}
        else if (jajda < 1) {System.out.println("Ты умер от жажды, игра окончена, твой рейтинг: " + rez);}
    }
}

// создание интерфейса, обязательных методов для переопределния наследниками абстрактного класса
interface Tradeable {
void SeelGoods(); // метод планета продает
void BuyGoods(); // мтод планета покупает
}
// создание абстрактного родительского класса для наследников 
abstract class planet implements Tradeable {String namePlanet; boolean kaf; boolean nal; boolean kor; List <String> seelGoods; List<String> buyGoods;
     Map<String, Double> buytMarket; Map<String, Double> seelMarket;
     // конструктор родительского класса планет (абстрактный )
    public planet (String namePlanet, boolean kaf, boolean nal, boolean kor, 
        List <String> seelGoods, List<String> buyGoods, Map<String, Double> buytMarket, Map<String, Double> seelMarket) {
        this.namePlanet = namePlanet; 
        this.kaf = kaf; 
        this.nal = nal; 
        this.kor = kor;
        this.seelGoods = new ArrayList<>(); 
        this.buyGoods = new ArrayList<>(); 
        this.buytMarket = buytMarket; 
        this.seelMarket = seelMarket;
        SeelGoods(); BuyGoods();;
    }
    
    // изьавление от возвращения null
    public double getSeelMarket(String goodName) {
        return seelMarket.getOrDefault(goodName, 0.0);}

    public double getBuyMarket (String goodName) {
        return buytMarket.getOrDefault(goodName, 0.0);}  
    
    // вывод определения на какой планете находиться игрок 
    public String toString() {
        return "Ты находишься на планете, " + namePlanet + " Сдесь " + (kaf ? " Атмосфера пригодна для жизни | " : 
        " Атмосфера не пригодна для жизни | ") + (nal ? " На данной планете есть нужный артефакт | " : 
        " На данной планете нет нужного артефакта | ") + (kor ? " На данной планете есть чудовища | " : 
        " На данной планете нет чудовищь");
    }  
    // вывод списка товаров которые продает планета 
    public void showSeelGoods() {
        System.out.println("=== ТОВАРЫ НА ПРОДАЖУ (" + namePlanet + ") ===");
        for (String good : seelGoods) {
            if (seelMarket.containsKey(good)) {
                System.out.println(good + " - " + seelMarket.get(good) + "$");
            }
        }
    }
    // вывод списка товаров которые покупает планета 
    public void showByuGoods() {
        System.out.println("=== ТОВАРЫ НА ПОКУПКУ (" + namePlanet + ") ===");
        for (String good : buyGoods) {
            if (buytMarket.containsKey(good)) {
                System.out.println(good + " - " + buytMarket.get(good) + "$");
            }
        }
    }
}
    // класс зелмя наследование от общего класса планет 
    class Earch extends planet {byte civic;
        // конструктор планеты земля
        public  Earch (String namePlanet, boolean kaf, boolean nal, boolean kor, byte civic,
             List <String> seelGoods, List<String> buyGoods, Map<String, Double> buytMarket, Map<String, Double> seelMarket) {
            super (namePlanet, kaf, nal, kor, seelGoods, buyGoods, buytMarket, seelMarket); 
            this.civic = civic;}

            // переопределение списка того что планета продает 
            @Override
            public void SeelGoods() {
                seelGoods.add("Clothes"); // одежда
                seelGoods.add("Fangs"); // клыки
                seelGoods.add("Food"); // еда
                seelGoods.add("Water"); // вода
                seelGoods.add("Fuel"); // топливо
                seelGoods.add("FilterSuit"); // скафандр с фильтрами
                seelGoods.add("OxygenTank"); // балоны с кислородом 
                seelGoods.add("Weapon"); // оружие
            }

            // переопределение списка того что планета покупает 
            @Override
            public void BuyGoods() {
                buyGoods.add("Fangs"); // клыки
                buyGoods.add("Food"); // еда
                buyGoods.add("Water"); // вода
                buyGoods.add("Fuel"); // топливо
                buyGoods.add("FilterSuit"); // скафандр с фильтрами 
                buyGoods.add("OxygenTank"); // балоны с кислородом 
                buyGoods.add("Weapon"); // оружие
            }

            // переопределния вывода текущей планеты на которой находится игрок
            @Override
            public String toString() {
                return super.toString() + " | на планете есть " + civic + " разумная цивилизация";
            }
    }

    class Mars extends planet {double kocent;
        public Mars (String namePlanet, boolean kaf, boolean nal, boolean kor, double kocent, 
             List <String> seelGoods, List<String> buyGoods, Map<String, Double> buytMarket, Map<String, Double> seelMarket) {
            super (namePlanet, kaf, nal, kor, seelGoods, buyGoods, buytMarket, seelMarket); 
            this.kocent = kocent;}

            // переопределение списка того что планета продает
            @Override
            public void SeelGoods() {
                seelGoods.add("Clothes"); // одежда
                seelGoods.add("Fangs"); // клыки
                seelGoods.add("Food"); // еда
                seelGoods.add("Water"); // вода
                seelGoods.add("Fuel"); // топливо
                seelGoods.add("AdvancedSuit"); // продвинутый скафандр
                seelGoods.add("Weapon"); // оружие
                seelGoods.add("Repellent"); // отпугиватель
            }

            // переопределение списка того что планета покупает
            @Override // покупка
            public void BuyGoods() { 
                buyGoods.add("Food"); // еда
                buyGoods.add("Water"); // вода
                buyGoods.add("Fuel"); // топливо
                buyGoods.add("AdvancedSuit"); // продвинутый скафандр 
                buyGoods.add("Weapon"); // оружие
            }

            // переопределния вывода текущей планеты на которой находится игрок
            @Override
            public String toString() {
                return super.toString() + " | вам повезло, концентрация кислорода в атмосфере планеты равна " 
                + kocent + "%" + " а это значит что вам достаточно скафадра с фильтрами, но если его нет ой ой";
            }
    }

    class Titan extends planet {String kolvoYad;/*название яда в атмосфере */
        public Titan (String namePlanet, boolean kaf, boolean nal, boolean kor, String kolvoYad,
            List <String> seelGoods, List<String> buyGoods, Map<String, Double> buytMarket, Map<String, Double> seelMarket) {
            super (namePlanet, kaf, nal, kor, seelGoods, buyGoods, buytMarket, seelMarket); 
            this.kolvoYad = kolvoYad;}
            
            // переопределение списка того что планета продает
            @Override
            public void SeelGoods() {
                seelGoods.add("Fangs"); // клыки
                seelGoods.add("Food"); // еда
                seelGoods.add("Water"); // вода
                seelGoods.add("Fuel"); // топливо
                seelGoods.add("Weapon"); // оружие
                seelGoods.add("Artifact"); // артефакт
            }

            // переопределение списка того что планета покупает
            @Override // покупка
            public void BuyGoods() { 
                buyGoods.add("Fuel"); // топливо
                buyGoods.add("Artifact"); // артефакт
                buyGoods.add("Weapon"); // оружие
                buyGoods.add("Repellent"); // отпугиватель 
            }

            // переопределния вывода текущей планеты на которой находится игрок
            @Override
            public String toString() {
                return super.toString() + " | К сожалению атмомфера планеты не пригодна дял жизни, здесь следует ходить в професиональном скафандре" + kolvoYad;
            }
    }

// основа программы 
public class space_market {
    public static void main (String[] agrs) {
        // создание сканера 
        Scanner scan = new Scanner (System.in, "UTF-8");
        
        // создание переменных товаров
        String eda = "Food"; // еда
        String woner = "Water"; // вода
        String Fuel = "Fuel"; // топливо (механика использования будет реализованна в обновлении)
        String odejda = "Clothes"; // одежда (нужна дял механизма заработка )
        String artefact = "Artifact"; // артефакт (его наличие и привоз на землю означает победу)
        String SkafFiltr = "FilterSuit"; // скафандр с фильтрами (предназначен для Марса)
        String SkafPRO = "AdvancedSuit"; // продвинутый скафандр (преднозначен для титана (бесполезен без балонов с кислородом))
        String balonO = "OxygenTank"; // балоны с кислородом (преднозначен для Титана(бесполезен без продвинутого скафандра))
        String otp = "Repellent"; // отпугиватель чудовищь(иначе при прилете на Титан игра окончена)
        String oruj = "Weapon"; // оружие (механиз использовния будет в грядущем обновлении)
        String col = "Fangs"; // клыки (механизм использования будет в грядущем обновлении)
        // kaf - пригодность для жизни человека 
        // nal - наличие нужного артефакта 
        // kor - наличие чудовищь

        // заполнение коллекции Map товарами и ценами продажи со стороны рынка 
        Map<String, Double> SeelGoods = new HashMap<>();
        SeelGoods.put(eda, 24.99);
        SeelGoods.put(woner, 19.99);
        SeelGoods.put(Fuel, 39.99);
        SeelGoods.put(odejda, 59.49);
        SeelGoods.put(artefact, 100000.49);
        SeelGoods.put(SkafFiltr, 120.99);
        SeelGoods.put(SkafPRO, 250.99);
        SeelGoods.put(balonO, 50.99);
        SeelGoods.put(otp, 149.99);
        SeelGoods.put(oruj, 349.49);
        SeelGoods.put(col, 199.99);

        // заполнение коллекции Map товарами и ценами покупки со стороны рынка 
        Map<String, Double> BuyGoods = new HashMap<>(); 
        BuyGoods.put(eda, 12.99);
        BuyGoods.put(woner, 9.99);
        BuyGoods.put(Fuel, 19.99);
        BuyGoods.put(odejda, 79.49);
        BuyGoods.put(artefact, 100000.49);
        BuyGoods.put(SkafFiltr, 60.99);
        BuyGoods.put(SkafPRO, 125.99);
        BuyGoods.put(balonO, 24.99);
        BuyGoods.put(otp, 74.99);
        BuyGoods.put(oruj, 149.49);
        BuyGoods.put(col, 99.99);

        // создание объекта планеты змеля
        String namePlanet = "Earch";  
        boolean kaf = true;
        boolean nal = false;
        boolean kor = false;
        byte civic = 1;
        Earch planetEarch = new Earch(namePlanet, kaf, nal, kor, civic, new ArrayList<>(), new ArrayList<>(), SeelGoods, BuyGoods);

        // создание объекта планеты Марс
        namePlanet = "Mars"; 
        kaf = false;
        nal = false;
        kor = false;
        double kocent = 0.15;
        Mars planetMars = new Mars(namePlanet, kaf, nal, kor, kocent, new ArrayList<>(), new ArrayList<>(), SeelGoods, BuyGoods);

        // создание объекта планеты Титан
        namePlanet = "Titan";
        kaf = false;
        nal = true;
        kor = true;
        String kolvoYad = "Циановодород, Синиальная кислота, Метилцианид и т.д";
        Titan planetTitan = new Titan(namePlanet, kaf, nal, kor, kolvoYad, new ArrayList<>(), new ArrayList<>(), SeelGoods, BuyGoods);

        // создания игрока и введение его в курс дела
        System.out.println("Добро подаловать в игру Space market, где можно путишествовать по планетам, покупать и продавать товары");
        System.out.println("Твоя главная цель, найти артефакт и привести его на землю");
        System.out.println();
        System.out.print("Введи имя своего персонажа: ");
        // создаем инвентарь куда будет довлять твоары и убирать их в случае продажи, ключь - навзание товара, значение - его колличество
        Map<String, Integer> inventory = new HashMap<>(); // ключь - навзание товара, значение - его колличество
        String PLANET = "beginning"; // переменная определения на какой планете игрок
        String name = scan.nextLine(); // пользователь вводит имя своего персонажа
        double money = 100; 
        byte golod = 11; 
        double jajda = 16;
        int rez = 0; // счетчик для отслеживания рейтинга, чем меньше было действий для достижения цели тем ты крут
        boolean life = true; // создание переменной определяющей жив игрок или нет
        player players = new player(name, rez, money, golod, jajda, life);
        
        // начало цикла игры 
        while (true) {
            if (!players.life) {return;}
            // проверка уровня голода и жажды
            if (players.getGolod() <= 0 || players.getJajda() <= 0) {life = false;}
            // проверка на то что игрок мертв
            if (!life) {
                System.out.println();
                System.out.println("Конец игры!"); 
                System.out.println();
                return;}

            // создания переменной определяющей 
            // на какой планете игрок для взаимодейтсия с рынком 
            // только той планеты на которой игрок    
            planet currentPlanet = planetEarch;

            // проверка на то что игра только началась 
            if (PLANET.equals("beginning")) {
                System.out.println("Это начало игры");
                PLANET = "Earch"; System.out.println(); // переключение на планету Земля 
            }
            
            // проверка на то что игрок на Земле
            else if (PLANET.equals("Earch")) {
                System.out.println(planetEarch);
                System.out.println();
                if(players.inventory.containsKey("Artifact")) {
                    System.out.println("Поздравляю, ты нашел атефакт и привез его на Землю");
                    inventary(players);
                    System.out.println("Ты выиграл, игра окончена!");
                    System.out.println("Твой результат: " + players.rez);
                    return;}
                }

            // проверка на то что игрок на Марсе  
            else if (PLANET.equals("Mars")) {
                System.out.println(planetMars);
                System.out.println();
                inventary(players);
                currentPlanet = planetMars;
                if(!players.inventory.containsKey("FilterSuit")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя не было скафандра с фильтрами");
                    System.out.println("Твой результат: " + players.rez); 
                    return;}
            }    

            // проверка на то что игрок на Титане 
            else if (PLANET.equals("Titan")) {
                System.out.println(planetTitan);
                inventary(players);
                currentPlanet = planetTitan;
                if (!players.inventory.containsKey("AdvancedSuit") && !inventory.containsKey("OxygenTank")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя не было продвинутого скафандра с балонами кислорода ");
                    System.out.println("Твой результат: " + players.rez); 
                    return;}
                else if (!players.inventory.containsKey("AdvancedSuit")) {
                    System.out.println("Игра оконченна, ты задохнулся, у тебя были балоны кислорода но не было продвинутого скафандра");
                    System.out.println("Твой результат: " + players.rez);
                    return;
                } 
                else if (!players.inventory.containsKey("OxygenTank")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя был продвинутый скафандр но не было балонов с кислородом");
                    System.out.println("Твой результат: " + players.rez);
                    return;
                }
                else if (!players.inventory.containsKey("Repellent")) {
                    System.out.println("Твоя песенка спета, игра окончена тебя разтерзали чудовища");
                    System.out.println("А все потому-что у тебя не было отпугивателя");
                    System.out.println("Твой результат: " + players.rez);
                }
            }
            // создания переменой для определения надобности выхода в основное меню
            boolean exit = false;

            // создания переменной menu для switch на основе модуля вывода меню
            int menu = menu(scan, players, planetMars);
            switch (menu) {
                case 1: // пользователь выбрал пункт покупка-продажа
                    if (exit) {System.out.println();break;} // определине статуса переменой выхода из switch в начало основного цикла while boolean exit снова сбрасывается 
                    int menuDev1 = dev1(scan, inventory, players, currentPlanet); // выбор пользователя из меню модуля dev1
                    System.out.println();
                    switch (menuDev1) {
                        case 1: pokupka(scan, players, inventory, currentPlanet); System.out.println(); break; // покупка на рынке текущей планеты игрока 
                        case 2: prodaja(scan, players, inventory, currentPlanet); System.out.println(); break; // продажа на рынке текущей планеты игрока
                        case 3: Exit(scan); System.out.println();break; // вывод модуля выхода в основное меню
                        default: System.out.println("Вы выбрали не существущий пункт меню, повторите попытку"); System.out.println();break;       
                    } break;
                case 2: // пользователь выбрал пункт поесть-попить
                    if (exit) {System.out.println();break;} // определине статуса переменой выхода из switch в начало основного цикла while boolean exit снова сбрасывается 
                    int menuDev2 = dev2(scan, inventory, players); // выбор пользователя из меню модуля dev2
                    System.out.println();
                    switch(menuDev2) {
                        case 1: poest(scan, inventory, players); System.out.println(); break; // игрок есть
                        case 2: popit(scan, inventory, players); System.out.println(); break; // игрок пьет 
                        case 3: Exit(scan); System.out.println(); break; // вывод модуля выхода в основное меню
                        default: System.out.println("Вы выбрали не существущий пункт меню, повторите попытку"); System.out.println();break;   
                    } break;
                case 3:  // пользователь выбрал пунтк перелета на другую планету
                    if (exit) {System.out.println();break;}  // вывод модуля выхода в основное меню
                    PLANET = perelet(scan, players, currentPlanet); System.out.println(); 
                    break; // вывод выбора планеты перелета
                case 4: // если игкрок на титане то открываеться доступ к 4 пунтку основного меню
                    if(!currentPlanet.namePlanet.equals("Titan")) {// доп. проверка на то что если игрок без увиденного пункта меню введет цифру 4
                        System.out.println("На ходясь на планете: " + currentPlanet.namePlanet + " Такого пунткта меню нет!");
                        break;} // выход в основное меню если игрок не на Титане 
                    else {Battle(scan, players);}
                    break;
            }
        }
    }
    // МЕТОД ДЛЯ ВЫВОДА ИНВЕНТОРЯ И ПРОВЕРКИ НА ЕГО ПУСТОТУ 
    public static void inventary(player players) {
        if (players.inventory.isEmpty()) {
            System.out.println();
            System.out.println("ИНВЕНТАРЬ ПУСТ!");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("=== ИНВЕНТАРЬ ==="); 
            for (Map.Entry<String, Integer> word : players.inventory.entrySet()) {
                System.out.println(word.getKey() + " -- " + word.getValue());
            }
            System.out.println();
        }
    }
    // МЕТОД ДЛЯ СОХРАНЕНИЯ ИГРЫ 
    public static void saveGame(player players, String currentPlanet) {

        boolean proverka = false; //
        while (!proverka) {
            try (BufferedWriter word = new BufferedWriter(new FileWriter("Save.txt"))) {
                word.write(players.name); word.newLine();
                word.write(String.valueOf(players.getGolod())); word.newLine();
                word.write(String.valueOf(players.getJajda())); word.newLine();
                word.write(String.valueOf(players.rez)); word.newLine();
                word.write(String.valueOf(players.money)); word.newLine();
                word.write(currentPlanet); word.newLine();
                for (Map.Entry<String, Integer> entry : players.inventory.entrySet() ) {
                    word.write(entry.getKey() + ":" + entry.getValue()); word.newLine();
                }
                System.out.println("Игра сохранена!");
                proverka = true;
                System.out.println();
            } catch (IOException error) {
                System.out.println("Ошибка сохранения! повторите попытку");
                System.out.println();
            }
        }
    }
    // МЕТОД dev1 CASE 1 ДЛЯ ОСНОВНОГО МЕНЮ 
    public static int dev1 (Scanner scan, Map<String, Integer> inventory, player players, planet currPlanet) {
        System.out.println();
        System.out.println(players); // вывод метода toString
        inventary(players); // метод вывода инвенторя 
        players.cheekStatus(); // метод проверки статуса голода и жаджды
        System.out.println();
        System.out.println("=== Рынок планеты - " + currPlanet.namePlanet + " ==="); // объявления рынка планеты 
        System.out.println();

        currPlanet.showSeelGoods(); // вывод цен и товаров продажи со стороны планеты на которой игрок сейчас
        System.out.println();
        currPlanet.showByuGoods(); // вывод цен и товаров покупки со стороны планеты на которой игрок сейчас

        // вывод меню
        System.out.println();
        System.out.println("1. Купить");
        System.out.println("2. Продать");
        System.out.println("3. Выйти в основное меню");
        int dedScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Выберите действие из пункта меню: ");
                dedScan = scan.nextInt();// получение данных от пользвателя 
                if (dedScan >= 1 && dedScan <= 3) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {System.out.println("Введите число как в меню 1, 2 или 3");}
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int ded = dedScan; // получение выбора пунта меню от пользователя 
        scan.nextLine();
        System.out.println();
        if (ded >= 1 && ded <= 3) {return ded;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
        else {System.out.println("Не верный пункт меню!"); return 0;} // срабатываение проверка на не существующий пункт меню от пользователя  
    }
    // МЕТОД dev2 CASE 2 ДЛЯ ОСНОВНОГО МЕНЮ 
    public static int dev2 (Scanner scan, Map<String, Integer> inventory, player players) {
        if (inventory.isEmpty()) {System.out.println("Инвентарь пуст"); System.out.println();} // проверка инвентаря на пустоту
        System.out.println();
        System.out.println(players); // вывод метода toString
        inventary(players); // метода инвенторя
        System.out.println("=== ВЫБЕРИТЕ ДЕЙСТВИЕ ==="); // вывод меню
        System.out.println("1. Поесть");
        System.out.println("2. Попить");
        System.out.println("3. Выйти в основное меню"); 
        int dedScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Выберите действие из пункта меню: ");
                dedScan = scan.nextInt();// получение данных от пользвателя 
                if (dedScan >= 1 && dedScan <= 3) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {System.out.println("Введите число как в меню 1, 2 или 3");}
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int ded = dedScan;
        scan.nextLine();
        if (ded >= 1 && ded <= 3) {return ded;}// проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
        else {System.out.println("Не верный пункт меню!"); return 0;} // срабатываение проверка на не существующий пункт меню от пользователя  
    }
    // МЕТОД ОСНОВНОГО МЕНЮ
    public static int menu (Scanner scan, player players, planet currePlanet) {
        // меню 
        inventary(players); //  метод инвенторя
        System.out.println("1. Покупка-продажа");
        System.out.println("2. Поесть-попить");
        System.out.println("3. В путь");
        if (currePlanet.namePlanet.equals("Titan")){ // проверка на то что игрок сейчас на Титане чтобы чтобы добавить вывод 4 пункта меню
            System.out.println("4. Сразиться с чудовищами");
        }
        System.out.println();
        byte viborScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Выберите действие из пункта меню: ");
                viborScan = scan.nextByte();// получение данных от пользвателя 
                if (viborScan >= 1 && viborScan <= 4) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {
                    if (currePlanet.namePlanet.equals("Earch") || currePlanet.namePlanet.equals("Mars")) {
                    System.out.println("Введите число как в меню 1, 2 или 3");
                    } else {System.out.println("Введите число как в меню 1, 2, 3 или 4");}
                }
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        byte vibor = viborScan; // создание переменной выбора пункта меню от пользователя
        scan.nextLine();
        if (vibor >= 1 && vibor <= 3) {return vibor;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
        else {System.out.println("Не верный пункт меню!");return 0;} // срабатываение проверка на не существующий пункт меню от пользователя  
    }
    // МЕТОД ЧТОБЫ ПОЕСТЬ
    public static void poest(Scanner scan, Map<String, Integer> inventory, player players) {
        System.out.println();
        players.cheekStatus();System.out.println(); // проверка на уровень голода и жажды
        // провека на наличие еды в инвентаре 
        if (!players.inventory.containsKey("Food")) {// провека на отсутствие еды в инвентаре 
        System.out.println("В инвентаре нет еды!");System.out.println(); return;}
        System.out.println("Одна единица еды дает +2 к шкале");
        inventary(players); // метод инвенторя
        int kolvoEDA = players.inventory.get("Food"); // создание переенной и прививание ей колличество еды в инвентаре
        int EMEdaScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("У тебя в инвентаре сейчас: " + kolvoEDA + " единиц еды, сколько ты хочешь израсходовать?: ");
                EMEdaScan = scan.nextInt();// получение данных от пользвателя 
                proverka = true; // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int EMEda = EMEdaScan; // получение данных от пользователя в каком количестве еды он хочет поесть
        if (EMEda > kolvoEDA) { // сравнение что колличество которое хочет съесть игрок не привышает колличество которо есть в инвентаре 
            System.out.println();
            System.out.println("Еще раз, у тебя только " + kolvoEDA + " еды ");
            System.out.println("А ты хочешь съесть больше чем у тебя есть, наглый жулик");
            System.out.println("Жулик, не жульничей, раз ты ввел больше чем у тебя есть, то съешь все свои запасы");
            System.out.println();
            EMEda = kolvoEDA; // в случае если колличество в инвентаре меньше желаемого, то игрок съест максимум из того что есть
        }
        int newKolvo = kolvoEDA - EMEda; // удаление из инвентаря колличество еды которое будет съедено, и полное удаление в случае 0
        if (newKolvo == 0) {players.inventory.remove("Food");}
        else {players.inventory.put("Food", newKolvo);} 
        int newGolod = players.getGolod() + (EMEda * 2); // прибавление к шкале голода +2 пункта за каждую съеденную единцу еды 
        players.setGolod((byte) newGolod); // проверка Сеттера максимального лимита шкалы голода 
        System.out.println("Готово, ты поел"); 
        players.cheekStatus(); // вызов метода проверки статуса голода и жажды
        System.out.println();
    }
    // МЕТОД ЧТОБЫ ПОПИТЬ
    public static void popit(Scanner scan, Map<String, Integer> inventory, player players) {
        System.out.println();
        players.cheekStatus(); // вызов метода проверки статуса голода и жажды
        System.out.println();
        if (!players.inventory.containsKey("Water")) { // проверка на отсутствие воды в инвентаре 
        System.out.println("В инвентаре нет воды!"); System.out.println();return;}
        System.out.println("Одна единица воды дает +3 к шкале");
        inventary(players); // метод инвенторя
        int kolvoEDA = players.inventory.get("Water"); // прививание переменной колличество воды в инвентаре 
        int EMEdaScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("У тебя в инвентаре сейчас: " + kolvoEDA + " единиц воды, сколько ты хочешь израсходовать?: ");
                EMEdaScan = scan.nextInt();// получение данных от пользвателя 
                proverka = true; // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int EMEda = EMEdaScan; // получение от пользователя данных какое колличество единиц воды он хочет выпить
        if (EMEda > kolvoEDA) { // сравнение что колличество которое хочет съесть игрок не привышает колличество которо есть в инвентаре 
            System.out.println();
            System.out.println("Еще раз, у тебя только " + kolvoEDA + " воды ");
            System.out.println("А ты хочешь випить больше чем у тебя есть, наглый жулик");
            System.out.println("Жулик, не жульничей, раз ты ввел больше чем у тебя есть, то выпьешь все свои запасы");
            System.out.println();

            EMEda = kolvoEDA; // в слачае если колличество в инвентаре меньше желаемого, игрок выпьет максимум из того что есть.
        }
        int newKolvo = kolvoEDA - EMEda; // после питья вычитание колличество единиц воды из инвенторя удаляем в случае остатка 0
        if (newKolvo == 0) {players.inventory.remove("Water");}
        else {players.inventory.put("Water", newKolvo);} 
        double newJajda = players.getJajda() + (EMEda * 3); // прибовление к шкале жажды +3 за каждую выпитую единицу воды
        players.setJajda(newJajda); // проверка Сеттера максимального лимита шкалы жажды
        System.out.println("Готово, ты попил");
        System.out.println();
        players.cheekStatus(); // вызов метода проверки статуса голода и жажды
        System.out.println();
    }
    // МЕТОД ДЛЯ ПОКУПКИ
    public static void pokupka(Scanner scan, player players, Map<String, Integer> inventory, planet currentPlanet) {
        inventary(players); // метод инвенторя
        currentPlanet.showSeelGoods(); // вывпод списка товаров и цен которые продает текущая планета 
     
        System.out.print("Введите название товара, которое хотите купить: ");
        String tovarB = scan.nextLine(); // получение от пользователя название товара который он хочет купить 
        int shotBScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Введите количество: ");
                shotBScan = scan.nextInt();// получение данных от пользвателя 
                if (shotBScan > 0) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {System.out.println("Колличество не может быть равна 0");
                    System.out.println("Повтори попытку!");
                    System.out.println();;
                }
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Вводи цифрами а не словами!");
                System.out.println("Повтори попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int shotB = shotBScan; // получение от пользователя колличество выбранного товара который он хочет купить
        scan.nextLine();
        System.out.println();
        System.out.println("Вы ввели: " + tovarB);
        System.out.println();

        // Проверяем, есть ли товар на рынке этой планеты
       
        if (!currentPlanet.seelGoods.contains(tovarB)) {
            System.out.println("На планете " + currentPlanet.namePlanet + " нет такого товара!"); System.out.println();
            return;
        }
        // по текущей планете нахождения игрока берем из списка цену на выбранный игроком товар и прививаем ее к переменной price
        double price = currentPlanet.seelMarket.get(tovarB);
        double totalCost = price * shotB; // создаем переменную для получение полной стоимости покупки

        if (players.money < totalCost) { // проверка на наличие у игрока нужного колличества денег для совершенния данной покупки 
            System.out.println("Недостаточно денег! Нужно: " + totalCost + "$"); System.out.println();
            return; // если не достаточно то выход из программы и возврат в основное меню
        }

        // денег, хватает, добавляем товар и его колличество в инвентарь
        players.inventory.put(tovarB, players.inventory.getOrDefault(tovarB, 0) + shotB);
        players.money -= totalCost; // вычитаем колличество потраченных денег из кармана игрока
        int newGolod = players.getGolod() - 1; // уменшаем шкалу голода на 1 пункт
        players.setGolod((byte) newGolod);
        double newJajda = players.getJajda() - 1.5; // уменшаем шкалу жажды на один пункт
        players.setJajda(newJajda);
        players.rez++; // даем прибавку в рейтинге +1 за совершенное действие для достижения цели

        System.out.println("Куплено: " + tovarB + " x" + shotB + " за " + totalCost + "$ на планете " + currentPlanet.namePlanet);
        players.cheekStatus();
        System.out.println(players); // вывода метода toString 
        System.out.println();
    }
    // МЕТОД ДЛЯ ПРОДАЖИ
    public static void prodaja(Scanner scan, player players, Map<String, Integer> inventory, planet currentPlanet) {
        currentPlanet.showByuGoods(); // вывод рырка товаров и цен которые планета хочет купить
        inventary(players); // метод инвенторя
        System.out.print("Введите название товара, который хотите продать: ");
        String tovarS = scan.nextLine(); // получение название товара от пользователя который он хочет продать 
        int shotSScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Введите количество: ");
                shotSScan = scan.nextInt();// получение данных от пользвателя 
                if (shotSScan > 0) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {System.out.println("Колличество не может быть равна 0");
                    System.out.println("Повтори попытку!");
                    System.out.println();;
                }
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Вводи цифрами а не словами!");
                System.out.println("Повтори попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int shotS = shotSScan; // получение колличества которое он хочет продать
        scan.nextLine();

        if (!players.inventory.containsKey(tovarS)) { // проверка на наличе данного товара в инвентаре 
            System.out.println("В твоем инвентаре нет такого товара!"); System.out.println();
            return; // в случае если нет, то выход из программы в главное меню
        }

        int currentQty = players.inventory.get(tovarS); // прививание к переменной колличество товара на продажу которое есть в инвентаре 
        if (currentQty < shotS) { // сравнение хватит ли на продажу того что есть 
            System.out.println("У тебя только " + currentQty + " шт. " + tovarS); System.out.println();
            return; // в случае если не хватает, вывод сообщения в консоль и выход в основное меню
        }

        if (!currentPlanet.buyGoods.contains(tovarS)) { // проверка на то что введенный пользователем товар купят на рынке текузей планеты 
            System.out.println("На планете " + currentPlanet.namePlanet + " не принимают этот товар!"); System.out.println();
            return; // в случае если нет то выход в основное меню
        }

        double price = currentPlanet.buytMarket.get(tovarS); // создание перемнной price и привывание ей цену выбранного товара на продажу
        double totalIncome = price * shotS; // вывод общей суммы дохода, которую получит игрок продав данный товар в данном колличестве

        players.money += totalIncome; // прибавка к деньгам игрока суммы дохода с продажи 
        int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенное действие 
        players.setGolod((byte) newGolod);
        double newJajda = players.getJajda() - 1.5; // уменшение уровня жажды на 1.5 пункта за совершенное действие
        players.setJajda(newJajda);
        players.rez++; // увеличение в рейтинге на 1 пункт за совершонное действие 

        int newQty = currentQty - shotS; // проверка на то что игрок продал все колличество выбранного товара 
        if (newQty == 0) {
            players.inventory.remove(tovarS); // если да то удаление самого товара из инвентаря
        } else {
            players.inventory.put(tovarS, newQty); // если нет то обновление колличества товара 
        }

        System.out.println("Продано: " + tovarS + " x" + shotS + " за " + totalIncome + "$ на планете " + currentPlanet.namePlanet);
        players.cheekStatus(); // выхов метода проверки уровня голода и жажды 
        System.out.println(players); //вызов метода toString
        System.out.println();
    }
    // МЕТОД ДЛЯ ВЫХОДА В ОСНОВНОЕ МЕНЮ
    public static boolean Exit(Scanner scan) {
        // меню для выхода
        System.out.println("Вы точно хотите выйти в основное меню? ");
        System.out.println("Yes");
        System.out.println("No");
        System.out.print("Введите свой ответ: ");
        String otvet = scan.nextLine(); // получение выбора пунта меню от пользователя
        if(otvet.equalsIgnoreCase("Yes")) {return true;} // в случае положительного выбора, присвоение к изначальному boolean exit = false значение true
        else {return false;} // в ином случае остаеться false
    }
    // МЕТОД CASE 3 ПЕРЕЛЕТ НА ДРУГУЮ ПЛАНЕТУ
    public static String perelet (Scanner scan, player players, planet currentPlanet) {
        players.cheekStatus(); // вызов метода проверки уровня голода и жажды 
        System.out.println();
        if (!players.inventory.containsKey("Fuel")) {
            System.out.println();
            System.out.println("С пустым баком по планетам, серьезно?");
            System.out.println();
            System.out.println("Полет запрещен!"); return currentPlanet.namePlanet; // действие в случае недостаточного колличества топлива для перелета
        }
        int FuelKolvo = players.inventory.get("Fuel");
        if(!players.inventory.containsKey("Fuel") || FuelKolvo <= 20 ) {
            System.out.println("Недостаточно топлива, должно быть минимум 20 единииц."); 
            System.out.println("Полет запрещен!"); return currentPlanet.namePlanet; // действие в случае недостаточного колличества топлива для перелета
        }
        if (currentPlanet.namePlanet.equals("Earch")) { // проверка того что текущая планета нахождения игрока это земля
            System.out.println("Для перелета вам доступна только планета Марс, убедитесь что у вас есть в инвентаре скафандр с фильтрами иначе вам не выжить");
            inventary(players); // метод инвенторя
            System.out.print("Если согласен перелететь напиши 'Yes', если хотите выйти в основное меню, напишите 'Exit': ");
            String otvet = scan.nextLine(); // получение ответа от пользвателя, на согласие или отказ от перелета на ближайшую планету Марс
            if (otvet.equalsIgnoreCase("Yes")) { // при положительном ответе 
                int newFuelkolvo = FuelKolvo - 20;
                if (newFuelkolvo == 0) {players.inventory.remove("Fuel");}
                else {players.inventory.put("Fuel", newFuelkolvo );}
                players.rez++; // увеличение рейтинга за совершенное действие на 1 пункт
                int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенного действие
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5; // уменшение уровня голода на 1.5 пункт за совершенного действие
                players.setJajda(newJajda);
                players.cheekStatus(); // проверка уровня голода и жажды 
                System.out.println(); 
                return "Mars"; // сделать текющую планету нахождения игрока Марс
            }
            else if (otvet.equalsIgnoreCase("Exit")) { // в случае если игрок выбрал выйти из пункта перелета
                Exit(scan); 
                System.out.println();
                return "Earch"; // текущая планета остаеться земля 
            }
            else {System.out.println("Вы не подтвердили перелет"); // при других ответах оставить игрока на земле
            System.out.println();
            return "Earch";
            }
        }
        
        if (currentPlanet.namePlanet.equals("Mars")) { // проверка на то что текущая планета на которой находиться игрок это марс
            System.out.println("Сочувствую что ты не нашел артефакт на Марсе, попытай удачу на Титане.");
            System.out.println("Но помни, там тебе нужен продвинутый скафандр с отдельными для них балонами с кислоролом.");
            System.out.println("И не забудь запастись оружием, планета кишит монстрами.");
            System.out.println("Если у тебя нет нужных вещей в инвентаре и ты не видел их на рынке марса, советую вернуться на змелю");
            System.out.println("Если выбираешь планету Титан напиши 'Titan' если хочешь вернуться на Землю напиши 'Earch'");
            System.out.println("Если хочешь выйти в основное меню напиши 'Exit'");
            inventary(players); // метод инвенторя
            System.out.print("Итак, ваш выбор: ");
            String otvet = scan.nextLine(); // получение ответа от пользователя для дальнейших действий 
            if (otvet.equalsIgnoreCase("Titan")) { // вариант при выборе планте Титан для перелета 
                int newFuelkolvo = FuelKolvo - 20;
                if (newFuelkolvo == 0) {players.inventory.remove("Fuel");}
                else {players.inventory.put("Fuel", newFuelkolvo );}
                players.rez++; // увеличение рейтинга на 1 пункт для совершенное действие 
                int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенное действие 
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5; // уменшение уровня жажды на 1.5 пункта за совершенное действие 
                players.setJajda(newJajda);
                players.cheekStatus(); // вызов метода проверки уровня голода и жажды 
                System.out.println();
                return "Titan"; // сделать текущую планету нахождения игрока Титан
            }
            else if (otvet.equalsIgnoreCase("Earch")) {
                int newFuelkolvo = FuelKolvo - 20;
                if (newFuelkolvo == 0) {players.inventory.remove("Fuel");}
                else {players.inventory.put("Fuel", newFuelkolvo );}
                players.rez++; // увеличение рейтинга на 1 пункт для совершенное действие
                int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенное действие 
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5; // уменшение уровня жажды на 1.5 пункта за совершенное действие 
                players.setJajda(newJajda);
                players.cheekStatus(); // вызов метода проверки уровня голода и жажды
                System.out.println();
                return "Earch"; // сделать текущую планету нахождения игрока Земля
            }
            else if (otvet.equalsIgnoreCase("Exit")) { // игрок выбрал виход
                Exit(scan); 
                System.out.println();
                return "Mars"; // остаться на марсе в случае выбора выхода из раздела перелет
            } 
            else {System.out.println("Вы не подтвердили перелет"); 
            System.out.println();
            return "Mars"; // остатья на марсе в случае получения не известного ответа
            }
        }    

        if (currentPlanet.namePlanet.equals("Titan")) { // в случае если игрок находиться на Титане 
            System.out.println("Поздравляю ты побывал на последней планете и выжил");
            System.out.println("Убедись что ты положил в инвентарь артефакт и полетели в обратный путь");
            inventary(players); // метод инвенторя
            System.out.println("Единсвтенная планета для перелета из доступных это Марс");
            System.out.println("Если подтверждаешь перелет, напиши 'Yes'");
            System.out.println("Если хочешь выйти в основное меню напиши 'Exit'");
            System.out.print("Итак, ващ выбор: ");
            String otvet = scan.nextLine();
            if (otvet.equalsIgnoreCase("Yes")) {
                int newFuelkolvo = FuelKolvo - 20;
                if (newFuelkolvo == 0) {players.inventory.remove("Fuel");}
                else {players.inventory.put("Fuel", newFuelkolvo );}
                players.rez++; // увеличение рейтинга на 1 пункт для совершенное действие
                int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенное действие 
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5;  // уменшение уровня жажды на 1.5 пункта за совершенное действие 
                players.setJajda(newJajda);
                players.cheekStatus(); // вызов метода проверки уровня голода и жажды 
                System.out.println();
                return "Mars"; // перелет на марс в случае выбора марса 
            }
            else if (otvet.equalsIgnoreCase("Exit")) { // выход в случае выбора выхд 
                Exit(scan);
                System.out.println();
                return "Titan";} // остаться на Титане в случае выбора выход 
            else {System.out.println("Вы не подтвердили перелет"); 
            System.out.println();
            return "Titan"; // остатся на Титане в случае получения не известного ответа 
            }
        }    
        System.out.println();
        return currentPlanet.namePlanet; // если не один из операторов не сработал то оставить игрока на текущей планете 
    }
    // МЕТОД ВЗАИМОДЕЙСТВИЯ С ЧУДОВИЩАМИ
    public static void Battle(Scanner scan, player players) {
        inventary(players);
        System.out.println("Итак ты решил помериться силами с чудовищами");
        System.out.println("Похвально");
        System.out.println("А сможешь ли?");
        System.out.println("За каждого убитого тобой мостра ты удостоищься чести вырвать ему клыки");
        System.out.println("Всего их четыре");
        System.out.println("Убедись что ты вооружен, это важно ");
        System.out.println();
        System.out.println("1. Атаковать");
        System.out.println("2. Лучше подготовлюсь по лучше");
        int otvetScan = 0;
        boolean proverka = false;
        while (!proverka) { // запуск цикла до получения данных от пользователя соответствующие пункту меню
            try { // создание исключени
                System.out.print("Выберите действие: ");
                otvetScan = scan.nextInt();// получение данных от пользвателя 
                if (otvetScan >= 1 && otvetScan <= 2) {proverka = true;} // проверка веденного выбора пункта меню, для последубщего ее присвоения для основного пункта меню
                else {System.out.println("Введите число как в меню 1 или 2");}
            } catch (Exception error) { // вывод текста при проваливании программы, переводит в начало цикла (повторный ввод)
                System.out.println();
                System.out.println("Ошибка");
                System.out.println("Выберите пункт меню введя цифру!");
                System.out.println("Повторите попытку еще раз!");
                System.out.println();
                scan.nextLine();
            }
        }
        int otvet = otvetScan;
        if(otvet == 1) {
            if (!players.inventory.containsKey("Weapon")) {
                System.out.println("У тебя не было оружия");
                System.out.println("Ты умер");
                System.out.println("Игра окончена!");
                players.life = false;
            }
            else {
                System.out.println();
                System.out.println("Поздравляю воин");
                System.out.println("Ты завалил чудовище и забрал себе его клыки");
                System.out.println("Правда сломал оружие, но ничего страшного");
                System.out.println();
                players.rez++; // увеличение рейтинга за совершенное действие на 1 пункт
                int newGolod = players.getGolod() - 1; // уменшение уровня голода на 1 пункт за совершенного действие
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5; // уменшение уровня голода на 1.5 пункт за совершенного действие
                players.setJajda(newJajda);
                players.cheekStatus(); // проверка уровня голода и жажды 
                System.out.println(); 
                players.inventory.put("Fangs", players.inventory.getOrDefault("Fangs", 0) + 4);
                int kolvoWeapon = players.inventory.get("Weapon");
                kolvoWeapon --;
                players.inventory.put("Weapon", kolvoWeapon);
            }
        }
    }
}

