class Foo {
    public int threadNumber;

    public Foo() {
        threadNumber = 1;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        while(threadNumber != 1) wait();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        threadNumber++;
        notifyAll();
    }
    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while(threadNumber != 2) wait();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        threadNumber++;
        notifyAll();
    }
    public synchronized void third(Runnable printThird) throws InterruptedException {
        while(threadNumber != 3) wait();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        threadNumber++;
        notifyAll();
    }
}


 class Test {
    public static void main(String[] args) throws Exception {
        
        Foo foo = new Foo();
       // for (int i = 0; i < 100; i++) {


            Thread t1 = new Thread(() -> call(foo::first, "first, "));
            Thread t2 = new Thread(() -> call(foo::second, "second, "));
            Thread t3 = new Thread(() -> call(foo::third, "third, "));

            // Start threads out of order, with delay between them, giving each thread
            // enough time to complete, if not adequately coded to ensure execution order.

            t2.start();
//        Thread.sleep(500);
            t3.start();
//        Thread.sleep(500);
            t1.start();

            // Wait for threads to complete
//            t2.join();
//            t3.join();
//            t1.join();

       // }

        // At this point, the program output should be "first,second,third."
    }


    interface FooMethod {
        void call(Runnable printFirst) throws InterruptedException;
    }


    private static void call(FooMethod method, String text) {
        try {
            method.call(() -> System.out.print(text));
        } catch (InterruptedException e) {
//            System.out.println(e);
        }
    }
}
