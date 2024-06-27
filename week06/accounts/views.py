from django.shortcuts import render, redirect
from .forms import SignUpForm
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import login, logout

def signup_view(request):
    # GET 요청 시 HTML 응답
    if request.method == 'GET':
        form = SignUpForm
        context = {'form' : form}
        return render(request, 'accounts/signup.html', context)
    # POST 요청 시 데이터 확인 후 회원 생성
    else:
        form = SignUpForm(request.POST)
        if form.is_valid():
            # 회원가입 성공
            instance = form.save()
            return redirect('index')
        else: # 회원가입 실패
            return redirect('accounts:signup')
        
def login_view(request):
    # GET 요청 - 로그인 HTML 응답
    if request.method == 'GET':
        return render(request, 'accounts/login.html', {'form':AuthenticationForm()})
    # POST 요청
    else:
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            login(request, form.user_cache)
            return redirect('index')
        else:
            return render(request, 'accounts/login.html', {'form':form})

def logout_view(request):
    # 데이터 유효성 검사
    if request.user.is_authenticated:
        logout(request)
    return redirect('index')