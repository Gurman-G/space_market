import java.util.*;

class player {String name; double money; private byte golod; private double jajda; int rez; Map<String, Integer> inventory;
    // конструктор класса player
    public player (String name, int rez, double money, byte golod, double jajda /*уровень жажды воды */) {
        this.name = name; 
        setGolod(golod);
        setJajda(jajda);
        this.money = money; 
        this.rez = rez;
        this.inventory = new HashMap<>();}
    // медот для вывода всех переменных объекта игрока     
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
        if (golod == 1) {System.out.println("Ты голоден, надо сроно поесть ");}
        if (golod < 1) {System.out.println("Ты умер от голода, игра окончена. Твой рейтинг: " + rez);}
        if (jajda == 7) {System.out.println("Ты хочешь пить, но это не критично");}
        if (jajda == 4) {System.out.println("Тебя мучает жажда, выпей воды");}
        if (jajda ==1) {System.out.println("Срочно выпей воды");}
        if (jajda < 1) {System.out.println("Ты умер от жажды, игра окончена, твой рейтинг: " + rez);}
    }
}
class planet {String namePlanet; boolean kaf; boolean nal; boolean kor; List <String> seelGoods; List<String> buyGoods;
     Map<String, Double> buytMarket; Map<String, Double> seelMarket;
     // конструктор родительского класса планет 
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
    SeelGoods(); BuyGoods();}
    // создание пустых методов в родительском классе для заполнения их переоприделения в класах наследников 
    public void SeelGoods() {}
    public void BuyGoods() {}

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

        Map<String, Integer> inventory = new HashMap<>();
        String PLANET = "beginning"; // переменная определения на какой планете игрок
        String name = scan.nextLine();
        double money = 100; 
        byte golod = 11; 
        double jajda = 16;
        int rez = 0; // счетчик для отслеживания рейтинга, чем меньше было действий для достижения цели тем ты крут
        player players = new player(name, rez, money, golod, jajda);
        boolean life = true; // создание переменной определяющей жив игрок или нет

        // начало цикла игры 
        while (true) {
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
                System.out.println();
                System.out.println(players);
                System.out.println();
                PLANET = "Earch"; System.out.println(); // переключение на планету Земля 
            }
            
            // проверка на то что игрок на Земле
            else if (PLANET.equals("Earch")) {
                System.out.println(planetEarch);
                System.out.println();
                if(inventory.containsKey("Artifact")) {
                    System.out.println("Поздравляю, ты нашел атефакт и привез его на Землю");
                    System.out.println();
                    System.out.println(players);
                    System.out.println();
                    System.out.println("Ты выиграл, игра окончена!");
                    System.out.println("Твой результат: " + players.rez);
                    return;}
                }

            // проверка на то что игрок на Марсе  
            else if (PLANET.equals("Mars")) {
                System.out.println(planetMars);
                System.out.println();
                currentPlanet = planetMars;
                if(!inventory.containsKey("FilterSuit")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя не было скафандра с фильтрами");
                    System.out.println("Твой результат: " + players.rez); 
                    return;}
            }    

            // проверка на то что игрок на Титане 
            else if (PLANET.equals("Titan")) {
                System.out.println(planetTitan);
                System.out.println();
                currentPlanet = planetTitan;
                if (!inventory.containsKey("AdvancedSuit") && !inventory.containsKey("OxygenTank")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя не было продвинутого скафандра с балонами кислорода ");
                    System.out.println("Твой результат: " + players.rez); 
                    return;}
                else if (!inventory.containsKey("AdvancedSuit")) {
                    System.out.println("Игра оконченна, ты задохнулся, у тебя были балоны кислорода но не было продвинутого скафандра");
                    System.out.println("Твой результат: " + players.rez);
                    return;
                } 
                else if (!inventory.containsKey("OxygenTank")) {
                    System.out.println("Игра окончена, ты задохнулся, у тебя был продвинутый скафандр но не было балонов с кислородом");
                    System.out.println("Твой результат: " + players.rez);
                    return;
                }
                else if (!inventory.containsKey("Repellent")) {
                    System.out.println("Твоя песенка спета, игра окончена тебя разтерзали чудовища");
                    System.out.println("А все потому-что у тебя не было отпугивателя");
                    System.out.println("Твой результат: " + players.rez);
                }
            }

            // создания переменой для определения надобности выхода в основное меню
            boolean exit = false;

            // создания переменной menu для switch на основе модуля вывода меню
            int menu = menu(scan);
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
                    PLANET = perelet(scan, players, currentPlanet); System.out.println(); break; // вывод выбора планеты перелета
            }
        }
    }
    // МЕТОД dev1 CASE 1 ДЛЯ ОСНОВНОГО МЕНЮ 
    public static int dev1 (Scanner scan, Map<String, Integer> inventory, player players, planet currPlanet) {
        if (inventory.isEmpty()) {System.out.println("Инвентарь пуст"); System.out.println();}
        System.out.println(players);
        System.out.println();
        players.cheekStatus();
        System.out.println();
        System.out.println("=== Рынок планеты - " + currPlanet.namePlanet + " ===");
        System.out.println();

        currPlanet.showSeelGoods(); 

        System.out.println();

        currPlanet.showByuGoods();

        //currPlanet.showByuGoods();
        System.out.println();
        System.out.println("1. Купить");
        System.out.println("2. Продать");
        System.out.println("3. Выйти в основное меню");
        System.out.print("Выберите действие: ");
        int ded = scan.nextInt();
        scan.nextLine();
        System.out.println();
        if (ded >= 1 && ded <= 3) {return ded;} 
        else {System.out.println("Не верный пункт меню!"); return 0;}
    }
    // МЕТОД CASE 2 ДЛЯ ОСНОВНОГО МЕНЮ 
    public static int dev2 (Scanner scan, Map<String, Integer> inventory, player players) {
        if (inventory.isEmpty()) {System.out.println("Инвентарь пуст"); System.out.println();}
        System.out.println();
        System.out.println(players);
        System.out.println();
        players.cheekStatus();  
        System.out.println();   
        System.out.println("=== ВЫБЕРИТЕ ДЕЙСТВИЕ ===");
        System.out.println("1. Поесть");
        System.out.println("2. Попить");
        System.out.println("3. Выйти в основное меню"); 
        System.out.print("Впишите свой выбор: ");
        int ded = scan.nextInt();
        scan.nextLine();
        if (ded >= 1 && ded <= 3) {return ded;}
        else {System.out.println("Не верный пункт меню!"); return 0;}
    }
    // МЕТОД ОСНОВНОГО МЕНЮ
    public static int menu (Scanner scan) {
        // меню 
        System.out.println();
        System.out.println("1. Покупка-продажа");
        System.out.println("2. Поесть-попить");
        System.out.println("3. В путь");
        System.out.println();
        System.out.print("Выберите пункт основного меню: ");
        byte vibor = scan.nextByte();
        scan.nextLine();
        if (vibor >= 1 && vibor <= 3) {return vibor;}
        else {System.out.println("Не верный пункт меню!");return 0;}
    }
    // МЕТОД ЧТОБЫ ПОЕСТЬ
    public static void poest(Scanner scan, Map<String, Integer> inventory, player players) {
        System.out.println();
        players.cheekStatus();System.out.println();
        if (!inventory.containsKey("Food")) {
        System.out.println("В инвентаре нет еды!");System.out.println(); return;}
        System.out.println("Одна единица еды дает +2 к шкале");
        int kolvoEDA = inventory.get("Food");
        System.out.print("У тебя в инвентаре сейчас: " + kolvoEDA + " единиц еды, сколько ты хочешь израсходовать?: ");
        int EMEda = scan.nextInt();
        if (EMEda > kolvoEDA) {
            System.out.println();
            System.out.println("Еще раз, у тебя только " + kolvoEDA + " еды ");
            System.out.println("А ты хочешь съесть больше чем у тебя есть, наглый жулик");
            System.out.println("Жулик, не жульничей, раз ты ввел больше чем у тебя есть, то съешь все свои запасы");
            System.out.println();
            EMEda = kolvoEDA;
        }

        inventory.put("Food", kolvoEDA - EMEda);
        int newGolod = players.getGolod() + (EMEda * 2);
        players.setGolod((byte) newGolod);
        System.out.println("Готово, ты поел"); 
        System.out.println();
        players.cheekStatus();
        System.out.println();
    }
    // МЕТОД ЧТОБЫ ПОПИТЬ
    public static void popit(Scanner scan, Map<String, Integer> inventory, player players) {
        System.out.println();
        players.cheekStatus();
        System.out.println();
        if (!inventory.containsKey("Water")) {
        System.out.println("В инвентаре нет воды!"); System.out.println();return;}
        System.out.println("Одна единица воды дает +3 к шкале");
        int kolvoEDA = inventory.get("Water");
        System.out.print("У тебя в инвентаре сейчас: " + kolvoEDA + " единиц воды, сколько ты хочешь израсходовать?: ");
        int EMEda = scan.nextInt();
        if (EMEda > kolvoEDA) {
            System.out.println();
            System.out.println("Еще раз, у тебя только " + kolvoEDA + " воды ");
            System.out.println("А ты хочешь випить больше чем у тебя есть, наглый жулик");
            System.out.println("Жулик, не жульничей, раз ты ввел больше чем у тебя есть, то выпьешь все свои запасы");
            System.out.println();

            EMEda = kolvoEDA;
        }

        inventory.put("Water", kolvoEDA - EMEda);
        double newJajda = players.getJajda() + (EMEda * 3);
        players.setJajda(newJajda);
        System.out.println("Готово, ты попил");
        System.out.println();
        players.cheekStatus();
        System.out.println();
    }
    // МЕТОД ДЛЯ ПОКУПКИ
    public static void pokupka(Scanner scan, player players, Map<String, Integer> inventory, planet currentPlanet) {
        // Используем рынок переданной планеты
        currentPlanet.showSeelGoods();
     
        System.out.print("Введите название товара, которое хотите купить: ");
        String tovarB = scan.nextLine();
        System.out.print("Введите количество: ");
        int shotB = scan.nextInt();
        scan.nextLine();
        System.out.println();
        System.out.println("Вы ввели: " + tovarB);
        System.out.println();

        // Проверяем, есть ли товар на рынке этой планеты
       
        if (!currentPlanet.seelGoods.contains(tovarB)) {
            System.out.println("На планете " + currentPlanet.namePlanet + " нет такого товара!"); System.out.println();
            return;
        }

        double price = currentPlanet.seelMarket.get(tovarB);
        double totalCost = price * shotB;

        if (players.money < totalCost) {
            System.out.println("Недостаточно денег! Нужно: " + totalCost + "$"); System.out.println();
            return;
        }

        // Добавляем в инвентарь
        inventory.put(tovarB, inventory.getOrDefault(tovarB, 0) + shotB);
        players.money -= totalCost;
        int newGolod = players.getGolod() - 1;
        players.setGolod((byte) newGolod);
        double newJajda = players.getJajda() - 1.5;
        players.setJajda(newJajda);
        players.rez++;

        System.out.println("Куплено: " + tovarB + " x" + shotB + " за " + totalCost + "$ на планете " + currentPlanet.namePlanet);
        players.cheekStatus();
        System.out.println(players);
        System.out.println();
    }
    // МЕТОД ДЛЯ ПРОДАЖИ
    public static void prodaja(Scanner scan, player players, Map<String, Integer> inventory, planet currentPlanet) {
        currentPlanet.showByuGoods();
    
        System.out.print("Введите название товара, который хотите продать: ");
        String tovarS = scan.nextLine();
        System.out.print("Введите количество: ");
        int shotS = scan.nextInt();
        scan.nextLine();

        if (!inventory.containsKey(tovarS)) {
            System.out.println("В твоем инвентаре нет такого товара!"); System.out.println();
            return;
        }

        int currentQty = inventory.get(tovarS);
        if (currentQty < shotS) {
            System.out.println("У тебя только " + currentQty + " шт. " + tovarS); System.out.println();
            return;
        }

        if (!currentPlanet.buyGoods.contains(tovarS)) {
            System.out.println("На планете " + currentPlanet.namePlanet + " не принимают этот товар!"); System.out.println();
            return;
        }

        double price = currentPlanet.buytMarket.get(tovarS);
        double totalIncome = price * shotS;

        players.money += totalIncome;
        int newGolod = players.getGolod() - 1;
        players.setGolod((byte) newGolod);
        double newJajda = players.getJajda() - 1.5;
        players.setJajda(newJajda);
        players.rez++;

        int newQty = currentQty - shotS;
        if (newQty == 0) {
            inventory.remove(tovarS);
        } else {
            inventory.put(tovarS, newQty);
        }

        System.out.println("Продано: " + tovarS + " x" + shotS + " за " + totalIncome + "$ на планете " + currentPlanet.namePlanet);
        players.cheekStatus();
        System.out.println(players);
        System.out.println();
    }
    // МЕТОД ДЛЯ ВЫХОДА В ОСНОВНОЕ МЕНЮ
    public static boolean Exit(Scanner scan) {

        System.out.println("Вы точно хотите выйти в основное меню? ");
        System.out.println("Yes");
        System.out.println("No");
        System.out.print("Введите свой ответ: ");
        String otvet = scan.nextLine();
        if(otvet.equalsIgnoreCase("Yes")) {return true;} 
        else {return false;}
    }
    // ПЕРЕЛЕТ НА ДРУГУЮ ПЛАНЕТУ
    public static String perelet (Scanner scan, player players, planet currentPlanet) {
        players.cheekStatus();
        System.out.println();
        if (currentPlanet.namePlanet.equals("Earch")) {
            System.out.println("Для перелета вам доступна только планета Марс, убедитесь что у вас есть в инвентаре скафандр с фильтрами иначе вам не выжить");
            if (players.inventory.isEmpty()) {
                System.out.println("Инвентарь пуст!");
                System.out.println();
            } else {System.out.println(players.inventory); System.out.println();}
            System.out.print("Если согласен перелететь напиши 'Yes', если хотите выйти в основное меню, напишите 'Exit': ");
            String otvet = scan.nextLine();
            if (otvet.equalsIgnoreCase("Yes")) {
                players.rez++;
                int newGolod = players.getGolod() - 1;
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5;
                players.setJajda(newJajda);
                players.cheekStatus(); 
                System.out.println(); 
                return "Mars";
            }
            else if (otvet.equalsIgnoreCase("Exit")) {
                Exit(scan); 
                System.out.println();
                return "Earch";
            }
            else {System.out.println("Вы не подтвердили перелет"); 
            System.out.println();
            return "Earch";
            }
        }
        
        if (currentPlanet.namePlanet.equals("Mars")) {
            System.out.println("Сочувствую что ты не нашел артефакт на Марсе, попытай удачу на Титане.");
            System.out.println("Но помни, там тебе нужен продвинутый скафандр с отдельными для них балонами с кислоролом.");
            System.out.println("И не забудь запастись оружием, планета кишит монстрами.");
            System.out.println("Если у тебя нет нужных вещей в инвентаре и ты не видел их на рынке марса, советую вернуться на змелю");
            System.out.println("Если выбираешь планету Титан напиши 'Titan' если хочешь вернуться на Землю напиши 'Earch'");
            System.out.println("Если хочешь выйти в основное меню напиши 'Exit'");
            System.out.print("Итак, ваш выбор: ");
            if (players.inventory.isEmpty()) {
                System.out.println("Инвентарь пуст!");
                System.out.println();
            } else {System.out.println(players.inventory); System.out.println();}
            String otvet = scan.nextLine();
            if (otvet.equalsIgnoreCase("Titan")) {
                players.rez++;
                int newGolod = players.getGolod() - 1;
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5;
                players.setJajda(newJajda);
                players.cheekStatus(); 
                System.out.println();
                return "Titan";
            }
            else if (otvet.equalsIgnoreCase("Earch")) {
                players.rez++;
                int newGolod = players.getGolod() - 1;
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5;
                players.setJajda(newJajda);
                players.cheekStatus(); 
                System.out.println();
                return "Earch"; 
            }
            else if (otvet.equalsIgnoreCase("Exit")) {
                Exit(scan); 
                System.out.println();
                return "Mars";
            } 
            else {System.out.println("Вы не подтвердили перелет"); 
            System.out.println();
            return "Mars";
            }
        }    

        if (currentPlanet.namePlanet.equals("Titan")) {
            System.out.println("Поздравляю ты побывал на последней планете и выжил");
            System.out.println("Убедись что ты положил в инвентарь артефакт и полетели в обратный путь");
            if (players.inventory.isEmpty()) {
                System.out.println("Инвентарь пуст!");
                System.out.println();
            } else {System.out.println(players.inventory); System.out.println();}
            System.out.println("Единсвтенная планета для перелета из доступных это Марс");
            System.out.println("Если подтверждаешь перелет, напиши 'Yes'");
            System.out.println("Если хочешь выйти в основное меню напиши 'Exit'");
            System.out.print("Итак, ващ выбор: ");
            String otvet = scan.nextLine();
            if (otvet.equalsIgnoreCase("Yes")) {
                players.rez++;
                int newGolod = players.getGolod() - 1;
                players.setGolod((byte) newGolod);
                double newJajda = players.getJajda() - 1.5;
                players.setJajda(newJajda);
                players.cheekStatus();  
                System.out.println();
                return "Mars";
            }
            else if (otvet.equalsIgnoreCase("Exit")) {
                Exit(scan);
                System.out.println();
                return "Titan";} 
            else {System.out.println("Вы не подтвердили перелет"); 
            System.out.println();
            return "Titan";
            }
        }    
        System.out.println();
        return currentPlanet.namePlanet;
    }
}

