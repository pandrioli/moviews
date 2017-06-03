package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import digitalhouse.android.a0317moacns1c_02.Adapters.ImageListRecyclerAdapter;
import digitalhouse.android.a0317moacns1c_02.DAO.Media.ImageListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends Fragment implements View.OnClickListener {
    public final static String TITLE_KEY = "title";
    public final static String IMAGE_LIST_KEY = "imageList";

    private List<ImageListItem> imageList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String title;

    public ImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(view);
        Bundle bundle = getArguments();
        imageList = bundle.getParcelableArrayList(IMAGE_LIST_KEY);
        adapter = new ImageListRecyclerAdapter(imageList, getContext(), this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewPersons);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        title = bundle.getString(TITLE_KEY);
        ((TextView)view.findViewById(R.id.textViewPersonListTitle)).setText(title);
        return view;
    }

    @Override
    public void onClick(View v) {
        ImageListItem selectedItem = imageList.get((Integer)v.getTag());
        ImageClickeable myActivity = (ImageClickeable)getActivity();
        myActivity.onClick(selectedItem.getId(), title);
    }

    public interface ImageClickeable {
        void onClick(Integer id, String title);
    }

}
