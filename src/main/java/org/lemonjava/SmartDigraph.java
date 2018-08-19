package org.lemonjava;

public class SmartDigraph extends AbstractNativeObject {

    public SmartDigraph() {
        ptr = Library.LIB.SmartDigraph_construct();
    }

    @Override
    protected void delete() {
        Library.LIB.SmartDigraph_destruct(ptr);
    }

    public Node addNode() {
        return new Node();
    }

    public Arc addArc(Node node1, Node node2) {
        return new Arc(node1, node2);
    }

    public NodeMap createNodeMap() {
        return new NodeMap();
    }

    public ArcMap createArcMap() {
        return new ArcMap();
    }

    public NetworkSimplex networkSimplex() {
        return new NetworkSimplex();
    }

    public CostScaling costScaling() {
        return new CostScaling();
    }

    public CapacityScaling capacityScaling() {
        return new CapacityScaling();
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

    public interface MinCostFlowAlgorithm extends AutoCloseable {
        void setSupplyMap(NodeMap map);

        void setCostMap(ArcMap map);

        void setLowerMap(ArcMap map);

        void setUpperMap(ArcMap map);

        MinCostFlowResultType run();

        long flow(Arc arc);
    }

    public class Node extends AbstractNativeObject {

        private Node() {
            ptr = Library.LIB.SmartDigraph_addNode(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.deleteObject(ptr);
        }
    }

    public class Arc extends AbstractNativeObject {

        private Arc(Node node1, Node node2) {
            ptr = Library.LIB.SmartDigraph_addArc(SmartDigraph.this.ptr, node1.ptr, node2.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.deleteObject(ptr);
        }
    }

    public class NodeMap extends AbstractNativeObject {

        private NodeMap() {
            ptr = Library.LIB.SmartDigraph_NodeMap_LONG_construct(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SmartDigraph_NodeMap_LONG_destruct(ptr);
        }

        public long get(Node node) {
            return Library.LIB.SmartDigraph_NodeMap_LONG_get(ptr, node.ptr);
        }

        public void set(Node node, long value) {
            Library.LIB.SmartDigraph_NodeMap_LONG_set(ptr, node.ptr, value);
        }
    }

    public class ArcMap extends AbstractNativeObject {

        private ArcMap() {
            ptr = Library.LIB.SmartDigraph_ArcMap_LONG_construct(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SmartDigraph_ArcMap_LONG_destruct(ptr);
        }

        public long get(Arc arc) {
            return Library.LIB.SmartDigraph_ArcMap_LONG_get(ptr, arc.ptr);
        }

        public void set(Arc arc, long value) {
            Library.LIB.SmartDigraph_ArcMap_LONG_set(ptr, arc.ptr, value);
        }
    }

    public class NetworkSimplex extends AbstractNativeObject implements MinCostFlowAlgorithm {

        private NetworkSimplex() {
            ptr = Library.LIB.SmartDigraph_NetworkSimplex_LONG_construct(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SmartDigraph_NetworkSimplex_LONG_destruct(ptr);
        }

        @Override
        public void setSupplyMap(NodeMap map) {
            Library.LIB.SmartDigraph_NetworkSimplex_LONG_setSupplyMap(ptr, map.ptr);
        }

        @Override
        public void setCostMap(ArcMap map) {
            Library.LIB.SmartDigraph_NetworkSimplex_LONG_setCostMap(ptr, map.ptr);
        }

        @Override
        public void setLowerMap(ArcMap map) {
            Library.LIB.SmartDigraph_NetworkSimplex_LONG_setLowerMap(ptr, map.ptr);
        }

        @Override
        public void setUpperMap(ArcMap map) {
            Library.LIB.SmartDigraph_NetworkSimplex_LONG_setUpperMap(ptr, map.ptr);
        }

        @Override
        public MinCostFlowResultType run() {
            int r = Library.LIB.SmartDigraph_NetworkSimplex_LONG_run(ptr);
            return getMinCostFlowResultType(r);
        }

        @Override
        public long flow(Arc arc) {
            return Library.LIB.SmartDigraph_NetworkSimplex_LONG_flow(ptr, arc.ptr);
        }
    }

    public class CostScaling extends AbstractNativeObject implements MinCostFlowAlgorithm {

        private CostScaling() {
            ptr = Library.LIB.SmartDigraph_CostScaling_LONG_construct(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SmartDigraph_CostScaling_LONG_destruct(ptr);
        }

        @Override
        public void setSupplyMap(NodeMap map) {
            Library.LIB.SmartDigraph_CostScaling_LONG_setSupplyMap(ptr, map.ptr);
        }

        @Override
        public void setCostMap(ArcMap map) {
            Library.LIB.SmartDigraph_CostScaling_LONG_setCostMap(ptr, map.ptr);
        }

        @Override
        public void setLowerMap(ArcMap map) {
            Library.LIB.SmartDigraph_CostScaling_LONG_setLowerMap(ptr, map.ptr);
        }

        @Override
        public void setUpperMap(ArcMap map) {
            Library.LIB.SmartDigraph_CostScaling_LONG_setUpperMap(ptr, map.ptr);
        }

        @Override
        public MinCostFlowResultType run() {
            int r = Library.LIB.SmartDigraph_CostScaling_LONG_run(ptr);
            return getMinCostFlowResultType(r);
        }

        @Override
        public long flow(Arc arc) {
            return Library.LIB.SmartDigraph_CostScaling_LONG_flow(ptr, arc.ptr);
        }
    }

    public class CapacityScaling extends AbstractNativeObject implements MinCostFlowAlgorithm {

        private CapacityScaling() {
            ptr = Library.LIB.SmartDigraph_CapacityScaling_LONG_construct(SmartDigraph.this.ptr);
        }

        @Override
        protected void delete() {
            Library.LIB.SmartDigraph_CapacityScaling_LONG_destruct(ptr);
        }

        @Override
        public void setSupplyMap(NodeMap map) {
            Library.LIB.SmartDigraph_CapacityScaling_LONG_setSupplyMap(ptr, map.ptr);
        }

        @Override
        public void setCostMap(ArcMap map) {
            Library.LIB.SmartDigraph_CapacityScaling_LONG_setCostMap(ptr, map.ptr);
        }

        @Override
        public void setLowerMap(ArcMap map) {
            Library.LIB.SmartDigraph_CapacityScaling_LONG_setLowerMap(ptr, map.ptr);
        }

        @Override
        public void setUpperMap(ArcMap map) {
            Library.LIB.SmartDigraph_CapacityScaling_LONG_setUpperMap(ptr, map.ptr);
        }

        @Override
        public MinCostFlowResultType run() {
            int r = Library.LIB.SmartDigraph_CapacityScaling_LONG_run(ptr);
            return getMinCostFlowResultType(r);
        }

        @Override
        public long flow(Arc arc) {
            return Library.LIB.SmartDigraph_CapacityScaling_LONG_flow(ptr, arc.ptr);
        }
    }
}
