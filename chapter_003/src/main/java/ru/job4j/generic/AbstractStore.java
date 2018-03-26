package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    SimpleArray<T> array = new SimpleArray<>();

    @Override
    public void add(T model) {
        array.add((T) model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean answer = false;
        if (findIndex(id) != -1) {
            array.set(findIndex(id), model);
            answer = true;
        }
        return answer;
    }

    @Override
    public boolean delete(String id) {
        boolean answer = false;
        if (findIndex(id) != -1) {
            array.delete(findIndex(id));
            answer = true;
        }
        return answer;
    }

    @Override
    public T findById(String id) {
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
