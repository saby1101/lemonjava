package org.lemonjava;

public class VectorOfPairs extends AbstractNativeObject {

    public VectorOfPairs() {
        ptr = Library.LIB.PV_construct();
    }

    public void add(int first, int second) {
        Library.LIB.PV_push_back(ptr, first, second);
    }

    @Override
    protected void delete() {
        Library.LIB.PV_destruct(ptr);
    }
}
