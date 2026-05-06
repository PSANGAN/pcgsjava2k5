package com.pcgs.core.java.pocs.core;

public interface SimpleWorker {

    void work();

    String performWork();
}

@FunctionalInterface
interface SimpleWorker2 {
    int doubleWork( int x, int y);
}

@FunctionalInterface
interface SimpleWorker3 {
    void doPrint( String str);
}

class WorkerInvoker {

    String varTemp = "WorkerInvoker Variable";
    public static void main(String[] args) {
        System.out.println("WorkerInvoker");

        SimpleWorker anoSimpleWorker = new SimpleWorker() {
            String varTemp = "Anonymous Variable";
            @Override
            public void work() {
                System.out.println("anoSimpleWorker - work ");
            }

            @Override
            public String performWork() {
                return "anoSimpleWorker - performWork";
            }

            public String normalWork() {
                return this.varTemp;
            }
        };

        anoSimpleWorker.work();
        System.out.println(anoSimpleWorker.performWork());

        SimpleWorker2 addWorker = (x, y) -> x + y;
        System.out.println(addWorker.doubleWork(10, 20));

        addWorker = (x, y) -> x * y;
        System.out.println(addWorker.doubleWork(10, 20));

        addWorker = (x, y) -> x - y;
        System.out.println(addWorker.doubleWork(10, 20));

        WorkerInvoker worker = new WorkerInvoker();
        SimpleWorker3 printWorker = (str) -> System.out.println(str);
        printWorker.doPrint(worker.varTemp);


    }
}