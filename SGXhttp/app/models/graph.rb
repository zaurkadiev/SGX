class Graph
  include Mongoid::Document
  field :uid, type: String
  field :cuid, type: String
  field :max_nodes, type: String
  field :level, type: String
  field :time_stamp, type: String
  field :vertexes, type:Array
  field :edges, type:Array

  def as_json(*args)
    res = super

    # convert BSON::ObjectId to string
    res["_id"] = res["_id"].to_s

    # or you also can change attribute name from _id to id
    # res["id"] = res.delete("_id").to_s

    res
  end
end
