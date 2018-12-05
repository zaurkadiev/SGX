//= require sigma
//= require sigma.plugins.neighborhoods/sigma.plugins.neighborhoods.js
//= require sigma.plugins.dragNodes/sigma.plugins.dragNodes.js
//= require sigma.layout.forceAtlas2/sigma.layout.forceAtlas2.js

$(document).ready(function () {


    function randomInteger(min, max) {
        var rand = min - 0.5 + Math.random() * (max - min + 1)
        rand = Math.round(rand);
        return rand;
    }


    function setGraphInitialState(s){
        $(" .neigbors_1 ").html("");
        $(" .neigbors_2 ").html("");



        s.graph.nodes().forEach(function(n) {
            $(" .neigbors_1 ").append("<li>" + n.id + "</li>");
        });

        s.graph.edges().forEach(function(n) {
            $(" .neigbors_2 ").append("<li>" + "<i>from_" + n.source + "</i>" + "<i> to_" + n.target + "</i>" + "</li>");
        });

        s.graph.nodes().forEach(function(e) {
            e.hidden = false;
            e.color = "#222";
        });

        s.graph.edges().forEach(function(n) {
            n.hidden = false;
            n.color = "#ccc";
        });

        $(" .selected ").text("ALL USERS ( n" + s.graph.nodes().length +" ) " + " ( v" + s.graph.edges().length + " ) " );

        // Same as in the previous event:
        s.refresh();
    }
});
