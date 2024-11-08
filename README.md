# API 명세서

---

|기능|Method|URL|Request|Response|상태코드|
|:---:|:---:|:---:|:---:|:---:|:---:|
|일정 작성|POST|/api/schedules|내용 body|작성 성공 메시지|201|
|전체 일정 조회|GET|/api/schedules|-|모든 일정|200|
|선택 일정 조회|GET|/api/schedules/{schedule_id}||선택 일정|200|
|일정 수정|PATCH|/api/schedules/{schedule_id}|내용 body|수정 성공 메시지|200|
|일정 작성|POST|/api/schedules/{schedule_id}|-|삭제 성공 메시지|200|

---

## 일정 작성

### POST

__기능__ : 저장할 일정을 작성한다.

__URL__ : /api/schedules

__Request__ : 

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


__Response__ :

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

## 전체 일정 조회

### GET

__기능__ : 모든 일정을 조회한다.

__URL__ : /api/schedules

__Request__ : 없음

__Response__ :

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

### GET

__기능__ : 선택한 일정을 조회한다.

__URL__ : /api/schedules/{schedule_id}

__Request__ : 

```json
{
  "schedule_id" : 1
}
```

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|일정id|schedule_id|int|NOT NULL|


__Response__ :

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

## 일정 수정

### PATCH

__기능__ : 선택한 일정을 수정한다.

__URL__ : /api/schedules/{schedule_id}

__Request__ : 

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


__Response__ :

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

### DELETE

__기능__ : 선택한 일정을 삭제한다.

__URL__ : /api/schedules/{schedule_id}

|Logical Name|Physical Name|Type|Allow NULL|
|:---:|:---:|:---:|:---:|
|비밀번호|password|varchar(20)|NOT NULL|


__Request__ : 

```json
{
  "password" : "qwe123"
}
```

__Response__ :

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

![ERD 스크린샷](https://github.com/Mini9709/Project_ScheduleManagement/blob/main/ScheduleManageMent_ERD.png)


---

# 유저 관리

---

## 유저 등록

### POST

__url__ : /users

__request__ : body "name" "email"

__response__ : "userId", "result", "message"

__status code__ : 201 Created

__특이사항__ : 

등록날짜, 수정날짜가 현재날짜로 갱신됨


---

## 전체 유저 조회

### GET

__url__ : /users

__request__ : x

__response__ : "userId", "name", "email", "fixedDate", "registeredDate"

__status code__ : 200 Ok

---

## 선택 유저 조회

### GET

__url__ : /users/{userId}

__request__ : x

__response__ : "userId", "name", "email", "fixedDate", "registeredDate"

__status code__ : 200 Ok

---

## 유저 정보 수정

### PATCH

__url__ : /users/{userId}

__request__ : body "name", "email"

__response__ : "userId", "result", "message"

__status code__ : 200 Ok

__특이사항__ :

유저 정보를 수정할 때 fixedDate가 현재 날짜로 갱신

---

## 유저 정보 삭제

### DELETE

__url__ : /users/{userId}

__request__ : x

__response__ : "userId", "result", "message"

__status code__ : 200 Ok

__특이사항__ : 

해당 유저가 작성한 일정도 같이 삭제

---

## 유저 일정 조회

### GET

__url__ : /users/{userId}/schedules

__request__ : x

__response__ : "scheduleId", "title", "name", "fixedDate", "registeredDate", "contents"

__status code__ : 200 Ok

__특이사항__ : 

유저가 존재하고 일정이 없을 경우, 빈 배열 리턴

유저가 없을 경우, 404 Not Found

---

# 일정 관리

---

## 일정 작성

### POST

__url__ : /schedules

__request__ : body "title", "userId", "password", "contents"

__response__ : "scheduleId", "result", "message"

__status code__ : 201 Created

__특이사항__ : 

등록날짜, 수정날짜가 현재날짜로 갱신됨

---

## 전체 일정 조회

### GET

__url__ : /schedules

__request__ : x

__response__ : "scheduleId", "title", "name", "fixedDate", "registeredDate", "contents"

__status code__ : 200 Ok

---

## 선택 일정 조회

### GET

__url__ : /schedules/{scheduleId}

__request__ : x

__response__ : "scheduleId", "title", "name", "fixedDate", "registeredDate", "contents"

__status code__ : 200 Ok

---

## 일정 수정

### PATCH

__url__ : /schedules/{scheduleId}

__request__ : body "name", "password", "contents"

__response__ : "scheduleId", "result", "message"

__status code__ : 200 Ok

__특이사항__ : 

contents는 200자 내로 작성

password가 맞지 않으면 실패 메시지 출력

---

## 일정 삭제

### DELETE

__url__ : /schedules/{scheduleId}

__request__ : body "password"

__response__ : "scheduleId", "result", "message"

__status code__ : 200 Ok

__특이사항__ : 

password가 맞지 않으면 실패 메시지 출력

---

# 페이징

---

## 페이징 테스트

### GET

__url__ : /users/schedules/pages

__request__ : param int page, int size

__response__ : "scheduleId", "title", "name", "fixedDate", "registeredDate", "contents"

__status code__ : 200 Ok

__특이사항__ : 

리스트를 초과하는 페이지 호출 시 빈 배열 리턴

---

# 예외 처리

---

__ExceptionHandler__ 를 이용하여 공통 예외 처리 진행

__ExceptionController__ 가 진행하는 예외처리

__ResponseStatusException__ : url 오기 시 404 Not Found, 입력 값이 잘못된 경우 400 Bad Request

__MethodArgumentNotValidException__ : @Valid 진행 중 유효하지 않다는 예외 발생 시 400 Bad Request

__그 외__ : 500 Internal Server Error

---

# 유효성 검사

---

__Controller__ 에 __@Valid__ 를 이용하여 유효성 검사

## UserRequestDto

__email__ : __@Email__ 을 이용해 이메일 형식 검사

## ScheduleRequestDto

__password__ : __@NotBlank__ 를 이용해 Null 및 String값 검사

__contents__ : __@Size__ 를 이용해 200자 초과여부 검사, __@NotBlank__ 를 이용해 Null 및 String값 검사

