package ru.javawebinar.topjava;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StopWatchRule extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(StopWatchRule.class);
    private static final List<String> resultTestTime = new ArrayList<>();

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        log.info(String.format("Test %s %s, spent %d ms",
                testName, status, TimeUnit.NANOSECONDS.toMillis(nanos)));
        if (status.equals("finished")) {
            resultTestTime.add(String.format("Test %s - %s, spent %d ms",
                    testName, status, TimeUnit.NANOSECONDS.toMillis(nanos)));
        }
    }

    public static void getAllResultList() {
        resultTestTime.forEach(log::info);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}
