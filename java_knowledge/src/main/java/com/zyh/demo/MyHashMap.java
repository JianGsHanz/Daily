package com.zyh.demo;

import androidx.annotation.Nullable;


/**
 * Time:2020/2/19
 * Author:zyh
 * Description:简单实现1.7的HashMap
 */
public class MyHashMap<K,V>{

    private Entry<K,V>[] table; //数组
    private static int CAPACITY = 8; //数组容量
    private int size = 0; //map大小

    public MyHashMap() {
        table = new Entry[CAPACITY];
    }

    public int size() {
        return size;
    }

    @Nullable
    public V get(@Nullable Object key) {
        int hash = key.hashCode(); //获取key的hash值
        int index = hash % table.length; //根据hash值获取下标

        //根据key取value的逻辑
        for (Entry<K,V> entry = table[index];entry!=null;entry = entry.next){
            if (entry.k.equals(key)){
                return entry.v;
            }
        }
        return null;
    }

    @Nullable
    public V put(K key, V value) {
        int hash = key.hashCode(); //获取key的hash值
        int index = hash % table.length; //根据hash值获取下标

        System.out.println(String.format("%s的value是%s，hash是%s,下标是%s",key,value,hash,index));
        //key重复，return oldValue,将新的value赋值给Entry数组
        for (Entry<K,V> entry = table[index];entry!=null;entry = entry.next){
            if (entry.k.equals(key)){
                V oldValue = entry.v;
                entry.v = value;
                return oldValue;
            }
        }
        //根据hash值获取的下标一样的话，产生链表形式，新的Entry占据原来位置，新Entry的next指向老的Entry
        addEntry(key, value, index);
        return null;


    }

    //冲突的时候
    private void addEntry(K key, V value, int index) {
        Entry entry = new Entry<>(key, value, table[index]);
        table[index] = entry;
        size++;
    }

    class Entry<K,V>{
        private K k;
        private V v;
        private Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
