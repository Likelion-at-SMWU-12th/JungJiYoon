from django.shortcuts import render

from django.views.generic import TemplateView

# 자기소개 View (CBV)
class my_info(TemplateView):
    template_name='myinfo.html'

# 일기 View (FBV)
def my_diary(request):
    print(f'request.POST: {request.POST}')
    return render(request, 'mydiary.html')