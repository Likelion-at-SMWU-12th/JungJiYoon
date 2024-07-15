from django.contrib import admin
from django.urls import path, include
from blog.views import *

app_name = 'posts'

urlpatterns = [
    path('', PostListView.as_view()),
    path('<int:pk>/', PostRetrieveUpdateView.as_view()),
    path('<int:post_id>/comment/', CommentCreateView.as_view()),
]
