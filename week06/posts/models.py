from django.db import models

# Create your models here.
class Post (models.Model):

    image = models.ImageField(verbose_name='이미지')
    contentn = models.TextField('내용')
    created_at = models.DateTimeField('작성일')
    view_count = models.IntegerField('조회수', default=0)

class Comment (models.Model):
    image = models.ImageField(verbose_name='이미지')
    nickname = models.CharField('닉네임', max_length=10)
    content = models.TextField('내용')
    time = models.TimeField('작성 시간')
    likes = models.IntegerField('좋아요수', default=0)
    dislikes = models.IntegerField('싫어요수', default=0)
