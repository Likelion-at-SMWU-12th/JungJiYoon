from rest_framework.serializers import ModelSerializer
from .models import Post, Comment

class PostModelSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__'

class PostListSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        fields = ['title', 'contents', 'created_at']
        depth = 1