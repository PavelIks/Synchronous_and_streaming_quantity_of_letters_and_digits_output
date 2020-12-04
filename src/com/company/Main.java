package com.company;
class CommonResource
{
    String str = "1a2b3c4d5f";
    int Letter = 0;
    int Digit = 0;

    synchronized void inc1()
    {
        for(char a : str.toCharArray())
        {
            if(Character.isLetter(a) == true)
            {
                Letter++;
            }
        }
        System.out.printf("Букв:" + "\t" + Letter + "\n" );
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    synchronized void inc2()
    {
        for(char a : str.toCharArray())
        {
            if(Character.isDigit(a) == true)
            {
                Digit++;
            }
        }
        System.out.printf("Цифр:" + "\t" + Digit + "\n" );
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

class CountThread implements Runnable
{
    CommonResource resource;
    CountThread(CommonResource com)
    {
        resource = com;
    }
    @Override
    public void run()
    {
        synchronized (resource)
        {
            resource.inc1();
            resource.inc2();
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        CommonResource resource = new CommonResource();
        Thread thread = new Thread(new CountThread(resource));
        thread.start();
    }
}