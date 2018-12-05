Rails.application.routes.draw do

  root :to => 'main#index'

  get 'confirm', to: 'main#confirm'
  get 'dashboard', to: 'main#dashboard'
  get 'list', to: 'main#list'
  get 'scene/:graph_id', to: 'scene#index'
  get 'logout', to: 'main#logout'
  get 'list', to: 'main#list'

  get 'search_user', to: 'main#search_user'
  get 'delete/:graph_id', to: 'main#delete_graph'

  post 'new_task', to: 'main#new_task'
end
