## 2021-08-14 진행상황
+ 바텀 네비게이션 바 완성(빈 Fragment에 연결 성공)
+ 스플래쉬 및 런처 아이콘 등록 완료
+ My야놀자 레이아웃 만드는 중(로그인 부분을 포함하고 있는 fragment) 

## 2021-08-15 진행상황
+ My야놀자 레이아웃 배치 완료 

## 2021-08-16 진행상황
+ 로그인 화면 구현 중
1. 설정, 로그인 탭(기본,소셜) 레이아웃 구현 완료
2. 비밀번호 찾기, 회원가입 레이아웃 구현 진행 중 
+ 로그인 레이아웃 구현 완료 후 jwt 연결 예정  

## 2021-08-17 진행상황
+ 회원가입 화면 구현 중 - (2/3 완성) 
1. 이메일,비밀번호 받기 완료(with.TextInputLayout)
2. 정보동의 제공 화면 완료(with.BottomSheetDialogFragment)

#### 2차 피드벡까지의 목표
+ 최소한 "객실상세정보(4번)"까지는 완료하기<br>
+ "홈화면" 회원가입 api 연결 후 바로 작업시작 하기

## 2021-08-18 진행상황
+ 회원가입 화면 구현 완료
+ 회원가입 화면에서 기존에 문제되는 부분 수정완료
1. bottomsheet dialog 배경이 투명하게 안뜨는 현상
2. 인증번호 뜨는 창 토스트 메세지로 구현 완료

+ 로그인 jwt연결 완료(api 2번)
+ 자동로그인 기능 구현 완료 -> 로그인 시 My 야놀자 화면에 회원정보가 들어있음 

+ 회원가입 및 정보조회 api 진행 예정(api 1번,3번)

## 2021-08-19 진행상황
+ 로그인 api 연결 완료
+ 회원가입 api 연결 완료
+ 회원정보조회 api 연결 완료
+ 홈 화면 레이아웃 배치 진행 중(3/4 완료)
#### 아이콘들 위에 뜨는 알림도 최대한 표현하는 쪽으로 배치 진행 중입니다. (by.framelayout)
#### 내일 안으로 홈화면에 있는 탭(추천/국내숙소) 배치 완료 해놓을 예정입니다.  

## 2021-08-20 진행상황
+ 홈화면-추천탭 구현 완료
  + 광고 자동으로 넘어가는 부분 -> viewpager레이아웃에 thread를 사용하여 구현완료 
  + tablayout에 chip버튼을 넣는 방법이 낯설어 이 부분에서 시간 소요를 조금 했습니다.  

+ 홈화면-국내탭 진행 중
  + 원래 오늘 국내탭 화면까지 끝내려고 했는데 홈화면-추천탭에서 생각보다 시간이 많이 소요되어 그러질 못했습니다.  
  
 #### 국내 탭에 있는 레이아웃 기능은 이미 홈화면에 다 존재하는 레이아웃이기 때문에 시간 관계상 영상에 쓰일 상단 부분만 구현 해놓고
 #### 개발 우선 순위를 고려해 핵심 기능이 있는 지역탭으로 넘어가도록 하겠습니다.
 
## 2021-08-21 진행상황
+ 홈화면-국내탭 완료
 
+ 지역 탭 레이아웃 배치 진행중
  + listview로 화면을 구성했는데 하단 뷰가 짤리는 오류 발생 -> recyclerview로 다시 연결 시도 -> 오류 해결
  + (홈 -> MY야놀자 -> 홈)으로 이동시 홈 화면에 아무것도 뜨지 않는 오류가 발생하여 오류 해결 시도 -> parentFragmentManger를 childFragmentManager로 바꿨더니 해결됨
  + but 새로운 오류가 thread에서 발생 -> 해결하고 있는중 
+ 내일은 지역 호텔리스트 불러오는 화면 만들고 api 연결 시도할 예정(모텔과 호텔 조회 api 8,9번) 


