package io.keiji.mincomisample.paging.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Page implements Parcelable {

    private final int number;

    public int getNumber() {
        return number;
    }

    private final boolean isSpread;

    public boolean isSpread() {
        return isSpread;
    }

    private final String fileName;

    public String getFileName() {
        return fileName;
    }

    public Page(int number, boolean isSpread, String fileName) {
        this.number = number;
        this.isSpread = isSpread;
        this.fileName = fileName;
    }

    protected Page(Parcel in) {
        number = in.readInt();
        isSpread = in.readInt() == 1;
        fileName = in.readString();
    }

    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(isSpread ? 1 : 0);
        dest.writeString(fileName);
    }

}
