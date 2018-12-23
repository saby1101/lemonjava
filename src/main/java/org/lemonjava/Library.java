package org.lemonjava;

import jnr.ffi.Pointer;

import java.io.*;

import static java.io.File.createTempFile;
import static java.lang.System.getProperty;
import static java.lang.Thread.currentThread;
import static java.util.Locale.ENGLISH;
import static java.util.Objects.requireNonNull;
import static jnr.ffi.LibraryLoader.create;
import static jnr.ffi.Runtime.getRuntime;

public class Library {

    static final Lemon LIB;

    static final jnr.ffi.Runtime RUNTIME;

    static {
        final String arch = getProperty("os.arch");
        final boolean arch64 = "x64".equals(arch) || "amd64".equals(arch) || "x86_64".equals(arch);

        final String os = getProperty("os.name");
        final boolean linux = os.toLowerCase(ENGLISH).startsWith("linux");
        final boolean osx = os.startsWith("Mac OS X");
        final boolean windows = os.startsWith("Windows");

        String suffix;
        if (arch64 && linux) {
            suffix = "-linux-amd64.so";
        } else if (arch64 && osx) {
            suffix = "-osx-amd64.dylib";
        } else if (windows) {
            throw new IllegalArgumentException("Windows not supported!");
        } else {
            throw new IllegalStateException("Couldn't find lib for lemonc");
        }

        final String fileName = "liblemonc" + suffix;
        final String libToLoad = extract(fileName);

        LIB = create(Lemon.class).load(libToLoad);
        RUNTIME = getRuntime(LIB);
    }

    private Library() {
    }

    private static String extract(final String name) {
        final File file;
        try {
            file = createTempFile("lemonc-", null);
            file.deleteOnExit();
            final ClassLoader cl = currentThread().getContextClassLoader();
            try (InputStream in = cl.getResourceAsStream("libs/" + name);
                 OutputStream out = new FileOutputStream(file)) {
                requireNonNull(in, "Classpath resource not found");
                int bytes;
                final byte[] buffer = new byte[4096];
                while (-1 != (bytes = in.read(buffer))) {
                    out.write(buffer, 0, bytes);
                }
            }
            return file.getAbsolutePath();
        } catch (final IOException e) {
            throw new LemonException("Failed to extract " + name, e);
        }
    }

    public interface Lemon {
        void deleteObject(Pointer ptr);

        Pointer SmartDigraph_construct();

        void SmartDigraph_destruct(Pointer ptr);

        Pointer SmartDigraph_addNode(Pointer graphPtr);

        Pointer SmartDigraph_addArc(Pointer graphPtr, Pointer node1, Pointer node2);

        Pointer SmartDigraph_NodeMap_LONG_construct(Pointer graphPtr);

        void SmartDigraph_NodeMap_LONG_destruct(Pointer ptr);

        long SmartDigraph_NodeMap_LONG_get(Pointer mapPtr, Pointer nodePtr);

        void SmartDigraph_NodeMap_LONG_set(Pointer mapPtr, Pointer nodePtr, long value);

        Pointer SmartDigraph_ArcMap_LONG_construct(Pointer graphPtr);

        void SmartDigraph_ArcMap_LONG_destruct(Pointer ptr);

        long SmartDigraph_ArcMap_LONG_get(Pointer mapPtr, Pointer nodePtr);

        void SmartDigraph_ArcMap_LONG_set(Pointer mapPtr, Pointer nodePtr, long value);

        Pointer SmartDigraph_ArcMap_DOUBLE_construct(Pointer graphPtr);

        void SmartDigraph_ArcMap_DOUBLE_destruct(Pointer ptr);

        double SmartDigraph_ArcMap_DOUBLE_get(Pointer mapPtr, Pointer nodePtr);

        void SmartDigraph_ArcMap_DOUBLE_set(Pointer mapPtr, Pointer nodePtr, double value);

        Pointer SmartDigraph_NetworkSimplex_LONG_DOUBLE_construct(Pointer graphPtr);

        void SmartDigraph_NetworkSimplex_LONG_DOUBLE_destruct(Pointer ptr);

        void SmartDigraph_NetworkSimplex_LONG_DOUBLE_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_NetworkSimplex_LONG_DOUBLE_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_NetworkSimplex_LONG_DOUBLE_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_NetworkSimplex_LONG_DOUBLE_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int SmartDigraph_NetworkSimplex_LONG_DOUBLE_run(Pointer algoPtr);

        long SmartDigraph_NetworkSimplex_LONG_DOUBLE_flow(Pointer algoPtr, Pointer arcPtr);

        Pointer SmartDigraph_CostScaling_LONG_DOUBLE_construct(Pointer graphPtr);

        void SmartDigraph_CostScaling_LONG_DOUBLE_destruct(Pointer ptr);

        void SmartDigraph_CostScaling_LONG_DOUBLE_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CostScaling_LONG_DOUBLE_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CostScaling_LONG_DOUBLE_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CostScaling_LONG_DOUBLE_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int SmartDigraph_CostScaling_LONG_DOUBLE_run(Pointer algoPtr);

        long SmartDigraph_CostScaling_LONG_DOUBLE_flow(Pointer algoPtr, Pointer arcPtr);

        Pointer SmartDigraph_CapacityScaling_LONG_DOUBLE_construct(Pointer graphPtr);

