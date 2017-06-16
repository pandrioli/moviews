package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import digitalhouse.android.a0317moacns1c_02.Adapters.ImageListRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends Fragment implements View.OnClickListener {
    public final static String TITLE_KEY = "title";
    public final static String IMAGE_LIST_KEY = "imageList";

    private List<ImageListItem> imageList;
    @BindView(R.id.recyclerViewPersons) RecyclerView recyclerView;
    @BindView(R.id.textViewPersonListTitle) TextView textViewtitle;
    @BindView(R.id.cardViewImageList) CardView cardView;
    private RecyclerView.Adapter adapter;
    private String title;
    private Unbinder unbinder;

    public ImageListFragment() {
        // Required empty public constructor
    }

    public static ImageListFragment newInstance(ArrayList<ImageListItem> imageList, String title){
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGE_LIST_KEY ,imageList);
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            imageList = getArguments().getParcelableArrayList(IMAGE_LIST_KEY);
            title = getArguments().getString(TITLE_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(view);
        adapter = new ImageListRecyclerAdapter(imageList, getContext(), this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        textViewtitle.setText(title);
        if (title == null) {
            title = "";
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            textViewtitle.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        ImageListItem selectedItem = imageList.get((Integer)v.getTag());
        ImageClickeable myActivity = (ImageClickeable)getActivity();
        myActivity.onClick(selectedItem.getId(), title);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
    public interface ImageClickeable {
        void onClick(Integer id, String title);
    }

}
