package io.keiji.mincomisample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.keiji.mincomisample.R;

public class FavoritesFragment extends Fragment {

    private static final String[] DUMMY_TITLE = new String[]{
            "作品A",
            "作品B",
            "作品C",
            "作品D",
            "作品E",
            "作品F",
            "作品G",
            "作品H",
            "作品I",
            "作品J",
            "作品K",
            "作品L",
            "作品M",
            "作品N",
            "作品O",
            "作品P",
            "作品Q",
            "作品R",
            "作品S",
            "作品T",
            "作品U",
            "作品V",
            "作品W",
            "作品X",
    };

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    private RecyclerView mRecyclerView;

    public FavoritesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new Adapter(DUMMY_TITLE);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.FavoriteHolder> {

        private final String[] mTitleArray;

        public Adapter(String[] titleArray) {
            mTitleArray = titleArray;
        }

        @Override
        public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_row, parent, false);
            return new FavoriteHolder(view);
        }

        @Override
        public void onBindViewHolder(FavoriteHolder holder, int position) {
            holder.title.setText(mTitleArray[position]);
        }

        @Override
        public int getItemCount() {
            return mTitleArray.length;
        }

        class FavoriteHolder extends RecyclerView.ViewHolder {

            public final TextView title;

            public FavoriteHolder(View itemView) {
                super(itemView);

                title = (TextView) itemView.findViewById(R.id.title);
            }
        }
    }

}
