package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class ChainTable implements Hashable {
    private LinkedList<String>[] chainTableOfWords;

    public ChainTable(int length) {
        this.chainTableOfWords = new LinkedList[length];
    }

    //    unsigned long
//    hash(unsigned char *str)
//    {
//        unsigned long hash = 5381;
//        int c;
//
//        while (c = *str++)
//        hash = ((hash << 5) + hash) + c; /* hash * 33 + c */
//
//        return hash;
//    }
    public String bernsteinHashing(String word) {
        int hash = 5381;
        //int c = (int) word.charAt(0);
        for (int i = 0; i < word.length(); ++i) {
            hash = hash = ((hash << 5) + hash) + (int) word.charAt(i);
        }
        return Integer.toUnsignedString(hash);
    }

    //    public String bernsteinHashing(String word)
//    {
//        Long hash = Integer.toUnsignedLong(5381);
//        //int c = (int) word.charAt(0);
//        for(int i = 0;i < word.length();++i)
//        {
//            hash = hash = ((hash << 5) + hash) + Integer.toUnsignedLong(word.charAt(i));
//        }
//        return String.valueOf(hash);
//    }
    @Override
    public long modularHashing(String word) {
        long asciiValue = 0;
        for (int i = 0; i < word.length(); ++i) {
            asciiValue += (long) word.charAt(i);
        }
        return asciiValue;
    }

    @Override
    public long modularHashingSimplified(String word) {
        long asciiValue = 0;
        for (int i = 0; i < word.length(); i += 2) {
            asciiValue += (long) word.charAt(i);
        }
        return asciiValue;
    }

    @Override
    public void writeOut() {
        for (LinkedList<String> iterator :
                chainTableOfWords) {

        }
        for (int i = 0; i < chainTableOfWords.length; ++i) {
            System.out.print(i + ". ");
            Iterator iterator;
            if (chainTableOfWords[i] != null) {
                iterator = chainTableOfWords[i].iterator();
            } else {
                System.out.println();
                continue;
            }
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ", ");
                //iterator.next();
            }
            System.out.println();
        }
    }

    public void insert(long asciiValue, String word) {
        if (chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)] == null) {
            chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)] = new LinkedList<>();
        }
        chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)].add(word);
    }

    public boolean deleteWordFromTable(long asciiValue, String word) {
        try {
            chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)].remove(word);
        } catch (Exception e) {
            System.out.println("No such element");
        }
        return true;
    }
    public int searchInTable(long asciiValue, String word) {
        if (chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)] != null) {
            if (chainTableOfWords[(int) (asciiValue % chainTableOfWords.length)].indexOf(word) != -1) {
                return (int) (asciiValue % chainTableOfWords.length);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}

