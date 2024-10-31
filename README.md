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

---

## 선택 일정 조회

기능 : 선택한 일정을 조회한다.
Request : 

```json
{
  "schedule_id" : 1
}
```

Response :

```json
{
    "title" : "일정1", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "content" : "일정 내용1"
}
```

---

## 일정 작성

기능 : 저장할 일정을 작성한다.
Request : 

```json
{
  "title" : "일정4", "fixedDate" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "password" : "qwe123", "content" : "일정 내용4"
}
```

Response :

```json
{ 
    {"result" : "Success"},
    {"message" : "일정 작성 완료"}
}
```

---

## 일정 수정

기능 : 선택한 일정을 수정한다.
Request : 

```json
{
  "schedule_id" : 1, "title" : "일정11", "fixed_date" : "2024.10.30.", "set_date" : "2024.10.30.", "name" : "조성민", "password" : "qwe123", "content" : "일정 내용11"
}
```

Response :

```json
{ 
    {"result" : "Success"},
    {"message" : "일정 수정 완료"}
}
```

---

## 일정 삭제

기능 : 선택한 일정을 삭제한다.
Request : 

```json
{
  "schedule_id" : 1
}
```

Response :

```json
{ 
    {"result" : "Success"},
    {"message" : "일정 삭제 완료"}
}
```
