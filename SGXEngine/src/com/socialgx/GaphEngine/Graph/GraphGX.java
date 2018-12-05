package com.socialgx.GaphEngine.Graph;

import com.socialgx.Tools.DataTools;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaurmac on 1/26/17.
 */
public class GraphGX {

    private List<EdgeGX> edgeGXList;
    private List<VertexGX> vertexGXList;

    public GraphGX(){
        edgeGXList = new ArrayList<>();
        vertexGXList = new ArrayList<>();
    }

    public GraphGX(List<VertexGX> vertexGXList, List<EdgeGX> edgeGXList){
        this.vertexGXList = vertexGXList;
        this.edgeGXList = edgeGXList;
    }

    public void setEdgeGXList(List<EdgeGX> edgeGXList) {
        this.edgeGXList = edgeGXList;
    }

    public void setVertexGXList(List<VertexGX> vertexGXList) {
        this.vertexGXList = vertexGXList;
    }

    public List<EdgeGX> getEdgeGXList() {
        return edgeGXList;
    }

    public List<VertexGX> getVertexGXList() {
        return vertexGXList;
    }

    public void addToEdgeGXList(List<EdgeGX> edgesList){
        this.edgeGXList.addAll(edgesList);
    }

    public void addToVertexGXList(List<VertexGX> vertexList){
        this.vertexGXList.addAll(vertexList);
    }

    public void addToEdgeGXList(EdgeGX edges){
        this.edgeGXList.add(edges);
    }

    public void addToVertexGXList(VertexGX vertex){
        this.vertexGXList.add(vertex);
    }

    public String toJSON(String startedAt){


        JSONArray jsonNodes = new JSONArray();
        JSONArray jsonEdges = new JSONArray();


        for (VertexGX vertex : getVertexGXList()) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.putOnce("id", vertex.getVertexID());
            jsonObject.putOnce("rank", vertex.getVertexVariable());
            jsonNodes.put(jsonObject);
        }

        for (EdgeGX edge : getEdgeGXList()) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.putOnce("fromID", edge.getFromID());
            jsonObject.putOnce("toID", edge.getToID());
            jsonObject.putOnce("weigth", edge.getEdgeWeight());

            jsonEdges.put(jsonObject);
        }


        JSONObject jsonObject = new JSONObject();

        jsonObject.putOnce("created_at", DataTools.getCurrentDate());
        jsonObject.putOnce("started_at", startedAt);
        jsonObject.putOnce("nodes", jsonNodes);
        jsonObject.putOnce("edges", jsonEdges);

        return jsonObject.toString();
    }


}
