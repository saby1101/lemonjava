package org.lemonjava;

import org.junit.Assert;
import org.junit.Test;

public class NetworkSimplexTest {

    @Test
    public void smartDigraph() {
        SmartDigraph graph = new SmartDigraph();

        SmartDigraph.Node node1 = graph.addNode();
        SmartDigraph.Node node2 = graph.addNode();
        SmartDigraph.Node node3 = graph.addNode();

        SmartDigraph.Arc arc1 = graph.addArc(node1, node2);
        SmartDigraph.Arc arc2 = graph.addArc(node2, node3);

        SmartDigraph.NodeMap supplyMap = graph.createNodeMap();
        supplyMap.set(node1, 1);
        supplyMap.set(node2, 2);
        supplyMap.set(node3, -3);

        SmartDigraph.ArcMap costMap = graph.createArcMap();
        costMap.set(arc1, 10);
        costMap.set(arc2, 20);

        SmartDigraph.ArcMap upperMap = graph.createArcMap();
        upperMap.set(arc1, 1);
        upperMap.set(arc2, 3);

        SmartDigraph.NetworkSimplex simplex = graph.networkSimplex();
        simplex.setCostMap(costMap);
        simplex.setUpperMap(upperMap);
        simplex.setSupplyMap(supplyMap);

        MinCostFlowResultType result = simplex.run();

        Assert.assertEquals(MinCostFlowResultType.OPTIMAL, result);

        long flow1 = simplex.flow(arc1);
        long flow2 = simplex.flow(arc2);

        Assert.assertEquals(1, flow1);
        Assert.assertEquals(3, flow2);

        SmartDigraph.CostScaling costScaling = graph.costScaling();
        costScaling.setCostMap(costMap);
        costScaling.setUpperMap(upperMap);
        costScaling.setSupplyMap(supplyMap);

        result = costScaling.run();

        Assert.assertEquals(MinCostFlowResultType.OPTIMAL, result);

        flow1 = costScaling.flow(arc1);
        flow2 = costScaling.flow(arc2);

        Assert.assertEquals(1, flow1);
        Assert.assertEquals(3, flow2);

        SmartDigraph.CapacityScaling capacityScaling = graph.capacityScaling();
        capacityScaling.setCostMap(costMap);
        capacityScaling.setUpperMap(upperMap);
        capacityScaling.setSupplyMap(supplyMap);

        result = capacityScaling.run();

        Assert.assertEquals(MinCostFlowResultType.OPTIMAL, result);

        flow1 = capacityScaling.flow(arc1);
        flow2 = capacityScaling.flow(arc2);

        Assert.assertEquals(1, flow1);
        Assert.assertEquals(3, flow2);

        simplex.close();
        costScaling.close();
        capacityScaling.close();
        graph.close();
    }

    @Test
    public void listDigraph() {
        ListDigraph graph = new ListDigraph();

        ListDigraph.Node node1 = graph.addNode();
        ListDigraph.Node node2 = graph.addNode();
        ListDigraph.Node node3 = graph.addNode();

        ListDigraph.Arc arc1 = graph.addArc(node1, node2);
        ListDigraph.Arc arc2 = graph.addArc(node2, node3);

        ListDigraph.NodeMap supplyMap = graph.createNodeMap();
        supplyMap.set(node1, 1);
        supplyMap.set(node2, 2);
        supplyMap.set(node3, -3);

        ListDigraph.ArcMap costMap = graph.createArcMap();
        costMap.set(arc1, 10);
        costMap.set(arc2, 20);

        ListDigraph.ArcMap upperMap = graph.createArcMap();
        upperMap.set(arc1, 1);
        upperMap.set(arc2, 3);

        ListDigraph.NetworkSimplex simplex = graph.networkSimplex();
        simplex.setCostMap(costMap);
        simplex.setUpperMap(upperMap);
        simplex.setSupplyMap(supplyMap);

        MinCostFlowResultType result = simplex.run();

        Assert.assertEquals(MinCostFlowResultType.OPTIMAL, result);

        long flow1 = simplex.flow(arc1);
        long flow2 = simplex.flow(arc2);

        Assert.assertEquals(1, flow1);
        Assert.assertEquals(3, flow2);

        simplex.close();
        graph.close();
    }
}