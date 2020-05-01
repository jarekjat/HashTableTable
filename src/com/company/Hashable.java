package com.company;

public interface Hashable {
    public long modularHashing(String word);
    public long modularHashingSimplified(String word);
    public String bernsteinHashing(String word);
    public void writeOut();
    //public boolean deleteWordFromTable(int asciiValue, String word);
}
