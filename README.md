# :soccer::meat_on_bone::basketball:한동 예약 관리 프로그램
Input image here
<!--<img width="500" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/96f3d83f-f0ef-41fe-b7b5-2d850d043ed0">-->

## 프로젝트 소개
- 한동대학교 장소 예약 관리 프로그램입니다.
- 장소 예약 및 관리가 가능합니다.
- 히딩크 필드, 농구장, 테니스장, bbq시트 예약이 가능합니다.(예정)

## 팀원 구성
|  이민석(22100504)   |                             남종운(22000220)                                           |
|:-----------:|:-----------------------------------------------------------------------------------:|
| <img src="https://github.com/Glen02lee.png" alt="프로필 이미지" width="200" height="200"> |  <img src="https://github.com/jwoon0606.png" alt="프로필 이미지" width="200" height="200">|
|[@Glen02lee](https://github.com/Glen02lee)|                            [@jwoon0606](https://github.com/jwoon0606)               |

## 1. 개발 환경
- Front : Java GUI(Swing)
- Back-end : Java
- 버전 관리 : Github

## 2. 개발 기간
### 개발 기간
- 전체 개발 기간 : 2024-5-15 ~ (진행중)
- GUI 구현 : 2024-5-15 ~ (진행중)
- 기능 구현 : 2024-5-15 ~ (진행중)

### 작업 관리
- 주기적으로 오프라인 미팅을 가졌습니다.

## 3. 메뉴별 기능
(아래는 예시)
### [조회]
- 현재 등록된 회원들의 정보를 출력합니다.(번호,회원 번호,이름,연락처,총 소비량,등급,포인트 잔액)
    + 번호는 등록된 순서대로 배정됩니다. 번호를 이용하여 정보 수정, 삭제가 가능합니다.
    + 회원 번호는 중복되는 이름을 구별하기 위해 임의로(1~9999) 주어지는 번호입니다.

| 조회 기능     |
|:-----------:|
|<img width="532" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/f208b176-f318-4f81-9cbe-ea0611b9fe3a">|  
<br>

### [회원 추가]
- 새로운 회원을 추가할 수 있습니다.
- 이름과 휴대폰 번호가 필요합니다.
- 회원 등록되면 자동으로 2000 포인트 적립됩니다.

|   회원 추가 기능   |
|:-----------:|
|<img width="535" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/9df42f6d-2eb6-4421-9d3c-9987665cddde">|
<br>

### [회원 정보 수정]
- 회원 정보를 수정할 수 있습니다.
- 회원 번호만 임의의 번호가 배정되며, 나머지는 모두 수정 가능 합니다.
- 등급과 포인트 사용 가능 여부가 자동적으로 수정됩니다.

|   회원 정보 수정 기능   |
|:-----------:|
|<img width="535" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/e4d0a0e6-9b15-41f2-9ebd-65d9e875adfa">|
<br>

### [회원 정보 삭제]
- 회원의 정보를 삭제할 수 있습니다.
- 해당 회원이 삭제되면, 뒤 번호 회원들의 번호가 하나씩 당겨 집니다.

|   회원 정보 삭제  |
|:-----------:|
|<img width="600" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/018775a0-7485-434e-baf4-14ddbd1502f9">|
<br>


### [회원 검색]
- 원하는 회원을 검색할 수 있습니다.
- 이름, 회원 번호, 등급 으로 검색이 가능힙니다.

|  이름으로 찾기   |
|:-----------:|
|<img width="535" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/a8cd52d5-cd3a-41b1-accc-4bb38e158746">|
<br>

|  회원 번호로 찾기  |
|:-----------:|
|<img width="535" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/66823c23-262d-4992-bae7-4961bbeb7f68">|
<br>

|  등급으로 찾기  |
|:-----------:|
|<img width="600" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/4bca2649-2d92-49e1-9ed6-716b62e7079f">|
<br>

### [계산]
- 회원이 계산할 금액을 정해줍니다.
- 등급에 따라 자동적으로 할인율이 적용됩니다.
- 5000포인트 이상일 때는 포인트 사용이 가능합니다.
- 구매 금액의 10%가 포인트로 적립됩니다.

|  계산 기능  |
|:-----------:|
|<img width="600" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/5402aa3e-53b2-40ee-8b36-35adeb7f50cf">|
<br>



### [저장]
- "members.txt"파일과 "report.txt"파일에 저장할 수 있습니다.
- 파일 변경은 shop_functions.c 파일의 8,9line에서 파일 명을 수정하면 됩니다.
    + (8) #define INPUT_FILE "members.txt"
    + (9) #define OUPUT_FILE "report.txt"

|  저장 완료  |
|:-----------:|
|<img width="600" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/967b6221-393a-4034-9ad1-b2df8196ea19">|
<br>

|  "members.txt"  |
|:-----------:|
|<img width="492" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/82259c09-4d49-40f2-b7d0-710ed01cadea">|
<br>


|  "report.txt"  |
|:-----------:|
|<img width="513" src="https://github.com/jwoon0606/ProgrammingStudio/assets/162769592/ef08f0db-d38d-446a-8ea7-d2a4b17a7bfc">|
<br>

## 4. 제작 소감
<!--
- 헤더파일 작성 경험  
  이때까지 연습으로만 해보고 실제 프로그램을 만들 때 헤더파일을 작성해본 경험이 없었는데 이번 기회를 통해 작성법과 사용법을 익힐 수 있었습니다..!

- 브렌치 사용 경험  
  비록 혼자서 제작했지만 마치 다양한 사람들과 함께 일하는 것처럼 따로따로 커밋하는 경험이 꽤 흥미로웠습니다.
  앞으로 협업할 기회가 있다면 브랜치를 적극적으로 사용해 볼 것입니다.

- 아이디어 구현 경험  
  상상만 하던 기능들을 직접 구현해 보면서, 다른 기능들도 구현할 수 있겠다는 자신감이 생겼습니다.

온전한 저의 아이디어로 제작했기 때문에 재밌게 제작할 수 있었습니다.  
비록 README제작을 깜빡해서 좀 늦긴 했지만 뿌듯한 과제였습니다!
-->
### 남종운(jwoon0606)
- 소감
### 이민석(Glen02lee)
- 소감