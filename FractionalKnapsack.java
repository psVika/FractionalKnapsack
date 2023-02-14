/* Задача о рюкзаке.
Входные данные:
-веса предметов w1,w1,w3
-ценность каждого предмета v1, v2, v3
-вместимость рюкзака W
-колличество предметов n
Выходные данные:
-Максимальное значение ценности, которое помещается в рюкзак с вместимостью W
 */

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    public static void main(String[] args){
        final Item item1 = new Item(4,20);
        final Item item2 = new Item(2,14);
        final Item item3 = new Item(3,18);

        final Item[] items = {item1, item2, item3}; //набор всех имеющихся у нас предметов

        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerUnitOfWright).reversed());
        // O(N* log(N)), где N кол-во элементов в массиве items.  Сортировка элемонтов по значению удельной ценности
        System.out.println(Arrays.toString(items));

        final int W = 7;//вместимость рюкзака

        int weightSoFar = 0; //текущий вес
        double valueSoFar = 0; //ценность, которую уже набрали в рюкзак
        int currentItem = 0; //индекс текущего предмета

        while(currentItem < items.length && weightSoFar != W) {
            if(weightSoFar + items[currentItem].getWeight() < W) {
                //пока мы не вышли за пределы массива items и пока текущий вес не превышает вместимость рюкзака
               // и если накопленный вес и вес следующего предмета меньше заданного, то берем объект целиком.
               valueSoFar += items[currentItem].getValue();
               weightSoFar += items[currentItem].getWeight();
            } else{
                //берем только часть объекта
                valueSoFar +=((W - weightSoFar) / (double) items[currentItem].getWeight()) *
                        items[currentItem].getValue(); //получаем значение какую объекта мы сможем в итоге взять и умножаем на ценность
                //Получаем ценность той части, которую мы взяли

                weightSoFar = W;//полный рюкзак
            }
            currentItem++;
        }
        System.out.println("Ценность наилучшего набора: " + valueSoFar);
    }
}

class Item {
    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    public double valuePerUnitOfWright(){
        return value / (double)weight; //метод возвращает удельную ценность для текущего предмета.
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public String toString(){
        return "{w:" + weight + ",v:" + value + "}";
    }
}