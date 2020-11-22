import java.util.Scanner;

public class Main {
public static void main(String args[]){
    System.out.println("Введите количество очередей.");
    Scanner scan = new Scanner(System.in);
    int numberOfQueues = scan.nextInt();
    System.out.println("Введите пропускную способность.");
    int capacity = scan.nextInt();
    double priority[] = new double[numberOfQueues - 1];

    System.out.println("Введите количество элементов приоритетной очереди.");
    Queue priorityQueue = new Queue();
    int numberOfElements = scan.nextInt();
    priorityQueue.add(numberOfElements,0);

    Queue wheighedQueues[] = new Queue[numberOfQueues - 1];
    double prioritySum = 0;

    //если кол-во очередей = 1 , то взешенные очереди создаваться не будут
    if (numberOfQueues > 1) {

        Scanner s = new Scanner(System.in);
        for (int i = 0; i < numberOfQueues - 1; i++) {
            System.out.println("Введите вес " + (i + 1) + " взвешенной очереди.");
            priority[i] = s.nextDouble();
            prioritySum += priority[i];
            System.out.println("Ведите количество ее элементов.");
            wheighedQueues[i] = new Queue();
            wheighedQueues[i].add(scan.nextInt(),(i + 1));
        }
    }

    for (int i = 0; i < (priorityQueue.size / capacity); ) {
        System.out.print("Элементы приоритетной очереди:");
        priorityQueue.show(capacity);
        System.out.println();
    }

    //если остался остаток
    if (priorityQueue.size != 0) {
        int cap = capacity - priorityQueue.size;
        System.out.print("Элементы приоритетной очереди: ");
        priorityQueue.show(priorityQueue.size);
        System.out.println();
        System.out.println("Элементы взвешенных очередей: ");
        showWeighedQueues(wheighedQueues, priority, prioritySum, cap);
        System.out.println();
    }
    //цикл вывода элементов взвешенных очередей
    while (true) {
        prioritySum = 0;
        for (int i = 0; i < priority.length; i++) {
            if (wheighedQueues[i].size != 0) prioritySum += priority[i];
        }
        if (prioritySum != 0) {
            showWeighedQueues(wheighedQueues, priority, prioritySum, capacity);
            System.out.println();
        } else break;
    }
}
    private static void showWeighedQueues(Queue[] queues, double[] pr, double sum, int capacity) {
        boolean en = true;
        int cap = capacity;
        double s = sum;
        for (int i = 0; i < queues.length; i++) {
            int num = (int) Math.round((double) capacity / sum * pr[i]);
            if (num <= queues[i].size) {
                queues[i].show(num);
                cap -= num;
            } else {
                cap -= queues[i].size;
                queues[i].show(queues[i].size);
                en = false;
                s -= pr[i];
                pr[i] = 0;

            }
        }
        if (!en) {
            showWeighedQueues(queues, pr, s, cap);
        }


    }

}
