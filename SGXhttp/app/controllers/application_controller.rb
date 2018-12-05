class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :exception

  $redirect_uri = "http://localhost:3000/confirm"
  $app_id = 5824537
  $display = "page"
  $scope = 273823 #all access
  $response_type = "code"
  $version = 5.64
  $secure_key = 'IN3iExHYLkBWzGWaq3Bw'
  $rabbit_mq_gx_queue_name = 'gx_queue'
  $rabbit_mq_web_queue_name = 'web_queue'
  @task_exist_message = 'task_exist'

  # USER LIST OF UIDS of USERS THAT WAITS THEIR TASKS
  $user_list = {}

  # LISTEN WEB QUEUE FROM GX
  conn = Bunny.new :hostname => 'localhost'
  conn.start
  ch = conn.create_channel
  q = ch.queue($rabbit_mq_web_queue_name)
  # RECEIVE MESSAGES FROM GX ()
  Thread.new do
    q.subscribe(:block => false) do |delivery_info, properties, body|
      puts "MESSAGE FROM GX " + body
      end_task_id = body.to_s.split('_').last
      puts "END TASK ID = " + end_task_id
      $user_list.delete(end_task_id)
    end
  end

  # SEND REQUESTS TO WEB
  def send_request url
    url = URI.encode(url)
    uri = URI.parse(url)
    puts uri.inspect
    Net::HTTP.get_response(uri)
  end

  # SEND MESSAGES TO GX
  def send_message_to_gx_engine message
    conn = Bunny.new :hostname => 'localhost'
    conn.start
    ch = conn.create_channel
    queue = ch.queue($rabbit_mq_gx_queue_name)
    ch.default_exchange.publish(message, :routing_key => queue.name)
    conn.close
    $user_list[session[:user_id].to_s] = true
  end

  def isTaskPerforming
    $user_list[session[:user_id].to_s]
  end
end
