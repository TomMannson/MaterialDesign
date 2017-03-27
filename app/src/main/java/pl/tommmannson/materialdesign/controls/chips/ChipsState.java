package pl.tommmannson.materialdesign.controls.chips;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ChipsState implements Parcelable {

    private int id;
    private int mChipsColor;
    private int mChipsColorSelected;
    private int mChipsTextColor;
    private String mChipsText;
    private Drawable mChipsImage;
    private boolean mDeleteEnable;
    private boolean selected;

    public ChipsState() {
    }

    protected ChipsState(Parcel in) {
        id = in.readInt();
        mChipsColor = in.readInt();
        mChipsColorSelected = in.readInt();
        mChipsTextColor = in.readInt();
        mChipsText = in.readString();
        mDeleteEnable = in.readByte() != 0;
        selected = in.readByte() != 0;
    }

    public static final Creator<ChipsState> CREATOR = new Creator<ChipsState>() {
        @Override
        public ChipsState createFromParcel(Parcel in) {
            return new ChipsState(in);
        }

        @Override
        public ChipsState[] newArray(int size) {
            return new ChipsState[size];
        }
    };


    public int getId() {
        return id;
    }

    public ChipsState setId(int id) {
        this.id = id;
        return this;
    }

    public int getChipsColor() {
        return mChipsColor;
    }

    public ChipsState setChipsColor(int mChipsColor) {
        this.mChipsColor = mChipsColor;
        return this;
    }



    public int getChipsTextColor() {
        return mChipsTextColor;
    }

    public ChipsState setChipsTextColor(int mChipsTextColor) {
        this.mChipsTextColor = mChipsTextColor;
        return this;
    }



    public String getChipsText() {
        return mChipsText;
    }

    public ChipsState setChipsText(String mChipsText) {
        this.mChipsText = mChipsText;
        return this;
    }



    public Drawable getChipsImage() {
        return mChipsImage;
    }

    public ChipsState setChipsImage(Drawable mChipsImage) {
        this.mChipsImage = mChipsImage;
        return this;
    }



    public boolean isDeleteEnable() {
        return mDeleteEnable;
    }

    public ChipsState setDeleteEnable(boolean mDeleteEnable) {
        this.mDeleteEnable = mDeleteEnable;
        return this;
    }

    public int getChipsColorSelected() {
        return mChipsColorSelected;
    }

    public ChipsState setChipsColorSelected(int mChipsColorSelected) {
        this.mChipsColorSelected = mChipsColorSelected;
        return this;
    }

    public boolean isSelected() {
        return selected;
    }

    public ChipsState setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(mChipsColor);
        dest.writeInt(mChipsColorSelected);
        dest.writeInt(mChipsTextColor);
        dest.writeString(mChipsText);
        dest.writeByte((byte) (mDeleteEnable ? 1 : 0));
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}