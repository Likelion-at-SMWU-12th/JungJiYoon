from django.shortcuts import render
from rest_framework import generics
from rest_framework.permissions import IsAuthenticated
from .models import *
from .serializers import PostListSerializer, CommentSerializer

# 게시글 목록 + 생성
class PostListView(generics.ListAPIView, generics.CreateAPIView):
    queryset = Post.objects.all()
    serializer_class = PostListSerializer

# 게시글 상세 + 수정 + 삭제
class PostRetrieveUpdateView(generics.RetrieveAPIView, generics.UpdateAPIView, generics.DestroyAPIView):
    queryset = Post.objects.all()
    serializer_class = PostListSerializer

# 댓글 생성
class CommentCreateView(generics.CreateAPIView):
    queryset = Comment.objects.all()
    serializer_class = CommentSerializer

    def perform_create(self, serializer):
        post_id = self.kwargs.get('post_id')
        post = Post.objects.get(id=post_id)
        serializer.save(post=post)