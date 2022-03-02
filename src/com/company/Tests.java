package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Tests {
    public static void main(String[] args) {
        //test_1();
    }

    /**
     * Получить List чисел в виде текстовых элементов
     */
    public static void test_1() {
        List<Integer> integerList = getIntList();
        List<String> strList = integerList.stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.toList());
        System.out.println(strList);
    }

    /**
     * Отсортировать список по убыванию
     */
    public static void test_2() {
        List<Integer> integerList = getIntList();
        List<Integer> intList = integerList.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(intList);
    }

    /**
     * Получить одну строку текста. Каждый элемент должен начинаться с текста "Number - ".
     * Элементы должны разделяться запятой и пробелом.
     * В начале итоговой строки должен быть текст "Number list: "
     * В конце итоговой строки должен быть текст "end of list.".
     */
    public static void test_3() {
        List<String> stringList = getStringList();
        String str = stringList
                .stream()
                .map(item -> "Number - " + item)
                .collect(Collectors.joining(", ", "Number list: ", " end of list."));
        System.out.println(str);
    }

    /**
     * Получить мапу со значениями, ключи которых больше трех и меньше девяти
     */
    public static void test_4() {
        Map<Integer, String> map = getMap();
        Map<Integer, String> map2 = map.entrySet()
                .stream()
                .filter(item -> item.getKey() > 3 && item.getKey() < 9)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map2);
    }

    /**
     * Перемешать все элементы в мапе.
     * Пример результата:
     * Элемент 1: ключ - 5, значение "five"
     * Элемент 2: ключ - 7, значение "seven"
     * Элемент 3: ключ - 2, значение "two"
     * и так далее.
     */
    public static void test_5() {
        Map<Integer, String> map = getMap();
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.shuffle(list);

        Map<Integer, String> shuffleMap = new LinkedHashMap<>();
        list.forEach(k->{
            shuffleMap.put(k, map.get(k));
        });
        int i = 0;
        for (Map.Entry res: shuffleMap.entrySet()) {
            i = i+1;
            System.out.println("Элемент " + i + ": ключ - " + res.getKey() + ", значение \"" + res.getValue() + "\"");
        }
    }

    /**
     * Установить во всех элементах isDisplayed = true, и оставить в списке только элементы с value NULL.
     */
    public static void test_6() {
        List<WebElement> elements = getElements();
        List<WebElement> elements1 = elements
                .stream()
                .peek(item -> item.setDisplayed(true))
                .filter(item -> item.getValue() == null)
                .collect(Collectors.toList());
    }

    /**
     * Инвертировать isDisplayed параметр каждого элемента и отсортировать список по типу элемента
     * со следующим приоритетом:
     * 1. TEXT
     * 2. INPUT_FIELD
     * 3. CHECKBOX
     * 4. BUTTON
     * 5. RADIO_BUTTON
     * 6. IMAGE
     */
    public static void test_7() {
        List<WebElement> elements = getElements();

        //В этой задачке я получила отдельные списки и потом соединила их вместе

        List<WebElement> elementsTEXT = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.TEXT)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());

        List<WebElement> elementsINPUTFIELD = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.INPUT_FIELD)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());

        List<WebElement> elementsCHECKBOX = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.CHECKBOX)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());

        List<WebElement> elementsBUTTON = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.BUTTON)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());


        List<WebElement> elementsRADIOBUTTON = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.RADIO_BUTTON)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());

        List<WebElement> elementsIMAGE = elements
                .stream()
                .peek(item -> item.setDisplayed(!item.isDisplayed()))
                .filter(item -> item.getType() == Type.IMAGE)
                .sorted((item_1,item_2) -> item_1.getType().compareTo(item_2.getType()))
                .collect(Collectors.toList());

        List<WebElement> elementsSort = new ArrayList ();

        elementsSort.addAll(elementsTEXT);
        elementsSort.addAll(elementsINPUTFIELD);
        elementsSort.addAll(elementsCHECKBOX);
        elementsSort.addAll(elementsBUTTON);
        elementsSort.addAll(elementsRADIOBUTTON);
        elementsSort.addAll(elementsIMAGE);

        for(int i =0 ; i < elementsSort.toArray().length; i++)
        {
            System.out.println(elementsSort.get(i).getType() + " " + elementsSort.get(i).getText()  + " " + elementsSort.get(i).getValue() + " " + elementsSort.get(i).isDisplayed());
        }

    }


    /**
     * Создать мапу:
     * ключ - текст
     * значение - тип элемента
     */
    public static void test_8() {
        List<WebElement> elements = getElements();
        List<WebElement> elements2 = elements
                .stream()
                .filter(item -> item.getText() != null)
                .collect(Collectors.toList());
        Map<String, Type> mapElements = elements2
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getText(),
                        t -> t.getType()));
        System.out.println(mapElements);
    }

    /**
     * Получить список элементов, у которых текст или значение оканчивается на число от 500 и более.
     * И отсортировать по увеличению сначала элементы с текстом, а затем по убыванию элементы со значением.
     */
    public static void test_9() {
        List<WebElement> elements = getElements();

        //Формируем элементов, у которых текст или значение оканчивается на число от 500 и более
        List<WebElement> elementsMore500 = elements.stream()
                .filter(item -> {
                    if (item.getText() == null && item.getValue() != null) return Integer.parseInt(item.getValue().substring(17)) > 500;
                    else
                        return item.getValue() == null;
                })
                .filter(item -> {
                    if (item.getText() != null && item.getValue() == null) return Integer.parseInt(item.getText().substring(16)) > 500;
                    else
                        return item.getText() == null;
                })
                .collect(Collectors.toList());

        //Формируем отдельный список с отсортированными по возрастанию элементами с текстом
        List<WebElement> elementsSortText = elementsMore500
                .stream()
                .filter(item -> item.getText() != null)
                .sorted((item1,item2) -> {
                    int result = 0;
                    if (Integer.parseInt(item1.getText().substring(16)) < (Integer.parseInt(item2.getText().substring(16)))) result = -1;
                    else if (Integer.parseInt(item1.getText().substring(16)) > (Integer.parseInt(item2.getText().substring(16)))) result = 1;
                    return result;
                })
                .collect(Collectors.toList());

        //Формируем отдельный список с отсортированными по убыванию элементами со значением
        List<WebElement> elementsSortValue = elementsMore500
                .stream()
                .filter(item -> item.getValue() != null)
                .sorted((item1,item2) -> {
                    int result = 0;
                    if (Integer.parseInt(item1.getValue().substring(17)) < (Integer.parseInt(item2.getValue().substring(17)))) result = 1;
                    else if (Integer.parseInt(item1.getValue().substring(17)) > (Integer.parseInt(item2.getValue().substring(17)))) result = -1;
                    return result;
                })
                .collect(Collectors.toList());

        //Формируем отдельный список с null
        List<WebElement> elementsNull = elementsMore500
                .stream()
                .filter(item -> item.getText() == null && item.getValue() == null)
                .collect(Collectors.toList());

        //Собираем все списки в один
        List<WebElement> elementsFinal = new ArrayList ();
        elementsFinal.addAll(elementsSortText);
        elementsFinal.addAll(elementsSortValue);
        elementsFinal.addAll(elementsNull);

        //Выводим для проверки
        for(int i =0 ; i < elementsFinal.toArray().length; i++)
        {
            System.out.println(elementsFinal.get(i).getText()  + " " + elementsFinal.get(i).getValue());
        }
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        return map;
    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("one");
        list.add("nine");
        list.add("ten");
        return list;
    }

    public static List<Integer> getIntList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        return list;
    }

    public static List<WebElement> getElements() {
        List<WebElement> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            result.add(new WebElement());
        }
        return result;
    }
}
