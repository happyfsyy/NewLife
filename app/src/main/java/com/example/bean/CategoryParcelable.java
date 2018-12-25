package com.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryParcelable implements Parcelable {
    private String categoryName;
    private int categoryCode;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
        dest.writeInt(categoryCode);
    }

    public static final Parcelable.Creator<CategoryParcelable> CREATOR=new Parcelable.Creator<CategoryParcelable>(){
        @Override
        public CategoryParcelable createFromParcel(Parcel source) {
            CategoryParcelable category=new CategoryParcelable();
            category.categoryName=source.readString();
            category.categoryCode=source.readInt();
            return category;
        }

        @Override
        public CategoryParcelable[] newArray(int size) {
            return new CategoryParcelable[size];
        }
    };


}
