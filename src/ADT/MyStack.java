package ADT;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStack<E> extends Stack<E>
{
    private List<E> list = new ArrayList<>();

    @Override
    public E pop()
    {
        return list.remove(list.size() - 1);
    }

    @Override
    public E push(E item)
    {
        list.add(item);
        return item;
    }

    @Override
    public E peek()
    {
        return list.get(list.size() - 1);
    }

    @Override
    public boolean empty()
    {
        return list.isEmpty();
    }

    @Override
    public int search(Object o)
    {
        return list.indexOf(o) + 1;
    }

}