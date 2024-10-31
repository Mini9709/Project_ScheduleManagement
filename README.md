# API 명세서

---

|기능|Method|URL|Request|Response|상태코드|
|:---:|:---:|:---:|:---:|:---:|:---:|
|전체 일정 조회|GET|/api/schedules|-|모든 일정|200|
|선택 일정 조회|GET|/api/schedules/{schedule_id}||선택 일정|200|
|일정 작성|POST|/api/schedules|내용 body|작성 성공 메시지|200|
|일정 수정|PUT|/api/schedules/{schedule_id}|내용 body|수정 성공 메시지|200|
|일정 작성|POST|/api/schedules/{schedule_id}|-|삭제 성공 메시지|200|

---

## 전체 일정 조회

기능 : 모든 일정을 조회한다.

Request : 없음

Response :

```json
{
  data :
    [
    {"title" : "일정1", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "content" : "일정 내용1"},
    {"title" : "일정2", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "content" : "일정 내용2"},
    {"title" : "일정3", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "content" : "일정 내용3"}
    ]
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|제목|title|varchar(20)|NOT NULL|
|수정날짜|fixed_date|date|NOT NULL|
|등록날짜|set_date|date|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|내용|content|varchar|-|


---

## 선택 일정 조회

기능 : 선택한 일정을 조회한다.

Request : 

```json
{
  "schedule_id" : 1
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|일정id|schedule_id|int|NOT NULL|


Response :

```json
{
    "title" : "일정1", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "content" : "일정 내용1"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|제목|title|varchar(20)|NOT NULL|
|수정날짜|fixed_date|date|NOT NULL|
|등록날짜|set_date|date|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|내용|content|varchar|-|


---

## 일정 작성

기능 : 저장할 일정을 작성한다.

Request : 

```json
{
  "title" : "일정4", "fixedDate" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "password" : "qwe123", "content" : "일정 내용4"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|제목|title|varchar(20)|NOT NULL|
|수정날짜|fixed_date|date|NOT NULL|
|등록날짜|set_date|date|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|비밀번호|password|varchar(20)|NOT NULL|
|내용|content|varchar|-|


Response :

```json
{ 
    {"result" : "Success"},
    {"message" : "일정 작성 완료"}
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|결과|result|boolean|NOT NULL|
|메시지|message|varchar(10)|-|


---

## 일정 수정

기능 : 선택한 일정을 수정한다.

Request : 

```json
{
  "schedule_id" : 1, "title" : "일정11", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "password" : "qwe123", "content" : "일정 내용11"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|일정id|schedule_id|int|NOT NULL|
|제목|title|varchar(20)|NOT NULL|
|수정날짜|fixed_date|date|NOT NULL|
|등록날짜|set_date|date|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|비밀번호|password|varchar(20)|NOT NULL|
|내용|content|varchar|-|


Response :

```json
{ 
    {"result" : "Success"},
    {"message" : "일정 수정 완료"}
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|결과|result|boolean|NOT NULL|
|메시지|message|varchar(10)|-|


---

## 일정 삭제

기능 : 선택한 일정을 삭제한다.

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|일정id|schedule_id|int|NOT NULL|


Request : 

```json
{
  "schedule_id" : 1
}
```

Response :

```json
{ 
    {"result" : true},
    {"message" : "일정 삭제 완료"}
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|결과|result|boolean|NOT NULL|
|메시지|message|varchar(10)|-|


---
