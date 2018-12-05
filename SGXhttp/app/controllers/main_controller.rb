class MainController < ApplicationController
  def index
    if session[:access_token] != nil
      if session[:login_time] + session[:expires_in] > Time.now.to_i
        redirect_to action: :dashboard
      end
    end
  end

  def confirm
    session[:code] = params[:code];
    res = send_request("https://oauth.vk.com/access_token?client_id=#{$app_id}&client_secret=#{$secure_key}&redirect_uri=#{$redirect_uri}&code=#{params[:code]}")
    res_hash = JSON.parse(res.body)

    session[:access_token] = res_hash['access_token']
    session[:expires_in] = res_hash['expires_in']
    session[:login_time] = Time.now.to_i
    session[:user_id] = res_hash['user_id']
    session[:api] = 'vk'
    puts res_hash.inspect

    redirect_to action: :dashboard
  end

  def dashboard
    res = send_request("https://api.vk.com/method/users.get?user_ids=#{session[:user_id]}&fields=photo_200_orig")
    puts res.body
    @me = JSON.parse(res.body)['response']
  end

  def new_task
    puts "USER LIST "+$user_list.inspect
    if isTaskPerforming
      # flash[:notice] = "Нельзя выполнять более одного задания!"
      respond_to do |format|
        format.json { render :json => '{"message": "Идет выполнение задания! Разрешено выполнение только одного здания.", "code":"task_existed"}' }
      end
      puts 'DONT CREATE NEW TASK'
      return
    end

    puts params.inspect

    t_stamp = Time.now.to_i.to_s

    # MESSAGE FORMAT
    message = "uid=#{session[:user_id]}&cuid=#{params[:cuid]}&"+
              "friends_list=#{params[:friends_list]}&"+
              "bookmarks=#{params[:bookmarks]}&"+
              "max_nodes=#{params[:max_nodes]}&"+
              "level=#{params[:level]}&"+
              "access_token=#{session[:access_token]}&"+
              "time_stamp=#{t_stamp}"

    Graph.new( time_stamp: t_stamp, uid: session[:user_id]).save!

    send_message_to_gx_engine message
    puts 'CREATED NEW TASK'
    respond_to do |format|
      format.json { render :json => '{"message": "Запрос успешно подтвержден", "code":"task_new"}' }
    end
  end

  def logout
    session.clear;
    redirect_to controller: :main, action: :index
  end

  def list
    @graphs = Graph.where('uid' => session[:user_id])
  end

  def search_user
    count = 40
    response = send_request("https://api.vk.com/method/users.search?q=#{params[:q]}&sort=0&fields=photo_100&count=#{count}&access_token=#{session[:access_token]}&v=#{$version}")
    puts response.body.inspect
    respond_to do |format|
        format.json {render :json => response.body}
    end
  end

  def delete_graph
    id = params[:graph_id]
    graph = Graph.find_by(id: params[:graph_id])
    graph.destroy
    flash[:notice] = "Удалено"
    redirect_to :back
  end
end
