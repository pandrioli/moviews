package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageViewFragment extends Fragment {
    private static final String IMAGE_URL = "imageURL";

    private String imageURL;


    public ImageViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageURL Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageViewFragment newInstance(String imageURL) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, imageURL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageURL = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_view, container, false);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoViewImageView);
        Picasso.with(getContext()).load(imageURL).fit().centerInside().into(photoView);
        return view;
    }

}
