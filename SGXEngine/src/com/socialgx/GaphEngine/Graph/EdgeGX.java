package com.socialgx.GaphEngine.Graph;

/**
 * Created by zaurmac on 1/26/17.
 */
public class EdgeGX {

    private String edgeName;
    private double edgeWeight;
    private long toID;
    private long fromID;

    public EdgeGX(String name){
        this.edgeName = name;
    }

    public EdgeGX(String name, long fromID, long toID){
        this.edgeName = name;
        this.fromID = fromID;
        this.toID = toID;
    }

    public EdgeGX(String name, long fromID, long toID, double edgeWeight){
        this.edgeName = name;
        this.fromID = fromID;
        this.toID = toID;
        this.edgeWeight = edgeWeight;
    }


    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    public void setEdgeWeight(double edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    public void setFromID(long fromID) {
        this.fromID = fromID;
    }

    public void setToID(long toID) {
        this.toID = toID;
    }

    public double getEdgeWeight() {
        return edgeWeight;
    }

    public long getFromID() {
        return fromID;
    }

    public long getToID() {
        return toID;
    }

    public String getEdgeName() {
        return edgeName;
    }

    @Override
    public boolean equals(Object obj) {

        boolean isEqual= false;

        if (obj != null && obj instanceof EdgeGX)
        {
            isEqual = (this.fromID == ((EdgeGX) obj).fromID && this.toID == ((EdgeGX) obj).toID);
        }

        return isEqual;
    }
}
