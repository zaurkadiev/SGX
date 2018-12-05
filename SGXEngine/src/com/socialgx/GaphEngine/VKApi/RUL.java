package com.socialgx.GaphEngine.VKApi;

import com.socialgx.GaphEngine.Graph.EdgeGX;
import com.socialgx.GaphEngine.Graph.VertexGX;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaurmac on 1/29/17.
 */
public class RUL { // inrul and outrul

    private VertexGX centerVertex;

    private List<VertexGX> vertexes;

    private List<EdgeGX> edges;

    public RUL(VertexGX centerNode) {
        this.centerVertex = centerNode;
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public RUL(VertexGX centerNode, List<VertexGX> nodes, List<EdgeGX> edges) {
        this.centerVertex = centerNode;
        this.vertexes = nodes;
        this.edges = edges;
    }

    public VertexGX getCenterVertex() {
        return centerVertex;
    }

    public List<EdgeGX> getEdges() {
        return edges;
    }

    public List<VertexGX> getVertexes() {
        return vertexes;
    }
}
