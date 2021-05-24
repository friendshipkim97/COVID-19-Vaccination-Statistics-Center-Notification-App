package com.example.mobileprogrammingproject.presenter;

import android.content.Context;

import com.example.mobileprogrammingproject.constants.Constants;
import com.example.mobileprogrammingproject.contract.ChangePersonalInformationContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentChangePersonalInformationBinding;

import java.util.regex.Pattern;

public class ChangePersonalPresenter implements ChangePersonalInformationContract.Presenter {

    // Attributes
    private ChangePersonalInformationContract.View changeInformationView;
    private FragmentChangePersonalInformationBinding mBinding;
    private AppDatabase mAppDatabase;
    private Context context;

    // Constructor
    public ChangePersonalPresenter(ChangePersonalInformationContract.View changeInformationView, FragmentChangePersonalInformationBinding mBinding
            , AppDatabase mAppDatabase, Context getApplicationContext){
        this.changeInformationView = changeInformationView;
        this.mBinding = mBinding;
        this.mAppDatabase = mAppDatabase;
        this.context = getApplicationContext;
    }

    @Override
    public boolean changePersonalInformation(String strEmail, String strEmailType) {

        if(validPasswordCheck()==false){
            return false;
        } else if(validNameCheck()==false){
            return false;
        } else if(phoneNumberCheck()==false){
            return false;
        } else if(genderCheck()==false){
            return false;
        } else{
            String password = mBinding.etChangePersonalPassword.getText().toString();
            String name = mBinding.etChangePersonalName.getText().toString();
            String phoneNumber = mBinding.etChangePersonalPhoneNumber.getText().toString();
            String dateOfBirth = Integer.toString(mBinding.dpChangePersonalDateOfBirth.getYear())+ Constants.EChangePersonalInformation.dpYear.getText()
                    +Integer.toString(mBinding.dpChangePersonalDateOfBirth.getMonth()+1)+ Constants.EChangePersonalInformation.dpMonth.getText()
                    +Integer.toString(mBinding.dpChangePersonalDateOfBirth.getDayOfMonth())+ Constants.EChangePersonalInformation.dpDay.getText();
            String genderChoice = null;
            if(mBinding.btnChangePersonalMale.isChecked()){
                genderChoice = mBinding.btnChangePersonalMale.getText().toString();
            } else if(mBinding.btnChangePersonalFemale.isChecked()){
                genderChoice = mBinding.btnChangePersonalFemale.getText().toString();
            }
            int id = mAppDatabase.userDao().findIdByEmailAndType(strEmail, strEmailType);
            mAppDatabase.userDao().updatePersonalInfoById(id, password, name, dateOfBirth, phoneNumber, genderChoice);
            changeInformationView.showToast(Constants.EChangePersonalInformation.changeCompleteMessage.getText());
            return true;
        }
    }


    public boolean validPasswordCheck(){
        String inputPW = mBinding.etChangePersonalPassword.getText().toString();
        String inputCheckPW = mBinding.etChangePersonalPasswordCheck.getText().toString();
        Boolean inputPWEnglishNumberResult = Pattern.matches(Constants.EChangePersonalInformation.pwEnglishNumberFormat.getText(), inputPW);
        Boolean inputCheckPWEnglishNumberResult = Pattern.matches(Constants.EChangePersonalInformation.pwEnglishNumberFormat.getText(), inputCheckPW);
        if(inputPW.length()==0 || inputCheckPW.length()==0){
            changeInformationView.showToast(Constants.EChangePersonalInformation.pwInputMessage.getText());
            return false;
        }
        if(inputPWEnglishNumberResult==false || inputCheckPWEnglishNumberResult==false){
            changeInformationView.showToast(Constants.EChangePersonalInformation.pwWarningMessage.getText());
            return false;
        }
        if(!inputPW.equals(inputCheckPW)){
            changeInformationView.showToast(Constants.EChangePersonalInformation.pwNotMatchMessage.getText());
            return false;
        }
        return true;
    }

    public boolean validNameCheck() {
        String inputName = mBinding.etChangePersonalName.getText().toString();
        if(inputName.length()==0){
            changeInformationView.showToast(Constants.EChangePersonalInformation.nameInputMessage.getText());
            return false;
        }
        return true;
    }

    public boolean phoneNumberCheck() {
        String inputPhoneNumber = mBinding.etChangePersonalPhoneNumber.getText().toString();
        Boolean inputPhoneNumberResult = Pattern.matches(Constants.EChangePersonalInformation.phoneNumberFormat.getText(), inputPhoneNumber);
        if(inputPhoneNumber.length()==0){
            changeInformationView.showToast(Constants.EChangePersonalInformation.phoneNumberInputMessage.getText());
            return false;
        }
        if(inputPhoneNumberResult==false){
            changeInformationView.showToast(Constants.EChangePersonalInformation.phoneNumberErrorMessage.getText());
            return false;
        }
        return true;
    }


    public boolean genderCheck() {
        if(((!mBinding.btnChangePersonalMale.isChecked())&&(!mBinding.btnChangePersonalFemale.isChecked()))){
            changeInformationView.showToast(Constants.EChangePersonalInformation.genderCheckMessage.getText());
            return false;
        }
        return true;
    }
}
