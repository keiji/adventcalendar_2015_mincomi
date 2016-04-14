package io.keiji.mincomisample.paging.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class PageContainer implements Parcelable {
    public final Page center;
    public final Page left;
    public final Page right;

    public PageContainer(@NonNull Page center) {
        this.center = center;
        this.left = null;
        this.right = null;
    }

    public PageContainer(@Nullable Page left, @Nullable Page right) {
        this.center = null;
        this.left = left;
        this.right = right;
    }

    protected PageContainer(Parcel in) {
        center = in.readParcelable(Page.class.getClassLoader());
        left = in.readParcelable(Page.class.getClassLoader());
        right = in.readParcelable(Page.class.getClassLoader());
    }

    public static final Parcelable.Creator<PageContainer> CREATOR = new Parcelable.Creator<PageContainer>() {
        @Override
        public PageContainer createFromParcel(Parcel in) {
            return new PageContainer(in);
        }

        @Override
        public PageContainer[] newArray(int size) {
            return new PageContainer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(center, flags);
        dest.writeParcelable(left, flags);
        dest.writeParcelable(right, flags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (center != null) {
            sb.append("page center = ")
                    .append(center.getNumber());
        }

        if (right != null) {
            sb.append("page right = ")
                    .append(right.getNumber());
        }

        if (left != null) {
            sb.append("\n")
                    .append("page left = ")
                    .append(left.getNumber());
        }

        return sb.toString();
    }
}
