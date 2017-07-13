package digitalhouse.android.a0317moacns1c_02.CustomViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Gregorio Martin on 11/7/2017.
 */

public class BottomSheetBookmark extends BottomSheetDialogFragment implements Serializable {

    private static final String ARG_LISTENER = "bookmark-actions-listener";
    private OnBottomSheetBookmarkListener mListener;


    public static BottomSheetBookmark getInstance(OnBottomSheetBookmarkListener listener){
        BottomSheetBookmark fragment = new BottomSheetBookmark();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LISTENER, listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mListener = (OnBottomSheetBookmarkListener) getArguments().getSerializable(ARG_LISTENER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_bookmark, container, false);

        View share = view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.share();
            }
        });

        View markAsUnseen = view.findViewById(R.id.markAsUnseen);
        markAsUnseen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.markAsUnseen();
            }
        });

        View unsave = view.findViewById(R.id.unsave);
        unsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.unsave();
            }
        });

        return view;
    }



    public interface OnBottomSheetBookmarkListener extends Serializable{
        void share();
        void unsave();
        void markAsUnseen();
    }

}
