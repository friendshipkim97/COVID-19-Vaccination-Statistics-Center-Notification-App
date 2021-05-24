package com.example.mobileprogrammingproject.constants;

public class Constants {

    public enum ELogin{

        googleLoginButtonText("구글 로그인"),
        kakaoLoginErrorMessage("로그인 도중에 오류가 발생했습니다."),
        kakaoSessionClosedMessage("세션이 닫혔습니다.. 다시 시도해주세요"),
        kakaoLoginSuccessMessage("환영 합니다 !"),
        kakaoSessionFailedMessage("세션열기를 실패했습니다."),
        intentName("name"),
        intentEmail("email"),
        intentProfileImg("profileImg"),
        intentCheck("check"),
        intentCheckKakao("KAKAO"),
        intentCheckGoogle("GOOGLE"),
        intentCheckApp("APP"),
        intentResultEmail("userEmail"),
        intentResultPassword("userPassword"),
        passwordError("비밀번호가 틀렸습니다."),
        appLoginSuccess("로그인을 성공했습니다."),
        typeError("로그인을 해주세요."),
        notValidEmail("존재하지 않는 이메일입니다."),
        kakaoLoginSuccess("카카오 로그인 성공했습니다."),
        kakaoSignUpMessage("카카오로 회원가입 했습니다."),
        googleLoginSuccess("구글 로그인 성공했습니다."),
        googleSignUpMessage("구글로 회원가입 했습니다.");


        private String text;
        private ELogin(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }
    public enum ESearchEmail{
        searchEmailNABText1("이름을 입력하세요"),
        searchEmailNABText2("생년월일을 입력하세요 ex)1997년4월30일"),
        searchEmailNABText3("아이디찾기"),
        searchEmailNAPText1("이름을 입력하세요"),
        searchEmailNAPText2("휴대폰번호를 입력하세요 ex)010-5243-5980"),
        searchEmailNAPText3("아이디찾기");

        private String text;
        private ESearchEmail(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }

    public enum ESearchEmailCustomAdapter{
        dateOfBirth("생년월일"),
        phone("휴대폰번호"),
        noMatchingEmail("일치하는 이메일이 없습니다."),
        inputName("이름을 입력해주세요."),
        notificationMessage1("찾고자 하는 이메일은 "),
        notificationMessage2("입니다."),
        emailAppType("APP");


        private String text;
        private ESearchEmailCustomAdapter(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }

    public enum ESearchPasswordPresenter{
        user("rlawjddn9704301@gmail.com"),
        password("wjddn4030"),
        mailTitle("찾고자 하는 password는 다음과 같습니다."),
        mailContents1("찾고자 하는 password는 "),
        mailContents2(" 입니다."),
        mailExceptionMessage("SendMail"),
        notValidEmail("존재하지 않는 이메일입니다."),
        completedSendMail("이메일로 비밀번호를 전송했습니다."),
        emailFormatErrorMessage("이메일을 알맞은 형태로 입력해주세요."),
        emailLetterCountError("이메일을 10~20자로 입력해주세요."),
        emailAppType("APP"),
        emailFormat("\\w+@\\w+\\.\\w+(\\.\\w+)?");


        private String text;
        private ESearchPasswordPresenter(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }

    public enum ESignUp{
        emailDuplicateMessage("이미 사용하고 있는 이메일입니다."),
        emailNonDuplicateMessage("사용가능한 이메일입니다."),
        emailFormatErrorMessage("이메일을 알맞은 형태로 입력해주세요."),
        emailLetterCountError("이메일을 10~20자로 입력해주세요."),
        emailFormat("\\w+@\\w+\\.\\w+(\\.\\w+)?"),
        pwEnglishNumberFormat("^.*(?=^.{8,12}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$"),
        pwInputMessage("비밀번호창에 비밀번호를 입력해주세요."),
        pwWarningMessage("비밀번호는 영어, 숫자, 특수문자를 조합해 8~12자리로 입력해주세요."),
        pwNotMatchMessage("비밀번호가 서로 일치하지 않습니다."),
        nameInputMessage("이름을 입력해주세요."),
        phoneNumberInputMessage("휴대폰번호를 입력해주세요,"),
        phoneNumberFormat("^\\d{3}-\\d{3,4}-\\d{4}$"),
        phoneNumberErrorMessage("올바른 휴대폰 번호를 입력해주세요."),
        genderCheckMessage("성별을 체크해주세요."),
        agreeCheckMessage("약관에 동의해주세요."),
        completeSignUpMessage("회원가입을 완료했습니다."),
        dpYear("년"),
        dpMonth("월"),
        dpDay("일"),
        emailAppType("APP"),
        userEmail("userEmail"),
        userPassword("userPassword");

        private String text;
        private ESignUp(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }

    }

    public enum EChangePersonalInformation {

        pwEnglishNumberFormat("^.*(?=^.{8,12}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$"),
        pwInputMessage("비밀번호창에 비밀번호를 입력해주세요."),
        pwWarningMessage("비밀번호는 영어, 숫자, 특수문자를 조합해 8~12자리로 입력해주세요."),
        pwNotMatchMessage("비밀번호가 서로 일치하지 않습니다."),
        nameInputMessage("이름을 입력해주세요."),
        phoneNumberInputMessage("휴대폰번호를 입력해주세요,"),
        phoneNumberFormat("^\\d{3}-\\d{3,4}-\\d{4}$"),
        phoneNumberErrorMessage("올바른 휴대폰 번호를 입력해주세요."),
        genderCheckMessage("성별을 체크해주세요."),
        dpYear("년"),
        dpMonth("월"),
        dpDay("일"),
        changeCompleteMessage("개인정보 변경을 완료했습니다.");

        private String text;
        private EChangePersonalInformation(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }

    public enum EGoogleMapFragment {

        baseUrl("https://api.odcloud.kr/api/"),
        gpsAccessMessage("이 앱을 실행하려면 위치 접근 권한이 필요합니다."),
        checkMessage("확인"),
        address("주소: "),
        latitude("위도: "),
        longitude("경도: "),
        notLocationMessage("위치정보 가져올 수 없음"),
        permissionAndGPSMessage("위치 퍼미션과 GPS 활성 요부 확인하세요"),
        deactivationMessage("위치 서비스 비활성화"),
        permission1("퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. "),
        permission2("퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. "),
        gpsServiceMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?"),
        setting("설정"),
        cancel("취소"),
        geoMessage("지오코더 서비스 사용불가합니다."),
        notGPSMessage("잘못된 GPS 좌표입니다."),
        notFindAddressMessage("주소를 발견하지 못했습니다.");




        private String text;
        private EGoogleMapFragment(String text){
            this.text=text;
        }
        public String getText(){
            return this.text;
        }
    }


}
