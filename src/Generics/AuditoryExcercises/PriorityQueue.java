package Generics.AuditoryExcercises;

import java.util.Set;
import java.util.TreeSet;

interface Drawable {
    String draw();
}

class DrawingItem implements Drawable {
    private String item;

    public DrawingItem(String item) {
        this.item = item;
    }

    @Override
    public String draw() {
        return item;
    }

    @Override
    public String toString() {
        return item;
    }
}

class Item<T> implements Comparable<Item<T>> {

    private T item;
    private int priority;


    public Item(T item, int priority) {
        this.item = item;
        this.priority = priority;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Item<T> o) {
        return Integer.compare(o.priority, this.priority);
    }

    @Override
    public String toString() {
        return String.format("[%s -- %d]", item, priority);
    }
}

public class PriorityQueue<T extends Drawable> {
    private Set<Item<T>> items;

    public PriorityQueue() {
        items = new TreeSet<>();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void add(T item, int priority)
    {
        items.add(new Item<>(item, priority));
    }

    public Item<T> remove()
    {
        Item<T> item = items.iterator().next();
        items.remove(item);
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PriorityQueue<DrawingItem> queue = new PriorityQueue<>();
        queue.add(new DrawingItem("test1"), 303);
        queue.add(new DrawingItem("test2"), 2);
        queue.add(new DrawingItem("test3"), 393300);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
