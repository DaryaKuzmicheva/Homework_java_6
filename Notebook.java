import java.util.*;

public class Notebook {
    private int ram;
    private int hddSize;
    private String operatingSystem;
    private String color;

    public Notebook(int ram, int hddSize, String operatingSystem, String color) {
        this.ram = ram;
        this.hddSize = hddSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getHddSize() {
        return hddSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    
    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<Integer, Object> filters) {
        Set<Notebook> result = new HashSet<>();

        for (Notebook notebook : notebooks) {
            boolean matchesFilter = true;

            for (Map.Entry<Integer, Object> entry : filters.entrySet()) {
                int criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case 1:
                        int minRam = Integer.parseInt((String) value);
                        if (notebook.getRam() < minRam) {
                            matchesFilter = false;
                        }
                        break;
                    case 2:
                        int minHddSize = Integer.parseInt((String) value);
                        if (notebook.getHddSize() < minHddSize) {
                            matchesFilter = false;
                        }
                        break;
                    case 3:
                        String operatingSystem = (String) value;
                        if (!notebook.getOperatingSystem().equalsIgnoreCase(operatingSystem)) {
                            matchesFilter = false;
                        }
                        break;
                    case 4:
                        String color = (String) value;
                        if (!notebook.getColor().equalsIgnoreCase(color)) {
                            matchesFilter = false;
                        }
                        break;
                    
                }
            }

            if (matchesFilter) {
                result.add(notebook);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook(8, 500, "Windows 10", "Black"));
        notebooks.add(new Notebook(16, 512, "MacOS", "Silver"));
        notebooks.add(new Notebook(16, 1000, "MacOS", "Silver"));
        notebooks.add(new Notebook(4, 128, "Linux", "Silver"));
        notebooks.add(new Notebook(12, 1000, "Windows 11", "White"));
        notebooks.add(new Notebook(12, 256, "Linux", "Red"));
        notebooks.add(new Notebook(8, 256, "Windows 10", "Silver"));
        
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        System.out.println("Введите минимальное значение для указанного критерия:");
        Object value = scanner.next();

        filters.put(criterion, value);

        for (int i = 2; i <= 4; i++) {
            System.out.println("Введите минимальное значение для критерия " + i + ":");
            Object minValue = scanner.next();
            filters.put(i, minValue);
        }

        
        Set<Notebook> result = filterNotebooks(notebooks, filters);

        if (result.isEmpty()) {
            System.out.println("Ноутбуки, отвечающие заданным критериям, не найдены.");
        } else {
            System.out.println("Ноутбуки, отвечающие заданным критериям:");
            for (Notebook notebook : result) {
                System.out.println("ОЗУ: " + notebook.getRam() + " ГБ");
                System.out.println("Объем ЖД: " + notebook.getHddSize() + " ГБ");
                System.out.println("Операционная система: " + notebook.getOperatingSystem());
                System.out.println("Цвет: " + notebook.getColor());
                System.out.println();
            }
        }
        scanner.close();
    }
}
