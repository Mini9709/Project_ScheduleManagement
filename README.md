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
    [
      {"scheduleId" : 1, "title" : "일정1", "name" : "조성민", "fixedDate" : "2024.10.30.", "registeredDate" : "2024.10.30.", "contents" : "일정 내용1"},
      {"scheduleId" : 2, "title" : "일정2", "name" : "조성민", "fixedDate" : "2024.10.30.", "registeredDate" : "2024.10.30.", "contents" : "일정 내용2"},
      {"scheduleId" : 3, "title" : "일정3", "name" : "조성민", "fixedDate" : "2024.10.30.", "registeredDate" : "2024.10.30.", "contents" : "일정 내용3"}
    ]
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|id|schedule_id|bigint|NOT NULL|
|제목|title|varchar(20)|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|수정날짜|fixed_date|varchar(30)|NOT NULL|
|등록날짜|registered_date|varchar(30)|NOT NULL|
|내용|contents|varchar(200)|-|


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
  "scheduleId" : 1, "title" : "일정1", "name" : "조성민", "fixedDate" : "2024.10.30.", "registeredDate" : "2024.10.30.", "contents" : "일정 내용1"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|id|schedule_id|bigint|NOT NULL|
|제목|title|varchar(20)|NOT NULL|
|이름|name|varchar(10)|NOT NULL|
|수정날짜|fixed_date|varchar(30)|NOT NULL|
|등록날짜|registered_date|varchar(30)|NOT NULL|
|내용|contents|varchar(200)|-|


---

## 일정 작성

기능 : 저장할 일정을 작성한다.

Request : 

```json
{
  "title" : "일정4", "userId" : 1, "password" : "qwe123", "contents" : "일정 내용4"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|제목|title|varchar(20)|NOT NULL|
|유저 id|user_id|bigint|NOT NULL|
|비밀번호|password|varchar(20)|NOT NULL|
|내용|contents|varchar(200)|-|


Response :

```json
{
  "scheduleId" : 4,
  "result" : "Success",
  "message" : "일정 작성 완료"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|id|schedule_id|bigint|NOT NULL|
|결과|result|varchar|-|
|메시지|message|varchar|-|


---

## 일정 수정

기능 : 선택한 일정을 수정한다.

Request : 

```json
{
  "name" : "조성민", "password" : "qwe123", "contents" : "일정 내용11"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|이름|name|varchar(10)|NOT NULL|
|비밀번호|password|varchar(20)|NOT NULL|
|내용|contents|varchar(200)|-|


Response :

```json
{
  "scheduleId" : 4,
  "result" : "Success",
  "message" : "일정 수정 완료"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|id|schedule_id|bigint|NOT NULL|
|결과|result|boolean|NOT NULL|
|메시지|message|varchar(10)|-|


---

## 일정 삭제

기능 : 선택한 일정을 삭제한다.

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|비밀번호|password|varchar(20)|NOT NULL|


Request : 

```json
{
  "password" : "qwe123"
}
```

Response :

```json
{
  "scheduleId" : 4,
  "result" : "Success",
  "message" : "일정 삭제 완료"
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|id|schedule_id|bigint|NOT NULL|
|결과|result|boolean|NOT NULL|
|메시지|message|varchar(10)|-|


---

# ERD

![ERD 스크린샷](https://github.com/Mini9709/Project_ScheduleManagement/blob/main/ERD.png)
