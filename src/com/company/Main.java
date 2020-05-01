package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File filePolishWords = new File("C:\\Users\\Jarosław\\Desktop\\Informatyka\\Złozone algorytmy\\slowa.txt");


        Scanner scanner = null;// = scanner = new Scanner(filePolishWords);
        PrintWriter writer = null;// = writer = new PrintWriter("C:\\Users\\Jarosław\\Desktop\\Informatyka\\Złozone algorytmy\\SlowaLancuchy" + length + "Slotow.txt","UTF-8");
        try
        {
            scanner = new Scanner(filePolishWords);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Zła ścieżka scanner");
        }
        try
        {
            writer = new PrintWriter("C:\\Users\\Jarosław\\Desktop\\Informatyka\\Złozone algorytmy\\SlowaAdrOtwarteSqueredModularnalEPSZ.txt","UTF-8");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Zła ściezka writer");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Zły system kodowania");
        }
        //writer.println("Czas włożenia do struktury średni/10000 el.");
        writer.println("Liczba slotow;Czas wstawienia/1 wyraz;Czas szukania/1 wyraz;Całkowity czas skasowania/1 wyraz; ");
        for(int i = 100;i <= 100000;i*=10)
        {
            Table myTable = new Table(i);


            long startOverAllInput = System.nanoTime();
            //petla wstawiająca
            int counter =0;
            while(scanner.hasNextLine() && counter < i)
            {
                String word = scanner.nextLine();
                myTable.insertSquaredMethod(myTable.modularHashing(word),word);
                //System.out.println("Wstawilem: " + word);
                ++counter;
            }

            long finishOverAllInput = System.nanoTime();



            scanner.close();
            try
            {
                scanner = new Scanner(filePolishWords);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Zła ścieżka scanner");
            }


            //kasuję skaner i tworzę nowy, aby kursor był na początku
            scanner.close();
            try
            {
                scanner = new Scanner(filePolishWords);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Zła ścieżka scanner");
            }
            long startSearch = System.nanoTime();

            //pętla szukająca
            counter = 0;
            while(scanner.hasNextLine() && counter < i)
            {
                String word = scanner.nextLine();
                System.out.println(myTable.searchInTableSquared(myTable.modularHashing(word),word) + "->" + word);


                //System.out.println("Znalazłem: " + word);
//                for(int j = 0;j < 100;++j)
//                {
//                    if(scanner.hasNextLine())
//                    {
//                        scanner.nextLine();
//                    }
//                }
                ++counter;
            }
            long finishSearch = System.nanoTime();

            //kasuję skaner i tworzę nowy, aby kursor był na początku
            scanner.close();
            try
            {
                scanner = new Scanner(filePolishWords);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Zła ścieżka scanner");
            }
            long startDelete = System.nanoTime();

            //pętla usuwająca
            counter = 0;
            while(scanner.hasNextLine() && counter < i)
            {
                String word = scanner.nextLine();
                myTable.deleteWordFromTableSquared(myTable.modularHashing(word),word);
                //System.out.println("Usunieto: " + word);
                ++counter;
            }
            long finishDelete = System.nanoTime();
            writer.println(i + ";" + (finishOverAllInput-startOverAllInput)/i + ";" + (finishSearch - startSearch)/i + ";" + (finishDelete-startDelete)/i + ";");
            //kasuję skaner i tworzę nowy, aby kursor był na początku
            scanner.close();
            try
            {
                scanner = new Scanner(filePolishWords);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Zła ścieżka scanner");
            }
            System.out.println("Druk");
        }
        writer.close();


//        Table myTable = new Table(20);
//        myTable.insertLinearMethod(myTable.modularHashing("dupa"),"dupa");
//        myTable.writeOut();
//        System.out.println("Word \"dupa\" found at position: " + myTable.searchInTableLinear(myTable.modularHashing("dupa"),"dupa"));

        //myChainTable.insert(myChainTable.modularHashing("macrin"),"macrin");
        //myChainTable.writeOut();
        //myChainTable.deleteWordFromTable(myChainTable.modularHashing("marcin"),"marcin");
        //long result = Long.parseLong(myChainTable.bernsteinHashing("mama"));
        //System.out.println(myChainTable.bernsteinHashing("mama"));
        //System.out.println(result);
        //hash(myTable.modularHashing("marcin"));
    }
    public static void hash(int asciiValue)
    {
        int length = 20;
        int result = 0; //= asciiValue % length;
        //System.out.println(result);
        int i = 0;
        while(true)
        {
            result = (asciiValue % length + ((i*asciiValue + 1) % (length-1))) % length;//double hash function
            System.out.println(result);
            ++i;

        }
    }
}
