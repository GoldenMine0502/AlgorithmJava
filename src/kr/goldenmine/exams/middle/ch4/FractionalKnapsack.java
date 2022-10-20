package kr.goldenmine.exams.middle.ch4;

import java.util.Arrays;
import java.util.Collections;

public class FractionalKnapsack {
    static class Product {
        int weight;
        int price;

        public Product(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }

    /*
    주석 50g, 5만원
    백금 10g, 60만원
    은 25g, 10만원
    금 15g, 75만원
     */
    public static void main(String[] args) {
        int C = 40;

        Product[] products = new Product[4];
        products[0] = new Product(50, 5);
        products[1] = new Product(10, 60);
        products[2] = new Product(25, 10);
        products[3] = new Product(15, 75);

        Arrays.sort(products, (o1, o2) -> {
            double p1 = (double)o1.price / o1.weight;
            double p2 = (double)o2.price / o2.weight;
            return -Double.compare(p1, p2);
        });

        System.out.println(Arrays.toString(products));

        double sumPrice = 0;
        double sumWeight = 0;

        int index = 0;
        while(sumWeight + products[index].weight <= C) {
            sumWeight += products[index].weight;
            sumPrice += products[index].price;
            index++;
        }

        if(C - sumWeight > 0) {
            System.out.println(products[index].price * ((C - sumWeight) / products[index].weight));
            sumPrice += products[index].price * ((C - sumWeight) / products[index].weight);
            sumWeight += C - sumWeight;
        }
        System.out.println(sumPrice);
        System.out.println(sumWeight);
    }
}
