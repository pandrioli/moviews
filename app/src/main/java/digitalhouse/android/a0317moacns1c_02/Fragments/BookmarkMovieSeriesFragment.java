package digitalhouse.android.a0317moacns1c_02.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Adapters.BookmarkRecyclerViewAdapter;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ListItem;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BookmarkMovieSeriesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_LIST = "item-list";
    private static final String ARG_IS_GENERAL = "is-general";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private Boolean isGeneral;
    private List<ListItem> mItemList;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BookmarkMovieSeriesFragment() {
    }

    // TODO: Customize parameter initialization
    public static BookmarkMovieSeriesFragment newInstance(ArrayList<ListItem> itemList, Boolean isGeneral) {
        BookmarkMovieSeriesFragment fragment = new BookmarkMovieSeriesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_LIST, itemList);
        args.putBoolean(ARG_IS_GENERAL, isGeneral);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mItemList = (List<ListItem>) getArguments().getSerializable(ARG_ITEM_LIST);
            isGeneral = getArguments().getBoolean(ARG_IS_GENERAL);
            //mColumnCount = obtainColumnCount(mItemList.size());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (isGeneral) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new BookmarkRecyclerViewAdapter(mItemList, mListener, isGeneral));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ListItem item);
    }
}
