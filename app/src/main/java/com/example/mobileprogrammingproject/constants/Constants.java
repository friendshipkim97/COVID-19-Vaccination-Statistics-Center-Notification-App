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
        intentResultEmail("userEmail"),
        intentResultPassword("userPassword");




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
        notificationMessage2("입니다.");


        private String text;
        private ESearchEmailCustomAdapter(String text){
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
        emailAppType("app"),
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


}
