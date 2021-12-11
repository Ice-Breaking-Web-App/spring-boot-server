# spring-boot-server

### BASE URL: http://3.145.74.164:5000/api

### API 명세서
|Method|Path|RequestParam|RequestBody|Return|Summary|
|------|----|------------|-----------|------|-------|
|PUT|/team/create|-|{teamName, leaderName, teamColor, questions, members}|{leaderCode, memberCode}|정보를 받아서 팀을 생성한 후, 팀장 코드와 팀원 코드를 반환한다|
|GET|/team/info|memberCode|-|{teamId, teamName, leaderName, teamColor, memberCount, createdAt, boardAvailable, qCount, paid}|팀원 코드를 받아서 현재 팀 정보를 조회한다|
|PATCH|/team/pay|memberCode, isPaid|-|-|팀원 코드를 받아서 결제 여부를 변경한다|
|GET|/team/pay|memberCode|-|isPaid|팀원 코드를 받아서 결제 여부를 조회한다|
|GET|/code/member|leaderCode|-|memberCode|팀장 코드를 받아서 팀원 코드를 조회한다|
|GET|/code/verification|memberCode|-|isValid|팀원 코드를 받아서 해당 코드가 유효한지 확인한다|
|GET|/member/status|memberCode|-|{statusList, isBoardAvailable}|팀원 코드를 받아서 현재 팀원의 참여 현황 리스트와 메인보드 접근가능 여부를 조회한다|
|PATCH|/member/status|memberCode, memberName, status|-|-|팀원 코드와 팀원 이름을 받아서 참여 상태를 변경한다 (pending, joining, done 셋 중 하나)|
|GET|/member/info|memberCode|-|memberList|팀원 코드를 받아서 모든 팀원 정보를 조회한다|
|GET|/questions/all|memberCode|-|questions|팀원 코드를 받아서 질문 리스트를 조회한다|
|GET|/answers/all|memberCode, qNumber|-|answerList|팀원 코드와 질문 번호를 받아서 해당 질문에 대한 모든 팀원들의 답변을 조회한다|
|PATCH|/answers/member|memberCode, memberName|{qNumber, aText}|-|정보를 받아서 질문 번호에 대한 답변을 입력/수정한다|
|GET|/answers/member|memberCode, memberName|-|answers|코드와 팀원 이름을 받아서 해당 팀원이 여태까지 작성한 답변들을 조회한다|
|GET|/answers/progress|memberCode, memberName|-|{qCount, aLast}|팀원 코드와 팀원 이름을 받아서 총 질문 개수와 작성 완료한 답변 수를 조회한다|
|PATCH|/member/score/member|memberCode, memberName, score|-|-|팀원 코드와 팀원 이름을 받아서 해당 팀원의 점수를 입력한다|
|GET|/member/score/all|memberCode|-|scoreList|팀원 코드를 받아서 점수를 내림차순으로 정렬한 팀원 리스트를 조회한다|
|GET|/recommend/tags|-|-|tagList|랜덤으로 태그 3개가 담긴 리스트를 조회한다|
|GET|/recommend/question|tag|-|question|태그를 받아서 해당 태그와 관련된 질문 1개를 랜덤으로 반환한다|
|PUT|	/team/create|	-	|teamName, leaderName, teamColor, questions, members|	leaderCode,memberCode	|정보를 받아서 팀을 생성한 후, 팀장 코드와 팀원 코드를 반환한다|


GET	/team/info	memberCode	-	teamInfo	팀원 코드를 받아서 현재 팀 정보를 반환한다 (팀장도 팀원 코드 사용 가능, 하지만 로컬에 팀장 코드가 저장된 경우에는 팀장으로 인식)
GET	/team/name	memberCode	-	teamName	팀원 코드를 받아서 팀 이름을 조회한다
PATCH	/team/pay	memberCode, isPaid	-	-	팀원 코드를 받아서 결제 여부를 변경한다
GET	/team/pay	memberCode	-	isPaid	팀원 코드를 받아서 결제 여부를 반환한다
GET	/code/member	leaderCode	-	memberCode	팀장 코드를 받아서 팀원 코드를 반환한다 
GET	/code/verification	memberCode	-	isValid	팀원 코드를 받아서 해당 코드가 유효한지 확인한다
GET	/member/status/all	memberCode	-	statusList, isBoardAvailable	팀원 코드를 받아서 현재 팀원의 참여 현황 리스트와 메인보드 접근가능 여부를 반환한다
GET	/member/status/member	memberCode, memberName	-	Status, isBoardAvailable, isGameDone	팀원 코드와 팀원 이름을 받아서 해당 팀원의 참여상태, 메인보드 접근가능 여부, 게임 완료 여부를 반환한다
PATCH	/member/join	memberCode, memberName	-	-	팀원 코드와 팀원 이름을 받아서 해당 팀원의 참여 상태를 joining으로 변경한다 (pending ⇨ joining)
GET	/member/info	memberCode	-	memberList	팀원 코드를 받아서 모든 팀원 정보를 반환한다
GET	/questions/all	memberCode	-	questions	팀원 코드를 받아서 질문 리스트를 반환한다
GET	/answers/all	memberCode, qNumber	-	answerList	팀원 코드와 질문 번호를 받아서 해당 질문에 대한 모든 팀원들의 답변을 반환한다
PATCH	/answers/member	memberCode, memberName	qNumber, aText	-	정보를 받아서 질문 번호에 대한 답변을 수정한다
PUT	/answers/member	memberCode, memberName	qNumber, aText	-	정보를 받아서 질문 번호에 대한 답변을 입력한다
GET	/answers/member	memberCode, memberName	-	answers	팀원 코드와 팀원 이름을 받아서 해당 팀원이 여태까지 작성한 답변들을 반환한다
GET	/answers/progress	memberCode, memberName	-	qCount, aLast	팀원 코드와 팀원 이름을 받아서 총 질문의 개수와 작성 완료한 답변의 수를 반환한다
PATCH	/member/score/ member	memberCode, memberName, score	-	-	팀원 코드와 팀원 이름을 바아서 해당 팀원의 점수를 입력한다
GET	/member/score/all	memberCode	-	scoreList	팀원 코드를 받아서 점수를 내림차순으로 정렬한 팀원 리스트를 반환한다
GET	/recommend/tags	-	-	tagList	랜덤으로 태그 3개가 담긴 리스트를 반환한다
GET	/recommend /question	tag	-	question	태그를 받아서 해당 태그와 관련된 질문 1개를 랜덤으로 반환한다
