
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;



// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// 1. Написать метод, который будет запрашивать у пользователя критерий фильтрации и выведет выборку: имя ноутбука и выбранный критерий . Критерии фильтрации можно хранить в Map.
// Сделать выборку.
// Например:
// Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// Пользователь ввел 1. Вывести в виде.
// |Имя| ОЗУ|
// |--------|-----------|
// | Asus | 2ГБ |
// | HP | 4ГБ |
// | MSI | 6ГБ |
// 2. Отфильтровать ноутбуки первоначального множества. По имени ноутбука в алфавитном порядке, по цене по убыванию
// Например, по алфавиту
// |Имя| ОЗУ| Цена
// |--------|-----------|-----------|
// | Asus | 2ГБ | 4666|
// | MSI | 4ГБ |6600|
// | HP | 6ГБ| 5555|

public class Laptop {
    public static void main(String[] args) {
        Laptops hp = new Laptops("HP", 8, 49_990, "blue", 256, "NA", 15.6);
        Laptops dell = new Laptops("DELL", 8, 89_990, "gray", 512, "Windows 11 Home", 15.6);
        Laptops asus = new Laptops("Asus", 32, 226_900, "black", 1024, "Windows 11 Home", 16.0);
        Laptops lenovo = new Laptops("Lenovo", 4, 28_700, "black", 512, "NA", 16.0);
        Laptops aser = new Laptops("Acer", 4, 35_990, "black", 256, "Linux", 15.6);
        Laptops huawei = new Laptops("Huawei", 16, 72_790, "gray", 512, "Windows 11 Home", 16.0);

        List<Laptops> laptopsList = Arrays.asList(hp, dell, asus, lenovo, aser, huawei);
        
    
        Menu(laptopsList);

    }

