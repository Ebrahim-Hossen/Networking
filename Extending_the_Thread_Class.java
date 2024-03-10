/*---------------------------------------------------------------------
This program demonstrates the concept of multithreading in Java. Here's a breakdown of its purpose and functionality:

Purpose:

1. This program demonstrates how to create and run multiple threads in Java.
2. It shows how each thread can execute its code independently (mostly).
3. The uncoordinated printing of messages from different threads highlights the non- deterministic nature of thread execution without synchronization.

Classes:

1. A, B, and C: These classes extend the Thread class, making them capable of running as independent threads. Each class has a run() method that defines the code to be executed by the thread.

2. wc: This class contains the main() method, which is the program's entry point.
----------------------------------------------------------------------*/
class A extends Thread
{
public void run()
{
for(int i=1; i<=5; i++)
{
System.out.println ("\t from yhread A: i="+i);
}
System.out.println("exit from A");
}
}

class B extends Thread
{
public void run()
{
for(int j=1; j<=5; j++)
{
System.out.println ("\t from yhread B: j="+j);
}
System.out.println("exit from B");
}
}
class C extends Thread
{
public void run()
{
for(int k=1; k<=5; k++)
{
System.out.println ("\t from yhread C:ki="+k);
}
System.out.println("exit from C");
}
}

class wc
{
public static void main(String args[])
{
new A ().start();
new B ().start();
new C ().start();
}
}
