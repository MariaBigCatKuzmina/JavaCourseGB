package Java3.Lesson1;


public class TestBox {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple(2));
        appleBox.add(new Apple(5.3));
        appleBox.add(new Apple(2.5));

        Box<Orange> orangeBox= new Box<>();
        orangeBox.add(new Orange(1.4));
        orangeBox.add(new Orange(2.6));
        orangeBox.add(new Orange(2.4));

        System.out.println(orangeBox.isEqualWeights(appleBox));
        System.out.println(orangeBox.compareTo(appleBox));

        System.out.println("Коробка с апельсинами весит: " + orangeBox.getBoxWeight());
        System.out.println("Коробка с яблоками весит: " + appleBox.getBoxWeight());

        Box<Orange> newFruitBox = new Box<>();
        if (orangeBox.pourInto(newFruitBox)) {
            System.out.println("Пересыпали фрукты из первой коробки во вторую");
        }
        System.out.println("Старая Коробка с апельсинами весит: " + orangeBox.getBoxWeight());
        System.out.println("Новая Коробка с апельсинами весит: " + newFruitBox.getBoxWeight());

    }
}