    public static void Menu(List<Laptops> list) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Runnable> menu = new HashMap<>();
        menu.put(1, () -> getAllRAM(list));
        menu.put(2, () -> getAllPrice(list));
        menu.put(3, () -> getAllOS(list));
        menu.put(4, () -> getAllColor(list));
        menu.put(5, () -> getAllHD(list));
        menu.put(6, () -> getAllDiagonal(list));
        menu.put(7, () -> System.out.println("Выход"));
        int userCommand = 0;
        do {
            try {
                System.out.println("\nВведите цифру, соответствующую необходимому критерию:");
                System.out.println(
                        "1 - ОЗУ\n2 - Цена\n3 - Операционная система\n4 - Цвет\n5 - Объем жесткого диска\n6 - Диагональ экрана\n7 - Выход");

                userCommand = sc.nextInt();

                menu.get(userCommand).run();
                sortMapLap(list,userCommand);
            } catch (Exception e) {
                System.out.println("Неправильный пункт меню!");
            }
        } while (userCommand != 7);
        sc.close();
    }
    public static void sortMapLap(List<Laptops> list,int userCommand){
    
        Scanner sc = new Scanner(System.in);
        System.out.println("Если хотите отсортировать по выбранной категории напишите - sort\nЕсли нет, то любой символ");
        String sortCheck=sc.next();
        if (sortCheck.equals("sort"))
        System.out.println("\nФирма|ОЗУ(ГБ)|Цена(руб)|Опер. Cистема|Цвет|Объем ЖД(ГБ)|Диагональ(Дюйм)|");
        try {
           printSortedListInteger((sortingByValueHashInteger(filLaptopMapsInteger(list,userCommand))));
        } catch (Exception e) {
            printSortedListString((sortingByValueHash(filLaptopMaps(list,userCommand))));
        }
        

    }

    public static Map<String, Object> getAllRAM(List<Laptops> list) {
        Map<String, Object> lapRam = new HashMap<>();
        for (Laptops el : list) {
            lapRam.put(el.getName(), el.getRam());
        }
        ShowMapLaptops(lapRam, " ГБ");
        return lapRam;
    }

    public static Map<String, Object> getAllPrice(List<Laptops> list) {
        Map<String, Object> lapPrice = new HashMap<>();
        for (Laptops el : list) {
            lapPrice.put(el.getName(), el.getPrice());
        }
        ShowMapLaptops(lapPrice, " руб");
        return lapPrice;
    }

    public static Map<String, Object> getAllOS(List<Laptops> list) {
        Map<String, Object> lapOS = new HashMap<>();
        for (Laptops el : list) {
            lapOS.put(el.getName(), el.getOperatingSystem());
        }
        ShowMapLaptops(lapOS, "");
        return lapOS;
    }

    public static Map<String, Object> getAllColor(List<Laptops> list) {
        Map<String, Object> lapColor = new HashMap<>();
        for (Laptops el : list) {
            lapColor.put(el.getName(), el.getColor());
        }
        ShowMapLaptops(lapColor, "");
        return lapColor;
    }

    public static Map<String, Object> getAllHD(List<Laptops> list) {
        Map<String, Object> lapHD = new HashMap<>();
        for (Laptops el : list) {
            lapHD.put(el.getName(), el.getHardDrive());
        }
        ShowMapLaptops(lapHD, " ГБ");
        return lapHD;
    }

    public static Map<String, Object> getAllDiagonal(List<Laptops> list) {
        Map<String, Object> lapDiagonal = new HashMap<>();
        for (Laptops el : list) {
            lapDiagonal.put(el.getName(), el.getScreenDiagonal());
        }
        ShowMapLaptops(lapDiagonal, " Дюймов");
        return lapDiagonal;
    }

    public static void ShowMapLaptops(Map<String, Object> map, String param) {
        Map<String,Object> sortedMap=new TreeMap<>(map);
        for (Map.Entry<String, Object> el : sortedMap.entrySet()) {
            System.out.println("| " + el.getKey() + " | " + el.getValue() + param + " | ");
        }
    }
    public static List<String> getLapClassFields(Laptops laptop){
        List<String> laptopField = Arrays.asList(laptop.getName(),Integer.toString(laptop.getRam()),Integer.toString(laptop.getPrice()),laptop.getOperatingSystem(),
        laptop.getColor(),Integer.toString(laptop.getHardDrive()), String.valueOf(laptop.getScreenDiagonal()));
        return laptopField;
    }

    public static Map<List<String>, String> filLaptopMaps(List<Laptops> listLap,int indexSort){
        Map<List<String>, String> mapForSorting = new HashMap<>();
        String temp1=new String();
        for (Laptops el : listLap) {
            List<String> temp=new ArrayList<>();
            temp=getLapClassFields(el);
            temp1=temp.get(indexSort);
            mapForSorting.put(temp,temp1);
        }
        return mapForSorting;
    
    }
    public static Map<List<String>, Integer> filLaptopMapsInteger(List<Laptops> listLap,int indexSort){
        Map<List<String>, Integer> mapForSorting = new HashMap<>();
        Integer temp1=0;
        for (Laptops el : listLap) {
            List<String> temp=new ArrayList<>();
            temp=getLapClassFields(el);
            temp1=Integer.parseInt(temp.get(indexSort));
            mapForSorting.put(temp,temp1);
        }
        return mapForSorting;
    
    }
    public static Map<List<String>, String> sortingByValueHash(Map<List<String>, String> userhashmap) {
        userhashmap = userhashmap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        return userhashmap;
    }
    public static Map<List<String>, Integer> sortingByValueHashInteger(Map<List<String>, Integer> userhashmap) {
        userhashmap = userhashmap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        return userhashmap;
    }
    public static void printSortedListInteger(Map<List<String>,Integer> map){
        for (var el : map.entrySet()) {
            System.out.println(String.join("| ", el.getKey()));
        }

    }
    public static void printSortedListString(Map<List<String>,String> map){
        for (var el : map.entrySet()) {
            System.out.println(String.join("| ", el.getKey()));
        }
    }



}

class Laptops {
    String name;
    Integer ram;
    Integer price;
    String color;
    Integer hardDrive;
    String operatingSystem;
    Double screenDiagonal;

    Laptops(String name, Integer ram, Integer price, String color, Integer hardDrive, String operatingSystem,
            Double screenDiagonal) {
        this.name = name;
        this.ram = ram;
        this.price = price;
        this.color = color;
        this.hardDrive = hardDrive;
        this.operatingSystem = operatingSystem;
        this.screenDiagonal = screenDiagonal;
    }

    public String getName() {
        return name;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public Integer getHardDrive() {
        return hardDrive;
    }

    public Double getScreenDiagonal() {
        return screenDiagonal;
    }

    

}
