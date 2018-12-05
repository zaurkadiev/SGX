package com.socialgx.GaphEngine.Graph;

/**
 * Created by zaurmac on 1/26/17.
 */
public class VertexGX {

    private int vertexID;
    private String vertexName;
    private double vertexVariable;

    public VertexGX(int vertexID){
        this.vertexID = vertexID;
    }

    public VertexGX(int id, String name){
        this.vertexID = id;
        this.vertexName = name;
        this.vertexVariable = 0;
    }

    public VertexGX(int id, String name, double rank){
        this.vertexID = id;
        this.vertexName = name;
    }

    public void setVertexName(String vertexName) {
        this.vertexName = vertexName;
    }

    public void setVertexID(int vertexID) {
        this.vertexID = vertexID;
    }


    public int getVertexID() {
        return vertexID;
    }

    public String getVertexName() {
        return vertexName;
    }

    public void setVertexVariable(double vertexVariable) {
        this.vertexVariable = vertexVariable;
    }

    public double getVertexVariable() {
        return vertexVariable;
    }

    @Override
    public boolean equals(Object obj) {

        boolean isEqual= false;

        if (obj != null && obj instanceof VertexGX)
        {
            isEqual = (this.vertexID == ((VertexGX) obj).vertexID);
        }

        return isEqual;
    }
}
