package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Adapters.MultimediaRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.Helpers.ImageMapper;
import digitalhouse.android.a0317moacns1c_02.Model.General.ImagesContainer;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageData;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaListFragment extends Fragment {

    public static final String MEDIA_IMAGE_CONTAINER_KEY = "mediaContainer";
    private Unbinder unbinder;

    private ImagesContainer imagesContainer;

    @BindView(R.id.multimedia_recycler_view) protected DiscreteScrollView mRecyclerView;

    public MediaListFragment() {
        // Required empty public constructor
    }

    public static MediaListFragment newInstance(ImagesContainer imagesContainer){
        MediaListFragment fragment = new MediaListFragment();
        Bundle args = new Bundle();
        args.putParcelable(MEDIA_IMAGE_CONTAINER_KEY, imagesContainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if(getArguments() != null){
            imagesContainer = getArguments().getParcelable(MEDIA_IMAGE_CONTAINER_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        MultimediaRecyclerAdapter mAdapter = new MultimediaRecyclerAdapter(view.getContext(),
                imagesContainer.getURLsList());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.00f)
                .setMinScale(0.6f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());
        mRecyclerView.setOffscreenItems(2);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
