/*------------------------------------------------------------------
Program for deadlock:

Classes:
1. A:
	1. Has two synchronized methods: foo(B b) and last().
	2. foo(B b) tries to call b.last() after a delay, potentially causing a deadlock.
2. B:
	1. Has two synchronized methods: bar(A a) and last().
	2.bar(A a) tries to call a.last() after a delay, also potentially causing a 	deadlock.
3. Deadlock:
	1. Creates objects of A and B.
	2. Starts a new thread and calls a.foo(b) in the main thread.
	3. The new thread calls b.bar(a).
	4. This setup creates a circular dependency for locks, leading to deadlock.
------------------------------------------------------------------*/

class A {
   synchronized void foo(B b) {
      String name = Thread.currentThread().getName();
      System.out.println(name + " entered A.foo"); 
      try {
         Thread.sleep(1000);
      } catch(Exception e) {
         System.out.println("A Interrupted");
      }
      System.out.println(name + " trying to call B.last()");
      b.last();
   }
   synchronized void last() {
      System.out.println("Inside A.last");
   }} class B {
   synchronized void bar(A a) {
      String name = Thread.currentThread().getName();
      System.out.println(name + " entered B.bar");
      try {
         Thread.sleep(1000); }
 catch(Exception e) {
System.out.println("B Interrupted");
      } System.out.println(name + " trying to callA.last()");
      a.last();
   }
synchronized void last() {
      System.out.println("Inside A.last");
   }}
class Deadlock implements Runnable {
   A a = new A();
   B b = new B();
   Deadlock() {
   Thread.currentThread().setName("MainThread");
      Thread t = new Thread(this, "RacingThread");
      t.start();
      a.foo(b); // get lock on a in this thread.
      System.out.println("Back in main thread");}
   public void run() {
      b.bar(a); // get lock on b in other thread.
      System.out.println("Back in other thread");}
   public static void main(String args[]) {
      new Deadlock();
   }
} 
