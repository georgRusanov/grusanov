package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store {

    SimpleArray<T> array = new SimpleArray<>();

    @Override
    public void add(Base model) {
        array.add((T) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean answer = false;
        if(findIndex(id) != -1) {
            array.set(findIndex(id), (T) model);
            answer = true;
        }
        return answer;
    }

    @Override
    public boolean delete(String id) {
        boolean answer = false;
        if(findIndex(id) != -1) {
            array.delete(findIndex(id));
            answer = true;
        }
        return answer;
    }

    @Override
    public Base findById(String id) {
        return  findIndex(id) != -1 ? array.get(findIndex(id)) : null;
    }

    public int findIndex(String id) {
        int index = -1;
        for (int i = 0; i < array.index; i++) {
            if (id.equals(array.get(i).getId())) {
                index = i;
                break;
            }
        }
        return  index;
    }
}
