/*-----------------------------------------------------------------
This program demonstrates various thread management techniques in Java, highlighting their effects on thread execution and lifecycle.

1. Thread Creation and Execution:
	1. Creating threads using the Thread class and extending the run() method.
	2. Starting threads with start().

2. Thread Scheduling and Yielding:

	1. The non-deterministic nature of thread scheduling by the operating system.
	2. Using yield() to suggest a voluntary context switch, allowing other threads to 	run.

3. Thread Termination and Dormancy:
	1. Forcibly stopping a thread with stop() (generally discouraged due to potential 	issues).
	2. Pausing a thread's execution with sleep(), letting other threads proceed.
-----------------------------------------------------------------*/
class A extends Thread{
public void run(){
for (int i=1; i<=5; i++){
if (i==1) yield ();
System.out.println ("\tFrom Thread A : i=" +i);
}
System.out.println ("Exit from A");
}}
class B extends Thread{
public void run (){
for (int j=1; j<=5; j++){
System.out.println ("\tFrom Thread B : j="+j);
if (j==3) stop ();
}
System.out.println ("Exit from C");
}  }
class C extends Thread{
public void run(){
for (int k=1; k<=5; k++){
System.out.println ("\tFrom Thread C:  k="+k);
if (k==1)
try{
sleep (1000);
}
catch (Exception e)
{  }  }
System.out.println ("Exit from C");
}  }
class lifecycle{
public static void main (String args[]){
A  threadA= new A();
B  threadB=new B ();
C  threadC= new C ();
System.out.println ("Start thread A");
threadA.start();
System.out.println ("Start thread B");
threadB.start();
System.out.println ("Start thread C");
threadC.start();
System.out.println ("End of main thread");
}  
}
