/*--------------------------------
**The program you provided demonstrates synchronization in Java using multiple threads**

----------------------------------*/

/*--------------------------------
Name: Callme Class
1. This class defines a method called call(String msg).
2. call(String msg) Method:
	1.Prints a bracket "[" followed by the provided message msg.
	2.Uses Thread.sleep(1000) to pause the thread execution for 1 second (1000 	milliseconds). This simulates some processing within the call.
	3.Includes a try-catch block:
		1. The try block attempts to execute the Thread.sleep method.
		2. The catch block handles any InterruptedException that might occur while 		sleeping. In this case, it simply prints "Interrupted".
	4. Prints a closing bracket "]" after the sleep.

----------------------------------*/
class Callme {
   void call(String msg) {
      System.out.print("[" + msg);
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         System.out.println("Interrupted");  }
      System.out.println("]");
   }  }

// File Name : Caller.java

/*--------------------------------
Name: Caller Class
1. This class implements the Runnable interface, which is required for creating threads.
2. Member Variables:
	1.msg: A String variable to store the message to be passed to the call method.
	2.target: A reference to a Callme object (the target for calling the call method).
	3.t: A Thread object that will run the run method of this Caller object.
3. Constructor:
	1.Takes two arguments: targ (a Callme object) and s (a String message).
	2. Assigns the provided targ to the target variable.
	3. Assigns the provided s to the msg variable.
	4. Creates a new thread using the this object (which implements Runnable) and 			assigns it to the t variable.
	5. Starts the newly created thread using the t.start() method. This essentially 	tells the thread to begin execution of the run method whenever the scheduler allows.
4. run Method:
	1. This method implements the logic that the thread will execute.
	2. The provided code has a commented-out synchronized block (//synchronized(target) 	{ ... }). This block would synchronize access to the call method of the target 	object. We'll discuss this further.
	3. Calls the target.call(msg) method. This essentially calls the call method on the 	Callme object (target) and passes the stored message (msg).
----------------------------------*/
class Caller implements Runnable {
   String msg;  Callme target;  Thread t;
   public Caller(Callme targ, String s) {
      target = targ;
      msg = s;
      t = new Thread(this);
      t.start();  }
    // synchronize calls to call()
   public void run() {
//synchronized(target) { // synchronized block
         target.call(msg);
     // } 
}  }
// File Name : Synch.java

/*--------------------------------
Name: Synch.java:
1. main Method: 
	This is the program's entry point.
2.Creating Callme Object:
	Creates a new Callme object and assigns it to the target variable.
3. Creating Caller Objects:
	1.Creates three Caller objects: ob1, ob2, and ob3.
	2.Each Caller object is constructed with the target object (the same one) and a 	different message string.
	3.By starting the threads (t.start()) in the Caller constructor, this essentially 	creates three threads that will call the call method on the same Callme object with 	different messages.
4. Waiting for Threads:
	1.Uses a try-catch block to handle potential InterruptedException.
	2.Inside the try block, it calls the join method on each thread object (ob1.t.join	(), ob2.t.join(), and ob3.t.join()).
	3.The join method essentially waits for the specific thread to finish executing 	before continuing to the next line of code. This ensures the program waits for all 	three threads to complete their calls before exiting.
----------------------------------*/
class sync {
   public static void main(String args[]) {
      Callme target = new Callme();
      Caller ob1 = new Caller(target, "Hello");
      Caller ob2 = new Caller(target, "Synchronized");
      Caller ob3 = new Caller(target, "World");
   
      // wait for threads to end
      try {
         ob1.t.join();
         ob2.t.join();
         ob3.t.join();
      } catch(InterruptedException e) {
         System.out.println("Interrupted");
      } 
   }   
}
