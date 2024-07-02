from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse
from rest_framework.viewsets import ModelViewSet

from django.views.generic import ListView
from .models import Post
from .forms import PostBasedForm, PostModelForm
from .serializers import PostModelSerializer

from rest_framework.response import Response
from rest_framework.decorators import api_view

@api_view(['POST'])
def calculator(request):
    data = request.data
    num1 = data.get('num1', 0) # 기본값 0
    num2 = data.get('num2', 0) # 기본값 0
    operator = data.get('operator') # 없으면 Null

    if operator == '+':
        result = int(num1) + int(num2)
    elif operator == '-':
        result = int(num1) - int(num2)
    elif operator == '*':
        result = int(num1) * int(num2)
    elif operator == '/':
        result = int(num1) / int(num2)
    else:
        result = 0

    # 사용된 연산자와 결과를 JSON 형식으로 반환
    data = {
        'operator' : operator,
        'result' : result
    }
    return Response(data)

class PostModelViewSet(ModelViewSet):
    queryset = Post.objects.all()
    serializer_class=PostModelSerializer

def index(request):
    return render(request, 'index.html')

def post_list_view(request):
    return render(request, 'posts/post_list.html')

def post_detail_view(request, id):
    return render(request, 'posts/post_detail.html')

def post_create_view(request):
    return render(request, 'posts/post_form.html')

# 추가
def post_form_view(request):
    if request.method == "GET":
        form = PostBasedForm()
        context = {'form':form}
        return render(request, 'posts/post_form2.html', context)
    else:
        return redirect('index')

def post_create_form_view(request):
    if request.method == "GET":
        form = PostBasedForm()
        context = {'form':form}
        return render(request, 'posts/post_form2.html', context)
    else: # POST 요청
        form = PostBasedForm(request.POST, request.FILES)

        if form.is_valid():
            Post.objects.create(
                image = form.cleaned_data['image'],
                content = form.cleaned_data['content'],
                writer = request.user
            )
        else:
            return redirect('post:post-new')
        return redirect('index')


def post_update_view(request, id):
    return render(request, 'posts/post_update.html')

def post_delete_view(request, id):
    return render(request, 'posts/post_confirm_delete.html')

def url_view(request):
    data={'code':'001','msg':'OK'}
    return HttpResponse('<h1>url.view</h1>')

def url_parameter_view(request, username):
    print('url_parameter_view()')
    print(f'username: {username}')
    print(f'request.GET: {request.GET}')
    return HttpResponse(username)

def function_view(request):
    print(f'request.method:{request.method}')
    print(f'request.GET: {request.GET}')
    print(f'request.POST: {request.POST}')
    return render(request, 'view.html')

class class_view(ListView):
    model=Post
    template_name='cbv_view.html'
