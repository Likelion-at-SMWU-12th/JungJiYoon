***
# 폼을 사용하여 작업하기
### HTML 폼
- 폼은 `<input>` 요소와 함께 2가지 지정
  1. 위치: 사용자의 입력에 해당하는 데이터를 반환해야 하는 url
  2. 방법: 데이터를 반환할 HTTP 메서드  

eg. 브라우저에 폼 데이터가 `<form>`의 action 속성에 지정된 URL로 전송되어야 하며 post 방식으로 전송되어야 함.

### GET과 POST
- 폼을 처리할 때 사용할 수 있는 메서드는 GET과 POST가 전부
- POST: 시스템 상태를 변경하는 데 사용될 수 있는 모든 요청
- GET: 시스테 상태에 영향을 주지 않는 요청에만 사용   
***
# 폼에서 Django의 역할
- 3가지 역할 수행  
  1. 렌더링할 수 있도록 데이터 준비&재구성
  2. 해당 데이터에 대한 HTML 양식 생성
  3. 클라이언트로부터 제출된 양식 및 데이터 수신 및 처리
***
# Forms in Django
### Django Form class
- form 클래스의 필드는 HTML 폼 `<input>` 요소에 매핑
- form 필드는 브라우저에서 사용자에게 HTML "widget"으로 표시됨.
***
# Form 인스턴스화, 처리 및 렌더링
- 장고에서 객체 렌더링 시 순서  
  1. view에서 해당 객체를 가져옴
  2. 템플릿 컨텍스트로 전달
  3. 템플릿 변수 사용하여 HTML 마크업으로 전개
- form을 비워두거나 다음과 같이 미리 채워둘 수 있음  
  1. 미리 저장된 모델의 데이터
  2. 다른 곳에서 수집한 데이터
  3. 이전 HTML 폼 제출 시에 받은 데이터  
     -> 사용자가 정보를 다시 웹사이트로 보낼 수 있게 해줌.
***
# Building a form
### 사용자의 이름을 입력 받기 위한 간단한 form
- 템플릿  
  ```
  <form action="/your-name/" method="post">
    <label for="your_name">Your name: </label>
    <input id="your_name" type="text" name="your_name" value="{{ current_name }}">
    <input type="submit" value="OK">
   </form>
   ```
  - 브라우저가 POST 방식으로, 데이터를 url `/your-name/`으로 반환
  - 템플릿 context에 `current_name`이라는 변수가 포함되어 있으면 이 변수가 `your_name` 필드를 미리 채우는데 사용됨
  - 폼이 제출되면 서버로 데이터를 POST 방식으로 전송
***
# Building a form in Django
### The Form class
- forms.py  
  ```
  from django import forms

  class NameForm(forms.Form):
    your_name = forms.CharField(label='Your name", max_length=100)
    ```
  - 단일 필드(your_name)가 있는 Form 클래스
- is_valid() 메서드
  - Form 객체에 있는 "모든 필드에 대해 유효성 검사 루틴을 실행"하는 메서드
  - True를 반환 -> form의 데이터를 `cleaned_data` 속성에 배치
- 전체 form은 다음과 같음
  ```
  <label for="your_name">Your name: </label>
  <input id="your_name" type="text" name="your_name" maxlength="100" required>
  ```
  - `<form>` 태그와 제출 버튼은 포함X, 템플릿에서 제공해야 함.

### The view
- views.py
  ```
  form django.http import HttpResponseRedirect
  form django.shortcuts import render
  form .forms import NameForm

  def get_name(request):
    if request.method == "POST":
      form = NameForm(request.POST)
      if form.is_valid():
        return HttpResponserRedirect("/thanks/")
    else:
      form = NameForm()
    return render(request, "name.html", {"form":form})
    ```
  - GET 요청 : 빈 폼 객체가 생성. 템플릿 컨텍스트에 배치되어 렌더링됨.(URL을 처음 방문)
  - POST 요청 : 폼 객체를 다시 만들고 요청 데이터로 채움. (폼에 '데이터 바인딩')
  - is_valid()가 True
    - 폼과 함께 템플릿으로 돌아감
    - 폼이 비어 있지 않으므로 이전에 제출한 데이터로 HTML 양식이 채워짐
    - `cleaned_data` 속성에서, 폼 데이터를 찾을 수 있음. -> db를 업데이트 하거나, 브라우저에 다음 행선지를 알려주는 HTTP 리다이렉션 가능
### The template
- name.html
  ```
  <form action=/your_name" method="post">
    {% csrf_token %}
    {{ form }}
    <input type="submit" value="Submit">
  </form>
  ```
  - 폼의 모든 필드와 속성은 `{{ form }}`에서 HTML 형식으로 표현
