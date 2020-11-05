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
    //создаем приоритетную очередь
    Queue priorityQueue = new Queue();
    int numberOfElements = scan.nextInt();
    System.out.println("Введите элементы приоритетной очереди");
    for(int i = 0; i < numberOfElements; i ++){
        priorityQueue.push(scan.nextInt());
    }
    //создаем взвешанные очереди
    Queue wheighedQueues[] = new Queue[numberOfQueues - 1];
    //prioritySum - сумма долей взвешенных очередей
    double prioritySum = 0;
    //если кол-во очередей=1, то взешенные очереди создаваться не будут
    if (numberOfQueues > 1) {

        Scanner s = new Scanner(System.in);
        //ввод данных взвешенных оередей
        for (int i = 0; i < numberOfQueues - 1; i++) {
            System.out.println("Введите приоритет " + (i + 1) + " взвешенной очереди.");
            priority[i] = s.nextDouble();
            prioritySum += priority[i];
            System.out.println("Ведите количество ее элементов.");
            wheighedQueues[i] = new Queue();
            wheighedQueues[i].add(scan.nextInt(),(i + 1));
        }
    }
    //сначала выведем элементы приоритетной очереди
    //в этом цикле выводятся элементы только в том случае, если элементы приоритетной очереди
    //полностью покрывают пропускную спосоность
    for (int i = 0; i < (priorityQueue.size / capacity); ) {
        System.out.print("Элементы приоритетной очереди:");
        priorityQueue.showMax(capacity);
        System.out.println();
    }
    //если не вывелись все элементы приоритетной очереди, т. е. осталось такое кол-во,
    //которое не покрываает пропускную способность,
    //то выводятся сначала элементы приоритетной очерели, потом из взвешенных
    if (priorityQueue.size != 0) {
        int cap = capacity - priorityQueue.size;
        System.out.print("Элементы приоритетной очереди: ");
        priorityQueue.showMax(priorityQueue.size);
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
