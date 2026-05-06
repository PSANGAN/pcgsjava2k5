package com.pcgs.core.java.pocs.interfaces;

public class InterfaceClient implements iMaker {
    public static void main(String[] args) {
        iWork refreWork = new WorkImplementaor();
        iSuper referSuper = new WorkImplementaor();
        refreWork.doWork();
        refreWork.doSomething();
        referSuper.doSuper();
        referSuper.doSomething();

        iMaker.make();
        iMaker.main(null);

        // InterfaceClient.make();// ERROR

    }
}

class WorkImplementaor implements iWork, iSuper, iMaker {
    @Override
    public void doWork() {
        System.out.println("Doing work");
    }

    @Override
    public void doSuper() {
        System.out.println("Doing super");
    }

    @Override
    public void doSomething() {
       iWork.super.doSomething();

        iMaker.make();
        iMaker.main(null);
    }
}