package com.evv.calculator.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import com.evv.calculator.model.CalculatorInterface;
import com.evv.calculator.model.Operator;
import com.evv.calculator.ui.CalculatorView;

public class CalculatorPresenter  implements Parcelable {

    private CalculatorView view;
    private CalculatorInterface calculator;
    private double argOne;
    private Double argTwo;
    private boolean operatorSet;
    private Operator selectedOperator;



    public CalculatorPresenter(CalculatorView view, CalculatorInterface calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    protected CalculatorPresenter(Parcel in) {
        argOne = in.readDouble();
        if (in.readByte() == 0) {
            argTwo = null;
        } else {
            argTwo = in.readDouble();
        }
        operatorSet = in.readByte() != 0;
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public void onDigitPressed (int value) {



    }
    public void onOperatorPressed (Operator operator) {


    }
    public void onDotPressed() {
    }

    public void allClean() {
    }

    public void deleteButtonClicked() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(argOne);
        if (argTwo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(argTwo);
        }
        parcel.writeByte((byte) (operatorSet ? 1 : 0));
    }
}
