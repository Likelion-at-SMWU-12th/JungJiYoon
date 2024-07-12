from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse
from rest_framework.viewsets import ModelViewSet

from django.views.generic import ListView
from .models import Post
from .forms import PostBasedForm, PostModelForm
from .serializers import PostModelSerializer, PostListSerializer, PostRetrieveSerializer, CommentListModelSerializer

from rest_framework.response import Response
from rest_framework.decorators import api_view, action, permission_classes
from rest_framework import generics
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser

from django.contrib.auth import authenticate
from rest_framework.authtoken.models import Token

# ViewSet
class PostModelViewSet(ModelViewSet):
    queryset=Post.objects.all()
    serializer_class=PostListSerializer

    @action(detail=True, methods=['get'])
    def get_comment_all(self, request, pk = None):
        post = self.get_object() 
        comment_all = post.comment_set.all()
        serializer = CommentListModelSerializer(comment_all, many=True)
        return Response(serializer.data)
    
    def get_permissions(self):
        action = self.action
        permission_classes = []
        if action == 'list' :
            permission_classes = [AllowAny]
        elif action == 'create':
            permission_classes = [IsAuthenticated]
        elif action == 'retrieve':
            permission_classes = [IsAuthenticated]
        elif action == 'update':
            permission_classes = [IsAdminUser]
        elif action == 'partial_update':
            permission_classes = [IsAdminUser]
        elif action == 'destroy':
            permission_classes = [IsAdminUser]
        return [permission() for permission in permission_classes]

# 게시글 목록 + 생성
class PostListCreateView(generics.ListAPIView, generics.CreateAPIView):
    queryset = Post.objects.all()
    serializer_class = PostListSerializer

# 게시글 상세 + 수정 + 삭제
class PostRetrieveUpdateView(generics.RetrieveAPIView, generics.UpdateAPIView, generics.DestroyAPIView):
    queryset = Post.objects.all()
    serializer_class = PostRetrieveSerializer

# 게시글 수정
# class PostUpdateView(generics.UpdateAPIView):
#     queryset = Post.objects.all()
#     serializer_class = PostListSerializer

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

@api_view(['POST'])
@permission_classes([AllowAny])
def login_view(request):
    username = request.POST['username']
    password = request.POST['password']
    user = authenticate(request,
                        username=username,
                        password=password,)
    
    if user is not None:
        token, _ = Token.objects.get_or_create(user=user)
        return Response({'token': token.key})
    
    else:
        return Response(status=401)