package com.company;

import java.util.Hashtable;

public class Table implements Hashable {
    private String [] tableOfWords;
    private int howManyInserted = 0;
    private int c1 = 3;
    private int c2 = 5;
    //private final int squareConstant;
    public Table(int length) {
        //if(squareConstant < 1) return;
        this.tableOfWords = new String[length];
        //this.squareConstant = squareConstant;
    }
    @Override
    public long modularHashing(String word)
    {
        long asciiValue = 0;
        for(int i = 0;i < word.length();++i)
        {
            asciiValue += (long) word.charAt(i);
        }
        return asciiValue;
    }
    public String bernsteinHashing(String word)
    {
        int hash = 5381;
        //int c = (int) word.charAt(0);
        for(int i = 0;i < word.length();++i)
        {
            hash = hash = ((hash << 5) + hash) + (int) word.charAt(i);
        }
        return Integer.toUnsignedString(hash);
    }

    @Override
    public long modularHashingSimplified(String word) {
        long asciiValue = 0;
        for(int i = 0;i < word.length();i+=2)
        {
            asciiValue += (long) word.charAt(i);
        }
        return asciiValue;
    }
    public boolean insertSquaredMethod(long hashValue, String word)
    {
        boolean inserted = false;
        if(howManyInserted == tableOfWords.length)
        {
            System.out.println("Tablica pełna");
            return false;
        }
            long i = 0;
            int sum;
            do
            {
                sum = (int) ((hashValue  + c1*i + c2*i*i) % tableOfWords.length);
                if(tableOfWords[sum] == null)
                {
                    tableOfWords[sum] = word;
                    ++howManyInserted;
                    inserted = true;
                }
                ++i;
            }while(!inserted && i <= tableOfWords.length);//warunek!!!

        if(inserted)return true;
        else return false;
    }
    public boolean insertDoubleMethod(long hashValue, String word)
    {
        boolean inserted = false;
        if(howManyInserted == tableOfWords.length)
        {
            System.out.println("Tablica pełna");
            return false;
        }
        long result;
        int i = 0;
        do
        {
            result = (hashValue % tableOfWords.length + ((i*hashValue + 1) % (tableOfWords.length-1))) % tableOfWords.length;//double hash function
            ++i;
        }while(tableOfWords[(int) result]!= null && i <= tableOfWords.length);
        if(tableOfWords[(int) result] == null)
        {
            tableOfWords[(int) result] = word;
            ++howManyInserted;
            return true;
        }
        else {
            return false;
        }

    }
    public boolean insertLinearMethod(long hashValue, String word)
    {
        if(howManyInserted == tableOfWords.length)
        {
            System.out.println("Tablica pełna");
            return false;
        }
        boolean inserted = false;
        long currentValue = hashValue;
        do
        {
            if(tableOfWords[(int) (currentValue % tableOfWords.length)] == null)
            {
                tableOfWords[(int) (currentValue % tableOfWords.length)] = word;
                ++howManyInserted;
                inserted = true;
            }
            else
            {
                currentValue += 1;
            }
        }while(!inserted && currentValue != tableOfWords.length);
        if(inserted) return true;
        else return false;
    }
    public boolean deleteWordFromTableLinear(long hashValue,String word)
    {
        int index = searchInTableLinear(hashValue,word);
        if(index != -1)
        {
            tableOfWords[index] = null;
            --howManyInserted;
            return true;
        }
        else {
            return false;
        }
    }
        public boolean deleteWordFromTableSquared(long hashValue,String word)
    {
        int index = searchInTableSquared(hashValue,word);
        if(index != -1)
        {
            tableOfWords[index] = null;
            --howManyInserted;
            return true;
        }
        else {
            return false;
        }
    }
    public boolean deleteWordFromTableDouble(long hashValue,String word)
    {
        int index = searchInTableDoubled(hashValue,word);
        if(index != -1)
        {
            tableOfWords[index] = null;
            --howManyInserted;
            return true;
        }
        else {
            return false;
        }
    }
    public void writeOut()
    {
        for(int i = 0;i < tableOfWords.length;++i)
        {
            System.out.println(i + ". " + tableOfWords[i]);
        }
    }
    public int searchInTableDoubled(long hashValue, String word)
    {
        if(howManyInserted == 0)
        {
            return -1;
        }
        long result;
        int i = 0;
        do
        {
            result = (hashValue % tableOfWords.length + ((i*hashValue + 1) % (tableOfWords.length-1))) % tableOfWords.length;//double hash function
            ++i;
        }while(!tableOfWords[(int) result].equals(word) && i <= tableOfWords.length);

        if(tableOfWords[(int) result].equals(word)) return (int) result;
        else
        {
            return -1;
        }
    }
        public int searchInTableSquared(long hashValue, String word) {
        if(howManyInserted == 0)
        {
                return -1;
        }

                long i = 0;
                boolean found = false;
                int sum;
                do
                {
                    sum = (int) ((hashValue  + c1*i + c2*i*i) % tableOfWords.length);
//                    if(tableOfWords[sum] == null)
//                    {
//                        return -1;
//                    }
                    if(tableOfWords[sum] != null && tableOfWords[sum].equals(word))
                    {
                        found = true;
                        return sum;
                    }
                    ++i;
                }while(!found && i <= tableOfWords.length);//warunek!!!
             return -1;
    }
    public int searchInTableLinear(long hashValue, String word)
    {
        if(howManyInserted == 0)
        {
            return -1;
        }
        boolean found = false;
        long current = hashValue;
        do
        {
            if(tableOfWords[(int) (current % tableOfWords.length)] != null && tableOfWords[(int) (current % tableOfWords.length)].equals(word))
            {
                found = true;
                return (int) (current % tableOfWords.length);
            }
            else
            {
                current += 1;
            }
        }while(!found && (current % tableOfWords.length) != ((hashValue % tableOfWords.length)-1));
        return -1;
    }

}
