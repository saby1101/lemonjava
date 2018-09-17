package org.lemonjava;

import org.junit.Assert;
import org.junit.Test;

public class StaticDigraphTest {
    private static final double DELTA = 1e-6;

    @Test
    public void costScaling() {
        StaticDigraph graph = new StaticDigraph();

        VectorOfPairs arcs = new VectorOfPairs();
        arcs.add(0, 1);
        arcs.add(0, 2);
        arcs.add(0, 3);
        arcs.add(1, 2);
        graph.build(4, arcs);
        arcs.close();

        StaticDigraph.NodeMap supplyMap = graph.createNodeMap();
        supplyMap.set(0, 3);
        supplyMap.set(1, 2);
        supplyMap.set(2, -3);
        supplyMap.set(3, -2);
        Assert.assertEquals(3, supplyMap.get(0));
        Assert.assertEquals(2, supplyMap.get(1));
        Assert.assertEquals(-3, supplyMap.get(2));
        Assert.assertEquals(-2, supplyMap.get(3));

        StaticDigraph.ArcMapDouble costMap = graph.createArcMapDouble();
        costMap.set(0, 10.5);
        costMap.set(1, 20.1);
        costMap.set(2, 100.0);
        costMap.set(3, 20.0);
        Assert.assertEquals(10.5, costMap.get(0), DELTA);
        Assert.assertEquals(20.1, costMap.get(1), DELTA);
        Assert.assertEquals(100.0, costMap.get(2), DELTA);
        Assert.assertEquals(20.0, costMap.get(3), DELTA);

        StaticDigraph.ArcMapLong upperMap = graph.createArcMapLong();
        upperMap.set(0, 1);
        upperMap.set(1, 0);
        upperMap.set(2, 2);
        upperMap.set(3, 3);
        Assert.assertEquals(1, upperMap.get(0));
        Assert.assertEquals(0, upperMap.get(1));
        Assert.assertEquals(2, upperMap.get(2));
        Assert.assertEquals(3, upperMap.get(3));

        StaticDigraph.CostScaling algo = graph.costScaling();
        algo.setCostMap(costMap);
        algo.setUpperMap(upperMap);
        algo.setSupplyMap(supplyMap);

        MinCostFlowResultType result = algo.run();

        Assert.assertEquals(MinCostFlowResultType.OPTIMAL, result);

        long flow1 = algo.flow(0);
        long flow2 = algo.flow(1);
        long flow3 = algo.flow(2);
        long flow4 = algo.flow(3);

        Assert.assertEquals(1, flow1);
        Assert.assertEquals(0, flow2);
        Assert.assertEquals(2, flow3);
        Assert.assertEquals(3, flow4);

        algo.close();
        graph.close();
    }
}
