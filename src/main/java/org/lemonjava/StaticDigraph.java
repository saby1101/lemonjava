package org.lemonjava;

public class StaticDigraph extends AbstractNativeObject {

    public StaticDigraph() {
        ptr = Library.LIB.SG_construct();
    }

    @Override
    protected void delete() {
        Library.LIB.SG_destruct(ptr);
    }

    public void build(int nodeCount, VectorOfPairs arcs) {
        Library.LIB.SG_build(ptr, nodeCount, arcs.ptr);
    }

    public NodeMap createNodeMap() {
        return new NodeMap();
    }

    public ArcMapLong createArcMapLong() {
        return new ArcMapLong();
    }

    public ArcMapDouble createArcMapDouble() {
        return new ArcMapDouble();
    }

    public CostScalingDouble costScalingDouble() {
        return new CostScalingDouble();
    }

    public CostScalingLong costScalingLong() {
        return new CostScalingLong();
    }

    private MinCostFlowResultType getMinCostFlowResultType(int r) {
        switch (r) {
            case 0:
                return MinCostFlowResultType.INFEASIBLE;
            case 1:
                return MinCostFlowResultType.OPTIMAL;
            case 2:
                return MinCostFlowResultType.UNBOUNDED;
        }
        throw new LemonException("Unknown result type!");
    }

    public interface MinCostFlowAlgorithmDouble extends AutoCloseable {
        void setSupplyMap(NodeMap map);

        void setCostMap(ArcMapDouble map);

        void setLowerMap(ArcMapLong map);

        void setUpperMap(ArcMapLong map);

        MinCostFlowResultType run();

        long flow(int arcIdx);
    }

    public interface MinCostFlowAlgorithmLong extends AutoCloseable {
        void setSupplyMap(NodeMap map);

        void setCostMap(ArcMapLong map);

        void setLowerMap(ArcMapLong map);

        void setUpperMap(ArcMapLong map);

        MinCostFlowResultType run();

        long flow(int arcIdx);
    }

    public class NodeMap extends AbstractNativeObject {

        private NodeMap() {
            ptr = Library.LIB.SG_NodeMap_LONG_construct(StaticDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SG_NodeMap_LONG_destruct(ptr);
        }

        public long get(int nodeIdx) {
            return Library.LIB.SG_NodeMap_LONG_get(ptr, nodeIdx);
        }

        public void set(int nodeIdx, long value) {
            Library.LIB.SG_NodeMap_LONG_set(ptr, nodeIdx, value);
        }
    }

    public class ArcMapLong extends AbstractNativeObject {

        private ArcMapLong() {
            ptr = Library.LIB.SG_ArcMap_LONG_construct(StaticDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SG_ArcMap_LONG_destruct(ptr);
        }

        public long get(int arcIdx) {
            return Library.LIB.SG_ArcMap_LONG_get(ptr, arcIdx);
        }

        public void set(int arcIdx, long value) {
            Library.LIB.SG_NodeMap_LONG_set(ptr, arcIdx, value);
        }
    }

    public class ArcMapDouble extends AbstractNativeObject {

        private ArcMapDouble() {
            ptr = Library.LIB.SG_ArcMap_DOUBLE_construct(StaticDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SG_ArcMap_DOUBLE_destruct(ptr);
        }

        public double get(int arcIdx) {
            return Library.LIB.SG_ArcMap_DOUBLE_get(ptr, arcIdx);
        }

        public void set(int arcIdx, double value) {
            Library.LIB.SG_ArcMap_DOUBLE_set(ptr, arcIdx, value);
        }
    }

    public class CostScalingDouble extends AbstractNativeObject implements MinCostFlowAlgorithmDouble {

        private CostScalingDouble() {
            ptr = Library.LIB.SG_CostScaling_LONG_DOUBLE_construct(StaticDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SG_CostScaling_LONG_DOUBLE_destruct(ptr);
        }

        @Override
        public void setSupplyMap(NodeMap map) {
            Library.LIB.SG_CostScaling_LONG_DOUBLE_setSupplyMap(ptr, map.ptr);
        }

        @Override
        public void setCostMap(ArcMapDouble map) {
            Library.LIB.SG_CostScaling_LONG_DOUBLE_setCostMap(ptr, map.ptr);
        }

        @Override
        public void setLowerMap(ArcMapLong map) {
            Library.LIB.SG_CostScaling_LONG_DOUBLE_setLowerMap(ptr, map.ptr);
        }

        @Override
        public void setUpperMap(ArcMapLong map) {
            Library.LIB.SG_CostScaling_LONG_DOUBLE_setUpperMap(ptr, map.ptr);
        }

        @Override
        public MinCostFlowResultType run() {
            int r = Library.LIB.SG_CostScaling_LONG_DOUBLE_run(ptr);
            return getMinCostFlowResultType(r);
        }

        @Override
        public long flow(int arcIdx) {
            return Library.LIB.SG_CostScaling_LONG_DOUBLE_flow(ptr, arcIdx);
        }
    }

    public class CostScalingLong extends AbstractNativeObject implements MinCostFlowAlgorithmLong {

        private CostScalingLong() {
            ptr = Library.LIB.SG_CostScaling_LONG_LONG_construct(StaticDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SG_CostScaling_LONG_LONG_destruct(ptr);
        }

        @Override
        public void setSupplyMap(NodeMap map) {
            Library.LIB.SG_CostScaling_LONG_LONG_setSupplyMap(ptr, map.ptr);
        }

        @Override
        public void setCostMap(ArcMapLong map) {
            Library.LIB.SG_CostScaling_LONG_LONG_setCostMap(ptr, map.ptr);
        }

        @Override
        public void setLowerMap(ArcMapLong map) {
            Library.LIB.SG_CostScaling_LONG_LONG_setLowerMap(ptr, map.ptr);
        }

        @Override
        public void setUpperMap(ArcMapLong map) {
            Library.LIB.SG_CostScaling_LONG_LONG_setUpperMap(ptr, map.ptr);
        }

        @Override
        public MinCostFlowResultType run() {
            int r = Library.LIB.SG_CostScaling_LONG_LONG_run(ptr);
            return getMinCostFlowResultType(r);
        }

        @Override
        public long flow(int arcIdx) {
            return Library.LIB.SG_CostScaling_LONG_LONG_flow(ptr, arcIdx);
        }
    }
}