***
# Django Form 클래스 추가 정보
- 모든 폼 클래스들은 `django.forms.Form`또는 `django.forms.ModelForm`의 서브클래스로 생성됨.
- ModelForm을 Form의 서브클래스라고 생각해도 됨
- Form과 ModelForm은 BaseForm으로부터 상속 받음.
***
# More on fields
### "contact me" 기능
- forms.py
  ```
  form django import forms

  class ContactForm(forms.Form):
    subject = forms.CharField(max_length=100)
    message = forms.CharField(widget=forms.Textarea)
    sender = forms.EmailField()
    cc_myself = forms.BooleanField(required=False)
    ```
    - 이번에는, subject, message, sender와 cc_myself 속성 추가

### Widgets
- 각각의 폼 필드는 대응되는 `Widget class`를 가짐.
- 그리고 `<input type="text">`와 같은 HTML 폼 위젯으로 대응됨
- 대부분의 경우, 필드는 적절한 디폴트 위젯을 가짐
  - `CharField` -> `TextInput`위젯 -> `<input type="text">`

### Field data
- 폼 데이터가 제출될 때, `is_valid()`에 의해 유효성 검증
- 검증된 폼 데이터는 `form.cleaned_data` 딕셔너리에 저장됨.
- 해당 데이터는 파이썬 타입으로 전환됨
- 위의 예시에서 `cc_myself`는 boolean 값임. 이것처럼, `IntegerField`와 `FloatField`는 파이썬의 int와 float 타입으로 전환
- views.py
  - 폼 데이터가 뷰에서 어떻게 처리되는지 보여줌
  ```
  from django.core.mail import send_mail

  if form.is_valid():
    subject = form.cleaned_data["subject"]
    message = form.cleaned_data["message"]
    sender = form.cleaned_data["sender"]
    cc_myself = form.cleaned_data["cc_myself"]

    recipients = ["info@example.com"]
    if cc_myself:
      recipients.append(sender)
    
    send_mail(subject, message, sender, recipients)
    return HttpResponserRedirect("/thanks/")
    ```
- 몇몇 필드 타입들은 추가로 다뤄줘야 하는 부분들이 있음
  - 폼으로 업로드되는 파일들은 다르게 처리되어야 함
  - `request.POST`가 아닌 `request.FILES`로 접근해야 함.

***
# 폼 템플릿 작업
- 폼 인스턴스를 템플릿 컨텍스트에 배치해야 함
- context의 'form'이라는 이름으로, {{ form }}은 `<label>`과 `<input>` 요소로 적절하게 렌더링됨
### 재사용 가능한 폼 템플릿
- 폼 렌더링 시의 HTML은 템플릿을 통해 만들어짐
- 폼의 템플릿 이름을 오버라이딩 함으로써 커스텀 가능
- {{ form }}이 form_snippet.html 템플릿의 출력으로 렌더링됨  
  ```
  {{ form }}

  {% for field in form%}
    <div class="fieldWrapper">
      {{ field.errors }}
      {{ field.label_tag}} {{ field }}
    </div>
  {% endfor %}
  ```
- 그런 다음, `FORM_RENDER` 설정 구성
  - settings.py
  ```
  from django.forms.renderers import TemplatesSetting

  class CoustomFormRenderer(TemplatesSetting):
    from_template_name = "form_snippet.hmlt"

  FORM_RENDERER = "project.settings.CustomFormRender"
  ```
***
# 폼 필드 반복
- {% for %} 루프를 통해 반복을 줄일 수 있음.
  ```
  {% for field in form %}
    <div class = "fieldWrapper">
      {{ field.errors }}
      {{ field.label_tag}} {{ field }}
      {% if field.help_text %}
        <p class="help" id="{{ field.auto_id }}_helptext">
          {{ field.help_text|safe }}
        </p>
      {% endif %}
    </div>
  {% endfor %}
  ```
- {{ field.errors }}
  - 필드에 대응하는 유효성 검사 에러를 포함하는 `<ul class="errorlist">` 출력
  - {% for error in field.errors %}로 에러 표현 커스텀 가능
- {{ field.field }}
  - 필드 속성에 접근하기 위해 사용 가능
- {{ field.help_text }}
  - 필드와 관련되어 있는 어떠한 help 텍스트
- {{ field.html_name }}
  - input 요소의 name 필드로 사용되는 필드의 이름
- {{ field.id_for_label }}
  - 이 필드에 사용될 id
- {{ field.is_hidden }}
  - 이 속성은 폼 필드가 숨겨져 있거나, False이면 True가 된다.
  - 템플릿 변수로 유용하진 않지만, 다음과 같은 조건부 테스트에서 유용
  ```
  {% if field.is_hidden %}
    {# Do something special #}
  {% endif %}
  ```
- {{ field.label }}
  - 필드의 label
  - eg) 이메일 주소
- {{ field.label_tag }}
  - 적절한 HTML `<label>` 태그에 감싸져있는 필드의 label
- {{ field.legend_tag }}
  - field.label_tag와 비슷하지만, `<legend>` 태그를 사용
- {{ field.use_fieldset }}
  - `<fieldset>`에 여러 입력이 포함된 경우 이 속성은 True
- {{ field.value }}
  - 필드의 값
  - eg) someone@example.com