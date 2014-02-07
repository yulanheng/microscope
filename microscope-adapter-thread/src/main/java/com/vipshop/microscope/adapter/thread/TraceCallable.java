package com.vipshop.microscope.adapter.thread;

import java.util.concurrent.Callable;

import com.vipshop.microscope.trace.Trace;
import com.vipshop.microscope.trace.Tracer;

public class TraceCallable<V> implements Callable<V> {

    private final Callable<V> impl;
    private final Trace parent;

    public TraceCallable(Callable<V> impl) {
        this.parent = Tracer.getContext();
        this.impl = impl;
    }

    public TraceCallable(Trace parent, Callable<V> impl) {
    	this.parent = parent;
        this.impl = impl;
    }

    @Override
    public V call() throws Exception {
        if (parent != null) {
            Tracer.setContext(parent);
        }
        return impl.call();
    }

    public Callable<V> getImpl() {
        return impl;
    }
}
