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

