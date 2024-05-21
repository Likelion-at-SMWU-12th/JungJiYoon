from django.db import models
from django.contrib.auth import get_user_model
from django.utils import timezone
# Create your models here.

User=get_user_model()

class Post (models.Model):
    image = models.ImageField(verbose_name='이미지', null=True, blank=True)
    content = models.TextField(verbose_name='내용')
    created_at = models.DateTimeField(verbose_name='작성일', auto_now_add=True)
    view_count = models.IntegerField(verbose_name='조회수', default=0)
    writer = models.ForeignKey(to=User, on_delete=models.CASCADE, verbose_name='작성자', null=True, blank=True)

# class Comment (models.Model):
#     image = models.ImageField(verbose_name='이미지')
#     nickname = models.CharField('닉네임', max_length=10)
#     content = models.TextField('내용')
#     time = models.TimeField('작성 시간')
#     likes = models.IntegerField('좋아요수', default=0)
#     dislikes = models.IntegerField('싫어요수', default=0)

class Comment(models.Model):
    content = models.TextField(verbose_name='내용')
    created_at=models.DateTimeField(verbose_name='작성일', auto_now_add=True)
    post=models.ForeignKey(to='Post', on_delete=models.CASCADE, verbose_name='게시글')
    writer=models.ForeignKey(to=User, on_delete=models.CASCADE, verbose_name='작성자', null=True)