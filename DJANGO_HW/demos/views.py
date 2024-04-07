from django.shortcuts import render

# Create your views here.
def myHobby(request):
    return render(request, 'myhobby.html')