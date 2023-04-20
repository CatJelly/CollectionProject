package org.example;

class Data<K, V> {
    private K key;
    private V value;

    public Data(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}


public class MyHashMap<K, V> {
    private final int BUCKET_SIZE = 30;
    private MyArrayList<Data<K, V>>[] mapList;
    private int elementSize;

    public MyHashMap() {
        mapList = new MyArrayList[BUCKET_SIZE];
        for (int i = 0; i < BUCKET_SIZE; i++) {
            mapList[i] = new MyArrayList<>();
        }
        elementSize = 0;
    }

    public int size() {
        return elementSize;
    }

    public V put(K key, V value) {
        int index = key.hashCode() % BUCKET_SIZE;
        Data<K, V> newData = new Data<>(key, value);

        MyArrayList temp = mapList[index];
        if (temp == null) {
            temp = new MyArrayList<>();
        }

        for (int i = 0; i < temp.size(); i++) {
            Data<K, V> data = (Data) temp.get(i);
            if (data.getKey().equals(key)) {
                V preValue = data.getValue();
                data.setValue(value);
                return preValue;
            }
        }
        mapList[index].add(newData);
        elementSize++;

        return null;
    }

    public V get(K key) {
        int index = key.hashCode() % BUCKET_SIZE;
        MyArrayList temp = mapList[index];

        if (temp == null) {
            return null;
        }

        for (int i = 0; i < temp.size(); i++) {
            Data<K, V> data = (Data) temp.get(i);
            if (data.getKey().equals(key)) {
                return data.getValue();
            }
        }

        return null;
    }

    public V remove(K key) {
        int index = key.hashCode() % BUCKET_SIZE;
        MyArrayList temp = mapList[index];

        if (temp == null) {
            return null;
        }

        for (int i = 0; i < temp.size(); i++) {
            Data<K, V> data = (Data) temp.get(i);
            if (data.getKey().equals(key)) {
                elementSize--;
                V value = data.getValue();
                temp.remove(i);
                return value;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = key.hashCode() % BUCKET_SIZE;
        MyArrayList temp = mapList[index];

        if (temp == null) {
            return false;
        }

        for (int i = 0; i < temp.size(); i++) {
            Data<K, V> data = (Data) temp.get(i);
            if (data.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < mapList.length; i++) {
            for (int j=0; j<mapList[i].size(); j++) {
                Data<K, V> data = (Data) mapList[i].get(j);
                if (data.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        for(int i=0; i<mapList.length; i++) {
            mapList[i].clear();
        }
        elementSize = 0;
    }


    public boolean isEmpty() {
        return elementSize == 0;
    }
}
