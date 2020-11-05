import java.util.Enumeration;

public class Queue {
    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public void push(int value) {
        Node node = new Node(null, value);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int pop() {
        if (head == null) {
            System.out.println("Queue is empty");
            System.exit(0);
        }
        Node temp = head;
        head = temp.next;
        int value = temp.value;
        size--;
        return value;
    }

    public void show(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(pop() + " ");
        }
    }
    public void showMax(int num){
        for (int i = 0; i < num; i++) {
            System.out.print(getMaxNum() + " ");
        }
    }

    public void add(int numberOfElements, int elelment) {
        if (numberOfElements == 0) {
            return;
        }
        for (int i = 0; i < numberOfElements; i++) {
            push(elelment);
        }
    }
    public int getMaxNum(){
        int max =  head.value;
        Node maxNode = head;
        Node current = head;
        while(current != null){
            if(current.value > max){
                max = current.value;
                maxNode = current;
            }
            current = current.next;
        }
        maxNode.value = head.value;
        head.value = max;
        return pop();
    }
}
