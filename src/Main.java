import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();

        List<Item> valuesWeight = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Item item = new Item(scanner.nextInt(), scanner.nextInt());
            valuesWeight.add(item);
        }

        Loot loot = new Loot(capacity, valuesWeight);
        System.out.println(loot.getOptimalValue());
    }
}

class Item implements Comparable<Item> {
    private double value;
    private double weight;
    private BigDecimal ratioBig;
    private double ratioDouble;

    Item(int value, int weight) {
        this.value = (double) value;
        this.weight = (double) weight;
        this.ratioBig = BigDecimal.valueOf(this.value / this.weight);
        this.ratioDouble = this.value / this.weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public BigDecimal getRatioBig() {
        return ratioBig;
    }

    public double getRatioDouble() {
        return ratioDouble;
    }

    @Override
    public int compareTo(Item item) {
        return ratioBig.compareTo(item.getRatioBig());
    }
}

class Loot {
    private double capacity;
    private List<Item> valuesWeight;
    private double optimalValue;

    Loot(int capacity, List<Item> valuesWeight) {
        this.capacity = capacity;
        this.valuesWeight = valuesWeight;

        // sorting Items in a loot list
        this.valuesWeight.sort(Item::compareTo);
        Collections.reverse(this.valuesWeight);

        calculateOptimal();
    }

    private void calculateOptimal() {

        for (int i = 0; i < valuesWeight.size(); i++) {
            Item item = valuesWeight.get(i);
            double value = item.getValue();
            double weight = item.getWeight();
            if (capacity == 0) break;

            int a = (int) Math.min(capacity, weight);
            optimalValue += a * value / weight;
            capacity -= a;
        }

    }

    double getOptimalValue() {
        return (double) Math.round(this.optimalValue * 10000) / 10000;
    }
}