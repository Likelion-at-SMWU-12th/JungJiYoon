from django import forms
from .models import Post

# Form (일반 폼)
class PostBasedForm(forms.Form):
    image = forms.ImageField(label='이미지')
    content = forms.CharField(label='내용', widget=forms.Textarea)
    CATEGORY_CHOICES = [
        ('1', '일반'),
        ('2', '계정'),
    ]
    category = forms.ChoiceField(label='카테고리', choices=CATEGORY_CHOICES)

# Model Form(모델 폼)
# class PostForm(forms.ModelForm):
#     class Meta:
#         model = Post
#         # '__all__' 설정 시 전체 필드 추가
#         fields = ['title', 'content']

# Model Forms
class PostModelForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = '__all__'