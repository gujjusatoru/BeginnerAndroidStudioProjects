package com.example.foodcart;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class CardDataModel implements Parcelable {
    private String cardName;
    private int cardImg;

    private int cardCount;

    private boolean select;

    public CardDataModel(String cardName, int cardImg, boolean select, int cardCount) {
        this.cardName = cardName;
        this.cardImg = cardImg;
        this.select=select;
        this.cardCount=cardCount;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected CardDataModel(Parcel in) {
        cardName = in.readString();
        cardImg = in.readInt();
        select=in.readBoolean();
        cardCount=in.readInt();
    }

    public static final Creator<CardDataModel> CREATOR = new Creator<CardDataModel>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public CardDataModel createFromParcel(Parcel in) {
            return new CardDataModel(in);
        }

        @Override
        public CardDataModel[] newArray(int size) {
            return new CardDataModel[size];
        }
    };

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardImg() {
        return cardImg;
    }

    public void setCardImg(int cardImg) {
        this.cardImg = cardImg;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(cardName);
        dest.writeInt(cardImg);
        dest.writeBoolean(select);
        dest.writeInt(cardCount);
    }
}

