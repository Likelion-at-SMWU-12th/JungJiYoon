# Generated by Django 5.0.7 on 2024-07-15 07:19

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('blog', '0003_alter_comment_post'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='comment',
            name='author_id',
        ),
        migrations.RemoveField(
            model_name='post',
            name='author_id',
        ),
    ]
