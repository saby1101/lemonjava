package org.lemonjava;

import jnr.ffi.Pointer;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractNativeObject implements AutoCloseable {
    protected Pointer ptr;

    private AtomicBoolean closeCalled = new AtomicBoolean(false);

    @Override
    public void close() {
        if (closeCalled.compareAndSet(false, true)) {
            delete();
        }
    }

    protected abstract void delete();

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
