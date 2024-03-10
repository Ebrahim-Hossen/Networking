/*-------------------------------------------------------------------
Purpose:

1. This program demonstrates the concept of thread creation and multithreading in Java.
2. It shows how multiple threads can run concurrently, even with shared resources like the console.

Key Concepts:

Thread Creation:

	1. A separate thread of execution is created using a Runnable object and a Thread 	object.
	2. The Runnable interface defines the task to be executed by the thread (run() 	method).
Thread Execution:

	1. The start() method initiates a thread's execution.
	2. Threads run independently, potentially interleaving their output.

Thread Coordination:

	1. Thread.sleep() pauses a thread's execution temporarily, allowing other threads to 	run.
-------------------------------------------------------------------*/

class NewThread implements Runnable{
Thread t;

NewThread() {
t = new Thread (this,"Demo Thread");
System.out.println ("Child thread:" +t);
t.start () ;//start the thread 
}

public void run(){
try{
for (int i=5; i>0; i--){
System.out.println("child thread:"+i);
Thread.sleep(500);
}
}catch (InterruptedException e){
System.out.println("child interrupted.");
}
System.out.println("exiting child thread.");
}
}
class wc{
public static void main (String args[]){
new NewThread(); //creatr a new thread
try{
for(int i=5; i>0; i--){
System.out.println("main thread:"+i);
Thread.sleep(1000);
}
}catch (InterruptedException e){
System.out.println ("main thread interrupted.");
}
System.out.println("main thread exiting.");
}
}
