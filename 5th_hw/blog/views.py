from django.shortcuts import render
from rest_framework import generics
from .models import *
from .serializers import PostListSerializer

class PostListView(generics.ListAPIView):
    queryset = Post.objects.all()
    serializer_class = PostListSerializer