        void SmartDigraph_CapacityScaling_LONG_DOUBLE_destruct(Pointer ptr);

        void SmartDigraph_CapacityScaling_LONG_DOUBLE_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CapacityScaling_LONG_DOUBLE_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CapacityScaling_LONG_DOUBLE_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void SmartDigraph_CapacityScaling_LONG_DOUBLE_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int SmartDigraph_CapacityScaling_LONG_DOUBLE_run(Pointer algoPtr);

        long SmartDigraph_CapacityScaling_LONG_DOUBLE_flow(Pointer algoPtr, Pointer arcPtr);

        Pointer ListDigraph_construct();

        void ListDigraph_destruct(Pointer ptr);

        Pointer ListDigraph_addNode(Pointer graphPtr);

        Pointer ListDigraph_addArc(Pointer graphPtr, Pointer node1, Pointer node2);

        Pointer ListDigraph_NodeMap_LONG_construct(Pointer graphPtr);

        void ListDigraph_NodeMap_LONG_destruct(Pointer ptr);

        long ListDigraph_NodeMap_LONG_get(Pointer mapPtr, Pointer nodePtr);

        void ListDigraph_NodeMap_LONG_set(Pointer mapPtr, Pointer nodePtr, long value);

        Pointer ListDigraph_ArcMap_LONG_construct(Pointer graphPtr);

        void ListDigraph_ArcMap_LONG_destruct(Pointer ptr);

        long ListDigraph_ArcMap_LONG_get(Pointer mapPtr, Pointer nodePtr);

        void ListDigraph_ArcMap_LONG_set(Pointer mapPtr, Pointer nodePtr, long value);

        Pointer ListDigraph_ArcMap_DOUBLE_construct(Pointer graphPtr);

        void ListDigraph_ArcMap_DOUBLE_destruct(Pointer ptr);

        double ListDigraph_ArcMap_DOUBLE_get(Pointer mapPtr, Pointer nodePtr);

        void ListDigraph_ArcMap_DOUBLE_set(Pointer mapPtr, Pointer nodePtr, double value);

        Pointer ListDigraph_NetworkSimplex_LONG_DOUBLE_construct(Pointer graphPtr);

        void ListDigraph_NetworkSimplex_LONG_DOUBLE_destruct(Pointer ptr);

        void ListDigraph_NetworkSimplex_LONG_DOUBLE_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void ListDigraph_NetworkSimplex_LONG_DOUBLE_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void ListDigraph_NetworkSimplex_LONG_DOUBLE_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void ListDigraph_NetworkSimplex_LONG_DOUBLE_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int ListDigraph_NetworkSimplex_LONG_DOUBLE_run(Pointer algoPtr);

        long ListDigraph_NetworkSimplex_LONG_DOUBLE_flow(Pointer algoPtr, Pointer arcPtr);

        Pointer PV_construct();

        void PV_destruct(Pointer ptr);

        void PV_push_back(Pointer ptr, int first, int second);

        Pointer SG_construct();

        void SG_destruct(Pointer ptr);

        void SG_build(Pointer graphPtr, int nodeCount, Pointer arcsPtr);

        Pointer SG_NodeMap_LONG_construct(Pointer graphPtr);

        void SG_NodeMap_LONG_destruct(Pointer ptr);

        long SG_NodeMap_LONG_get(Pointer mapPtr, int nodeIdx);

        void SG_NodeMap_LONG_set(Pointer mapPtr, int nodeIdx, long value);

        Pointer SG_ArcMap_LONG_construct(Pointer graphPtr);

        void SG_ArcMap_LONG_destruct(Pointer ptr);

        long SG_ArcMap_LONG_get(Pointer mapPtr, int arcIdx);

        void SG_ArcMap_LONG_set(Pointer mapPtr, int arcIdx, long value);

        Pointer SG_ArcMap_DOUBLE_construct(Pointer graphPtr);

        void SG_ArcMap_DOUBLE_destruct(Pointer ptr);

        double SG_ArcMap_DOUBLE_get(Pointer mapPtr, int arcIdx);

        void SG_ArcMap_DOUBLE_set(Pointer mapPtr, int arcIdx, double value);

        Pointer SG_CostScaling_LONG_DOUBLE_construct(Pointer graphPtr);

        void SG_CostScaling_LONG_DOUBLE_destruct(Pointer ptr);

        void SG_CostScaling_LONG_DOUBLE_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_DOUBLE_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_DOUBLE_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_DOUBLE_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int SG_CostScaling_LONG_DOUBLE_run(Pointer algoPtr);

        long SG_CostScaling_LONG_DOUBLE_flow(Pointer algoPtr, int arcIdx);

        Pointer SG_CostScaling_LONG_LONG_construct(Pointer graphPtr);

        void SG_CostScaling_LONG_LONG_destruct(Pointer ptr);

        void SG_CostScaling_LONG_LONG_setCostMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_LONG_setLowerMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_LONG_setUpperMap(Pointer algoPtr, Pointer mapPtr);

        void SG_CostScaling_LONG_LONG_setSupplyMap(Pointer algoPtr, Pointer mapPtr);

        int SG_CostScaling_LONG_LONG_run(Pointer algoPtr);

        long SG_CostScaling_LONG_LONG_flow(Pointer algoPtr, int arcIdx);
    }
}
