package io.keiji.mincomisample.paging;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.keiji.mincomisample.R;
import io.keiji.mincomisample.paging.entity.Align;
import io.keiji.mincomisample.paging.entity.Page;
import io.keiji.mincomisample.paging.entity.PageContainer;

/**
 * Created by keiji_ariyama on 4/14/16.
 */
public class PageFragment extends Fragment {
    private static final String TAG = PageFragment.class.getSimpleName();

    private static final String KEY_PAGE = "key_page";

    @Nullable
    private PageContainer pageContainer;

    @Nullable
    private PageView centerPage;

    @Nullable
    private PageView leftPage;

    @Nullable
    private PageView rightPage;

    @NonNull
    public static PageFragment newInstance(@NonNull PageContainer container) {
        PageFragment fragment = new PageFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_PAGE, container);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageContainer = getArguments().getParcelable(KEY_PAGE);

        if (pageContainer != null) {
            Log.d(TAG, pageContainer.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);
        if (pageContainer == null) {
            return view;
        }

        // １ページ
        centerPage = (PageView) view.findViewById(R.id.centerPage);

        if (centerPage != null) {
            if (pageContainer.center != null) {
                centerPage.setPage(pageContainer.center, Align.Center);
            } else if (pageContainer.left != null) {
                centerPage.setPage(pageContainer.left, Align.Left);
            } else if (pageContainer.right != null) {
                centerPage.setPage(pageContainer.right, Align.Right);
            }

            return view;
        }

        // ２ページ
        leftPage = (PageView) view.findViewById(R.id.leftPage);
        rightPage = (PageView) view.findViewById(R.id.rightPage);

        if (pageContainer.left != null || pageContainer.right != null) {
            if (leftPage != null) {
                leftPage.setPage(pageContainer.left, Align.Left);
            }
            if (rightPage != null) {
                rightPage.setPage(pageContainer.right, Align.Right);
            }
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (centerPage != null) {
            centerPage.destroy();
        }
        if (leftPage != null) {
            leftPage.destroy();
        }
        if (rightPage != null) {
            rightPage.destroy();
        }
    }
}
