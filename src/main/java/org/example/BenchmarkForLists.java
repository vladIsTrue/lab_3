/**
 * This class serves as a benchmark for measuring the performance of basic operations on ArrayList and LinkedList.
 * It uses the Java Microbenchmarking Harness (JMH) library for conducting the measurements.
 *
 * The operations benchmarked in this class include inserting an element into a list, retrieving an element from a list,
 * and deleting an element from a list. To accurately measure the remove operation, local copies of the lists are created
 * before performing the removal operation to avoid side effects on the original lists.
 *
 * This benchmark performs measurements in "SingleShotTime" mode and measures the execution time of each operation in milliseconds.
 *
 * To run the benchmark, use the `main` method, which creates benchmark options and runs it with a single fork.
 *
 * Please note that this code includes a license notice and distribution and usage requirements.
 */

/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(batchSize = 100000, iterations = 100)
@Warmup(batchSize = 100000, iterations = 100)
@State(Scope.Thread)

public class BenchmarkForLists {
    private ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    private LinkedList<Integer> linkedList= new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    private int getValue = 5;
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkForLists.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public boolean addArrayList() {
        return arrayList.add(getValue);
    }

    @Benchmark
    public boolean addLinkedList() {
        return linkedList.add(getValue);
    }

    @Benchmark
    public Integer getArrayList() {
        return arrayList.get(getValue);
    }

    @Benchmark
    public Integer getLinkedList() {
        return linkedList.get(getValue);
    }

    @Benchmark
    public Integer deleteArrayList() {
        ArrayList<Integer> localList = new ArrayList<>(arrayList);
        return localList.remove(0); }

    @Benchmark
    public Integer deleteLinkedList() {
        LinkedList<Integer> localList = new LinkedList<>(linkedList);
        return localList.remove(0);
    }

}
