package com.socialgx.GaphEngine.VKApi;

import com.socialgx.GaphEngine.Graph.EdgeGX;
import com.socialgx.GaphEngine.Graph.GraphGX;
import com.socialgx.GaphEngine.Graph.VertexGX;
import com.socialgx.GaphEngine.VKApi.CustomVK.CommentXtrPidGX;
import com.socialgx.GaphEngine.VKApi.VKAccount.VKAccount;
import com.socialgx.GaphEngine.VKApi.VKTools.Loader;
import com.socialgx.Tools.DataTools;
import com.vk.api.sdk.objects.photos.CommentXtrPid;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zaurmac on 1/29/17.
 */
public class VKTaskExecutor {

    private static Logger LOGGER = Logger.getLogger("VKTaskExecutor");

    public static String explorePhotoComments(VKAccount vkAccount, int level, int cupID){

        String startedAt = DataTools.getCurrentDate();
        GraphGX graphGX = new GraphGX();
        VertexGX vertexGX = new VertexGX(cupID); // initial vertex
        List<VertexGX> vertexLevelList = new ArrayList<>();
        vertexLevelList.add(vertexGX);
        List<VertexGX> vertexNextLevelList = new ArrayList<>();

        for (int i = 0; i <= level; i++){

            for (int j = 0; j< vertexLevelList.size(); j++) {

                VertexGX vertex = vertexLevelList.get(j);
                RUL inRul = new RUL(vertex);
                RUL outRul = new RUL(vertex);

//                List<CommentXtrPid> photoComments = Loader.loadAccountPhotoComments(vkAccount, vertex.getVertexID());
                List<CommentXtrPidGX> photoComments = Loader.loadAccountPhotoCommentsExecute(vkAccount, vertex.getVertexID());

                for (CommentXtrPidGX comment : photoComments) {
                    VertexGX vrtx = new VertexGX(comment.getFromId());
                    EdgeGX edge = new EdgeGX("", comment.getFromId(), inRul.getCenterVertex().getVertexID());

                    if(level == i){
                        if(!graphGX.getVertexGXList().contains(vrtx)) continue; // limit last level
                    }

                    if(comment.getFromId().equals(vertex.getVertexID())) continue; // prevent self loops in graph

                    if(!inRul.getVertexes().contains(vrtx)){
                        inRul.getVertexes().add(vrtx);
                    }

                    if(!inRul.getEdges().contains(edge)){
                        edge.setEdgeWeight(1);
                        inRul.getEdges().add(edge);
                    } else {
                        for (EdgeGX item : inRul.getEdges()) {

                            if(item.equals(edge)){
                                double weight = item.getEdgeWeight();
                                weight = weight + 1;
                                item.setEdgeWeight(weight);
                            }
                        }
                    }

                    for (EdgeGX item : inRul.getEdges()) {
                        double rank = inRul.getCenterVertex().getVertexVariable();
                        inRul.getCenterVertex().setVertexVariable(rank + item.getEdgeWeight());
                    }
                }




                if(!graphGX.getVertexGXList().contains(inRul.getCenterVertex())){
                    graphGX.addToVertexGXList(inRul.getCenterVertex());
                }

                for (EdgeGX edgeGX : inRul.getEdges()) {
                    if(!graphGX.getEdgeGXList().contains(edgeGX)) graphGX.addToEdgeGXList(edgeGX);
                }


                for (VertexGX item : inRul.getVertexes()) {
                    if(!vertexNextLevelList.contains(item) && !graphGX.getVertexGXList().contains(item)) vertexNextLevelList.add(item);
                }
                for (VertexGX item : outRul.getVertexes()) {
                    if(!vertexNextLevelList.contains(item) && !graphGX.getVertexGXList().contains(item)) vertexNextLevelList.add(item);
                }
            }


            LOGGER.info("LEVEL " + i + " ENDED WITH " + vertexLevelList.size() + " vertexes");
            vertexLevelList.clear();
            vertexLevelList.addAll(vertexNextLevelList);
        }

        String jsonGraph = graphGX.toJSON(startedAt);

        LOGGER.info(graphGX.toJSON(startedAt));

        return jsonGraph;

    }



}
