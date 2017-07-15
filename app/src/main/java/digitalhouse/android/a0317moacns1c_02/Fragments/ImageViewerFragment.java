package digitalhouse.android.a0317moacns1c_02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import digitalhouse.android.a0317moacns1c_02.Helpers.AnimationHelper;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageViewerFragment extends Fragment {
    private static final String IMAGE_URL = "imageURL";

    private String imageURL;


    public ImageViewerFragment() {
        // Required empty public constructor
    }

    public static ImageViewerFragment newInstance(String imageURL) {
        ImageViewerFragment fragment = new ImageViewerFragment();
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
        Picasso.with(getContext()).load(imageURL).fit().centerInside().into(photoView, new Callback() {
            @Override
            public void onSuccess() {
                AnimationHelper.startPostponedTransition(getActivity());
            }

            @Override
            public void onError() {
                AnimationHelper.startPostponedTransition(getActivity());
            }
        });
        return view;
    }

}
