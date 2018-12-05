class SceneController < ApplicationController

  def index
    unless session[:user_id]
      redirect_to controller: :main, action: :index
    end


    graph = Graph.find_by(id: params[:graph_id])

    @nodes = graph.vertexes.to_json.html_safe
    @edges = graph.edges.to_json.html_safe

    @graph_id = params[:graph_id]
    @user_photo = graph.vertexes.first[:photo_100]
    @user_name = graph.vertexes.first[:first_name]
    @user_last_name = graph.vertexes.first[:last_name]
    @nodes_count = graph.vertexes.count
    @edges_count = graph.edges.count
    @level = graph.level
    @cuid = graph.cuid
  end

end
